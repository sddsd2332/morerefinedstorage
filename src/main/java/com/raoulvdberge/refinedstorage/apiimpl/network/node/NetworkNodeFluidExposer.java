package com.raoulvdberge.refinedstorage.apiimpl.network.node;

import com.raoulvdberge.refinedstorage.RS;
import com.raoulvdberge.refinedstorage.api.network.INetwork;
import com.raoulvdberge.refinedstorage.inventory.fluid.FluidHandlerExposer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.capability.IFluidHandler;

public class NetworkNodeFluidExposer extends NetworkNode{

    public static final String ID = "fluidexposer";
    private FluidHandlerExposer  fluidHand;

    public NetworkNodeFluidExposer(World world, BlockPos pos) {
        super(world, pos);
    }

    @Override
    public int getEnergyUsage() {
        return RS.INSTANCE.config.fluidexposerUsage;
    }

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public void onConnected(INetwork network) {
        super.onConnected(network);
        fluidHand = new FluidHandlerExposer(network);
        network.getFluidStorageCache().addListener(fluidHand);
    }

    @Override
    public void onDisconnected(INetwork network) {
        super.onDisconnected(network);
        network.getFluidStorageCache().removeListener(fluidHand);
        fluidHand = null;
    }

    public IFluidHandler getFluidHandler(){
        return fluidHand;
    }
}
