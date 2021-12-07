package com.jab125.thonkutil.mixin;

import com.jab125.thonkutil.config.ThonkUtilConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
@Environment(EnvType.CLIENT)
public class KeyboardMixin {
    @Shadow @Final private MinecraftClient client;

    @Inject(method = "onKey", at = @At(value = "RETURN"))
    private void thonkKeyPressMixin(long window, int key, int scancode, int action, int modifiers, CallbackInfo ci) {
        if (this.client.currentScreen == null || this.client.currentScreen.passEvents) {
            InputUtil.Key bl = InputUtil.fromKeyCode(key, scancode);
            if (action == 0) {
                KeyBinding.setKeyPressed(bl, false);
                if (key == 292) {
                    ThonkUtilConfig.THONKUTIL_DEBUG.toggleValue();
                    if (ThonkUtilConfig.THONKUTIL_DEBUG.getValue()) this.client.options.debugEnabled = false;
                }
            }
            System.out.println("KEY:" + key);
        }
    }
    @ModifyVariable(method = "onKey", at = @At(value = "STORE", target = "Lnet/minecraft/client/option/GameOptions;debugEnabled:Z"))
    private boolean allowMeToHijackThis(boolean b) {
        if (b) ThonkUtilConfig.THONKUTIL_DEBUG.setValue(false);
        return b;
    }
}
