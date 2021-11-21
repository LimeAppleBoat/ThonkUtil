package com.jab125.thonkutil.mixin;

import com.jab125.thonkutil.impl.RemoveTippedPotionRecipeImpl;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.TippedArrowRecipe;
import net.minecraft.util.registry.Registry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Arrays;

@Mixin(TippedArrowRecipe.class)
public class TippedPotionRecipeMixin {
    @ModifyVariable(method = "craft", at = @At(value = "STORE", ordinal = 0))
    private ItemStack modifyCrafting(ItemStack itemStack) {
        if (RemoveTippedPotionRecipeImpl.contains(PotionUtil.getPotion(itemStack))) {
            return ItemStack.EMPTY;
        }
        return itemStack;
    }
}
