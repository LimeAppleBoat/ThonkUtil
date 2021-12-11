package com.jab125.thonkutil.api;

import com.jab125.thonkutil.impl.RemovePotionRecipeImpl;
import net.minecraft.potion.Potion;

@SuppressWarnings("unused")
public class RemovePotionRecipe {
    public static void removeTippedArrow(Potion potion) {
        RemovePotionRecipeImpl.RemoveTippedArrowRecipeImpl.removeTippedArrowRecipe(potion);
    }
    public static void removeSplashPotion(Potion potion) {
        RemovePotionRecipeImpl.RemoveSplashPotionRecipeImpl.removeSplashPotionRecipe(potion);
    }
    public static void removeLingeringPotion(Potion potion) {
        RemovePotionRecipeImpl.RemoveLingeringPotionRecipeImpl.removeLingeringPotionRecipe(potion);
    }
}
