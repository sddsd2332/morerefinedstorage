package com.raoulvdberge.refinedstorage.container.advancedCrafter;

import com.raoulvdberge.refinedstorage.container.ContainerBase;
import com.raoulvdberge.refinedstorage.tile.advancedCrafter.TileGoldCrafter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerGoldCrafter extends ContainerBase {
    public ContainerGoldCrafter(TileGoldCrafter crafter, EntityPlayer player) {
        super(crafter, player);

        for (int i = 0; i < 9; ++i) {
            addSlotToContainer(new SlotItemHandler(crafter.getNode().getPatternItems(), i, 8 + (18 * i), 20));
        }
        for (int i = 9; i < 18; ++i) {
            addSlotToContainer(new SlotItemHandler(crafter.getNode().getPatternItems(), i,8 + (18 * (i-9)) , 20 + 18));
        }
        for (int i = 18; i < 27; ++i) {
            addSlotToContainer(new SlotItemHandler(crafter.getNode().getPatternItems(), i,8 + (18 * (i-18)) , 20 + 18*2));
        }
        for (int i = 27; i < 36; ++i) {
            addSlotToContainer(new SlotItemHandler(crafter.getNode().getPatternItems(), i,8 + (18 * (i-27)) , 20 + 18*3));
        }
        for (int i = 36; i < 45; ++i) {
            addSlotToContainer(new SlotItemHandler(crafter.getNode().getPatternItems(), i,8 + (18 * (i-36)) , 20 + 18*4));
        }
        for (int i = 0; i < 4; ++i) {
            addSlotToContainer(new SlotItemHandler(crafter.getNode().getUpgradeHandler(), i, 187, 6 + (i * 18)));
        }

        addPlayerInventory(8, 55 + 18*4);

        transferManager.addBiTransfer(player.inventory, crafter.getNode().getUpgradeHandler());
        transferManager.addBiTransfer(player.inventory, crafter.getNode().getPatternItems());
    }
}
