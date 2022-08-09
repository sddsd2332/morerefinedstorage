package com.raoulvdberge.refinedstorage.gui.advancedCrafter;

import com.raoulvdberge.refinedstorage.container.advancedCrafter.ContainerGoldCrafter;
import com.raoulvdberge.refinedstorage.gui.GuiBase;
import com.raoulvdberge.refinedstorage.tile.advancedCrafter.TileGoldCrafter;
import com.raoulvdberge.refinedstorage.util.RenderUtils;

public class GuiGoldCrafter extends GuiBase {
    public GuiGoldCrafter(ContainerGoldCrafter container) {
        super(container, 211, 211);
    }

    @Override
    public void init(int x, int y) {
    }

    @Override
    public void update(int x, int y) {
    }

    @Override
    public void drawBackground(int x, int y, int mouseX, int mouseY) {
        bindTexture("gui/goldcrafter.png");

        drawTexture(x, y, 0, 0, screenWidth, screenHeight);
    }

    @Override
    public void drawForeground(int mouseX, int mouseY) {
        drawString(7, 7, RenderUtils.shorten(t(TileGoldCrafter.NAME.getValue()), 26));
        drawString(7, 43 + (18*4), t("container.inventory"));
    }
}
