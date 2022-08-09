package com.raoulvdberge.refinedstorage.apiimpl.network.node;

import com.raoulvdberge.refinedstorage.RS;
import com.raoulvdberge.refinedstorage.api.network.IWirelessDimensionTransmitter;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nullable;

public class NetworkNodeWirelessDimensionTransmitter extends NetworkNode implements IWirelessDimensionTransmitter{
    public static final String ID = "wireless_dimension_transmitter";


    public NetworkNodeWirelessDimensionTransmitter(World world, BlockPos pos) {
        super(world, pos);
    }

    @Override
    public int getEnergyUsage() {
        return RS.INSTANCE.config.wirelessDimensionTransmitterUsage;
    }

    @Override
    public void read(NBTTagCompound tag) {
        super.read(tag);

    }

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public NBTTagCompound write(NBTTagCompound tag) {
        super.write(tag);

        return tag;
    }

    @Override
    public int getRange() {
        return RS.INSTANCE.config.wirelessDimensionTransmitterBaseRange;
    }

    @Override
    public BlockPos getOrigin() {
        return pos;
    }

    @Override
    public boolean canConduct(@Nullable EnumFacing direction) {
        return EnumFacing.DOWN.equals(direction);
    }

    @Override
    public boolean hasConnectivityState() {
        return true;
    }

    @Override
    public void visit(Operator operator) {
        operator.apply(world, pos.offset(EnumFacing.DOWN), EnumFacing.UP);
    }

}
