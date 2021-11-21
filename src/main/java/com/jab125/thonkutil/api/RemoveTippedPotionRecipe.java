package com.jab125.thonkutil.api;

import com.jab125.thonkutil.impl.RemoveTippedPotionRecipeImpl;
import net.minecraft.potion.Potion;

@SuppressWarnings("unused")
public class RemoveTippedPotionRecipe {
    public static void remove(Potion potion) {
        RemoveTippedPotionRecipeImpl.removeTippedPotionRecipe(potion);
    }
}
