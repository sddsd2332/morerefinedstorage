package com.raoulvdberge.refinedstorage.gui.advancedImporter;

import com.raoulvdberge.refinedstorage.api.util.IComparer;
import com.raoulvdberge.refinedstorage.container.advancedImporter.ContainerUltimateImporter;
import com.raoulvdberge.refinedstorage.gui.GuiBase;
import com.raoulvdberge.refinedstorage.gui.control.SideButtonCompare;
import com.raoulvdberge.refinedstorage.gui.control.SideButtonMode;
import com.raoulvdberge.refinedstorage.gui.control.SideButtonRedstoneMode;
import com.raoulvdberge.refinedstorage.gui.control.SideButtonType;
import com.raoulvdberge.refinedstorage.tile.advancedImporter.TileUltimateImporter;

public class GuiUltimateImporter extends GuiBase {
    public GuiUltimateImporter(ContainerUltimateImporter container) {
        super(container, 211, 227);
    }

    @Override
    public void init(int x, int y) {
        addSideButton(new SideButtonRedstoneMode(this, TileUltimateImporter.REDSTONE_MODE));

        addSideButton(new SideButtonType(this, TileUltimateImporter.TYPE));

        addSideButton(new SideButtonMode(this, TileUltimateImporter.MODE));

        addSideButton(new SideButtonCompare(this, TileUltimateImporter.COMPARE, IComparer.COMPARE_DAMAGE));
        addSideButton(new SideButtonCompare(this, TileUltimateImporter.COMPARE, IComparer.COMPARE_NBT));
    }

    @Override
    public void update(int x, int y) {
        //NO OP
    }

    @Override
    public void drawBackground(int x, int y, int mouseX, int mouseY) {
        bindTexture("gui/ultimate_exporter_importer.png");

        drawTexture(x, y, 0, 0, screenWidth, screenHeight);
    }

    @Override
    public void drawForeground(int mouseX, int mouseY) {
        drawString(7, 7, t("gui.refinedstorage:ultimate_importer"));
        drawString(7, 43 + 18 * 5, t("container.inventory"));
    }
}
