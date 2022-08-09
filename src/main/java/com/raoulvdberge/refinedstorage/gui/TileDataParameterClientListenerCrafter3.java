package com.raoulvdberge.refinedstorage.gui;

import com.raoulvdberge.refinedstorage.gui.advancedCrafter.GuiDiamondCrafter;
import com.raoulvdberge.refinedstorage.gui.control.SideButtonCrafterMode3;
import com.raoulvdberge.refinedstorage.tile.data.TileDataParameterClientListener;

public class TileDataParameterClientListenerCrafter3 implements TileDataParameterClientListener<Boolean> {
    @Override
    public void onChanged(boolean initial, Boolean hasRoot) {
        if (!hasRoot) {
            GuiBase.executeLater(GuiDiamondCrafter.class, gui -> gui.addSideButton(new SideButtonCrafterMode3(gui)));
        }
    }
}
