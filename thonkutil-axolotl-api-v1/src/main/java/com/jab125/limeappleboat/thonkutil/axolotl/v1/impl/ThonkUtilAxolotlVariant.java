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
package com.jab125.limeappleboat.thonkutil.axolotl.v1.impl;

import com.jab125.limeappleboat.thonkutil.axolotl.v1.impl.mixin.AxolotlTypeMixin;
import net.minecraft.entity.passive.AxolotlEntity;
import net.minecraft.util.Identifier;

public class ThonkUtilAxolotlVariant {
    public static boolean p = false;
    private boolean custom = false;
    private Identifier id;
    private AxolotlEntity.Variant type;
    public static ThonkUtilAxolotlVariant make(AxolotlEntity.Variant type) {
        ThonkUtilAxolotlVariant thonkUtilAxolotlVariant = new ThonkUtilAxolotlVariant();
        thonkUtilAxolotlVariant.type = type;
        return thonkUtilAxolotlVariant;
    }

    public void custom() {
        custom = true;
    }

    public void setId(Identifier id) {
        this.id = id;
    }

    public boolean isCustom() {
        return custom;
    }

    public Identifier getId() {
        return !custom ? new Identifier(type.getName()) : id;
    }
}
