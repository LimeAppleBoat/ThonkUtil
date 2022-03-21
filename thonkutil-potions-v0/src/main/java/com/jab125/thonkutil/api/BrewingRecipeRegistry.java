package com.jab125.thonkutil.api;

import net.minecraft.item.Item;
import net.minecraft.potion.Potion;

@Deprecated
public class BrewingRecipeRegistry {
    private BrewingRecipeRegistry() {
        throw new RuntimeException("This Util class is not allowed to be instantiated!");
    }

    public static void registerPotionRecipe(Potion input, Item ingredient, Potion output) {
        BrewingHelper.registerPotionRecipe(input, ingredient, output);
    }

    public static void registerItemRecipe(Item input, Item ingredient, Item output) {
        BrewingHelper.registerItemRecipe(input, ingredient, output);
    }
}