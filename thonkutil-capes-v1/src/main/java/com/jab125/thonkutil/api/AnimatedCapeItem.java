package com.jab125.thonkutil.api;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class AnimatedCapeItem extends CapeItem {
    private final int frames;
    private final int delay;
    private long nextFrameTime;
    private int currentFrame = 0;
    public AnimatedCapeItem(Settings settings, int frames, int delay) {
        this(settings, frames, delay, true, false);
    }

    public AnimatedCapeItem(Settings settings, int frames, int delay, boolean hasElytraTexture, boolean has2WingedElytra) {
        super(settings, hasElytraTexture, has2WingedElytra);
        this.frames = frames;
        this.delay = delay;
        this.setNextFrameTime(delay);
    }

    public AnimatedCapeItem(Settings settings, int frames, int delay, boolean hasElytraTexture) {
        this(settings, frames, delay, hasElytraTexture, false);
    }

    @Override
    public Identifier getCapeTexture() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis > nextFrameTime) {
            currentFrame = currentFrame == frames-1 ? 0 : currentFrame + 1;
            setNextFrameTime(delay);
        }
        return Identifier.tryParse(Registry.ITEM.getId(this).getNamespace() + ":textures/cape/"+ Registry.ITEM.getId(this).getPath() + "/" + currentFrame + ".png");
    }

    @Override
    public Identifier getElytraTexture() {
        return has2WingedElytra() ? Identifier.tryParse(Registry.ITEM.getId(this).getNamespace() + ":textures/elytra/"+ Registry.ITEM.getId(this).getPath() + "/" + currentFrame + ".png") : getCapeTexture();
    }

    private void setNextFrameTime(long nextFrameTime) {
        this.nextFrameTime = nextFrameTime + System.currentTimeMillis();
    }
}
