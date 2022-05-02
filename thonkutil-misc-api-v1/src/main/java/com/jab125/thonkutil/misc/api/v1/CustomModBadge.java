package com.jab125.thonkutil.misc.api.v1;

import com.jab125.thonkutil.misc.asm.creation.CustomBoatType2;
import com.jab125.thonkutil.misc.asm.creation.CustomModBadge2;
import com.terraformersmc.modmenu.util.mod.Mod;
import com.terraformersmc.modmenu.util.mod.ModSearch;
import oshi.util.tuples.Pair;

import java.util.ArrayList;
import java.util.Arrays;

public class CustomModBadge {
    private static final ArrayList<Pair<String, Mod.Badge>> translatable = new ArrayList<>();
    //private static final ArrayList<Pair<String, CustomModBadge2>> translatable = new ArrayList<>();

//    public static ArrayList<Pair<String, CustomModBadge2>> getTranslatable() {
//        if (!StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).getCallerClass().equals(ModSearch.class)) throw new IllegalStateException(StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).getCallerClass() + " shouldn't call this!");
//        return translatable;
//    }

    public static ArrayList<Pair<String, Mod.Badge>> getTranslatable() {
        if (!StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).getCallerClass().equals(ModSearch.class)) throw new IllegalStateException(StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).getCallerClass() + " shouldn't call this!");
        return translatable;
    }

        public static Mod.Badge create(String internalName, String translationKey, int outlineColor, int fillColor, String key) {
        throw new IllegalStateException("This shouldn't happen!");
    }

    public static Mod.Badge create(String internalName, String translationKey, int outlineColor, int fillColor, String key, String searchKey) {
        var type = create(internalName, translationKey, outlineColor, fillColor, key);
        translatable.add(new Pair<>(searchKey, type));
        return type;
    }

//    public static CustomModBadge2 create(String internalName, String translationKey, int outlineColor, int fillColor, String key) {
//        var types = new ArrayList<>(Arrays.asList(CustomModBadge2.$VALUES));
//        var last = types.get(types.size()-1);
//        var type = new CustomModBadge2(internalName, last.ordinal() + 1, translationKey, outlineColor, fillColor, key);
//        types.add(type);
//        CustomModBadge2.$VALUES = types.toArray(new CustomModBadge2[0]);
//        CustomModBadge2.lambda$static$0(type);
//        return type;
//    }
//
//    public static CustomModBadge2 create(String internalName, String translationKey, int outlineColor, int fillColor, String key, String searchKey) {
//        var type = create(internalName, translationKey, outlineColor, fillColor, key);
//        translatable.add(new Pair<>(searchKey, type));
//        return type;
//    }
}
