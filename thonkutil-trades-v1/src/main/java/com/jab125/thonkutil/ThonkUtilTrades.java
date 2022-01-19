package com.jab125.thonkutil;

import com.jab125.thonkutil.config.ThonkUtilTradeConfigManager;
import com.jab125.thonkutil.gui.ThonkUtilOptionsScreen;
import com.jab125.thonkutil.gui.ThonkUtilTradesOptionsScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.LiteralText;

public class ThonkUtilTrades implements ModInitializer {
    @Override
    public void onInitialize() {
        ThonkUtilTradeConfigManager.initializeConfig();
    }
}
