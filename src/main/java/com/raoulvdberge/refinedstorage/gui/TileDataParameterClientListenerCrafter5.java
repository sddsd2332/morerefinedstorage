package com.raoulvdberge.refinedstorage.gui;

import com.raoulvdberge.refinedstorage.gui.advancedCrafter.GuiCreativeCrafter;
import com.raoulvdberge.refinedstorage.gui.control.SideButtonCrafterMode5;
import com.raoulvdberge.refinedstorage.tile.data.TileDataParameterClientListener;

public class TileDataParameterClientListenerCrafter5 implements TileDataParameterClientListener<Boolean> {
    @Override
    public void onChanged(boolean initial, Boolean hasRoot) {
        if (!hasRoot) {
            GuiBase.executeLater(GuiCreativeCrafter.class, gui -> gui.addSideButton(new SideButtonCrafterMode5(gui)));
        }
    }
}
