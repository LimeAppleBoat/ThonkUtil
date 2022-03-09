package com.jab125.thonkutil.api.events.client.screen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;

@Environment(EnvType.CLIENT)
public class TitleScreenEvent extends ScreenEvent {
    public TitleScreenEvent(Screen screen, MinecraftClient client, int scaledWidth, int scaledHeight) {
        super(screen, client, scaledWidth, scaledHeight);
    }
}
