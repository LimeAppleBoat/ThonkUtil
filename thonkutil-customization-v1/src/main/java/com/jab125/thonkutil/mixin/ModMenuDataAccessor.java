package com.jab125.thonkutil.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.gen.Invoker;

@Pseudo
@Mixin(targets = "com.terraformersmc.modmenu.util.mod.fabric.FabricMod$ModMenuData")
public interface ModMenuDataAccessor {
    @Invoker("fillParentIfEmpty")
    void callFillParentIfEmpty(String string);
}
