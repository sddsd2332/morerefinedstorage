package com.raoulvdberge.refinedstorage.gui.advancedCrafter;

import com.raoulvdberge.refinedstorage.container.advancedCrafter.ContainerEmeraldCrafter;
import com.raoulvdberge.refinedstorage.gui.GuiBase;
import com.raoulvdberge.refinedstorage.tile.advancedCrafter.TileEmeraldCrafter;
import com.raoulvdberge.refinedstorage.util.RenderUtils;

public class GuiEmeraldCrafter extends GuiBase {
    public GuiEmeraldCrafter(ContainerEmeraldCrafter container) {

        super(container, 211, 281);

    }

    @Override
    public void init(int x, int y) {
    }

    @Override
    public void update(int x, int y) {
    }

    @Override
    public void drawBackground(int x, int y, int mouseX, int mouseY) {
        bindTexture("gui/emeraldcrafter.png");
        drawModalRectWithCustomSizedTexture(x, y, 0, 0, screenWidth, screenHeight,256,281);
    }

    @Override
    public void drawForeground(int mouseX, int mouseY) {
        drawString(7, 7, RenderUtils.shorten(t(TileEmeraldCrafter.NAME.getValue()), 26));
        drawString(7, 43 + (18*8), t("container.inventory"));
    }
}
