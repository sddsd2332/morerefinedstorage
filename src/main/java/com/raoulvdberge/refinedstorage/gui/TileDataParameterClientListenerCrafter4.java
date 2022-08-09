package com.raoulvdberge.refinedstorage.gui;

import com.raoulvdberge.refinedstorage.gui.advancedCrafter.GuiEmeraldCrafter;
import com.raoulvdberge.refinedstorage.gui.control.SideButtonCrafterMode4;
import com.raoulvdberge.refinedstorage.tile.data.TileDataParameterClientListener;

public class TileDataParameterClientListenerCrafter4 implements TileDataParameterClientListener<Boolean> {
    @Override
    public void onChanged(boolean initial, Boolean hasRoot) {
        if (!hasRoot) {
            GuiBase.executeLater(GuiEmeraldCrafter.class, gui -> gui.addSideButton(new SideButtonCrafterMode4(gui)));
        }
    }
}
