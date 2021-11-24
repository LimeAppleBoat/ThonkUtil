package com.jab125.thonkutil.potion.impl;

import net.minecraft.potion.Potion;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class RemoveTippedPotionRecipeImpl {
    static ArrayList<Potion> potions = new ArrayList<>();
    public static void removeTippedPotionRecipe(Potion potion) {
        potions.add(potion);
    }

    public static Potion[] getPotions() {
        return potions.toArray(Potion[]::new);
    }
    public static boolean contains(Potion potion) {
        System.out.println(Registry.POTION.getId(potion).toString());
        for (var potion2 : potions) {
            System.out.println(Registry.POTION.getId(potion2).toString());
            if (potion2.equals(potion)) return true;
        }
        return false;
    }
}
