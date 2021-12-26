package com.jab125.thonkutil.impl;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;

public interface PlayerEntityExtension {
    ModelPart thonkutil_getItemCape();

    void renderItemCape(MatrixStack matrices, VertexConsumer vertices, int light, int overlay);
}
