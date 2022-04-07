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
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3f;

public class ItemCapeRenderer<T extends LivingEntity, M extends EntityModel<T>> extends FeatureRenderer<T, M> {

    public ItemCapeRenderer(FeatureRendererContext<T, M> context, EntityModelLoader loader) {
        super(context);
    }

    @Override
    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, T abstractClientPlayerEntity, float f, float g, float h, float j, float k, float l) {
        var extension = (LivingEntityExtension)abstractClientPlayerEntity;
        double prevCapeX = extension.thonkutil$getPrevCapeX();
        double prevCapeY = extension.thonkutil$getPrevCapeY();
        double prevCapeZ = extension.thonkutil$getPrevCapeZ();
        double capeX = extension.thonkutil$getCapeX();
        double capeY = extension.thonkutil$getCapeY();
        double capeZ = extension.thonkutil$getCapeZ();
        float prevStrideDistance = 0;
        float strideDistance = 0;
        boolean canRenderCapeTexture = true;
        if (abstractClientPlayerEntity instanceof AbstractClientPlayerEntity entity) {
            prevCapeX = entity.prevCapeX;
            prevCapeY = entity.prevCapeY;
            prevCapeZ = entity.prevCapeZ;
            capeX = entity.capeX;
            capeY = entity.capeY;
            capeZ = entity.capeZ;
            prevStrideDistance =  entity.prevStrideDistance;
            strideDistance = entity.strideDistance;
            canRenderCapeTexture = entity.canRenderCapeTexture();
        }
        if (canRenderCapeTexture && !abstractClientPlayerEntity.isInvisible() && !ThonkUtilCapes.getCape(abstractClientPlayerEntity).equals(ItemStack.EMPTY) && ThonkUtilCapes.getCape(abstractClientPlayerEntity).getItem() instanceof CapeItem) {
            ItemStack itemStack = abstractClientPlayerEntity.getEquippedStack(EquipmentSlot.CHEST);
            if (!itemStack.isOf(Items.ELYTRA)) {
                matrixStack.push();
                matrixStack.translate(0.0D, 0.0D, 0.125D);
                double d = MathHelper.lerp(h, prevCapeX, capeX) - MathHelper.lerp(h, abstractClientPlayerEntity.prevX, abstractClientPlayerEntity.getX());
                double e = MathHelper.lerp(h, prevCapeY, capeY) - MathHelper.lerp(h, abstractClientPlayerEntity.prevY, abstractClientPlayerEntity.getY());
                double m = MathHelper.lerp(h, prevCapeZ, capeZ) - MathHelper.lerp(h, abstractClientPlayerEntity.prevZ, abstractClientPlayerEntity.getZ());
                float n = abstractClientPlayerEntity.prevBodyYaw + (abstractClientPlayerEntity.bodyYaw - abstractClientPlayerEntity.prevBodyYaw);
                double o = MathHelper.sin(n * 0.017453292F);
                double p = -MathHelper.cos(n * 0.017453292F);
                float q = (float) e * 10.0F;
                q = MathHelper.clamp(q, -6.0F, 32.0F);
                float r = (float) (d * o + m * p) * 100.0F;
                r = MathHelper.clamp(r, 0.0F, 150.0F);
                float s = (float) (d * p - m * o) * 100.0F;
                s = MathHelper.clamp(s, -20.0F, 20.0F);
                if (r < 0.0F) {
                    r = 0.0F;
                }

                float t = MathHelper.lerp(h, prevStrideDistance, strideDistance);
                q += MathHelper.sin(MathHelper.lerp(h, abstractClientPlayerEntity.prevHorizontalSpeed, abstractClientPlayerEntity.horizontalSpeed) * 6.0F) * 32.0F * t;
                if (abstractClientPlayerEntity.isInSneakingPose()) {
                    q += 25.0F;
                }

                matrixStack.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(6.0F + r / 2.0F + q));
                matrixStack.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(s / 2.0F));
                matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(180.0F - s / 2.0F));
                VertexConsumer enchantmentGlint = ItemRenderer.getItemGlintConsumer(vertexConsumerProvider, RenderLayer.getEntityTranslucent(((CapeItem) ThonkUtilCapes.getCape(abstractClientPlayerEntity).getItem()).getCapeTexture()), false, ThonkUtilCapes.getCape(abstractClientPlayerEntity).hasGlint());
                VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getEntitySolid(((CapeItem) ThonkUtilCapes.getCape(abstractClientPlayerEntity).getItem()).getCapeTexture()));
                if (this.getContextModel() instanceof PlayerEntityModel<?> da)
                da.renderCape(matrixStack, enchantmentGlint, i, OverlayTexture.DEFAULT_UV);
                else if (this.getContextModel() instanceof ArmorStandModelExtension extension1)
                extension1.thonkutil$renderCape(matrixStack, enchantmentGlint, i, OverlayTexture.DEFAULT_UV);
                matrixStack.pop();
            }
        }
    }
}
