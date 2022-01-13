package com.jab125.thonkutil.config;

import com.google.common.collect.ImmutableMap;
import com.jab125.thonkutil.gui.ThonkUtilOptionsScreen;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

import java.util.Map;

public class ThonkUtilModMenuCompat implements ModMenuApi {
    @Override
    public Map<String, ConfigScreenFactory<?>> getProvidedConfigScreenFactories() {
        return ImmutableMap.of("thonkutil", ThonkUtilOptionsScreen::new);
    }
}
