package com.raoulvdberge.refinedstorage.tile.advancedExporter;

import com.raoulvdberge.refinedstorage.apiimpl.network.node.advancedExporter.NetworkNodeEliteExporter;
import com.raoulvdberge.refinedstorage.tile.TileNode;
import com.raoulvdberge.refinedstorage.tile.config.FilterConfig;
import com.raoulvdberge.refinedstorage.tile.data.TileDataParameter;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class TileEliteExporter extends TileNode<NetworkNodeEliteExporter> {
    public static final TileDataParameter<Integer, TileEliteExporter> COMPARE = FilterConfig.createCompareParameter();
    public static final TileDataParameter<Integer, TileEliteExporter> TYPE = FilterConfig.createFilterTypeParameter();

    public TileEliteExporter() {
        dataManager.addWatchedParameter(COMPARE);
        dataManager.addWatchedParameter(TYPE);
    }

    @Override
    @Nonnull
    public NetworkNodeEliteExporter createNode(World world, BlockPos pos) {
        return new NetworkNodeEliteExporter(world, pos);
    }

    @Override
    public String getNodeId() {
        return NetworkNodeEliteExporter.ID;
    }
}
