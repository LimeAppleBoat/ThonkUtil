package com.jab125.limeappleboat.thonkutil.axolotl.v1.impl.mixin;

import com.jab125.limeappleboat.thonkutil.axolotl.v1.impl.AxolotlTypeExtension;
import com.jab125.limeappleboat.thonkutil.axolotl.v1.impl.ThonkUtilAxolotlVariant;
import net.minecraft.client.render.entity.AxolotlEntityRenderer;
import net.minecraft.entity.passive.AxolotlEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import java.util.Locale;

@Mixin(AxolotlEntityRenderer.class)
public class AxolotlEntityRendererMixin {
    @ModifyArgs(method = "method_33307", at = @At(value = "INVOKE", target = "Ljava/util/HashMap;put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"))
    private static void thonkutil$method_33307(Args args) {
        AxolotlEntity.Variant variant = args.get(0);
        ThonkUtilAxolotlVariant metadata = ((AxolotlTypeExtension)(Object)variant).thonkutil$thonkutil_metadata();
        if (metadata.isCustom()) {
            Identifier newType = new Identifier(metadata.getId().getNamespace(), String.format(Locale.ROOT, "textures/entity/axolotl/axolotl_%s.png", metadata.getId().getPath()));
            args.set(1, newType);
        }
    }

    @Inject(method = "<clinit>", at = @At("HEAD"))
    private static void thonkutil$clinit(CallbackInfo ci) {
        ThonkUtilAxolotlVariant.p = true;
    }
    @Inject(method = "<clinit>", at = @At("RETURN"))
    private static void thonkutil$clinit2(CallbackInfo ci) {
        ThonkUtilAxolotlVariant.p = false;
    }
}
