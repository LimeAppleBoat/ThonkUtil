package com.jab125.thonkutil.config;

import com.jab125.thonkutil.gui.ThonkUtilTradesOptionsScreen;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

public class ThonkUtilTradesModMenuCompat implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return ThonkUtilTradesOptionsScreen::new;
    }
}
