package com.jab125.thonkutil.mixin;

import com.jab125.thonkutil.config.ThonkUtilConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(net.minecraft.client.option.GameOptions.class)
public class GameOptions {

    @ModifyConstant(method = "<init>", constant = @Constant(floatValue = 32.0F), require = 0)
    public float increaseRenderDistance(float maxRenderDist) {
        return (float) ThonkUtilConfig.MAXIMUM_RENDER_DISTANCE.getValue();
    }

    @ModifyConstant(method = "<init>", constant = @Constant(floatValue = 16.0F), require = 0)
    public float increaseRenderDistance32Bit(float maxRenderDist) {
        return (float) ThonkUtilConfig.MAXIMUM_RENDER_DISTANCE.getValue();
    }
}
