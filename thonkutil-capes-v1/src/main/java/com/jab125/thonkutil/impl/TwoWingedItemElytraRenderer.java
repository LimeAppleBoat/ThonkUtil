//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

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
        if (itemStack.isOf(Items.ELYTRA) && ThonkUtilCapes.getCape(livingEntity).getItem() instanceof CapeItem && ((CapeItem) ThonkUtilCapes.getCape(livingEntity).getItem()).hasElytraTexture() && ((CapeItem) ThonkUtilCapes.getCape(livingEntity).getItem()).has2WingedElytra()) {
            Identifier identifier;
            LivingEntity abstractClientPlayerEntity = livingEntity;
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
