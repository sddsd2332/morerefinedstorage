package com.raoulvdberge.refinedstorage.container.advancedImporter;

import com.raoulvdberge.refinedstorage.container.ContainerBase;
import com.raoulvdberge.refinedstorage.container.slot.filter.SlotFilter;
import com.raoulvdberge.refinedstorage.container.slot.filter.SlotFilterFluid;
import com.raoulvdberge.refinedstorage.tile.advancedImporter.TileAdvancedImporter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerAdvancedImporter extends ContainerBase {
    public ContainerAdvancedImporter(TileAdvancedImporter importer, EntityPlayer player) {
        super(importer, player);

        for (int i = 0; i < 4; ++i) {
            addSlotToContainer(new SlotItemHandler(importer.getNode().getUpgradeHandler(), i, 187, 6 + (i * 18)));
        }

        for (int i = 0; i < 9; ++i) {
            addSlotToContainer(new SlotFilter(importer.getNode().getConfig().getItemHandler(), i, 8 + (18 * i), 20).setEnableHandler(() -> importer.getNode().getConfig().isFilterTypeItem()));
        }
        for (int i = 9; i < 18; ++i) {
            addSlotToContainer(new SlotFilter(importer.getNode().getConfig().getItemHandler(), i, 8 + (18 * (i - 9)), 38).setEnableHandler(() -> importer.getNode().getConfig().isFilterTypeItem()));
        }


        for (int i = 0; i < 9; ++i) {
            addSlotToContainer(new SlotFilterFluid(importer.getNode().getConfig().getFluidHandler(), i, 8 + (18 * i), 20).setEnableHandler(() -> importer.getNode().getConfig().isFilterTypeFluid()));
        }
        for (int i = 9; i < 18; ++i) {
            addSlotToContainer(new SlotFilterFluid(importer.getNode().getConfig().getFluidHandler(), i, 8 + (18 * (i - 9)), 38).setEnableHandler(() -> importer.getNode().getConfig().isFilterTypeFluid()));
        }

        addPlayerInventory(8, 73);

        transferManager.addBiTransfer(player.inventory, importer.getNode().getUpgradeHandler());
        transferManager.addFilterTransfer(player.inventory, importer.getNode().getConfig().getItemHandler(), importer.getNode().getConfig().getFluidHandler(), importer.getNode().getConfig()::getFilterType);
    }
}
