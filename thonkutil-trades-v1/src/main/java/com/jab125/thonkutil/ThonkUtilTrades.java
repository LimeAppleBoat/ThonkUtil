package com.jab125.thonkutil;

import com.jab125.thonkutil.config.ThonkUtilTradeConfigManager;
import net.fabricmc.api.ModInitializer;

public class ThonkUtilTrades implements ModInitializer {
    @Override
    public void onInitialize() {
        ThonkUtilTradeConfigManager.initializeConfig();
    }
}
