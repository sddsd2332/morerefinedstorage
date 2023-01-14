package com.raoulvdberge.refinedstorage.gui.advancedExporter;

import com.raoulvdberge.refinedstorage.api.util.IComparer;
import com.raoulvdberge.refinedstorage.container.advancedExporter.ContainerCreativeExporter;
import com.raoulvdberge.refinedstorage.gui.GuiBase;
import com.raoulvdberge.refinedstorage.gui.control.SideButtonCompare;
import com.raoulvdberge.refinedstorage.gui.control.SideButtonRedstoneMode;
import com.raoulvdberge.refinedstorage.gui.control.SideButtonType;
import com.raoulvdberge.refinedstorage.tile.advancedExporter.TileCreativeExporter;

public class GuiCreativeExporter extends GuiBase {

    public GuiCreativeExporter(ContainerCreativeExporter container) {
        super(container, 211, 263);
    }

    @Override
    public void init(int x, int y) {
        addSideButton(new SideButtonRedstoneMode(this, TileCreativeExporter.REDSTONE_MODE));

        addSideButton(new SideButtonType(this, TileCreativeExporter.TYPE));

        addSideButton(new SideButtonCompare(this, TileCreativeExporter.COMPARE, IComparer.COMPARE_DAMAGE));
        addSideButton(new SideButtonCompare(this, TileCreativeExporter.COMPARE, IComparer.COMPARE_NBT));
    }

    @Override
    public void update(int x, int y) {
        //NO OP
    }

    @Override
    public void drawBackground(int x, int y, int mouseX, int mouseY) {
        bindTexture("gui/creative_exporter_importer.png");

        drawModalRectWithCustomSizedTexture(x, y, 0, 0, screenWidth, screenHeight,256,263);
    }

    @Override
    public void drawForeground(int mouseX, int mouseY) {
        drawString(7, 7, t("gui.refinedstorage:creative_exporter"));
        drawString(7, 43 + 18 * 7, t("container.inventory"));
    }
}
