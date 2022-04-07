package com.jab125.thonkutil.impl;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;

public interface LivingEntityExtension {
    double thonkutil$getCapeX();
    double thonkutil$getCapeY();
    double thonkutil$getCapeZ();
    
    double thonkutil$getPrevCapeX();
    double thonkutil$getPrevCapeY();
    double thonkutil$getPrevCapeZ();
}
