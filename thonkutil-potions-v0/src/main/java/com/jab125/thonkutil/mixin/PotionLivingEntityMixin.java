package com.jab125.thonkutil.mixin;

import com.jab125.thonkutil.impl.Potionable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.potion.PotionUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class PotionLivingEntityMixin {
    @Shadow
    public abstract boolean addStatusEffect(StatusEffectInstance effect);

    @Inject(method = "damage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;isSleeping()Z"))
    private void thonkutil$potionableItemInject(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (!(source.getAttacker() instanceof LivingEntity livingEntity)) return;
        if (!(livingEntity.getMainHandStack().getItem() instanceof Potionable potionableItem)) return;
        for (StatusEffectInstance potionEffect : PotionUtil.getPotionEffects(livingEntity.getMainHandStack())) {
            this.addStatusEffect(new StatusEffectInstance(potionEffect));
        }
    }
}
