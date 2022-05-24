/*
 * Copyright (c) 2021, 2022 Jab125 & LimeAppleBoat
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
