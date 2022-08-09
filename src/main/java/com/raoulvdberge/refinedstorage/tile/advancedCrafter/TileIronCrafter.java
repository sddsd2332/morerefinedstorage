package com.raoulvdberge.refinedstorage.tile.advancedCrafter;

import com.raoulvdberge.refinedstorage.api.autocrafting.ICraftingPatternContainer;
import com.raoulvdberge.refinedstorage.apiimpl.network.node.advancedCrafter.NetworkNodeIronCrafter;
import com.raoulvdberge.refinedstorage.gui.TileDataParameterClientListenerCrafter1;
import com.raoulvdberge.refinedstorage.tile.TileNode;
import com.raoulvdberge.refinedstorage.tile.data.TileDataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TileIronCrafter extends TileNode<NetworkNodeIronCrafter> {
    public static final TileDataParameter<String, TileIronCrafter> NAME = new TileDataParameter<>(DataSerializers.STRING, NetworkNodeIronCrafter.DEFAULT_NAME, t -> t.getNode().getName());
    public static final TileDataParameter<Integer, TileIronCrafter> MODE = new TileDataParameter<>(DataSerializers.VARINT, ICraftingPatternContainer.CrafterMode.IGNORE.ordinal(), t -> t.getNode().getMode().ordinal(), (t, v) -> t.getNode().setMode(
            ICraftingPatternContainer.CrafterMode.getById(v)));
    private static final TileDataParameter<Boolean, TileIronCrafter> HAS_ROOT = new TileDataParameter<>(DataSerializers.BOOLEAN, false, t -> t.getNode().getRootContainerNotSelf().isPresent(), null, (t, v) -> new TileDataParameterClientListenerCrafter1().onChanged(t, v));

    public TileIronCrafter() {
        dataManager.addWatchedParameter(NAME);
        dataManager.addWatchedParameter(MODE);
        dataManager.addParameter(HAS_ROOT);
    }

    @Override
    @Nonnull
    public NetworkNodeIronCrafter createNode(World world, BlockPos pos) {
        return new NetworkNodeIronCrafter(world, pos);
    }

    @Override
    public String getNodeId() {
        return NetworkNodeIronCrafter.ID;
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && facing != null &&
                !facing.equals(this.getDirection())) || super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && facing != null &&
                !facing.equals(this.getDirection()))
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(getNode().getPatternItems());

        return super.getCapability(capability, facing);
    }
}
