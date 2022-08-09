package com.raoulvdberge.refinedstorage.gui.control;

import com.raoulvdberge.refinedstorage.gui.GuiBase;
import com.raoulvdberge.refinedstorage.tile.advancedCrafter.*;
import com.raoulvdberge.refinedstorage.tile.data.TileDataManager;
import net.minecraft.util.text.TextFormatting;

public class SideButtonCrafterMode1 extends SideButton {
    public SideButtonCrafterMode1(GuiBase gui) {
        super(gui);
    }


    @Override
    public String getTooltip() {
        return GuiBase.t("sidebutton.refinedstorage:crafter_mode") + "\n" + TextFormatting.GRAY + GuiBase.t("sidebutton.refinedstorage:crafter_mode." + TileIronCrafter.MODE.getValue());
    }

    @Override
    protected void drawButtonIcon(int x, int y) {
        gui.drawTexture(x, y, TileIronCrafter.MODE.getValue() * 16, 0, 16, 16);
    }

    @Override
    public void actionPerformed() {
        TileDataManager.setParameter(TileIronCrafter.MODE, TileIronCrafter.MODE.getValue() + 1);
    }
}
