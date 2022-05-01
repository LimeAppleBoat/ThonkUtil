package com.jab125.thonkutil.mixin;

import com.jab125.thonkutil.impl.PlayerExpansion;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.feature.CapeFeatureRenderer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = AbstractClientPlayerEntity.class, priority = 2095808222)
public abstract class CapeQMixin implements PlayerExpansion {
    @Inject(method = "getCapeTexture", at = @At("HEAD"), cancellable = true)
    private void thonkutil$getCapeTexture(CallbackInfoReturnable<Identifier> cir) {
        if (this.thonkutil$getCosmetic().equals("blanketcon_cape")) {
            cir.setReturnValue(Identifier.tryParse("thonkutil" + ":textures/cape/" + "blanketcon_cape" + ".png"));
        } else if (this.thonkutil$getCosmetic().equals("grifferthrydwy_cape")) {
            cir.setReturnValue(Identifier.tryParse("thonkutil" + ":textures/cape/" + "grifferthrydwy_cape" + ".png"));
        }
    }
}
