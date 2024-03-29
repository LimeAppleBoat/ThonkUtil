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
package com.jab125.limeappleboat.thonkutil.axolotl.v1.api;

import com.jab125.limeappleboat.thonkutil.axolotl.v1.impl.AxolotlCreatorImpl;
import com.jab125.limeappleboat.thonkutil.axolotl.v1.impl.AxolotlTypeExtension;
import net.minecraft.entity.passive.AxolotlEntity;
import net.minecraft.util.Identifier;

public class AxolotlCreator {
    public static Builder builder(Identifier id) {
        var builder = new Builder();
        builder.id = id;
        return builder;
    }
    public static class Builder {
        private boolean natural = false;
        private Identifier id;
        private Builder(){}
        public Builder natural() {
            natural = true;
            return this;
        }
        public AxolotlEntity.Variant build() {
            AxolotlEntity.Variant[] variants = AxolotlEntity.Variant.values();
            AxolotlEntity.Variant lastVariant = variants[variants.length-1];
            String internalName = id.toString();
            int ordinal = variants[variants.length-1].ordinal()+1;
            int id = lastVariant.getId()+1;
            String name = internalName;
            boolean natural = this.natural;
            AxolotlEntity.Variant variant = AxolotlCreatorImpl.create(internalName, ordinal, id, name, natural);
            ((AxolotlTypeExtension) (Object) variant).thonkutil$thonkutil_metadata().custom();
            ((AxolotlTypeExtension) (Object) variant).thonkutil$thonkutil_metadata().setId(this.id);
            return variant;
        }
    }
}
