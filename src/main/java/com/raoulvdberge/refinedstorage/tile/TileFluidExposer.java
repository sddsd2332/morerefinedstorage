package com.raoulvdberge.refinedstorage.tile;

import com.raoulvdberge.refinedstorage.apiimpl.network.node.NetworkNodeFluidExposer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TileFluidExposer extends TileNode<NetworkNodeFluidExposer>{

    @Override
    public NetworkNodeFluidExposer createNode(World world, BlockPos pos) {
        return new NetworkNodeFluidExposer(world,pos);
    }
    @Override
    public String getNodeId() {
        return NetworkNodeFluidExposer.ID;
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing side) {
        if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY && getNode().getFluidHandler() != null) {
            return true;
        }
        return super.hasCapability(capability, side);
    }

    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing side) {
        if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY && getNode().getFluidHandler() != null){
            return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(getNode().getFluidHandler());
        }
        return  super.getCapability(capability, side);
    }




}
