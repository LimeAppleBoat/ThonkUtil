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

import net.minecraft.block.Block;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.util.Identifier;

public class BoatTypeCreator {
    public static BoatEntity.Type create(Identifier identifier, Block block) {
        var ordinal = BoatEntity.Type.values()[BoatEntity.Type.values().length-1].ordinal()+1;
        return createInternal(identifier.toString(), ordinal, block, identifier.getNamespace() + "/" + identifier.getPath());
    }

    private static BoatEntity.Type createInternal(String var0, int var1, Block var2, String var3) {
        throw new AssertionError();
    }
}
