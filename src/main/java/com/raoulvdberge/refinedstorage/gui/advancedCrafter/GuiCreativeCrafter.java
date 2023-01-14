package com.raoulvdberge.refinedstorage.gui.advancedCrafter;

import com.raoulvdberge.refinedstorage.container.advancedCrafter.ContainerCreativeCrafter;
import com.raoulvdberge.refinedstorage.gui.GuiBase;
import com.raoulvdberge.refinedstorage.tile.advancedCrafter.TileCreativeCrafter;
import com.raoulvdberge.refinedstorage.util.RenderUtils;

public class GuiCreativeCrafter extends GuiBase {
    public GuiCreativeCrafter(ContainerCreativeCrafter container) {

        super(container, 211, 335);

    }

    @Override
    public void init(int x, int y) {
    }

    @Override
    public void update(int x, int y) {
    }

    @Override
    public void drawBackground(int x, int y, int mouseX, int mouseY) {
        bindTexture("gui/creativecrafter.png");
        drawModalRectWithCustomSizedTexture(x, y, 0, 0, screenWidth, screenHeight,256,335);
    }

    @Override
    public void drawForeground(int mouseX, int mouseY) {
        drawString(7, 7, RenderUtils.shorten(t(TileCreativeCrafter.NAME.getValue()), 26));
        drawString(7, 43 + (18*11), t("container.inventory"));
    }
}
