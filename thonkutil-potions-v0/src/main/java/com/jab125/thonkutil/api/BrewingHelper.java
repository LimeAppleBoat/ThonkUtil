package com.jab125.thonkutil.api;

import net.minecraft.item.Item;
import net.minecraft.potion.Potion;

public class BrewingHelper {
    private BrewingHelper() {
        throw new RuntimeException("This Util class is not allowed to be instantiated!");
    }
    public static void registerPotionRecipe(Potion input, Item ingredient, Potion output) {
        net.minecraft.recipe.BrewingRecipeRegistry.registerPotionRecipe(input, ingredient, output);
    }
    public static void registerItemRecipe(Item input, Item ingredient, Item output) {
        net.minecraft.recipe.BrewingRecipeRegistry.registerItemRecipe(input, ingredient, output);
    }
}
