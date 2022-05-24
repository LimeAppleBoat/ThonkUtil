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
package com.jab125.thonkutil.compat;

import com.jab125.thonkutil.ThonkUtilCapes;
import com.jab125.thonkutil.api.CapeItem;
import dev.tr7zw.waveycapes.CapeRenderer;
import dev.tr7zw.waveycapes.support.MinecraftCapesSupport;
import dev.tr7zw.waveycapes.support.ModSupport;
import dev.tr7zw.waveycapes.support.SupportManager;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraftcapes.config.MinecraftCapesConfig;
import net.minecraftcapes.player.PlayerHandler;

public class WaveyCapesCompat implements ModSupport {
    private final ThonkUtilCapesRenderer render = new ThonkUtilCapesRenderer();
    public WaveyCapesCompat() {
        SupportManager.mods.add(this);
    }
    @Override
    public boolean shouldBeUsed(AbstractClientPlayerEntity abstractClientPlayerEntity) {
        return !ThonkUtilCapes.getCape(abstractClientPlayerEntity).isEmpty();
    }

    @Override
    public CapeRenderer getRenderer() {
        return render;
    }

    @Override
    public boolean blockFeatureRenderer(Object o) {
        return false;
    }

    private final class ThonkUtilCapesRenderer implements CapeRenderer {
        private ThonkUtilCapesRenderer() {
        }

        public void render(AbstractClientPlayerEntity player, int part, ModelPart model, MatrixStack poseStack, VertexConsumerProvider multiBufferSource, int light, int overlay) {
            boolean isCapeVisible = !ThonkUtilCapes.getCape(player).isEmpty();
            CapeItem cape = (CapeItem) ThonkUtilCapes.getCape(player).getItem();
            VertexConsumer vertexConsumer;
            if (isCapeVisible && cape.getCapeTexture() != null) {
                vertexConsumer = ItemRenderer.getArmorGlintConsumer(multiBufferSource, RenderLayer.getArmorCutoutNoCull(cape.getCapeTexture()), false, cape.hasGlint(ThonkUtilCapes.getCape(player)));
            } else {
                vertexConsumer = ItemRenderer.getArmorGlintConsumer(multiBufferSource, RenderLayer.getArmorCutoutNoCull(player.getCapeTexture()), false, false);
            }

            model.render(poseStack, vertexConsumer, light, OverlayTexture.DEFAULT_UV);
        }

        public VertexConsumer getVertexConsumer(VertexConsumerProvider multiBufferSource, AbstractClientPlayerEntity player) {
            boolean isCapeVisible = !ThonkUtilCapes.getCape(player).isEmpty();
            CapeItem cape = (CapeItem) ThonkUtilCapes.getCape(player).getItem();
            return isCapeVisible && cape.getCapeTexture() != null ? ItemRenderer.getArmorGlintConsumer(multiBufferSource, RenderLayer.getArmorCutoutNoCull(cape.getCapeTexture()), false, cape.hasGlint(ThonkUtilCapes.getCape(player))) : ItemRenderer.getArmorGlintConsumer(multiBufferSource, RenderLayer.getArmorCutoutNoCull(cape.getCapeTexture()), false, false);
        }

        public boolean vanillaUvValues() {
            return true;
        }
    }
}
