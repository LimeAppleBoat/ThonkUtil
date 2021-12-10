package com.jab125.thonkutil.api;

import net.minecraft.item.Item;
import net.minecraft.potion.Potion;

public class BrewingRecipeRegistry {
    public static void registerPotionRecipe(Potion input, Item ingredient, Potion output) {
        net.minecraft.recipe.BrewingRecipeRegistry.registerPotionRecipe(input, ingredient, output);
    }
    public static void registerItemRecipe(Item input, Item ingredient, Item output) {
        net.minecraft.recipe.BrewingRecipeRegistry.registerItemRecipe(input, ingredient, output);
    }
}