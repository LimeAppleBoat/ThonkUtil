package com.jab125.thonkutil.util.potion;

import com.jab125.thonkutil.api.BrewingRecipeRegistry;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;

@Deprecated
@SuppressWarnings("unused")
public class PotionUtil {
    public static void registerPotionRecipe(Potion input, Item ingredient, Potion output) {
        BrewingRecipeRegistry.registerPotionRecipe(input, ingredient, output);
    }

    public static void registerItemRecipe(Item input, Item ingredient, Item output) {
        BrewingRecipeRegistry.registerItemRecipe(input, ingredient, output);
    }
}
