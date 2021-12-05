package com.jab125.thonkutil.mixin;

import com.jab125.thonkutil.config.ThonkUtilConfig;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;setPosition(DDD)V"))
    private void redirect(PlayerEntity playerEntity, double x, double y, double z) {
        if (ThonkUtilConfig.UNLIMITED_TRAVEL.getValue()) playerEntity.setPosition(x, playerEntity.getY(), z);
    }
}
