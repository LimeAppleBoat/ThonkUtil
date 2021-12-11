package com.jab125.thonkutil.mixin;

import com.jab125.thonkutil.impl.RemovePotionRecipeImpl;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.BrewingRecipeRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BrewingRecipeRegistry.class)
public class PotionRecipeMixin {
    @Inject(method = "craft", at = @At("HEAD"), cancellable = true)
    private static void injectCraft(ItemStack ingredient, ItemStack input, CallbackInfoReturnable<ItemStack> cir) {
        if (!input.isEmpty()) {
            if (!RemovePotionRecipeImpl.shouldCraft(input, ingredient)) cir.setReturnValue(input);
        }
    }

    @Inject(method = "hasRecipe", at = @At("HEAD"), cancellable = true)
    private static void canCraft(ItemStack input, ItemStack ingredient, CallbackInfoReturnable<Boolean> cir) {
        if (!RemovePotionRecipeImpl.shouldCraft(input, ingredient)) cir.setReturnValue(false);
    }
}
