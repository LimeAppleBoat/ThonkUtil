package com.jab125.thonkutil.config;

import com.jab125.thonkutil.gui.ThonkUtilOptionsScreen;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

public class ModMenuCompat implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return ThonkUtilOptionsScreen::new;
    }
}
