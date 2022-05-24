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
package com.jab125.thonkutil.additionalcapes.v1.item;

import com.jab125.thonkutil.api.CapeItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static com.jab125.thonkutil.additionalcapes.v1.ThonkUtilAdditionalCapes.ADDITIONAL_CAPES_GROUP;

@SuppressWarnings("unused")
public class AdditionalCapeItems {
    public static CapeItem MISSING_TEXTURE_CAPE = createCape(false);
    //public static CapeItem NO_CAPE = createCape();
    public static CapeItem UKRAINE_CAPE = createCape();

    public static void registerCapes() {
        for (Field cape : AdditionalCapeItems.class.getFields()) {
            if (Modifier.isStatic(cape.getModifiers()) && cape.getType().isAssignableFrom(CapeItem.class)) {
                try {
                    CapeItem capeItem = (CapeItem) cape.get(null);
                    Registry.register(Registry.ITEM, new Identifier("thonkutil", cape.getName().toLowerCase()), capeItem);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private static CapeItem createCape(boolean hasElytra) {
        return new CapeItem(new Item.Settings().group(ADDITIONAL_CAPES_GROUP), hasElytra).doNotAddToCreativeInventory();
    }
    private static CapeItem createCape() {
        return createCape(true);
    }
}
