package com.raoulvdberge.refinedstorage.gui;

import com.raoulvdberge.refinedstorage.gui.advancedCrafter.GuiGoldCrafter;
import com.raoulvdberge.refinedstorage.gui.control.SideButtonCrafterMode2;
import com.raoulvdberge.refinedstorage.tile.data.TileDataParameterClientListener;

public class TileDataParameterClientListenerCrafter2 implements TileDataParameterClientListener<Boolean> {
    @Override
    public void onChanged(boolean initial, Boolean hasRoot) {
        if (!hasRoot) {
            GuiBase.executeLater(GuiGoldCrafter.class, gui -> gui.addSideButton(new SideButtonCrafterMode2(gui)));
        }
    }
}
