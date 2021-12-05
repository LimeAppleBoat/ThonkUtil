package com.jab125.thonkutil.mixin;

import com.jab125.thonkutil.config.ThonkUtilConfig;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Entity.class)
public class EntityMixin {
    @Redirect(method = "updatePosition", at = @At(target = "Lnet/minecraft/util/math/MathHelper;clamp(DDD)D", value = "INVOKE"))
    private double allowGoBrrrr(double value, double min, double max) {
        if (ThonkUtilConfig.UNLIMITED_TRAVEL.getValue()) return MathHelper.clamp(value, min, max);
        return value;
    }
}
