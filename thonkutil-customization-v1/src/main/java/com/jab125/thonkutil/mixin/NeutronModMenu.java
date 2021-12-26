package com.jab125.thonkutil.mixin;

import com.terraformersmc.modmenu.util.mod.Mod;
import com.terraformersmc.modmenu.util.mod.fabric.FabricMod;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.metadata.ModMetadata;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@Mixin(FabricMod.class)
public abstract class NeutronModMenu {

    @Shadow @Final private ModMetadata metadata;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void injectInit(ModContainer t, CallbackInfo ci) {
        if (metadata.getId().equals("thonkutil-customization-v1")) {
            ((FabricMod)(Object)this).getBadges().add(Mod.Badge.DEPRECATED);
        }
    }
}
