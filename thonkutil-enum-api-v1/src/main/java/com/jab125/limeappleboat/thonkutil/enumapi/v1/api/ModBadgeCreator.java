package com.jab125.limeappleboat.thonkutil.enumapi.v1.api;

import com.terraformersmc.modmenu.util.mod.Mod;
import net.minecraft.util.Identifier;

public class ModBadgeCreator {
    public static Mod.Badge create(Identifier identifier, int outlineColor, int fillColor, String key) {
        var ordinal = Mod.Badge.values()[Mod.Badge.values().length-1].ordinal()+1;
        return createInternal(identifier.toString(), ordinal, identifier.getNamespace() + ".badge." + identifier.getPath(), outlineColor, fillColor, key);
    }

    private static Mod.Badge createInternal(String var0, int var1, String translationKey, int outlineColor, int fillColor, String key) {
        throw new AssertionError();
    }
}
