package com.raoulvdberge.refinedstorage.gui.advancedCrafter;

import com.raoulvdberge.refinedstorage.container.advancedCrafter.ContainerIronCrafter;
import com.raoulvdberge.refinedstorage.gui.GuiBase;
import com.raoulvdberge.refinedstorage.tile.advancedCrafter.TileIronCrafter;
import com.raoulvdberge.refinedstorage.util.RenderUtils;

public class GuiIronCrafter extends GuiBase {
    public GuiIronCrafter(ContainerIronCrafter container) {
        super(container, 211, 173);
    }

    @Override
    public void init(int x, int y) {
    }

    @Override
    public void update(int x, int y) {
    }

    @Override
    public void drawBackground(int x, int y, int mouseX, int mouseY) {
        bindTexture("gui/ironcrafter.png");

        drawTexture(x, y, 0, 0, screenWidth, screenHeight);
    }

    @Override
    public void drawForeground(int mouseX, int mouseY) {
        drawString(7, 7, RenderUtils.shorten(t(TileIronCrafter.NAME.getValue()), 26));
        drawString(7, 43 + 18 + 18, t("container.inventory"));
    }
}
