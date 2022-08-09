package com.raoulvdberge.refinedstorage.tile.advancedCrafter;

import com.raoulvdberge.refinedstorage.api.autocrafting.ICraftingPatternContainer;
import com.raoulvdberge.refinedstorage.apiimpl.network.node.advancedCrafter.NetworkNodeDiamondCrafter;
import com.raoulvdberge.refinedstorage.gui.TileDataParameterClientListenerCrafter3;
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

public class TileDiamondCrafter extends TileNode<NetworkNodeDiamondCrafter> {
    public static final TileDataParameter<String, TileDiamondCrafter> NAME = new TileDataParameter<>(DataSerializers.STRING, NetworkNodeDiamondCrafter.DEFAULT_NAME, t -> t.getNode().getName());
    public static final TileDataParameter<Integer, TileDiamondCrafter> MODE = new TileDataParameter<>(DataSerializers.VARINT, ICraftingPatternContainer.CrafterMode.IGNORE.ordinal(), t -> t.getNode().getMode().ordinal(), (t, v) -> t.getNode().setMode(
            ICraftingPatternContainer.CrafterMode.getById(v)));
    private static final TileDataParameter<Boolean, TileDiamondCrafter> HAS_ROOT = new TileDataParameter<>(DataSerializers.BOOLEAN, false, t -> t.getNode().getRootContainerNotSelf().isPresent(), null, (t, v) -> new TileDataParameterClientListenerCrafter3().onChanged(t, v));

    public TileDiamondCrafter() {
        dataManager.addWatchedParameter(NAME);
        dataManager.addWatchedParameter(MODE);
        dataManager.addParameter(HAS_ROOT);
    }

    @Override
    @Nonnull
    public NetworkNodeDiamondCrafter createNode(World world, BlockPos pos) {
        return new NetworkNodeDiamondCrafter(world, pos);
    }

    @Override
    public String getNodeId() {
        return NetworkNodeDiamondCrafter.ID;
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
