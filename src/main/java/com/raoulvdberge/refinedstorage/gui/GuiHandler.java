package com.raoulvdberge.refinedstorage.gui;

import com.raoulvdberge.refinedstorage.RSGui;
import com.raoulvdberge.refinedstorage.apiimpl.network.node.IGuiReaderWriter;
import com.raoulvdberge.refinedstorage.apiimpl.network.node.NetworkNodeCraftingMonitor;
import com.raoulvdberge.refinedstorage.container.*;
import com.raoulvdberge.refinedstorage.container.advancedCrafter.*;
import com.raoulvdberge.refinedstorage.container.advancedExporter.*;
import com.raoulvdberge.refinedstorage.container.advancedImporter.*;
import com.raoulvdberge.refinedstorage.gui.advancedCrafter.*;
import com.raoulvdberge.refinedstorage.gui.advancedExporter.*;
import com.raoulvdberge.refinedstorage.gui.advancedImporter.*;
import com.raoulvdberge.refinedstorage.tile.*;
import com.raoulvdberge.refinedstorage.tile.advancedCrafter.*;
import com.raoulvdberge.refinedstorage.tile.advancedExporter.*;
import com.raoulvdberge.refinedstorage.tile.advancedImporter.*;
import com.raoulvdberge.refinedstorage.tile.craftingmonitor.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
    private Container getContainer(int id, EntityPlayer player, TileEntity tile) {
        switch (id) {
            case RSGui.CONTROLLER:
                return new ContainerController((TileController) tile, player);
            case RSGui.DISK_DRIVE:
                return new ContainerDiskDrive((TileDiskDrive) tile, player);
            case RSGui.IMPORTER:
                return new ContainerImporter((TileImporter) tile, player);
            case RSGui.ADVANCED_IMPORTER:
                return new ContainerAdvancedImporter((TileAdvancedImporter) tile,player);
            case RSGui.ELITE_IMPORTER:
                return new ContainerEliteImporter((TileEliteImporter) tile,player);
            case RSGui.ULTIMATE_IMPORTER:
                return new ContainerUltimateImporter((TileUltimateImporter) tile,player);
            case RSGui.CREATIVE_IMPORTER:
                return new ContainerCreativeImporter((TileCreativeImporter) tile,player);
            case RSGui.EXPORTER:
                return new ContainerExporter((TileExporter) tile, player);
            case RSGui.REQUESTER:
                return new ContainerRequester((TileRequester) tile , player);
            case RSGui.ADVANCED_EXPORTER:
                return new ContainerAdvancedExporter((TileAdvancedExporter) tile, player);
            case RSGui.ELITE_EXPORTER:
                return new ContainerEliteExporter((TileEliteExporter) tile, player);
            case RSGui.ULTIMATE_EXPORTER:
                return new ContainerUltimateExporter((TileUltimateExporter) tile, player);
            case RSGui.CREATIVE_EXPORTER:
                return new ContainerCreativeExporter((TileCreativeExporter) tile, player);
            case RSGui.DETECTOR:
                return new ContainerDetector((TileDetector) tile, player);
            case RSGui.DESTRUCTOR:
                return new ContainerDestructor((TileDestructor) tile, player);
            case RSGui.CONSTRUCTOR:
                return new ContainerConstructor((TileConstructor) tile, player);
            case RSGui.STORAGE:
                return new ContainerStorage((TileStorage) tile, player);
            case RSGui.EXTERNAL_STORAGE:
                return new ContainerExternalStorage((TileExternalStorage) tile, player);
            case RSGui.RELAY:
                return new ContainerRelay((TileRelay) tile, player);
            case RSGui.INTERFACE:
                return new ContainerInterface((TileInterface) tile, player);
            case RSGui.CRAFTING_MONITOR:
                return new ContainerCraftingMonitor(((TileCraftingMonitor) tile).getNode(), (TileCraftingMonitor) tile, player);
            case RSGui.WIRELESS_TRANSMITTER:
                return new ContainerWirelessTransmitter((TileWirelessTransmitter) tile, player);
            case RSGui.WIRELESS_DIMENSION_TRANSMITTER:
                return new ContainerWirelessDimensionTransmitter((TileWirelessDimensionTransmitter) tile, player);
            case RSGui.CRAFTER:
                return new ContainerCrafter((TileCrafter) tile, player);
            case RSGui.IRONCRAFTER:
                return new ContainerIronCrafter((TileIronCrafter) tile, player);
            case RSGui.GOLDCRAFTER:
                return new ContainerGoldCrafter((TileGoldCrafter) tile, player);
            case RSGui.DIAMONDCRAFTER:
                return new ContainerDiamondCrafter((TileDiamondCrafter) tile, player);
            case RSGui.EMERALDCRAFTER:
                return new ContainerEmeraldCrafter((TileEmeraldCrafter) tile, player);
            case RSGui.CREATIVECRAFTER:
                return new ContainerCreativeCrafter((TileCreativeCrafter) tile, player);
            case RSGui.NETWORK_TRANSMITTER:
                return new ContainerNetworkTransmitter((TileNetworkTransmitter) tile, player);
            case RSGui.FLUID_INTERFACE:
                return new ContainerFluidInterface((TileFluidInterface) tile, player);
            case RSGui.FLUID_STORAGE:
                return new ContainerFluidStorage((TileFluidStorage) tile, player);
            case RSGui.DISK_MANIPULATOR:
                return new ContainerDiskManipulator((TileDiskManipulator) tile, player);
            case RSGui.READER_WRITER:
                return new ContainerReaderWriter((IGuiReaderWriter) ((TileNode) tile).getNode(), (TileBase) tile, player);
            case RSGui.SECURITY_MANAGER:
                return new ContainerSecurityManager((TileSecurityManager) tile, player);
            case RSGui.STORAGE_MONITOR:
                return new ContainerStorageMonitor((TileStorageMonitor) tile, player);
            case RSGui.CRAFTER_MANAGER:
                return new ContainerCrafterManager((TileCrafterManager) tile, player, new ResizableDisplayDummy());
            default:
                return null;
        }
    }

    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        if (id == RSGui.FILTER) {
            return getFilterContainer(player, x);
        } else if (id == RSGui.WIRELESS_CRAFTING_MONITOR) {
            return getCraftingMonitorContainer(player, x);
        }

        return getContainer(id, player, world.getTileEntity(new BlockPos(x, y, z)));
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tile = world.getTileEntity(new BlockPos(x, y, z));

        switch (id) {
            case RSGui.CONTROLLER:
                return new GuiController((ContainerController) getContainer(id, player, tile), (TileController) tile);
            case RSGui.DISK_DRIVE:
                return new GuiStorage((ContainerDiskDrive) getContainer(id, player, tile), ((TileDiskDrive) tile).getNode(), "gui/disk_drive.png");
            case RSGui.IMPORTER:
                return new GuiImporter((ContainerImporter) getContainer(id, player, tile));
            case RSGui.ADVANCED_IMPORTER:
                return new GuiAdvancedImporter((ContainerAdvancedImporter)getContainer(id, player, tile));
            case RSGui.ELITE_IMPORTER:
                return new GuiEliteImporter((ContainerEliteImporter)getContainer(id, player, tile));
            case RSGui.ULTIMATE_IMPORTER:
                return new GuiUltimateImporter((ContainerUltimateImporter)getContainer(id, player, tile));
            case RSGui.CREATIVE_IMPORTER:
                return new GuiCreativeImporter((ContainerCreativeImporter)getContainer(id, player, tile));
            case RSGui.EXPORTER:
                return new GuiExporter((ContainerExporter) getContainer(id, player, tile));
            case RSGui.REQUESTER:
                return new GuiRequester((ContainerRequester) getContainer(id, player, tile));
            case RSGui.ADVANCED_EXPORTER:
                return new GuiAdvancedExporter((ContainerAdvancedExporter) getContainer(id, player, tile));
            case RSGui.ELITE_EXPORTER:
                return new GuiEliteExporter((ContainerEliteExporter) getContainer(id, player, tile));
            case RSGui.ULTIMATE_EXPORTER:
                return new GuiUltimateExporter((ContainerUltimateExporter)getContainer(id, player, tile));
            case RSGui.CREATIVE_EXPORTER:
                return new GuiCreativeExporter((ContainerCreativeExporter)getContainer(id, player, tile));
            case RSGui.DETECTOR:
                return new GuiDetector((ContainerDetector) getContainer(id, player, tile));
            case RSGui.DESTRUCTOR:
                return new GuiDestructor((ContainerDestructor) getContainer(id, player, tile));
            case RSGui.CONSTRUCTOR:
                return new GuiConstructor((ContainerConstructor) getContainer(id, player, tile));
            case RSGui.STORAGE:
                return new GuiStorage((ContainerStorage) getContainer(id, player, tile), ((TileStorage) tile).getNode());
            case RSGui.EXTERNAL_STORAGE:
                return new GuiStorage((ContainerExternalStorage) getContainer(id, player, tile), ((TileExternalStorage) tile).getNode());
            case RSGui.RELAY:
                return new GuiRelay((ContainerRelay) getContainer(id, player, tile));
            case RSGui.INTERFACE:
                return new GuiInterface((ContainerInterface) getContainer(id, player, tile));
            case RSGui.CRAFTING_MONITOR:
                NetworkNodeCraftingMonitor node = ((TileCraftingMonitor) tile).getNode();
                GuiCraftingMonitor gui = new GuiCraftingMonitor(null, node);
                gui.inventorySlots = new ContainerCraftingMonitor(node, (TileCraftingMonitor) tile, player);
                return gui;
            case RSGui.WIRELESS_TRANSMITTER:
                return new GuiWirelessTransmitter((ContainerWirelessTransmitter) getContainer(id, player, tile));
            case RSGui.WIRELESS_DIMENSION_TRANSMITTER:
                return new GuiWirelessDimensionTransmitter((ContainerWirelessDimensionTransmitter) getContainer(id, player, tile));
            case RSGui.CRAFTER:
                return new GuiCrafter((ContainerCrafter) getContainer(id, player, tile));
            case RSGui.IRONCRAFTER:
                return new GuiIronCrafter((ContainerIronCrafter) getContainer(id, player, tile));
            case RSGui.GOLDCRAFTER:
                return new GuiGoldCrafter((ContainerGoldCrafter) getContainer(id, player, tile));
            case RSGui.DIAMONDCRAFTER:
                return new GuiDiamondCrafter((ContainerDiamondCrafter) getContainer(id, player, tile));
            case RSGui.EMERALDCRAFTER:
                return new GuiEmeraldCrafter((ContainerEmeraldCrafter) getContainer(id, player, tile));
            case RSGui.CREATIVECRAFTER:
                return new GuiCreativeCrafter((ContainerCreativeCrafter) getContainer(id, player, tile));
            case RSGui.FILTER:
                return new GuiFilter(getFilterContainer(player, x));
            case RSGui.NETWORK_TRANSMITTER:
                return new GuiNetworkTransmitter((ContainerNetworkTransmitter) getContainer(id, player, tile), (TileNetworkTransmitter) tile);
            case RSGui.FLUID_INTERFACE:
                return new GuiFluidInterface((ContainerFluidInterface) getContainer(id, player, tile));
            case RSGui.FLUID_STORAGE:
                return new GuiStorage((ContainerFluidStorage) getContainer(id, player, tile), ((TileFluidStorage) tile).getNode());
            case RSGui.DISK_MANIPULATOR:
                return new GuiDiskManipulator((ContainerDiskManipulator) getContainer(id, player, tile));
            case RSGui.WIRELESS_CRAFTING_MONITOR:
                return getWirelessCraftingMonitorGui(player, x);
            case RSGui.READER_WRITER:
                return new GuiReaderWriter((ContainerReaderWriter) getContainer(id, player, tile), (IGuiReaderWriter) ((TileNode) tile).getNode());
            case RSGui.SECURITY_MANAGER:
                return new GuiSecurityManager((ContainerSecurityManager) getContainer(id, player, tile), (TileSecurityManager) tile);
            case RSGui.STORAGE_MONITOR:
                return new GuiStorageMonitor((ContainerStorageMonitor) getContainer(id, player, tile));
            case RSGui.CRAFTER_MANAGER:
                GuiCrafterManager crafterManagerGui = new GuiCrafterManager(((TileCrafterManager) tile).getNode());
                crafterManagerGui.setContainer(new ContainerCrafterManager((TileCrafterManager) tile, player, crafterManagerGui));
                return crafterManagerGui;
            default:
                return null;
        }
    }

    private WirelessCraftingMonitor getWirelessCraftingMonitor(EntityPlayer player, int invIndex) {
        return new WirelessCraftingMonitor(player.inventory.getStackInSlot(invIndex), invIndex);
    }

    private GuiCraftingMonitor getWirelessCraftingMonitorGui(EntityPlayer player, int invIndex) {
        WirelessCraftingMonitor craftingMonitor = getWirelessCraftingMonitor(player, invIndex);

        GuiCraftingMonitor gui = new GuiCraftingMonitor(null, craftingMonitor);
        gui.inventorySlots = new ContainerCraftingMonitor(craftingMonitor, null, player);
        return gui;
    }

    private ContainerCraftingMonitor getCraftingMonitorContainer(EntityPlayer player, int invIndex) {
        return new ContainerCraftingMonitor(getWirelessCraftingMonitor(player, invIndex), null, player);
    }

    private ContainerFilter getFilterContainer(EntityPlayer player, int hand) {
        return new ContainerFilter(player, player.getHeldItem(EnumHand.values()[hand]));
    }
}
