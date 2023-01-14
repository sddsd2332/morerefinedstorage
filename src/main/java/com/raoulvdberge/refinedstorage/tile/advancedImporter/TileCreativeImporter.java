package com.raoulvdberge.refinedstorage.tile.advancedImporter;


import com.raoulvdberge.refinedstorage.apiimpl.network.node.advancedImporter.NetworkNodeCreativeImporter;
import com.raoulvdberge.refinedstorage.tile.TileNode;
import com.raoulvdberge.refinedstorage.tile.config.FilterConfig;
import com.raoulvdberge.refinedstorage.tile.data.TileDataParameter;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class TileCreativeImporter extends TileNode<NetworkNodeCreativeImporter> {

    public static final TileDataParameter<Integer, TileCreativeImporter> COMPARE = FilterConfig.createCompareParameter();
    public static final TileDataParameter<Integer, TileCreativeImporter> MODE = FilterConfig.createFilterModeParameter();
    public static final TileDataParameter<Integer, TileCreativeImporter> TYPE = FilterConfig.createFilterModeParameter();

    public TileCreativeImporter(){
        dataManager.addWatchedParameter(COMPARE);
        dataManager.addWatchedParameter(MODE);
        dataManager.addWatchedParameter(TYPE);
    }

    @Override
    @Nonnull
    public NetworkNodeCreativeImporter createNode(World world, BlockPos pos) {
        return new NetworkNodeCreativeImporter(world, pos);
    }

        @Override
        public String getNodeId() {
            return NetworkNodeCreativeImporter.ID;
        }
}
