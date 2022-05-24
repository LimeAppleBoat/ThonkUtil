/*
 * Copyright (c) 2021, 2022 Jab125 & LimeAppleBoat
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
            var duration = Math.max(potionEffect.getDuration() * potionableItem.potionLengthMultiplier(), 1);
            var potionEffecc = new StatusEffectInstance(potionEffect);
            potionEffecc.duration = (int) duration;
            this.addStatusEffect(potionEffecc);
        }
    }
}
