package com.raoulvdberge.refinedstorage.gui.advancedImporter;

import com.raoulvdberge.refinedstorage.api.util.IComparer;
import com.raoulvdberge.refinedstorage.container.advancedImporter.ContainerAdvancedImporter;
import com.raoulvdberge.refinedstorage.gui.GuiBase;
import com.raoulvdberge.refinedstorage.gui.control.SideButtonCompare;
import com.raoulvdberge.refinedstorage.gui.control.SideButtonMode;
import com.raoulvdberge.refinedstorage.gui.control.SideButtonRedstoneMode;
import com.raoulvdberge.refinedstorage.gui.control.SideButtonType;
import com.raoulvdberge.refinedstorage.tile.advancedImporter.TileAdvancedImporter;

public class GuiAdvancedImporter extends GuiBase {
    public GuiAdvancedImporter(ContainerAdvancedImporter container) {
        super(container, 211, 155);
    }

    @Override
    public void init(int x, int y) {
        addSideButton(new SideButtonRedstoneMode(this, TileAdvancedImporter.REDSTONE_MODE));

        addSideButton(new SideButtonType(this, TileAdvancedImporter.TYPE));

        addSideButton(new SideButtonMode(this, TileAdvancedImporter.MODE));

        addSideButton(new SideButtonCompare(this, TileAdvancedImporter.COMPARE, IComparer.COMPARE_DAMAGE));
        addSideButton(new SideButtonCompare(this, TileAdvancedImporter.COMPARE, IComparer.COMPARE_NBT));
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
        drawString(7, 7, t("gui.refinedstorage:advanced_importer"));
        drawString(7, 61, t("container.inventory"));
    }
}
