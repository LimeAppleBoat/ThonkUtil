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
import com.jab125.thonkutil.ThonkUtilCapesClient;
import com.jab125.thonkutil.api.CapeItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class TwoWingedItemElytraRenderer<T extends LivingEntity, M extends EntityModel<T>> extends FeatureRenderer<T, M> {
    private final CustomLeftElytraEntityModel<T> elytra;
    private final CustomRightElytraEntityModel<T> elytra2;

    public TwoWingedItemElytraRenderer(FeatureRendererContext<T, M> context, EntityModelLoader loader) {
        super(context);
        this.elytra = new CustomLeftElytraEntityModel<>(loader.getModelPart(ThonkUtilCapesClient.TWO_LEFT_WINGED_ELYTRA));
        this.elytra2 = new CustomRightElytraEntityModel<T>(loader.getModelPart(ThonkUtilCapesClient.TWO_RIGHT_WINGED_ELYTRA));
    }

    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, T livingEntity, float f, float g, float h, float j, float k, float l) {
        ItemStack itemStack = livingEntity.getEquippedStack(EquipmentSlot.CHEST);
        if (itemStack.isOf(Items.ELYTRA) && livingEntity instanceof PlayerEntity && ThonkUtilCapes.getCape((PlayerEntity) livingEntity).getItem() instanceof CapeItem && ((CapeItem) ThonkUtilCapes.getCape((PlayerEntity) livingEntity).getItem()).hasElytraTexture() && ((CapeItem) ThonkUtilCapes.getCape((PlayerEntity) livingEntity).getItem()).has2WingedElytra()) {
            Identifier identifier;
            AbstractClientPlayerEntity abstractClientPlayerEntity = (AbstractClientPlayerEntity) livingEntity;
            ((CapeItem) ThonkUtilCapes.getCape(abstractClientPlayerEntity).getItem()).getElytraTexture();
            //identifier = ((CapeItem) ThonkUtilCapes.getCape(abstractClientPlayerEntity).getItem()).getElytraTexture();
            identifier = new Identifier("thonkutil", String.format("elytra/%s/%d", ((CapeItem) ThonkUtilCapes.getCape(abstractClientPlayerEntity).getItem()).getRegistryIdAsIdentifier().toUnderscoreSeparatedString(), 0));
            var identifier2 = new Identifier("thonkutil", String.format("elytra/%s/%d", ((CapeItem) ThonkUtilCapes.getCape(abstractClientPlayerEntity).getItem()).getRegistryIdAsIdentifier().toUnderscoreSeparatedString(), 1));
            matrixStack.push();
            matrixStack.translate(0.0D, 0.0D, 0.125D);
            this.getContextModel().copyStateTo(this.elytra);
            this.elytra.setAngles(livingEntity, f, g, j, k, l);
            VertexConsumer elytra = ItemRenderer.getItemGlintConsumer(vertexConsumerProvider, RenderLayer.getEntityTranslucent(identifier), false, itemStack.hasGlint());
            this.elytra.render(matrixStack, elytra, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
            matrixStack.pop();

            matrixStack.push();
            matrixStack.translate(0.0D, 0.0D, 0.125D);
            this.getContextModel().copyStateTo(this.elytra2);
            this.elytra2.setAngles(livingEntity, f, g, j, k, l);
            VertexConsumer elytra2 = ItemRenderer.getItemGlintConsumer(vertexConsumerProvider, RenderLayer.getEntityTranslucent(identifier2), false, itemStack.hasGlint());
            this.elytra2.render(matrixStack, elytra2, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
            matrixStack.pop();
        }
    }
}
