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
package com.jab125.thonkutil.impl;

import com.jab125.thonkutil.api.SkipPotion;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.registry.Registry;

public interface Potionable {
    default float potionLengthMultiplier() {
        return 1.0F;
    }

    default boolean addPotionsToCreativeInventory() {
        return false;
    }

    default void addToCreativeInventory(ItemGroup group, DefaultedList<ItemStack> stacks) {
        if (this instanceof Item item && addPotionsToCreativeInventory()) {
            if (item.isIn(group)) {
                for (Potion potion : Registry.POTION) {
                    if (!potion.getEffects().isEmpty() && !SkipPotionImpl.contains(potion, SkipPotion.getType(item))) {
                        stacks.add(PotionUtil.setPotion(new ItemStack(item), potion));
                    }
                }
            }
        }
    }
}
