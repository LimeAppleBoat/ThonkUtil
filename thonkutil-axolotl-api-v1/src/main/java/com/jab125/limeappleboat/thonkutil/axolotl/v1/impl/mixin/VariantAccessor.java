package com.jab125.limeappleboat.thonkutil.axolotl.v1.impl.mixin;

import net.minecraft.entity.passive.AxolotlEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(AxolotlEntity.Variant.class)
public interface VariantAccessor {
    @Mutable
    @Accessor
    static void setVARIANTS(AxolotlEntity.Variant[] VARIANTS) {
        throw new UnsupportedOperationException();
    }
}
