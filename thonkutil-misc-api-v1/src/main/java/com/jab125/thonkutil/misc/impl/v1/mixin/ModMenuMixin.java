package com.jab125.thonkutil.misc.impl.v1.mixin;

import com.jab125.thonkutil.misc.api.v1.ModMenuInitialization;
import com.terraformersmc.modmenu.ModMenu;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Pseudo
@Mixin(value = ModMenu.class, remap = false)
public class ModMenuMixin {
    @Inject(method = "onInitializeClient", at = @At("HEAD"))
    private void thonkutil$beforeInitializeClient(CallbackInfo ci) {
        FabricLoader.getInstance().getEntrypoints("thonkutil:modmenu_initialization", ModMenuInitialization.class).forEach(ModMenuInitialization::beforeModMenuInitialized);
    }
    @Inject(method = "onInitializeClient", at = @At("RETURN"))
    private void thonkutil$afterInitializeClient(CallbackInfo ci) {
        FabricLoader.getInstance().getEntrypoints("thonkutil:modmenu_initialization", ModMenuInitialization.class).forEach(ModMenuInitialization::afterModMenuInitialized);
    }
}
