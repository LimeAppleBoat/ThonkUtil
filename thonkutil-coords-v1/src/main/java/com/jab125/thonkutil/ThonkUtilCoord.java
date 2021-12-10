package com.jab125.thonkutil;

import com.jab125.thonkutil.config.ThonkUtilCoordConfigManager;
import net.fabricmc.api.ClientModInitializer;

public class ThonkUtilCoord implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ThonkUtilCoordConfigManager.initializeConfig();
    }
}
