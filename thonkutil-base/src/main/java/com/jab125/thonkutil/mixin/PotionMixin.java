package com.jab125.thonkutil.mixin;

import com.jab125.thonkutil.impl.RegistryUtil;
import net.minecraft.potion.Potion;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Potion.class)
public class PotionMixin implements RegistryUtil {
}
