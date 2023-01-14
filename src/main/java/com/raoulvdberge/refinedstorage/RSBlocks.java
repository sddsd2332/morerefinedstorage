package com.raoulvdberge.refinedstorage;

import com.raoulvdberge.refinedstorage.block.*;
import com.raoulvdberge.refinedstorage.block.advancedCrafter.*;
import com.raoulvdberge.refinedstorage.block.advancedExporter.*;
import com.raoulvdberge.refinedstorage.block.advancedImporter.*;

public final class RSBlocks {
    public static final BlockController CONTROLLER = new BlockController();
    public static final BlockCable CABLE = new BlockCable();
    public static final BlockGrid GRID = new BlockGrid();
    public static final BlockDiskDrive DISK_DRIVE = new BlockDiskDrive();
    public static final BlockExternalStorage EXTERNAL_STORAGE = new BlockExternalStorage();
    public static final BlockDetector DETECTOR = new BlockDetector();
    public static final BlockMachineCasing MACHINE_CASING = new BlockMachineCasing();
    public static final BlockDestructor DESTRUCTOR = new BlockDestructor();
    public static final BlockConstructor CONSTRUCTOR = new BlockConstructor();
    public static final BlockStorage STORAGE = new BlockStorage();
    public static final BlockRelay RELAY = new BlockRelay();
    public static final BlockInterface INTERFACE = new BlockInterface();
    public static final BlockCraftingMonitor CRAFTING_MONITOR = new BlockCraftingMonitor();
    public static final BlockNetworkTransmitter NETWORK_TRANSMITTER = new BlockNetworkTransmitter();
    public static final BlockNetworkReceiver NETWORK_RECEIVER = new BlockNetworkReceiver();
    public static final BlockFluidInterface FLUID_INTERFACE = new BlockFluidInterface();
    public static final BlockFluidStorage FLUID_STORAGE = new BlockFluidStorage();
    public static final BlockReader READER = new BlockReader();
    public static final BlockWriter WRITER = new BlockWriter();
    public static final BlockSecurityManager SECURITY_MANAGER = new BlockSecurityManager();
    public static final BlockQuartzEnrichedIron QUARTZ_ENRICHED_IRON = new BlockQuartzEnrichedIron();
    public static final BlockStorageMonitor STORAGE_MONITOR = new BlockStorageMonitor();
    public static final BlockPortableGrid PORTABLE_GRID = new BlockPortableGrid();
    public static final BlockCrafterManager CRAFTER_MANAGER = new BlockCrafterManager();
    public static final BlockRequester REQUESTER = new BlockRequester();

    //Importer
    public static final BlockImporter IMPORTER = new BlockImporter();
    public static final BlockAdvancedImporter ADVANCED_IMPORTER = new BlockAdvancedImporter();
    public static final BlockEliteImporter ELITE_IMPORTER = new BlockEliteImporter();
    public static final BlockUltimateImporter ULTIMATE_IMPORTER = new BlockUltimateImporter();
    public static final BlockCreativeImporter CREATIVE_IMPORTER = new BlockCreativeImporter();

    //Exporter
    public static final BlockExporter EXPORTER = new BlockExporter();
    public static final BlockAdvancedExporter ADVANCED_EXPORTER = new BlockAdvancedExporter();
    public static final BlockEliteExporter ELITE_EXPORTER = new BlockEliteExporter();
    public static final BlockUltimateExporter ULTIMATE_EXPORTER = new BlockUltimateExporter();
    public static final BlockCreativeExporter CREATIVE_EXPORTER = new BlockCreativeExporter();

    //Crafter
    public static final BlockCrafter CRAFTER = new BlockCrafter();
    public static final BlockIronCrafter IRONCRAFTER = new BlockIronCrafter();
    public static final BlockGoldCrafter GOLDCRAFTER = new BlockGoldCrafter();
    public static final BlockDiamondCrafter DIAMONDCRAFTER = new BlockDiamondCrafter();
    public static final BlockEmeraldCrafter EMERALDCRAFTER = new BlockEmeraldCrafter();
    public static final BlockCreativeCrafter CREATIVECRAFTER = new BlockCreativeCrafter();

    //WirelessTransmitter
    public static final BlockWirelessTransmitter WIRELESS_TRANSMITTER = new BlockWirelessTransmitter();
    public static final BlockWirelessDimensionTransmitter WIRELESS_DIMENSION_TRANSMITTER =new BlockWirelessDimensionTransmitter();

    //Disk Manipulator
    public static final BlockDiskManipulator DISK_MANIPULATOR = new BlockDiskManipulator();

    //WIP
    public static final BlockExposer EXPOSER = new BlockExposer();
    public static final BlockFluidExposer FLUID_EXPOSER = new BlockFluidExposer();
}