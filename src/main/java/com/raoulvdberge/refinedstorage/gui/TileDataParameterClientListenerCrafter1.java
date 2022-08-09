package com.raoulvdberge.refinedstorage.gui;

import com.raoulvdberge.refinedstorage.gui.advancedCrafter.GuiIronCrafter;
import com.raoulvdberge.refinedstorage.gui.control.SideButtonCrafterMode1;
import com.raoulvdberge.refinedstorage.tile.data.TileDataParameterClientListener;

public class TileDataParameterClientListenerCrafter1 implements TileDataParameterClientListener<Boolean> {
    @Override
    public void onChanged(boolean initial, Boolean hasRoot) {
        if (!hasRoot) {
            GuiBase.executeLater(GuiIronCrafter.class, gui -> gui.addSideButton(new SideButtonCrafterMode1(gui)));
        }
    }
}
