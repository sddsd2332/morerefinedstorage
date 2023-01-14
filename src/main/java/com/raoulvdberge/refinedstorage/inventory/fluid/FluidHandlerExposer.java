package com.raoulvdberge.refinedstorage.inventory.fluid;

import com.raoulvdberge.refinedstorage.api.network.INetwork;
import com.raoulvdberge.refinedstorage.api.storage.IStorageCacheListener;
import com.raoulvdberge.refinedstorage.api.util.StackListEntry;
import com.raoulvdberge.refinedstorage.api.util.StackListResult;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class FluidHandlerExposer implements IFluidHandler, IStorageCacheListener<FluidStack> {

    private final INetwork network;
    private FluidStack[] storageCacheData;

    public FluidHandlerExposer(INetwork network){
        this.network = network;
        invalidate();
    }

    @Override
    public void onAttached() {

    }

    @Override
    public void onInvalidated() {
        invalidate();
    }

    @Override
    public void onChanged(@Nonnull StackListResult<FluidStack> delta) {
        invalidate();
    }

    @Override
    public void onChangedBulk(@Nonnull List<StackListResult<FluidStack>> deltas) {
        invalidate();
    }

    @Override
    public IFluidTankProperties[] getTankProperties() {
        return new IFluidTankProperties[0];
    }

    @Override
    public int fill(FluidStack fluidStack, boolean b) {
        return 0;
    }

    @Nullable
    @Override
    public FluidStack drain(FluidStack fluidStack, boolean b) {
        return null;
    }

    @Nullable
    @Override
    public FluidStack drain(int i, boolean b) {
        return null;
    }

    private void invalidate() {
        this.storageCacheData = network.getFluidStorageCache().getList().getStacks().stream().map(StackListEntry::getStack).toArray(FluidStack[]::new);
    }
}
