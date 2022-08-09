package com.raoulvdberge.refinedstorage.container.advancedCrafter;

import com.raoulvdberge.refinedstorage.container.ContainerBase;
import com.raoulvdberge.refinedstorage.tile.advancedCrafter.TileIronCrafter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerIronCrafter extends ContainerBase {
    public ContainerIronCrafter(TileIronCrafter crafter, EntityPlayer player) {
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
        for (int i = 0; i < 4; ++i) {
            addSlotToContainer(new SlotItemHandler(crafter.getNode().getUpgradeHandler(), i, 187, 6 + (i * 18)));
        }

        addPlayerInventory(8, 55 + 18 * 2);

        transferManager.addBiTransfer(player.inventory, crafter.getNode().getUpgradeHandler());
        transferManager.addBiTransfer(player.inventory, crafter.getNode().getPatternItems());
    }
}
