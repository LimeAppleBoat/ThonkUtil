package com.jab125.limeappleboat.thonkutil.enumapi.v1.api;

import com.terraformersmc.modmenu.util.mod.Mod;
import net.minecraft.util.Identifier;
import net.minecraft.world.Difficulty;

public class DifficultyCreator {
    public static Difficulty create(Identifier identifier, int id) {
        var ordinal = Difficulty.values()[Difficulty.values().length-1].ordinal()+1;
        return createInternal(identifier.toString(), ordinal, id, identifier.toString());
    }

    private static Difficulty createInternal(String var0, int var1, int id, String name) {
        throw new AssertionError();
    }
}
