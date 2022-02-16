package com.jab125.thonkutil.api.events.client.screen;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.util.math.MatrixStack;

public class ScreenRenderEvent {
    private final Screen screen;
    private final MatrixStack matrices;
    private final int mouseX;
    private final int mouseY;
    private final float tickDelta;

    public ScreenRenderEvent(Screen screen, MatrixStack matrices, int mouseX, int mouseY, float tickDelta) {
        this.screen = screen;
        this.matrices = matrices;
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        this.tickDelta = tickDelta;
    }

    public Screen screen() {
        return screen;
    }

    public MatrixStack matrices() {
        return matrices;
    }

    public int mouseX() {
        return mouseX;
    }

    public int mouseY() {
        return mouseY;
    }

    public float tickDelta() {
        return tickDelta;
    }
}
