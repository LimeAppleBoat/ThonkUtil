package com.jab125.thonkutil.cosmetics.spinningpenguins;


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
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3f;

import static com.jab125.thonkutil.cosmetics.Cosmetics.SPINNING_PENGUINS;

public class SpinningPenguinsRenderer<T extends Entity, M extends EntityModel<T>> extends FeatureRenderer<T, M> {
    private static final Identifier TEXTURE = new Identifier("thonkutil:textures/entity/cosmetics/spinning_penguins.png");
    private final SpinningPenguinsModel<T> model;
    public SpinningPenguinsRenderer(FeatureRendererContext<T, M> context, EntityModelLoader loader) {
        super(context);
        this.model = new SpinningPenguinsModel<>(loader.getModelPart(SPINNING_PENGUINS));
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, T entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        if (entity.isInvisible()) return; // NOOP
        if (!(entity instanceof PlayerEntity player)) return;
        if (!player.thonkutil$getCosmetic().equals("spinning_penguins")) return;
        matrices.push();
        //System.out.println("Animation Progress: " + animationProgress);
        float lerp0 = animationProgress % 120;
        if (lerp0 > 60) {
            lerp0 = 120 - lerp0;
        }
        float lerp = (lerp0)/180;
        float lerp3 = lerp0;
        //System.out.println("Lerp: "+ lerp3);
        matrices.translate(0,-0.4 + lerp,0);
        matrices.translate(0,-2,0);
        matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(((animationProgress%180)*2)));
        VertexConsumer vertexConsumer = ItemRenderer.getArmorGlintConsumer(vertexConsumers, RenderLayer.getArmorCutoutNoCull(TEXTURE), false, player.thonkutil$cosmeticEnchanted());
        model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
        matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(120f));
        model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
        matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(120f));
        model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
        matrices.pop();
    }
}