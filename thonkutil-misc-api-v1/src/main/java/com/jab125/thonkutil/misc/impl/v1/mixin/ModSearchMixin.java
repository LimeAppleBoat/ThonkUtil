package com.jab125.thonkutil.misc.impl.v1.mixin;

import com.jab125.thonkutil.misc.api.v1.CustomModBadge;
import com.terraformersmc.modmenu.gui.ModsScreen;
import com.terraformersmc.modmenu.util.mod.Mod;
import com.terraformersmc.modmenu.util.mod.ModSearch;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.resource.language.I18n;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import oshi.util.tuples.Pair;

@Environment(EnvType.CLIENT)
@Pseudo
@Mixin(value = ModSearch.class, remap = false)
public class ModSearchMixin {

    @Inject(method = "passesFilters", at = @At(value = "RETURN"), cancellable = true)
    private static void thonkutil$passesFilters(ModsScreen screen, Mod mod, String query, CallbackInfoReturnable<Boolean> cir) {
        for (Pair<String, Mod.Badge> s : CustomModBadge.getTranslatable()) {
            //System.out.println(I18n.translate(s.getA()) + ", " + query);
            if (I18n.translate(s.getA()).contains(query) && mod.getBadges().contains(s.getB())) {
                cir.setReturnValue(true);
            }
        }
    }
}
