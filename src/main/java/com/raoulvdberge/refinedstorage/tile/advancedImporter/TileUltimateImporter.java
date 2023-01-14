package com.raoulvdberge.refinedstorage.tile.advancedImporter;


import com.raoulvdberge.refinedstorage.apiimpl.network.node.advancedImporter.NetworkNodeUltimateImporter;
import com.raoulvdberge.refinedstorage.tile.TileNode;
import com.raoulvdberge.refinedstorage.tile.config.FilterConfig;
import com.raoulvdberge.refinedstorage.tile.data.TileDataParameter;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class TileUltimateImporter extends TileNode<NetworkNodeUltimateImporter> {

    public static final TileDataParameter<Integer, TileUltimateImporter> COMPARE = FilterConfig.createCompareParameter();
    public static final TileDataParameter<Integer, TileUltimateImporter> MODE = FilterConfig.createFilterModeParameter();
    public static final TileDataParameter<Integer, TileUltimateImporter> TYPE = FilterConfig.createFilterModeParameter();

    public TileUltimateImporter(){
        dataManager.addWatchedParameter(COMPARE);
        dataManager.addWatchedParameter(MODE);
        dataManager.addWatchedParameter(TYPE);
    }

    @Override
    @Nonnull
    public NetworkNodeUltimateImporter createNode(World world, BlockPos pos) {
        return new NetworkNodeUltimateImporter(world, pos);
    }

        @Override
        public String getNodeId() {
            return NetworkNodeUltimateImporter.ID;
        }
}
