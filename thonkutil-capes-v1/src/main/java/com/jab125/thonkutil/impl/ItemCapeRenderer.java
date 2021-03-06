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
package com.jab125.thonkutil.impl;

import com.jab125.thonkutil.ThonkUtilCapes;
import com.jab125.thonkutil.api.CapeItem;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3f;

public class ItemCapeRenderer extends FeatureRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> {

    public ItemCapeRenderer(FeatureRendererContext<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> context, EntityModelLoader loader) {
        super(context);
    }

    @Override
    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, AbstractClientPlayerEntity abstractClientPlayerEntity, float f, float g, float h, float j, float k, float l) {
        if (abstractClientPlayerEntity.canRenderCapeTexture() && !abstractClientPlayerEntity.isInvisible() && !ThonkUtilCapes.getCape(abstractClientPlayerEntity).equals(ItemStack.EMPTY) && ThonkUtilCapes.getCape(abstractClientPlayerEntity).getItem() instanceof CapeItem) {
            ItemStack itemStack = abstractClientPlayerEntity.getEquippedStack(EquipmentSlot.CHEST);
            if (!itemStack.isOf(Items.ELYTRA)) {
                matrixStack.push();
                matrixStack.translate(0.0D, 0.0D, 0.125D);
                double d = MathHelper.lerp((double) h, abstractClientPlayerEntity.prevCapeX, abstractClientPlayerEntity.capeX) - MathHelper.lerp((double) h, abstractClientPlayerEntity.prevX, abstractClientPlayerEntity.getX());
                double e = MathHelper.lerp((double) h, abstractClientPlayerEntity.prevCapeY, abstractClientPlayerEntity.capeY) - MathHelper.lerp((double) h, abstractClientPlayerEntity.prevY, abstractClientPlayerEntity.getY());
                double m = MathHelper.lerp((double) h, abstractClientPlayerEntity.prevCapeZ, abstractClientPlayerEntity.capeZ) - MathHelper.lerp((double) h, abstractClientPlayerEntity.prevZ, abstractClientPlayerEntity.getZ());
                float n = abstractClientPlayerEntity.prevBodyYaw + (abstractClientPlayerEntity.bodyYaw - abstractClientPlayerEntity.prevBodyYaw);
                double o = (double) MathHelper.sin(n * 0.017453292F);
                double p = (double) (-MathHelper.cos(n * 0.017453292F));
                float q = (float) e * 10.0F;
                q = MathHelper.clamp(q, -6.0F, 32.0F);
                float r = (float) (d * o + m * p) * 100.0F;
                r = MathHelper.clamp(r, 0.0F, 150.0F);
                float s = (float) (d * p - m * o) * 100.0F;
                s = MathHelper.clamp(s, -20.0F, 20.0F);
                if (r < 0.0F) {
                    r = 0.0F;
                }

                float t = MathHelper.lerp(h, abstractClientPlayerEntity.prevStrideDistance, abstractClientPlayerEntity.strideDistance);
                q += MathHelper.sin(MathHelper.lerp(h, abstractClientPlayerEntity.prevHorizontalSpeed, abstractClientPlayerEntity.horizontalSpeed) * 6.0F) * 32.0F * t;
                if (abstractClientPlayerEntity.isInSneakingPose()) {
                    q += 25.0F;
                }

                matrixStack.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(6.0F + r / 2.0F + q));
                matrixStack.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(s / 2.0F));
                matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(180.0F - s / 2.0F));
                VertexConsumer enchantmentGlint = ItemRenderer.getItemGlintConsumer(vertexConsumerProvider, RenderLayer.getEntityTranslucent(((CapeItem) ThonkUtilCapes.getCape(abstractClientPlayerEntity).getItem()).getCapeTexture()), false, ThonkUtilCapes.getCape(abstractClientPlayerEntity).hasGlint());
                VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getEntitySolid(((CapeItem) ThonkUtilCapes.getCape(abstractClientPlayerEntity).getItem()).getCapeTexture()));
                this.getContextModel().renderCape(matrixStack, enchantmentGlint, i, OverlayTexture.DEFAULT_UV);
                matrixStack.pop();
            }
        }
    }
}
