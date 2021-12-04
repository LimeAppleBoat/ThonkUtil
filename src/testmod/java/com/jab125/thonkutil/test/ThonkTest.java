package com.jab125.thonkutil.test;

import net.fabricmc.api.ModInitializer;

public class ThonkTest implements ModInitializer {
    /**
     * Runs the mod initializer.
     */
    @Override
    public void onInitialize() {
        TestConfigManager.initializeConfig();
    }
}
