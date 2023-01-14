package com.raoulvdberge.refinedstorage.tile.advancedImporter;


import com.raoulvdberge.refinedstorage.apiimpl.network.node.advancedImporter.NetworkNodeEliteImporter;
import com.raoulvdberge.refinedstorage.tile.TileNode;
import com.raoulvdberge.refinedstorage.tile.config.FilterConfig;
import com.raoulvdberge.refinedstorage.tile.data.TileDataParameter;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class TileEliteImporter extends TileNode<NetworkNodeEliteImporter> {

    public static final TileDataParameter<Integer, TileEliteImporter> COMPARE = FilterConfig.createCompareParameter();
    public static final TileDataParameter<Integer, TileEliteImporter> MODE = FilterConfig.createFilterModeParameter();
    public static final TileDataParameter<Integer, TileEliteImporter> TYPE = FilterConfig.createFilterModeParameter();

    public TileEliteImporter(){
        dataManager.addWatchedParameter(COMPARE);
        dataManager.addWatchedParameter(MODE);
        dataManager.addWatchedParameter(TYPE);
    }

    @Override
    @Nonnull
    public NetworkNodeEliteImporter createNode(World world, BlockPos pos) {
        return new NetworkNodeEliteImporter(world, pos);
    }

        @Override
        public String getNodeId() {
            return NetworkNodeEliteImporter.ID;
        }
}
