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

import com.jab125.thonkutil.api.AnimatedCapeItem;
import com.jab125.thonkutil.api.CapeItem;
import com.jab125.thonkutil.compat.MinecraftCapesCompat;
import com.jab125.thonkutil.compat.WaveyCapesCompat;
import com.jab125.thonkutil.impl.CustomLeftElytraEntityModel;
import com.jab125.thonkutil.impl.CustomRightElytraEntityModel;
import com.jab125.thonkutil.impl.TextureLoader;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRenderEvents;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import static com.jab125.thonkutil.util.Util.isModInstalled;

@Environment(EnvType.CLIENT)
public class ThonkUtilCapesClient implements ClientModInitializer {
    public static final EntityModelLayer TWO_LEFT_WINGED_ELYTRA = new EntityModelLayer(new Identifier("thonkutil", "two_left_winged_elytra"), "two_left_winged_elytra_render_layer");
    public static final EntityModelLayer TWO_RIGHT_WINGED_ELYTRA = new EntityModelLayer(new Identifier("thonkutil", "two_right_winged_elytra"), "two_right_winged_elytra_render_layer");
    public static final EntityModelLayer CAPE = new EntityModelLayer(new Identifier("thonkutil", "cape"), "cape_render_layer");

    /**
     * Runs the mod initializer on the client environment.
     */
    @Override
    public void onInitializeClient() {
        if (isModInstalled("minecraftcapes")) {
            new MinecraftCapesCompat();
        }
        if (isModInstalled("waveycapes")) {
            new WaveyCapesCompat();
        }
        LivingEntityFeatureRenderEvents.ALLOW_CAPE_RENDER.register((abstractClientPlayerEntity) -> {
            return !(abstractClientPlayerEntity.canRenderCapeTexture() && !abstractClientPlayerEntity.isInvisible() && !ThonkUtilCapes.getCape(abstractClientPlayerEntity).equals(ItemStack.EMPTY) && ThonkUtilCapes.getCape(abstractClientPlayerEntity).getItem() instanceof CapeItem);
        });
        EntityModelLayerRegistry.registerModelLayer(TWO_LEFT_WINGED_ELYTRA, CustomLeftElytraEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(TWO_RIGHT_WINGED_ELYTRA, CustomRightElytraEntityModel::getTexturedModelData);
    }

    public static void loadCapes(AnimatedCapeItem cape) {
        TextureLoader.applyAnimatedCape(cape);
    }
}
