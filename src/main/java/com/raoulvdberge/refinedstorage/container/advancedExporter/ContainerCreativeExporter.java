package com.raoulvdberge.refinedstorage.container.advancedExporter;

import com.raoulvdberge.refinedstorage.container.ContainerBase;
import com.raoulvdberge.refinedstorage.container.slot.filter.SlotFilter;
import com.raoulvdberge.refinedstorage.container.slot.filter.SlotFilterFluid;
import com.raoulvdberge.refinedstorage.inventory.item.ItemHandlerUpgrade;
import com.raoulvdberge.refinedstorage.item.ItemUpgrade;
import com.raoulvdberge.refinedstorage.tile.advancedExporter.TileCreativeExporter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class ContainerCreativeExporter extends ContainerBase {

    private boolean hasRegulatorUpgrade;

    private final TileCreativeExporter exporter;

    public ContainerCreativeExporter(TileCreativeExporter exporter, EntityPlayer player) {
        super(exporter, player);
        this.exporter = exporter;

        //the client node of the exporter does not always know about the upgrades it has, that's why those other methods
        // are overriden
        this.hasRegulatorUpgrade = hasRegulatorUpgrade();

        initSlots();
    }

    private boolean hasRegulatorUpgrade() {
        return exporter.getNode().getUpgradeHandler().hasUpgrade(ItemUpgrade.TYPE_REGULATOR);
    }

    @Override
    public void putStackInSlot(int slotID, ItemStack stack) {
        //this is called when the actual slots are sent over
        detectRegulatorUpgradeChange();

        super.putStackInSlot(slotID, stack);
    }

    @Nonnull
    @Override
    public ItemStack slotClick(int id, int dragType, @Nonnull ClickType clickType, @Nonnull EntityPlayer player) {
        //called when putting anything in the filters
        detectRegulatorUpgradeChange();

        return super.slotClick(id, dragType, clickType, player);
    }

    private void detectRegulatorUpgradeChange() {
        boolean newState = hasRegulatorUpgrade();
        if (this.hasRegulatorUpgrade != newState) {
            this.hasRegulatorUpgrade = newState;
            this.initSlots();
        }
    }

    public void initSlots() {
        this.inventorySlots.clear();
        this.inventoryItemStacks.clear();

        this.transferManager.clearTransfers();

        TileCreativeExporter exporter = (TileCreativeExporter) getTile();
        ItemHandlerUpgrade upgrades = exporter.getNode().getUpgradeHandler();

        for (int i = 0; i < 4; ++i) {
            addSlotToContainer(new SlotItemHandler(upgrades, i, 187, 6 + (i * 18)));
        }

        for (int i = 0; i < 9; ++i) {
            addSlotToContainer(new SlotFilter(exporter.getNode().getConfig().getItemHandler(), i, 8 + (18 * i), 20, upgrades.hasUpgrade(ItemUpgrade.TYPE_REGULATOR) ? SlotFilter.FILTER_ALLOW_SIZE : 0).setEnableHandler(() -> exporter.getNode().getConfig().isFilterTypeItem()));
        }

        for (int i = 9; i < 18; ++i) {
            addSlotToContainer(new SlotFilter(exporter.getNode().getConfig().getItemHandler(), i, 8 + (18 * (i - 9)), 20 + 18 , upgrades.hasUpgrade(ItemUpgrade.TYPE_REGULATOR) ? SlotFilter.FILTER_ALLOW_SIZE : 0).setEnableHandler(() -> exporter.getNode().getConfig().isFilterTypeItem()));
        }

        for (int i = 18; i < 27; ++i) {
            addSlotToContainer(new SlotFilter(exporter.getNode().getConfig().getItemHandler(), i, 8 + (18 * (i - 9 * 2)), 20 + 18 * 2, upgrades.hasUpgrade(ItemUpgrade.TYPE_REGULATOR) ? SlotFilter.FILTER_ALLOW_SIZE : 0).setEnableHandler(() -> exporter.getNode().getConfig().isFilterTypeItem()));
        }

        for (int i = 27; i < 36; ++i) {
            addSlotToContainer(new SlotFilter(exporter.getNode().getConfig().getItemHandler(), i, 8 + (18 * (i - 9 * 3)), 20 + 18 * 3, upgrades.hasUpgrade(ItemUpgrade.TYPE_REGULATOR) ? SlotFilter.FILTER_ALLOW_SIZE : 0).setEnableHandler(() -> exporter.getNode().getConfig().isFilterTypeItem()));
        }

        for (int i = 36; i < 45; ++i) {
            addSlotToContainer(new SlotFilter(exporter.getNode().getConfig().getItemHandler(), i, 8 + (18 * (i - 9 * 4)), 20 + 18 * 4, upgrades.hasUpgrade(ItemUpgrade.TYPE_REGULATOR) ? SlotFilter.FILTER_ALLOW_SIZE : 0).setEnableHandler(() -> exporter.getNode().getConfig().isFilterTypeItem()));
        }

        for (int i = 45; i < 54; ++i) {
            addSlotToContainer(new SlotFilter(exporter.getNode().getConfig().getItemHandler(), i, 8 + (18 * (i - 9 * 5)), 20 + 18 * 5, upgrades.hasUpgrade(ItemUpgrade.TYPE_REGULATOR) ? SlotFilter.FILTER_ALLOW_SIZE : 0).setEnableHandler(() -> exporter.getNode().getConfig().isFilterTypeItem()));
        }

        for (int i = 54; i < 63; ++i) {
            addSlotToContainer(new SlotFilter(exporter.getNode().getConfig().getItemHandler(), i, 8 + (18 * (i - 9 * 6)), 20 + 18 * 6, upgrades.hasUpgrade(ItemUpgrade.TYPE_REGULATOR) ? SlotFilter.FILTER_ALLOW_SIZE : 0).setEnableHandler(() -> exporter.getNode().getConfig().isFilterTypeItem()));
        }

        for (int i = 63; i < 72; ++i) {
            addSlotToContainer(new SlotFilter(exporter.getNode().getConfig().getItemHandler(), i, 8 + (18 * (i - 9 * 7)), 20 + 18 * 7, upgrades.hasUpgrade(ItemUpgrade.TYPE_REGULATOR) ? SlotFilter.FILTER_ALLOW_SIZE : 0).setEnableHandler(() -> exporter.getNode().getConfig().isFilterTypeItem()));
        }

        for (int i = 0; i < 9; ++i) {
            addSlotToContainer(new SlotFilterFluid(exporter.getNode().getConfig().getFluidHandler(), i, 8 + (18 * i), 20, upgrades.hasUpgrade(ItemUpgrade.TYPE_REGULATOR) ? SlotFilterFluid.FILTER_ALLOW_SIZE : 0).setEnableHandler(() -> exporter.getNode().getConfig().isFilterTypeFluid()));
        }

        for (int i = 9; i < 18; ++i) {
            addSlotToContainer(new SlotFilterFluid(exporter.getNode().getConfig().getFluidHandler(), i, 8 + (18 * (i - 9)), 20 + 18, upgrades.hasUpgrade(ItemUpgrade.TYPE_REGULATOR) ? SlotFilterFluid.FILTER_ALLOW_SIZE : 0).setEnableHandler(() -> exporter.getNode().getConfig().isFilterTypeFluid()));
        }

        for (int i = 18; i < 27; ++i) {
            addSlotToContainer(new SlotFilterFluid(exporter.getNode().getConfig().getFluidHandler(), i, 8 + (18 * (i - 9 * 2)), 20 + 18 * 2, upgrades.hasUpgrade(ItemUpgrade.TYPE_REGULATOR) ? SlotFilterFluid.FILTER_ALLOW_SIZE : 0).setEnableHandler(() -> exporter.getNode().getConfig().isFilterTypeFluid()));
        }

        for (int i = 27; i < 36; ++i) {
            addSlotToContainer(new SlotFilterFluid(exporter.getNode().getConfig().getFluidHandler(), i, 8 + (18 * (i - 9 * 3)), 20 + 18 * 3, upgrades.hasUpgrade(ItemUpgrade.TYPE_REGULATOR) ? SlotFilterFluid.FILTER_ALLOW_SIZE : 0).setEnableHandler(() -> exporter.getNode().getConfig().isFilterTypeFluid()));
        }

        for (int i = 36; i < 45; ++i) {
            addSlotToContainer(new SlotFilterFluid(exporter.getNode().getConfig().getFluidHandler(), i, 8 + (18 * (i - 9 * 4)), 20 + 18 * 4, upgrades.hasUpgrade(ItemUpgrade.TYPE_REGULATOR) ? SlotFilterFluid.FILTER_ALLOW_SIZE : 0).setEnableHandler(() -> exporter.getNode().getConfig().isFilterTypeFluid()));
        }

        for (int i = 45; i < 54; ++i) {
            addSlotToContainer(new SlotFilterFluid(exporter.getNode().getConfig().getFluidHandler(), i, 8 + (18 * (i - 9 * 5)), 20 + 18 * 5, upgrades.hasUpgrade(ItemUpgrade.TYPE_REGULATOR) ? SlotFilterFluid.FILTER_ALLOW_SIZE : 0).setEnableHandler(() -> exporter.getNode().getConfig().isFilterTypeFluid()));
        }

        for (int i = 54; i < 63; ++i) {
            addSlotToContainer(new SlotFilterFluid(exporter.getNode().getConfig().getFluidHandler(), i, 8 + (18 * (i - 9 * 6)), 20 + 18 * 6, upgrades.hasUpgrade(ItemUpgrade.TYPE_REGULATOR) ? SlotFilterFluid.FILTER_ALLOW_SIZE : 0).setEnableHandler(() -> exporter.getNode().getConfig().isFilterTypeFluid()));
        }

        for (int i = 63; i < 72; ++i) {
            addSlotToContainer(new SlotFilterFluid(exporter.getNode().getConfig().getFluidHandler(), i, 8 + (18 * (i - 9 * 7)), 20 + 18 * 7, upgrades.hasUpgrade(ItemUpgrade.TYPE_REGULATOR) ? SlotFilterFluid.FILTER_ALLOW_SIZE : 0).setEnableHandler(() -> exporter.getNode().getConfig().isFilterTypeFluid()));
        }

        addPlayerInventory(8, 55 + 18 * 7);

        transferManager.addBiTransfer(getPlayer().inventory, upgrades);
        transferManager.addFilterTransfer(getPlayer().inventory, exporter.getNode().getConfig().getItemHandler(), exporter.getNode().getConfig().getFluidHandler(), exporter.getNode().getConfig()::getFilterType);
    }
}
