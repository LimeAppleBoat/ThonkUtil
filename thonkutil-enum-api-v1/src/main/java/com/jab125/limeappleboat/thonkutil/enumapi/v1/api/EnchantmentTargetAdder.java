package com.jab125.limeappleboat.thonkutil.enumapi.v1.api;

import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public class EnchantmentTargetAdder {
    public static EnchantmentTarget create(Identifier identifier) {
        var ordinal = EnchantmentTarget.values()[EnchantmentTarget.values().length-1].ordinal()+1;
        return createInternal(identifier.toString(), ordinal);
    }

    private static EnchantmentTarget createInternal(String var0, int var1) {
        throw new AssertionError();
    }

    public static interface Checker {
        boolean check(Item item);
    }
}
