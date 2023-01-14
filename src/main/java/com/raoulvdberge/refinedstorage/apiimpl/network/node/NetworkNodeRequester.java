package com.raoulvdberge.refinedstorage.apiimpl.network.node;

import com.raoulvdberge.refinedstorage.RS;
import com.raoulvdberge.refinedstorage.api.autocrafting.task.ICraftingTask;
import com.raoulvdberge.refinedstorage.api.util.Action;
import com.raoulvdberge.refinedstorage.apiimpl.API;
import com.raoulvdberge.refinedstorage.inventory.fluid.FluidInventory;
import com.raoulvdberge.refinedstorage.inventory.item.ItemHandlerBase;
import com.raoulvdberge.refinedstorage.inventory.listener.ListenerNetworkNode;
import com.raoulvdberge.refinedstorage.tile.TileRequester;
import com.raoulvdberge.refinedstorage.tile.config.FilterConfig;
import com.raoulvdberge.refinedstorage.tile.config.FilterType;
import com.raoulvdberge.refinedstorage.tile.config.IRSFilterConfigProvider;
import com.raoulvdberge.refinedstorage.tile.data.TileDataManager;
import com.raoulvdberge.refinedstorage.util.StackUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.items.IItemHandlerModifiable;

import javax.annotation.Nonnull;

public class NetworkNodeRequester extends NetworkNode implements IRSFilterConfigProvider{


    public static final String ID = "requester";

    private static final String NBT_FLUID_FILTERS = "FluidFilter";
    private static final String NBT_AMOUNT = "Amount";
    private static final String NBT_MISSING = "MissingItems";

    private ItemHandlerBase itemFilter = new ItemHandlerBase(1, new ListenerNetworkNode(this));
    private FluidInventory fluidFilter = new FluidInventory(1, new ListenerNetworkNode(this));

    private int amount = 0;
    private boolean isMissingItems = false;
    private ICraftingTask craftingTask = null;

    public NetworkNodeRequester(World world, BlockPos pos) {
        super(world, pos);
    }

    private final FilterConfig config = new FilterConfig.Builder(this)
            .allowedFilterModeWhitelist()
            .allowedFilterTypeItemsAndFluids()
            .filterTypeItems()
            .filterSizeNine()
            .customFilterTypeSupplier(ft -> world.isRemote ? FilterType.values()[TileRequester.TYPE.getValue()] : ft).build();

    static {
        TileDataManager.registerParameter(TileRequester.TYPE);
        TileDataManager.registerParameter(TileRequester.AMOUNT);
        TileDataManager.registerParameter(TileRequester.MISSING);
        API.instance().getNetworkNodeRegistry().add(NetworkNodeRequester.ID, (tag, world1, pos1) -> {
            NetworkNodeRequester networkNodeRequester = new NetworkNodeRequester(world1, pos1);
            networkNodeRequester.read(tag);
            return networkNodeRequester;
        });
    }


    @Override
    public void updateNetworkNode() {
        super.updateNetworkNode();
        if (network == null) return;
        if (canUpdate() && ticks % 10 == 0 && (craftingTask == null || !network.getCraftingManager().getTasks().contains(craftingTask))) {
            if (this.config.isFilterTypeItem()) {
                ItemStack filter = getItemFilters().getStackInSlot(0);
                if (!filter.isEmpty()) {
                    ItemStack current = network.extractItem(filter, amount, Action.SIMULATE);
                    if (current == null||current.isEmpty() || current.getCount() < amount) {
                        int count =  current == null ||current.isEmpty() ? amount : amount - current.getCount();
                        if (count > 0) {
                            craftingTask = network.getCraftingManager().request(this, filter, Math.min(RS.INSTANCE.config.maxcraftamount, count));
                            isMissingItems = true;
                        }
                    } else {
                        isMissingItems = false;
                    }
                }
            }
            if (this.config.isFilterTypeFluid()) {
                FluidStack filter = getFluidFilters().getFluid(0);
                if (filter != null) {
                    FluidStack current = network.extractFluid(filter, amount, Action.SIMULATE);
                    if (current == null || current.amount < amount) {
                        int count = current == null ? amount : amount - current.amount;
                        if (count > 0) {
                            craftingTask = network.getCraftingManager().request(this, filter, count);
                            isMissingItems = true;
                        }
                    } else {
                        isMissingItems = false;
                    }
                }
            }
        }
    }

    @Override
    public int getEnergyUsage() {
        return RS.INSTANCE.config.requesterUsage;
    }

    @Override
    public String getId() {
        return ID;
    }


    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
        markNetworkNodeDirty();
    }

    public boolean isMissingItems() {
        return isMissingItems && craftingTask == null;
    }


    public IItemHandlerModifiable getItemFilters() {
        return this.config.getItemHandler();
    }


    public FluidInventory getFluidFilters() {
        return this.config.getFluidHandler();
    }


    @Override
    public NBTTagCompound writeConfiguration(NBTTagCompound tag) {
        super.writeConfiguration(tag);
        StackUtils.writeItems(itemFilter, 0, tag);
        tag.setTag(NBT_FLUID_FILTERS, fluidFilter.writeToNbt());
        tag.setInteger(NBT_AMOUNT, amount);
        tag.setBoolean(NBT_MISSING, isMissingItems);
        tag.setTag("config", this.config.writeToNBT(new NBTTagCompound()));
        return tag;
    }

    @Override
    public void readConfiguration(NBTTagCompound tag) {
        super.readConfiguration(tag);
        StackUtils.readItems(itemFilter, 0, tag);
        if (tag.hasKey(NBT_FLUID_FILTERS)) {
            fluidFilter.readFromNbt(tag.getCompoundTag(NBT_FLUID_FILTERS));
        }
        if (tag.hasKey(NBT_AMOUNT)) {
            amount = tag.getInteger(NBT_AMOUNT);
        }
        if (tag.hasKey(NBT_MISSING)) {
            isMissingItems = tag.getBoolean(NBT_MISSING);
        }
        this.config.readFromNBT(tag.getCompoundTag("config"));
    }

    @Nonnull
    @Override
    public FilterConfig getConfig() {
        return this.config;
    }


    @Override
    public NBTTagCompound writeExtraNbt(NBTTagCompound tag) {
        StackUtils.writeItems(itemFilter, 0, tag);
        tag.setInteger(NBT_AMOUNT, amount);
        tag.setBoolean(NBT_MISSING, isMissingItems);
        return tag;
    }



    @Override
    public void readExtraNbt(NBTTagCompound tag) {
        StackUtils.readItems(itemFilter, 0, tag);
        if (tag.hasKey(NBT_AMOUNT)) {
            amount = tag.getInteger(NBT_AMOUNT);
        }
        if (tag.hasKey(NBT_MISSING)) {
            isMissingItems = tag.getBoolean(NBT_MISSING);
        }
    }

}
