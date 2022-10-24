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
package com.jab125.limeappleboat.thonkutil.enumapi.v1.api.creator;

import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.ApiStatus;

public class TooltipSectionCreator {
    @ApiStatus.Experimental // oh no
    public static ItemStack.TooltipSection create(Identifier identifier) {
        var ordinal = ItemStack.TooltipSection.values()[ItemStack.TooltipSection.values().length-1].ordinal()+1;
        return createInternal(identifier.toString(), ordinal);
    }

    private static ItemStack.TooltipSection createInternal(String var0, int var1) {
        throw new AssertionError();
    }
}
