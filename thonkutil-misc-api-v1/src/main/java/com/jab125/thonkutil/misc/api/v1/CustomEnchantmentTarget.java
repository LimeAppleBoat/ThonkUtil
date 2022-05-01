package com.jab125.thonkutil.misc.api.v1;

import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.item.Item;

import java.util.function.Predicate;

public class CustomEnchantmentTarget {
    public static EnchantmentTarget create(String internalName, Predicate<Item> acceptable) {
        throw new IllegalStateException("This shouldn't happen!");
    }
}