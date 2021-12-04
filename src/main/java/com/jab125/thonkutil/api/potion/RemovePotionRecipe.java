package com.jab125.thonkutil.api.potion;

import com.jab125.thonkutil.impl.RemovePotionRecipeImpl;
import net.minecraft.potion.Potion;

@SuppressWarnings("unused")
public class RemovePotionRecipe {
    public static void removeTippedArrow(Potion potion) {
        RemovePotionRecipeImpl.RemoveTippedArrowRecipeImpl.removeTippedArrowRecipe(potion);
    }
}
