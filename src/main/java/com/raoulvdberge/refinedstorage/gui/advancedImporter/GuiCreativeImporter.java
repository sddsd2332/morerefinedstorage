package com.raoulvdberge.refinedstorage.gui.advancedImporter;

import com.raoulvdberge.refinedstorage.api.util.IComparer;
import com.raoulvdberge.refinedstorage.container.advancedImporter.ContainerCreativeImporter;
import com.raoulvdberge.refinedstorage.gui.GuiBase;
import com.raoulvdberge.refinedstorage.gui.control.SideButtonCompare;
import com.raoulvdberge.refinedstorage.gui.control.SideButtonMode;
import com.raoulvdberge.refinedstorage.gui.control.SideButtonRedstoneMode;
import com.raoulvdberge.refinedstorage.gui.control.SideButtonType;
import com.raoulvdberge.refinedstorage.tile.advancedImporter.TileCreativeImporter;

public class GuiCreativeImporter extends GuiBase {
    public GuiCreativeImporter(ContainerCreativeImporter container) {
        super(container, 211, 263);
    }

    @Override
    public void init(int x, int y) {
        addSideButton(new SideButtonRedstoneMode(this, TileCreativeImporter.REDSTONE_MODE));

        addSideButton(new SideButtonType(this, TileCreativeImporter.TYPE));

        addSideButton(new SideButtonMode(this, TileCreativeImporter.MODE));

        addSideButton(new SideButtonCompare(this, TileCreativeImporter.COMPARE, IComparer.COMPARE_DAMAGE));
        addSideButton(new SideButtonCompare(this, TileCreativeImporter.COMPARE, IComparer.COMPARE_NBT));
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
        drawString(7, 7, t("gui.refinedstorage:creative_importer"));
        drawString(7, 43 + 18 * 7, t("container.inventory"));
    }
}
