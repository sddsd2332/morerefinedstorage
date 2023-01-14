package com.raoulvdberge.refinedstorage.tile.advancedCrafter;

import com.raoulvdberge.refinedstorage.api.autocrafting.ICraftingPatternContainer;
import com.raoulvdberge.refinedstorage.apiimpl.network.node.advancedCrafter.NetworkNodeCreativeCrafter;
import com.raoulvdberge.refinedstorage.gui.TileDataParameterClientListenerCrafter5;
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

public class TileCreativeCrafter extends TileNode<NetworkNodeCreativeCrafter> {
    public static final TileDataParameter<String, TileCreativeCrafter> NAME = new TileDataParameter<>(DataSerializers.STRING, NetworkNodeCreativeCrafter.DEFAULT_NAME, t -> t.getNode().getName());
    public static final TileDataParameter<Integer, TileCreativeCrafter> MODE = new TileDataParameter<>(DataSerializers.VARINT, ICraftingPatternContainer.CrafterMode.IGNORE.ordinal(), t -> t.getNode().getMode().ordinal(), (t, v) -> t.getNode().setMode(
            ICraftingPatternContainer.CrafterMode.getById(v)));
    private static final TileDataParameter<Boolean, TileCreativeCrafter> HAS_ROOT = new TileDataParameter<>(DataSerializers.BOOLEAN, false, t -> t.getNode().getRootContainerNotSelf().isPresent(), null, (t, v) -> new TileDataParameterClientListenerCrafter5().onChanged(t, v));

    public TileCreativeCrafter() {
        dataManager.addWatchedParameter(NAME);
        dataManager.addWatchedParameter(MODE);
        dataManager.addParameter(HAS_ROOT);
    }

    @Override
    @Nonnull
    public NetworkNodeCreativeCrafter createNode(World world, BlockPos pos) {
        return new NetworkNodeCreativeCrafter(world, pos);
    }

    @Override
    public String getNodeId() {
        return NetworkNodeCreativeCrafter.ID;
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
