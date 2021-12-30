//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jab125.thonkutil.impl;

import com.jab125.thonkutil.ThonkUtilCapes;
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
import net.minecraft.client.render.entity.model.ElytraEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
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
public class ItemElytraRenderer<T extends LivingEntity, M extends EntityModel<T>> extends FeatureRenderer<T, M> {
    private final ElytraEntityModel<T> elytra;

    public ItemElytraRenderer(FeatureRendererContext<T, M> context, EntityModelLoader loader) {
        super(context);
        this.elytra = new ElytraEntityModel<>(loader.getModelPart(EntityModelLayers.ELYTRA));
    }

    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, T livingEntity, float f, float g, float h, float j, float k, float l) {
        ItemStack itemStack = livingEntity.getEquippedStack(EquipmentSlot.CHEST);
        if (itemStack.isOf(Items.ELYTRA) && livingEntity instanceof PlayerEntity && ThonkUtilCapes.getCape((PlayerEntity) livingEntity).getItem() instanceof CapeItem && ((CapeItem) ThonkUtilCapes.getCape((PlayerEntity) livingEntity).getItem()).hasElytraTexture() && !((CapeItem) ThonkUtilCapes.getCape((PlayerEntity) livingEntity).getItem()).has2WingedElytra()) {
            Identifier identifier;
            AbstractClientPlayerEntity abstractClientPlayerEntity = (AbstractClientPlayerEntity)livingEntity;
            identifier = ((CapeItem) ThonkUtilCapes.getCape(abstractClientPlayerEntity).getItem()).getCapeTexture();

            matrixStack.push();
            matrixStack.translate(0.0D, 0.0D, 0.125D);
            this.getContextModel().copyStateTo(this.elytra);
            this.elytra.setAngles(livingEntity, f, g, j, k, l);
            VertexConsumer elytra = ItemRenderer.getArmorGlintConsumer(vertexConsumerProvider, RenderLayer.getArmorCutoutNoCull(identifier), false, itemStack.hasGlint());
            this.elytra.render(matrixStack, elytra, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
            matrixStack.pop();
        }
    }
}
