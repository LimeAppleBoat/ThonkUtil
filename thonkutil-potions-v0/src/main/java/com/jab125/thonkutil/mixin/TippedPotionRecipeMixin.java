package com.jab125.thonkutil.mixin;

import com.jab125.thonkutil.impl.RemovePotionRecipeImpl;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtil;
import net.minecraft.recipe.TippedArrowRecipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(TippedArrowRecipe.class)
public class TippedPotionRecipeMixin {
    @ModifyVariable(method = "craft", at = @At(value = "STORE", ordinal = 0))
    private ItemStack modifyCrafting(ItemStack itemStack) {
        if (RemovePotionRecipeImpl.RemoveTippedArrowRecipeImpl.contains(PotionUtil.getPotion(itemStack))) {
            return ItemStack.EMPTY;
        }
        return itemStack;
    }
}
