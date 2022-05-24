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
package com.jab125.thonkutil.api;

import com.jab125.thonkutil.api.item.*;
import com.jab125.thonkutil.impl.Potionable;
import com.jab125.thonkutil.impl.SkipPotionImpl;
import net.minecraft.item.Item;
import net.minecraft.item.LingeringPotionItem;
import net.minecraft.item.PotionItem;
import net.minecraft.item.SplashPotionItem;
import net.minecraft.potion.Potion;

@SuppressWarnings("unused")
public class SkipPotion {
    public static final int POTION = 0;
    public static final int SPLASH_POTION = 1;
    public static final int LINGERING_POTION = 2;
    public static final int TIPPED_ARROW = 3;

    public static final int POTIONABLE_AXE = 4;
    public static final int POTIONABLE_HOE = 5;
    public static final int POTIONABLE_ITEM = 6;
    public static final int POTIONABLE_PICKAXE = 7;
    public static final int POTIONABLE_SHOVEL = 8;
    public static final int POTIONABLE_SWORD = 9;
    public static final int POTIONABLE = 10;


    public static void skipAll(Potion potion) {
        for (var i = 0; i < 10; i++) {
            skipPotion(potion, i);
        }
    }
    public static void skipPotion(Potion potion, int skip) {
        SkipPotionImpl.skipPotion(potion, skip);
    }

    public static int getType(Item item) {
        if (item instanceof SplashPotionItem) return SkipPotion.SPLASH_POTION;
        if (item instanceof LingeringPotionItem) return SkipPotion.LINGERING_POTION;
        if (item instanceof PotionableAxe) return SkipPotion.POTIONABLE_AXE;
        if (item instanceof PotionableHoe) return SkipPotion.POTIONABLE_HOE;
        if (item instanceof PotionableSword) return SkipPotion.POTIONABLE_SWORD;
        if (item instanceof PotionableShovel) return SkipPotion.POTIONABLE_SHOVEL;
        if (item instanceof PotionablePickaxe) return SkipPotion.POTIONABLE_PICKAXE;
        if (item instanceof PotionableItem) return SkipPotion.POTIONABLE_ITEM;
        if (item instanceof Potionable) return SkipPotion.POTIONABLE;

        return SkipPotion.POTION;
    }
}
