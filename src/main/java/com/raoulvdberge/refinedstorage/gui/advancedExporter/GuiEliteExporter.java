package com.raoulvdberge.refinedstorage.gui.advancedExporter;

import com.raoulvdberge.refinedstorage.api.util.IComparer;
import com.raoulvdberge.refinedstorage.container.advancedExporter.ContainerEliteExporter;
import com.raoulvdberge.refinedstorage.gui.GuiBase;
import com.raoulvdberge.refinedstorage.gui.control.SideButtonCompare;
import com.raoulvdberge.refinedstorage.gui.control.SideButtonRedstoneMode;
import com.raoulvdberge.refinedstorage.gui.control.SideButtonType;
import com.raoulvdberge.refinedstorage.tile.advancedExporter.TileEliteExporter;

public class GuiEliteExporter extends GuiBase {

    public GuiEliteExporter(ContainerEliteExporter container) {
        super(container, 211, 191);
    }

    @Override
    public void init(int x, int y) {
        addSideButton(new SideButtonRedstoneMode(this, TileEliteExporter.REDSTONE_MODE));

        addSideButton(new SideButtonType(this, TileEliteExporter.TYPE));

        addSideButton(new SideButtonCompare(this, TileEliteExporter.COMPARE, IComparer.COMPARE_DAMAGE));
        addSideButton(new SideButtonCompare(this, TileEliteExporter.COMPARE, IComparer.COMPARE_NBT));
    }

    @Override
    public void update(int x, int y) {
        //NO OP
    }

    @Override
    public void drawBackground(int x, int y, int mouseX, int mouseY) {
        bindTexture("gui/elite_exporter_importer.png");

        drawTexture(x, y, 0, 0, screenWidth, screenHeight);
    }

    @Override
    public void drawForeground(int mouseX, int mouseY) {
        drawString(7, 7, t("gui.refinedstorage:elite_exporter"));
        drawString(7, 43 + 18 * 3, t("container.inventory"));
    }
}
