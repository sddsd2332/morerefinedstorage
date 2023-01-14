package com.raoulvdberge.refinedstorage.tile.advancedExporter;

import com.raoulvdberge.refinedstorage.apiimpl.network.node.advancedExporter.NetworkNodeUltimateExporter;
import com.raoulvdberge.refinedstorage.tile.TileNode;
import com.raoulvdberge.refinedstorage.tile.config.FilterConfig;
import com.raoulvdberge.refinedstorage.tile.data.TileDataParameter;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class TileUltimateExporter extends TileNode<NetworkNodeUltimateExporter> {
    public static final TileDataParameter<Integer, TileUltimateExporter> COMPARE = FilterConfig.createCompareParameter();
    public static final TileDataParameter<Integer, TileUltimateExporter> TYPE = FilterConfig.createFilterTypeParameter();

    public TileUltimateExporter() {
        dataManager.addWatchedParameter(COMPARE);
        dataManager.addWatchedParameter(TYPE);
    }

    @Override
    @Nonnull
    public NetworkNodeUltimateExporter createNode(World world, BlockPos pos) {
        return new NetworkNodeUltimateExporter(world, pos);
    }

    @Override
    public String getNodeId() {
        return NetworkNodeUltimateExporter.ID;
    }
}
