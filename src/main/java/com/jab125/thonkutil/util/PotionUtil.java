package com.jab125.thonkutil.util;

import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.recipe.BrewingRecipeRegistry;

public class PotionUtil {
    public static void registerPotionRecipe(Potion input, Item ingredient, Potion output) {
        BrewingRecipeRegistry.registerPotionRecipe(input, ingredient, output);
    }
    public static void registerItemRecipe(Item input, Item ingredient, Item output) {
        BrewingRecipeRegistry.registerItemRecipe(input, ingredient, output);
    }

}
