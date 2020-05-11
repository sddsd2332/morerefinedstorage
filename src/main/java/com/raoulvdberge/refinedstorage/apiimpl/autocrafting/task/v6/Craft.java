package com.raoulvdberge.refinedstorage.apiimpl.autocrafting.task.v6;

import com.google.common.primitives.Ints;
import com.raoulvdberge.refinedstorage.api.autocrafting.ICraftingPattern;
import com.raoulvdberge.refinedstorage.api.autocrafting.task.CraftingTaskReadException;
import com.raoulvdberge.refinedstorage.api.network.INetwork;
import com.raoulvdberge.refinedstorage.api.util.IStackList;
import com.raoulvdberge.refinedstorage.api.util.StackListEntry;
import com.raoulvdberge.refinedstorage.apiimpl.API;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants;
import org.apache.logging.log4j.LogManager;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public abstract class Craft {
    private static final String NBT_PATTERN = "Pattern";
    private static final String NBT_ROOT = "Root";
    private static final String NBT_IS_PROCESSING = "IsProcessing";
    private static final String NBT_ITEMS_TO_USE = "ItemsToUse";
    private static final String NBT_QUANTITY = "Quantity";
    private static final String NBT_NEEDED_PER_CRAFT = "NeededPerCraft";

    private boolean root;
    protected int quantity;
    private ICraftingPattern pattern;
    private Map<Integer, IStackList<ItemStack>> itemsToUse = new LinkedHashMap<>();
    private Map<Integer, Integer> neededPerCraft = new LinkedHashMap<>();

    Craft(ICraftingPattern pattern, boolean root) {
        this.pattern = pattern;
        this.root = root;
    }

    Craft(INetwork network, NBTTagCompound tag) throws CraftingTaskReadException {
        this.quantity = tag.getInteger(NBT_QUANTITY);
        this.pattern = CraftingTask.readPatternFromNbt(tag.getCompoundTag(NBT_PATTERN), network.world());
        this.root = tag.getBoolean(NBT_ROOT);
        NBTTagList list = tag.getTagList(NBT_ITEMS_TO_USE, Constants.NBT.TAG_LIST);
        for (int i = 0; i < list.tagCount(); i++) {
            this.itemsToUse.put(i, CraftingTask.readItemStackList((NBTTagList)list.get(i)));
        }
        List<Integer> perCraftList = Ints.asList(tag.getIntArray(NBT_NEEDED_PER_CRAFT));
        for (int i = 0; i < perCraftList.size(); i++) {
            neededPerCraft.put(i, perCraftList.get(i));
        }
    }

    static Craft createCraftFromNBT(INetwork network, NBTTagCompound tag) throws CraftingTaskReadException {
        return tag.getBoolean(NBT_IS_PROCESSING) ? new Processing(network, tag) : new com.raoulvdberge.refinedstorage.apiimpl.autocrafting.task.v6.Crafting(network, tag);
    }

    ICraftingPattern getPattern() {
        return pattern;
    }

    int getQuantity() {
        return quantity;
    }

    void addQuantity(int quantity) {
        this.quantity += quantity;
    }

    void next() {
        quantity--;
    }

    boolean isRoot() {
        return root;
    }

    boolean hasItems() {
        return !itemsToUse.isEmpty();
    }

    IStackList<ItemStack> getItemsToUse(boolean simulate) {
        IStackList<ItemStack> toReturn = API.instance().createItemStackList();
        for (int i = 0; i < itemsToUse.size(); i++) {
            int needed = neededPerCraft.get(i);
            if (!itemsToUse.get(i).isEmpty()) {
                Iterator<StackListEntry<ItemStack>> it = itemsToUse.get(i).getStacks().iterator();
                while (needed > 0 && it.hasNext()) {
                    ItemStack toUse = it.next().getStack();
                    if (needed < toUse.getCount()) {
                        if (!simulate) {
                            itemsToUse.get(i).remove(toUse, needed);
                        }
                        toReturn.add(toUse, needed);
                        needed = 0;
                    } else {
                        if (!simulate) {
                            it.remove();
                        }
                        needed -= toUse.getCount();
                        toReturn.add(toUse);
                    }
                }
            } else {
                LogManager.getLogger(Craft.class).warn("Craft requested more Items than available");
                this.quantity = 0; // stop crafting
                break;
            }
        }
        return toReturn;
    }

    void addItemsToUse(int ingredientNumber, ItemStack stack, int size, int perCraft) {
        if (!neededPerCraft.containsKey(ingredientNumber)) {
            neededPerCraft.put(ingredientNumber, perCraft);
        }
        if (!itemsToUse.containsKey(ingredientNumber)) {
            itemsToUse.put(ingredientNumber, API.instance().createItemStackList());
        }
        itemsToUse.get(ingredientNumber).add(stack, size);
    }

    NBTTagCompound writeToNbt() {
        NBTTagCompound tag = new NBTTagCompound();
        tag.setInteger(NBT_QUANTITY, quantity);
        tag.setBoolean(NBT_IS_PROCESSING, this instanceof Processing);
        tag.setBoolean(NBT_ROOT, root);
        tag.setTag(NBT_PATTERN, CraftingTask.writePatternToNbt(pattern));
        NBTTagList list = new NBTTagList();
        for (IStackList<ItemStack> stackList : itemsToUse.values()) {
            list.appendTag(CraftingTask.writeItemStackList(stackList));
        }
        tag.setTag(NBT_ITEMS_TO_USE, list);
        tag.setIntArray(NBT_NEEDED_PER_CRAFT, Ints.toArray(neededPerCraft.values()));

        return tag;
    }

}
