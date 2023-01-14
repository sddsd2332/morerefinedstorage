package com.raoulvdberge.refinedstorage.gui;

import com.raoulvdberge.refinedstorage.container.ContainerWirelessDimensionTransmitter;
import com.raoulvdberge.refinedstorage.gui.control.SideButtonRedstoneMode;
import com.raoulvdberge.refinedstorage.tile.TileWirelessDimensionTransmitter;

public class GuiWirelessDimensionTransmitter extends GuiBase {
    public GuiWirelessDimensionTransmitter(ContainerWirelessDimensionTransmitter container) {
        super(container, 177, 137);
    }

    @Override
    public void init(int x, int y) {
        addSideButton(new SideButtonRedstoneMode(this, TileWirelessDimensionTransmitter.REDSTONE_MODE));
    }

    @Override
    public void update(int x, int y) {
        //NO OP
    }

    @Override
    public void drawBackground(int x, int y, int mouseX, int mouseY) {
        bindTexture("gui/wireless_transmitter_dimension.png");
        drawTexture(x, y, 0, 0, screenWidth, screenHeight);
    }

    @Override
    public void drawForeground(int mouseX, int mouseY) {
        drawString(7, 7, t("gui.refinedstorage:wireless_dimension_transmitter"));
        drawString(28, 25, t("gui.refinedstorage:wireless_dimension_transmitter.distance", TileWirelessDimensionTransmitter.RANGE.getValue()));
        drawString(7, 43, t("container.inventory"));
    }
}
