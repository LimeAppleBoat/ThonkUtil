package com.jab125.thonkutil;

import com.jab125.thonkutil.config.ThonkUtilCustomizationConfigManager;
import net.fabricmc.api.ModInitializer;

public class ThonkUtilCustomization implements ModInitializer {
    /**
     * Runs the mod initializer.
     */
    @Override
    public void onInitialize() {
        ThonkUtilCustomizationConfigManager.initializeConfig();
    }
}
