package com.jab125.thonkutil.api;

import net.minecraft.potion.Potion;

@Deprecated
@SuppressWarnings("unused")
public class RemoveTippedPotionRecipe extends RemovePotionRecipe {
    public static void remove(Potion potion) {
        removeTippedArrow(potion);
    }
}
