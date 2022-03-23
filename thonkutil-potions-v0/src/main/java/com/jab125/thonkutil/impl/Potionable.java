package com.jab125.thonkutil.impl;

import com.jab125.thonkutil.api.SkipPotion;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.registry.Registry;

public interface Potionable {
    default float potionLengthMultiplier() {
        return 1.0F;
    }

    default boolean addPotionsToCreativeInventory() {
        return false;
    }

    default void addToCreativeInventory(ItemGroup group, DefaultedList<ItemStack> stacks) {
        if (this instanceof Item item) {
            if (item.isIn(group)) {
                for (Potion potion : Registry.POTION) {
                    if (!potion.getEffects().isEmpty() && !SkipPotionImpl.contains(potion, SkipPotion.getType(item))) {
                        stacks.add(PotionUtil.setPotion(new ItemStack(item), potion));
                    }
                }
            }
        }
    }
}
