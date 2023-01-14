package com.raoulvdberge.refinedstorage.tile.advancedExporter;

import com.raoulvdberge.refinedstorage.apiimpl.network.node.advancedExporter.NetworkNodeAdvancedExporter;
import com.raoulvdberge.refinedstorage.tile.TileNode;
import com.raoulvdberge.refinedstorage.tile.config.FilterConfig;
import com.raoulvdberge.refinedstorage.tile.data.TileDataParameter;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class TileAdvancedExporter extends TileNode<NetworkNodeAdvancedExporter> {
    public static final TileDataParameter<Integer, TileAdvancedExporter> COMPARE = FilterConfig.createCompareParameter();
    public static final TileDataParameter<Integer, TileAdvancedExporter> TYPE = FilterConfig.createFilterTypeParameter();

    public TileAdvancedExporter() {
        dataManager.addWatchedParameter(COMPARE);
        dataManager.addWatchedParameter(TYPE);
    }

    @Override
    @Nonnull
    public NetworkNodeAdvancedExporter createNode(World world, BlockPos pos) {
        return new NetworkNodeAdvancedExporter(world, pos);
    }

    @Override
    public String getNodeId() {
        return NetworkNodeAdvancedExporter.ID;
    }
}
