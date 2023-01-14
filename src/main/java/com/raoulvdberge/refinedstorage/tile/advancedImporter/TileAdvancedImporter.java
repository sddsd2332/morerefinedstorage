package com.raoulvdberge.refinedstorage.tile.advancedImporter;


import com.raoulvdberge.refinedstorage.apiimpl.network.node.advancedImporter.NetworkNodeAdvancedImporter;
import com.raoulvdberge.refinedstorage.tile.TileNode;
import com.raoulvdberge.refinedstorage.tile.config.FilterConfig;
import com.raoulvdberge.refinedstorage.tile.data.TileDataParameter;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class TileAdvancedImporter extends TileNode<NetworkNodeAdvancedImporter> {

    public static final TileDataParameter<Integer,TileAdvancedImporter> COMPARE = FilterConfig.createCompareParameter();
    public static final TileDataParameter<Integer,TileAdvancedImporter> MODE = FilterConfig.createFilterModeParameter();
    public static final TileDataParameter<Integer,TileAdvancedImporter> TYPE = FilterConfig.createFilterModeParameter();

    public TileAdvancedImporter(){
        dataManager.addWatchedParameter(COMPARE);
        dataManager.addWatchedParameter(MODE);
        dataManager.addWatchedParameter(TYPE);
    }

    @Override
    @Nonnull
    public NetworkNodeAdvancedImporter createNode(World world, BlockPos pos) {
        return new NetworkNodeAdvancedImporter(world, pos);
    }

        @Override
        public String getNodeId() {
            return NetworkNodeAdvancedImporter.ID;
        }
}
