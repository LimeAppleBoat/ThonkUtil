package com.jab125.thonkutil.impl;

import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;

public interface ArmorStandModelExtension {
    void thonkutil$renderCape(MatrixStack matrices, VertexConsumer vertices, int light, int overlay);
}
