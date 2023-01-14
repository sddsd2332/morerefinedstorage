package com.raoulvdberge.refinedstorage.tile.advancedExporter;

import com.raoulvdberge.refinedstorage.apiimpl.network.node.advancedExporter.NetworkNodeCreativeExporter;
import com.raoulvdberge.refinedstorage.tile.TileNode;
import com.raoulvdberge.refinedstorage.tile.config.FilterConfig;
import com.raoulvdberge.refinedstorage.tile.data.TileDataParameter;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class TileCreativeExporter extends TileNode<NetworkNodeCreativeExporter> {
    public static final TileDataParameter<Integer, TileCreativeExporter> COMPARE = FilterConfig.createCompareParameter();
    public static final TileDataParameter<Integer, TileCreativeExporter> TYPE = FilterConfig.createFilterTypeParameter();

    public TileCreativeExporter() {
        dataManager.addWatchedParameter(COMPARE);
        dataManager.addWatchedParameter(TYPE);
    }

    @Override
    @Nonnull
    public NetworkNodeCreativeExporter createNode(World world, BlockPos pos) {
        return new NetworkNodeCreativeExporter(world, pos);
    }

    @Override
    public String getNodeId() {
        return NetworkNodeCreativeExporter.ID;
    }
}
