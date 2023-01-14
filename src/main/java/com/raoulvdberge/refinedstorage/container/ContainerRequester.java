package com.raoulvdberge.refinedstorage.container;

import com.raoulvdberge.refinedstorage.container.slot.filter.SlotFilter;
import com.raoulvdberge.refinedstorage.container.slot.filter.SlotFilterFluid;
import com.raoulvdberge.refinedstorage.tile.TileRequester;
import net.minecraft.entity.player.EntityPlayer;

public class ContainerRequester extends ContainerBase{
    public ContainerRequester(TileRequester tile, EntityPlayer player) {
        super(tile, player);
        addSlotToContainer(new SlotFilter(tile.getNode().getConfig().getItemHandler(), 0, 17, 20).setEnableHandler(() -> tile.getNode().getConfig().isFilterTypeItem()));
        addSlotToContainer(new SlotFilterFluid(tile.getNode().getConfig().getFluidHandler(), 0, 17, 20).setEnableHandler(() -> tile.getNode().getConfig().isFilterTypeFluid()));
        addPlayerInventory(8, 55);
        transferManager.addFilterTransfer(player.inventory,tile.getNode().getConfig().getItemHandler(),tile.getNode().getConfig().getFluidHandler(),tile.getNode().getConfig()::getFilterType);
    }


}
