package com.raoulvdberge.refinedstorage.tile;


import com.raoulvdberge.refinedstorage.apiimpl.network.node.NetworkNodeRequester;
import com.raoulvdberge.refinedstorage.gui.GuiBase;

import com.raoulvdberge.refinedstorage.gui.GuiRequester;
import com.raoulvdberge.refinedstorage.tile.config.FilterConfig;
import com.raoulvdberge.refinedstorage.tile.data.TileDataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


public class TileRequester extends TileNode<NetworkNodeRequester>  {

    public static final TileDataParameter<Integer, TileRequester> TYPE = FilterConfig.createFilterTypeParameter();
    public static final TileDataParameter<Integer, TileRequester> AMOUNT = new TileDataParameter<>(DataSerializers.VARINT,
            0, t -> t.getNode().getAmount(),
            (t, v) -> {
                t.getNode().setAmount(v);
                t.getNode().markNetworkNodeDirty();
            },
            (initial, p) -> GuiBase.executeLater(GuiRequester.class, requester -> requester.getAmount().setText(String.valueOf(p))));
    public static final TileDataParameter<Boolean, TileRequester> MISSING = new TileDataParameter<>(DataSerializers.BOOLEAN, false, tileRequester -> tileRequester.getNode().isMissingItems(), (tileRequester, aBoolean) -> { });



    public TileRequester() {
        dataManager.addWatchedParameter(TYPE);
        dataManager.addWatchedParameter(AMOUNT);
        dataManager.addParameter(MISSING);
    }

    @Override
    public NetworkNodeRequester createNode(World world, BlockPos pos) {
        return new NetworkNodeRequester(world, pos);
    }

    @Override
    public String getNodeId() {
        return NetworkNodeRequester.ID;
    }



}
