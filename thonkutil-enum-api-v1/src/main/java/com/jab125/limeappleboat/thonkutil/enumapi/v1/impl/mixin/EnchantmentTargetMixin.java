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
package com.jab125.limeappleboat.thonkutil.enumapi.v1.impl.mixin;

import com.jab125.limeappleboat.thonkutil.enumapi.v1.api.EnchantmentTargetAdder;
import com.jab125.limeappleboat.thonkutil.enumapi.v1.api.EnchantmentTargetExtension;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(EnchantmentTarget.class)
public class EnchantmentTargetMixin implements EnchantmentTargetExtension {
    private EnchantmentTargetAdder.Checker checker;
    private boolean thonkutil$isAcceptableItem(Item item) /*isAcceptableItem*/ {
        return thonkutil$accept(item);
    }

    @Override
    public void thonkutil$setChecker(EnchantmentTargetAdder.Checker checker) {
        this.checker = checker;
    }

    @Override
    public boolean thonkutil$accept(Item item) {
        return this.checker.check(item);
    }
}