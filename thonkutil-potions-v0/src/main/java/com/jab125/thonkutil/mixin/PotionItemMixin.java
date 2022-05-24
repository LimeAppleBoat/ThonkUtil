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

import com.jab125.thonkutil.api.SkipPotion;
import com.jab125.thonkutil.impl.SkipPotionImpl;
import net.minecraft.item.*;
import net.minecraft.potion.Potion;
import net.minecraft.util.collection.DefaultedList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PotionItem.class)
public class PotionItemMixin {
    PotionItem potionItem;

    @Inject(method = "appendStacks", at = @At(value = "HEAD"))
    private void appendToStacks(ItemGroup group, DefaultedList<ItemStack> stacks, CallbackInfo ci) {
        this.potionItem = (PotionItem) (Object) this;
    }

    @ModifyVariable(method = "appendStacks", at = @At(value = "STORE", target = "Lnet/minecraft/item/TippedArrowItem;appendStacks(Lnet/minecraft/item/ItemGroup;Lnet/minecraft/util/collection/DefaultedList;)V"), ordinal = 0)
    private Potion modifyPotion(Potion potion) {
        if (SkipPotionImpl.contains(potion, getType(this.potionItem))) {
            return new Potion();
        }
        return potion;
    }

    private int getType(PotionItem potionItem) {
        if (potionItem instanceof SplashPotionItem) return SkipPotion.SPLASH_POTION;
        if (potionItem instanceof LingeringPotionItem) return SkipPotion.LINGERING_POTION;
        return SkipPotion.POTION;
    }
}
