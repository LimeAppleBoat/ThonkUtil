package dev.tr7zw.waveycapes;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
public interface CapeRenderer {
    public void render(AbstractClientPlayerEntity player, int part, ModelPart model, MatrixStack poseStack, VertexConsumerProvider multiBufferSource, int light, int overlay);

    public default VertexConsumer getVertexConsumer(VertexConsumerProvider multiBufferSource, AbstractClientPlayerEntity player) {
        throw new IllegalStateException("This shouldn't happen!");
    }

    public boolean vanillaUvValues();
}
