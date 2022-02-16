package com.jab125.thonkutil.titlescreen.v1.mixin;

import com.jab125.thonkutil.titlescreen.v1.config.ThonkUtilTitleScreenConfig;
import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(value = TitleScreen.class, priority = Integer.MAX_VALUE)
public class TitleScreenMixin {
//    @ModifyArg(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/Screen;init(Lnet/minecraft/client/MinecraftClient;II)V"), method = "init", index = 2)
//    private int thonkUtil$interceptModMenu(int width) {
//        return width;
//    }

    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/TitleScreen;drawStringWithShadow(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/font/TextRenderer;Ljava/lang/String;III)V", ordinal = 0))
    private String thonkutil$hijackString(String string) {
        if (!ThonkUtilTitleScreenConfig.MODIFY_TITLE_SCREEN.getValue()) return string;
        return "";
    }
}
