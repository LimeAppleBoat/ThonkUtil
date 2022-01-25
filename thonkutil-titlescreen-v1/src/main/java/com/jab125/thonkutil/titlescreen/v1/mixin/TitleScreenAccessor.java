package com.jab125.thonkutil.titlescreen.v1.mixin;

import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(TitleScreen.class)
public interface TitleScreenAccessor {
    @Accessor
    boolean isDoBackgroundFade();

    @Accessor
    long getBackgroundFadeStart();
}
