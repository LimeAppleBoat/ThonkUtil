package com.jab125.thonkutil;

import com.jab125.thonkutil.gui.ThonkUtilOptionsScreen;
import com.jab125.thonkutil.gui.ThonkUtilTradesOptionsScreen;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.text.LiteralText;

public class ThonkUtilTradesClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ThonkUtilOptionsScreen.optionList.add(ThonkUtilOptionsScreen.createOption(new LiteralText("Trades"), new ThonkUtilTradesOptionsScreen(null)));
    }
}
