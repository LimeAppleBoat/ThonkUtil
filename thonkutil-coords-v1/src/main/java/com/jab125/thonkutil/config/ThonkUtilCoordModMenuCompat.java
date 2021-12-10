package com.jab125.thonkutil.config;

import com.jab125.thonkutil.gui.ThonkUtilCoordOptionsScreen;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

public class ThonkUtilCoordModMenuCompat implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return ThonkUtilCoordOptionsScreen::new;
    }
}
