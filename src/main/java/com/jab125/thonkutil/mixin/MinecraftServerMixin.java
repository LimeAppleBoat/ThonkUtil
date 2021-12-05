package com.jab125.thonkutil.mixin;

import com.jab125.thonkutil.config.ThonkUtilConfig;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayNetworkHandler.class)
public abstract class MinecraftServerMixin {
    @Inject(method = "validateVehicleMove", at = @At("RETURN"), cancellable = true)
    private static void validateVehicleMove(CallbackInfoReturnable<Boolean> ci) {
        if (ThonkUtilConfig.UNLIMITED_TRAVEL.getValue()) return;
        ci.setReturnValue(false);
    }

    @Inject(method = "clampHorizontal", at = @At("RETURN"), cancellable = true)
    private static void clampHorizontal(double d, CallbackInfoReturnable<Double> cir) {
        if (ThonkUtilConfig.UNLIMITED_TRAVEL.getValue()) return;
        cir.setReturnValue(d);
    }

    @Inject(method = "clampVertical", at = @At("RETURN"), cancellable = true)
    private static void clampVertical(double d, CallbackInfoReturnable<Double> cir) {
        if (ThonkUtilConfig.UNLIMITED_TRAVEL.getValue()) return;
        cir.setReturnValue(d);
    }
}