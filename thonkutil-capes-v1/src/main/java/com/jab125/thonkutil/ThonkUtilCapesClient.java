package com.jab125.thonkutil;

import com.jab125.thonkutil.api.CapeItem;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRenderEvents;
import net.minecraft.item.ItemStack;

@Environment(EnvType.CLIENT)
public class ThonkUtilCapesClient implements ClientModInitializer {
    /**
     * Runs the mod initializer on the client environment.
     */
    @Override
    public void onInitializeClient() {
        LivingEntityFeatureRenderEvents.ALLOW_CAPE_RENDER.register((abstractClientPlayerEntity) -> {
            return !(abstractClientPlayerEntity.canRenderCapeTexture() && !abstractClientPlayerEntity.isInvisible() && !ThonkUtilCapes.getCape(abstractClientPlayerEntity).equals(ItemStack.EMPTY) && ThonkUtilCapes.getCape(abstractClientPlayerEntity).getItem() instanceof CapeItem);
        });
    }
}
