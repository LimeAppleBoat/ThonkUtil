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
package com.jab125.limeappleboat.thonkutil.axolotl.testmod;

import com.jab125.limeappleboat.thonkutil.axolotl.testmod.mixin.AxolotlEntityRendererAccessor;
import com.jab125.limeappleboat.thonkutil.axolotl.v1.api.AxolotlCreator;
import com.jab125.limeappleboat.thonkutil.axolotl.v1.impl.AxolotlTypeExtension;
import com.jab125.limeappleboat.thonkutil.enumapi.v1.api.creator.DifficultyCreator;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Blocks;
import net.minecraft.client.render.entity.AxolotlEntityRenderer;
import net.minecraft.entity.passive.AxolotlEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

import java.util.Arrays;

import static net.minecraft.entity.passive.AxolotlEntity.Variant.BLUE;

public class Testmod implements ModInitializer {
    @Override
    public void onInitialize() {
        System.out.println("========BEGIN TEST========");
        AxolotlEntity.Variant variant = AxolotlCreator.builder(new Identifier("thonkutil-axolotl-api-v1-testmod", "sandpaper"))
                .natural()
                .build();
        var metadata = ((AxolotlTypeExtension)(Object)variant).thonkutil$thonkutil_metadata();
        var blueMetadata = ((AxolotlTypeExtension)(Object)BLUE).thonkutil$thonkutil_metadata();
        System.out.println("Variant: " + BLUE + ", Custom: " +  blueMetadata.isCustom() + " Id: " + blueMetadata.getId() + " Texture: " + getTexture(BLUE));
        System.out.println("Variant: " + variant + ", Custom: " + metadata.isCustom() + " Id: " + metadata.getId() + " Texture: " + getTexture(variant));
        System.out.println("========FINISH TEST========");
        System.exit(0);
    }

    private static Identifier getTexture(AxolotlEntity.Variant variant) {
        return AxolotlEntityRendererAccessor.getTEXTURES().get(variant);
    }
}
