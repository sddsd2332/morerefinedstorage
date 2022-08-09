package com.raoulvdberge.refinedstorage.gui.advancedCrafter;

import com.raoulvdberge.refinedstorage.container.advancedCrafter.ContainerDiamondCrafter;
import com.raoulvdberge.refinedstorage.gui.GuiBase;
import com.raoulvdberge.refinedstorage.tile.advancedCrafter.TileDiamondCrafter;
import com.raoulvdberge.refinedstorage.util.RenderUtils;

public class GuiDiamondCrafter extends GuiBase {
    public GuiDiamondCrafter(ContainerDiamondCrafter container) {
        super(container, 211, 246);
    }

    @Override
    public void init(int x, int y) {
    }

    @Override
    public void update(int x, int y) {
    }

    @Override
    public void drawBackground(int x, int y, int mouseX, int mouseY) {
        bindTexture("gui/diamondcrafter.png");

        drawTexture(x, y, 0, 0, screenWidth, screenHeight);
    }

    @Override
    public void drawForeground(int mouseX, int mouseY) {
        drawString(7, 7, RenderUtils.shorten(t(TileDiamondCrafter.NAME.getValue()), 26));
        drawString(7, 43 + (18*6), t("container.inventory"));
    }
}
