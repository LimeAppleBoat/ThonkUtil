package com.jab125.thonkutil;

import com.jab125.thonkutil.config.ThonkUtilCoordConfigManager;
import com.jab125.thonkutil.gui.ThonkUtilCoordOptionsScreen;
import com.jab125.thonkutil.gui.ThonkUtilOptionsScreen;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.text.LiteralText;

public class ThonkUtilCoord implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ThonkUtilCoordConfigManager.initializeConfig();
        ThonkUtilOptionsScreen.optionList.add(ThonkUtilOptionsScreen.createOption(new LiteralText("Coords"), new ThonkUtilCoordOptionsScreen(null)));
    }
}
