package com.jab125.thonkutil.api;

import com.jab125.thonkutil.api.potion.RemovePotionRecipe;
import com.jab125.thonkutil.impl.RemovePotionRecipeImpl;
import net.minecraft.potion.Potion;
import org.jetbrains.annotations.ApiStatus.ScheduledForRemoval;

@Deprecated
@ScheduledForRemoval(inVersion = "1.5.0")
public class RemoveTippedPotionRecipe extends RemovePotionRecipe {
    public static void remove(Potion potion) {
        RemovePotionRecipe.removeTippedArrow(potion);
    }
}
