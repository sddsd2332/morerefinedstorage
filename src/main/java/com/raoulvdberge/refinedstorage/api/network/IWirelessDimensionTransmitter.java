package com.raoulvdberge.refinedstorage.api.network;

import net.minecraft.util.math.BlockPos;

/**
 * Represents a node that can send a wireless signal.
 */
public interface IWirelessDimensionTransmitter {
    /**
     * @return the range in blocks of this transmitter, starting from {@link IWirelessDimensionTransmitter#getOrigin()}
     */
    int getRange();

    /**
     * @return the position where the wireless signal starts
     */
    BlockPos getOrigin();

}
