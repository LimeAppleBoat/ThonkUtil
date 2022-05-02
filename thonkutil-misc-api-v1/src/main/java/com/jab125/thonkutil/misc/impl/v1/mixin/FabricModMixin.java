package com.jab125.thonkutil.misc.impl.v1.mixin;

import com.jab125.thonkutil.misc.impl.v1.ThonkUtilMisc;
import com.terraformersmc.modmenu.util.mod.Mod;
import com.terraformersmc.modmenu.util.mod.fabric.FabricMod;
import net.fabricmc.loader.api.ModContainer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Set;

@Mixin(FabricMod.class)
public class FabricModMixin {
    @Shadow @Final private Set<Mod.Badge> badges;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void thonkutil$init(ModContainer modContainer, CallbackInfo cir) {
        if (modContainer.getMetadata().getId().equals("mm")) { // Hardcode badges for Fabric ASM
            this.badges.add(Mod.Badge.LIBRARY);
            this.badges.add(ThonkUtilMisc.ASM_BADGE);
        }
    }
}
