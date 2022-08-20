package com.jab125.limeappleboat.thonkutil.enumapi.v1.impl.mixin;

import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.yggdrasil.YggdrasilUserApiService;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = YggdrasilUserApiService.class, remap = false)
public class YggdrasilUserApiServiceMixin {
    @Inject(method = "fetchProperties", at = @At("HEAD"), cancellable = true)
    public void thonkutil$fetchProperties(CallbackInfo ci) throws AuthenticationException {
        // SHUT UP
        System.out.println("Successfully deleted the auth system");
        ci.cancel();
    }
}
