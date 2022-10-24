package com.jab125.limeappleboat.thonkutil.axolotl.testmod.mixin;

import net.minecraft.client.render.entity.AxolotlEntityRenderer;
import net.minecraft.entity.passive.AxolotlEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(AxolotlEntityRenderer.class)
public interface AxolotlEntityRendererAccessor {
    @Accessor
    static Map<AxolotlEntity.Variant, Identifier> getTEXTURES() {
        throw new UnsupportedOperationException();
    }
}
