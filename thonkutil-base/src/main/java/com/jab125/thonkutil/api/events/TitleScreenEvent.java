package com.jab125.thonkutil.api.events;

import com.jab125.thonkutil.api.events.client.ScreenEvent;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;

public class TitleScreenEvent extends ScreenEvent {
    public TitleScreenEvent(Screen screen, MinecraftClient client, int scaledWidth, int scaledHeight) {
        super(screen, client, scaledWidth, scaledHeight);
    }
}
