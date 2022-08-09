package com.raoulvdberge.refinedstorage.tile;

import com.raoulvdberge.refinedstorage.apiimpl.network.node.NetworkNodeWirelessDimensionTransmitter;
import com.raoulvdberge.refinedstorage.tile.data.TileDataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class TileWirelessDimensionTransmitter extends TileNode<NetworkNodeWirelessDimensionTransmitter> {
    public static final TileDataParameter<Integer, TileWirelessDimensionTransmitter> RANGE = new TileDataParameter<>(DataSerializers.VARINT, 0, t -> t.getNode().getRange());

    public TileWirelessDimensionTransmitter() {
        dataManager.addWatchedParameter(RANGE);
    }

    @Override
    @Nonnull
    public NetworkNodeWirelessDimensionTransmitter createNode(World world, BlockPos pos) {
        return new NetworkNodeWirelessDimensionTransmitter(world, pos);
    }

    @Override
    public String getNodeId() {
        return NetworkNodeWirelessDimensionTransmitter.ID;
    }
}
