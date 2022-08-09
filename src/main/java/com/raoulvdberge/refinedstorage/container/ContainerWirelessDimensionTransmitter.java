package com.raoulvdberge.refinedstorage.container;

import com.raoulvdberge.refinedstorage.tile.TileWirelessDimensionTransmitter;
import net.minecraft.entity.player.EntityPlayer;

public class ContainerWirelessDimensionTransmitter extends ContainerBase {
    public ContainerWirelessDimensionTransmitter(TileWirelessDimensionTransmitter wirelessDimensionTransmitter, EntityPlayer player) {
        super(wirelessDimensionTransmitter, player);
        addPlayerInventory(8, 55);
    }
}
