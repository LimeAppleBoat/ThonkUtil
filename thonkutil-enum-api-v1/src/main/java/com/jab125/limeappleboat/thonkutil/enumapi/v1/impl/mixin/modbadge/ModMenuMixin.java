package com.jab125.limeappleboat.thonkutil.enumapi.v1.impl.mixin.modbadge;

import com.jab125.limeappleboat.thonkutil.enumapi.v1.api.ModBadgeCreator;
import com.terraformersmc.modmenu.ModMenu;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ModMenu.class, remap = false)
public class ModMenuMixin {
    @Inject(method = "onInitializeClient", at = @At("RETURN"))
    private void thonkutil$inject(CallbackInfo ci) {
        var c = ModBadgeCreator.create(new Identifier("thonkutil", "asm"), 0x77563833, 0x39F5292a, null);

        ModMenu.MODS.get("thonkutil").getBadges().add(c);
    }
}
