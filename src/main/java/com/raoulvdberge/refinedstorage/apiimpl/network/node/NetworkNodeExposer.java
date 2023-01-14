package com.raoulvdberge.refinedstorage.apiimpl.network.node;

import com.raoulvdberge.refinedstorage.RS;
import com.raoulvdberge.refinedstorage.api.network.INetwork;
import com.raoulvdberge.refinedstorage.inventory.item.ItemHandlerExposer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;

public class NetworkNodeExposer  extends NetworkNode {

    public static final String ID = "exposer";
    private ItemHandlerExposer itemHandler;

    public NetworkNodeExposer(World world, BlockPos pos) {
        super(world, pos);
    }

    @Override
    public int getEnergyUsage() {
        return RS.INSTANCE.config.exposerUsage;
    }

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public void onConnected(INetwork network) {
        super.onConnected(network);
        itemHandler = new ItemHandlerExposer(network);
        network.getItemStorageCache().addListener(itemHandler);
    }

    @Override
    public void onDisconnected(INetwork network) {
        super.onDisconnected(network);
        network.getItemStorageCache().removeListener(itemHandler);
        itemHandler = null;
    }



    public IItemHandler getItemHandler() {
        return itemHandler;
    }

}
