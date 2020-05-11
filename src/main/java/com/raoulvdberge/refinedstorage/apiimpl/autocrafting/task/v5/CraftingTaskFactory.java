package com.raoulvdberge.refinedstorage.apiimpl.autocrafting.task.v5;

import com.raoulvdberge.refinedstorage.api.autocrafting.ICraftingPattern;
import com.raoulvdberge.refinedstorage.api.autocrafting.registry.ICraftingTaskFactory;
import com.raoulvdberge.refinedstorage.api.autocrafting.task.CraftingTaskReadException;
import com.raoulvdberge.refinedstorage.api.autocrafting.task.ICraftingRequestInfo;
import com.raoulvdberge.refinedstorage.api.autocrafting.task.ICraftingTask;
import com.raoulvdberge.refinedstorage.api.network.INetwork;
import net.minecraft.nbt.NBTTagCompound;

import javax.annotation.Nonnull;

public class CraftingTaskFactory implements ICraftingTaskFactory {
    public static final String ID = "normal";

    @Nonnull
    @Override
    public ICraftingTask create(INetwork network, ICraftingRequestInfo requested, int quantity, ICraftingPattern pattern) {
        return new com.raoulvdberge.refinedstorage.apiimpl.autocrafting.task.v5.CraftingTask(network, requested, quantity, pattern);
    }

    @Override
    public ICraftingTask createFromNbt(INetwork network, NBTTagCompound tag) throws CraftingTaskReadException {
        return new CraftingTask(network, tag);
    }
}