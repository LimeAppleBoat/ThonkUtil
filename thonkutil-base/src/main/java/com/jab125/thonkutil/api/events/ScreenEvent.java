package com.jab125.thonkutil.api.events;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;

@Environment(EnvType.CLIENT)
public class ScreenEvent extends EventTaxiEvent {
    private final Screen screen;
    private final MinecraftClient client;
    private final int scaledWidth;
    private final int scaledHeight;
    public ScreenEvent(Screen screen, MinecraftClient client, int scaledWidth, int scaledHeight) {
        this.client = client;
        this.screen = screen;
        this.scaledHeight = scaledHeight;
        this.scaledWidth = scaledWidth;
    }

    public MinecraftClient getClient() {
        return client;
    }

    public Screen getScreen() {
        return screen;
    }

    public int getScaledHeight() {
        return scaledHeight;
    }

    public int getScaledWidth() {
        return scaledWidth;
    }
}
