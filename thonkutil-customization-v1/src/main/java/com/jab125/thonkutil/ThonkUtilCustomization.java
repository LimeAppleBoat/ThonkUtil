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
package com.jab125.thonkutil;

import com.jab125.thonkutil.config.ThonkUtilCustomizationConfigManager;
import net.fabricmc.api.ModInitializer;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ThonkUtilCustomization implements ModInitializer {
    public static final Enchantment VOID_SAVER = Registry.register(Registry.ENCHANTMENT, new Identifier("thonkutil", "void_saver"), new Enchantment(Enchantment.Rarity.RARE, EnchantmentTarget.TRIDENT, EquipmentSlot.values()) {
        @Override
        public int getMinLevel() {
            return 1;
        }

        @Override
        public int getMaxLevel() {
            return 3;
        }
    });

    /**
     * Runs the mod initializer.
     */
    @Override
    public void onInitialize() {
        ThonkUtilCustomizationConfigManager.initializeConfig();
    }
}
