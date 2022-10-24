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

import net.minecraft.util.Identifier;
import net.minecraft.world.Difficulty;
import org.jetbrains.annotations.ApiStatus;

public class DifficultyCreator {
    // WARNING!!! This is VERY UNSTABLE (the "id" field")
    @ApiStatus.Experimental
    public static Difficulty create(Identifier identifier, int id) {
        var ordinal = Difficulty.values()[Difficulty.values().length-1].ordinal()+1;
        return createInternal(identifier.toString(), ordinal, id, identifier.toString());
    }

    private static Difficulty createInternal(String var0, int var1, int id, String name) {
        throw new AssertionError();
    }
}
