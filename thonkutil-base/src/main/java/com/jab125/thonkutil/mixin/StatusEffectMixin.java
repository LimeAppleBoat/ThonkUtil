package com.jab125.thonkutil.mixin;

import com.jab125.thonkutil.impl.RegistryUtil;
import net.minecraft.entity.effect.StatusEffect;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(StatusEffect.class)
public class StatusEffectMixin implements RegistryUtil {
}
