package com.raoulvdberge.refinedstorage.gui.advancedExporter;

import com.raoulvdberge.refinedstorage.api.util.IComparer;
import com.raoulvdberge.refinedstorage.container.advancedExporter.ContainerAdvancedExporter;
import com.raoulvdberge.refinedstorage.gui.GuiBase;
import com.raoulvdberge.refinedstorage.gui.control.SideButtonCompare;
import com.raoulvdberge.refinedstorage.gui.control.SideButtonRedstoneMode;
import com.raoulvdberge.refinedstorage.gui.control.SideButtonType;
import com.raoulvdberge.refinedstorage.tile.advancedExporter.TileAdvancedExporter;

public class GuiAdvancedExporter extends GuiBase {

    public GuiAdvancedExporter(ContainerAdvancedExporter container) {
        super(container, 211, 155);
    }

    @Override
    public void init(int x, int y) {
        addSideButton(new SideButtonRedstoneMode(this, TileAdvancedExporter.REDSTONE_MODE));

        addSideButton(new SideButtonType(this, TileAdvancedExporter.TYPE));

        addSideButton(new SideButtonCompare(this, TileAdvancedExporter.COMPARE, IComparer.COMPARE_DAMAGE));
        addSideButton(new SideButtonCompare(this, TileAdvancedExporter.COMPARE, IComparer.COMPARE_NBT));
    }

    @Override
    public void update(int x, int y) {
        //NO OP
    }

    @Override
    public void drawBackground(int x, int y, int mouseX, int mouseY) {
        bindTexture("gui/advanced_exporter_importer.png");

        drawTexture(x, y, 0, 0, screenWidth, screenHeight);
    }

    @Override
    public void drawForeground(int mouseX, int mouseY) {
        drawString(7, 7, t("gui.refinedstorage:advanced_exporter"));
        drawString(7, 43 + 18 , t("container.inventory"));
    }
}
