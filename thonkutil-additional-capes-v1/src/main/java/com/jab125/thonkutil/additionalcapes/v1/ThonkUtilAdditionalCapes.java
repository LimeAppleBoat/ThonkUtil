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
package com.jab125.thonkutil.additionalcapes.v1;

import net.fabricmc.api.*;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static com.jab125.thonkutil.additionalcapes.v1.item.AdditionalCapeItems.registerCapes;

@EnvironmentInterface(value = EnvType.CLIENT, itf = ClientModInitializer.class)
public class ThonkUtilAdditionalCapes implements ModInitializer, ClientModInitializer {
    public static ItemGroup ADDITIONAL_CAPES_GROUP = FabricItemGroupBuilder.create(Identifier.tryParse("thonkutil:additional_capes")).icon(() -> new ItemStack(Registry.ITEM.get(new Identifier("thonkutil:minecon_2011_cape")))).build();

    @Environment(EnvType.CLIENT)
    @Override
    public void onInitializeClient() {
    }

    @Override
    public void onInitialize() {
        registerCapes();
    }
}
