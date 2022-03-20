package com.jab125.thonkutil;

import com.jab125.thonkutil.api.AnimatedCapeItem;
import com.jab125.thonkutil.api.CapeItem;
import com.jab125.thonkutil.impl.CustomRightElytraEntityModel;
import com.jab125.thonkutil.impl.TextureLoader;
import com.jab125.thonkutil.impl.CustomLeftElytraEntityModel;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRenderEvents;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class ThonkUtilCapesClient implements ClientModInitializer {
    public static final EntityModelLayer TWO_LEFT_WINGED_ELYTRA = new EntityModelLayer(new Identifier("thonkutil", "two_left_winged_elytra"), "two_left_winged_elytra_render_layer");
    public static final EntityModelLayer TWO_RIGHT_WINGED_ELYTRA = new EntityModelLayer(new Identifier("thonkutil", "two_right_winged_elytra"), "two_right_winged_elytra_render_layer");
    public static final EntityModelLayer CAPE = new EntityModelLayer(new Identifier("thonkutil", "cape"), "cape_render_layer");
    /**
     * Runs the mod initializer on the client environment.
     */
    @Override
    public void onInitializeClient() {
        LivingEntityFeatureRenderEvents.ALLOW_CAPE_RENDER.register((abstractClientPlayerEntity) -> {
            return !(abstractClientPlayerEntity.canRenderCapeTexture() && !abstractClientPlayerEntity.isInvisible() && !ThonkUtilCapes.getCape(abstractClientPlayerEntity).equals(ItemStack.EMPTY) && ThonkUtilCapes.getCape(abstractClientPlayerEntity).getItem() instanceof CapeItem);
        });
        EntityModelLayerRegistry.registerModelLayer(TWO_LEFT_WINGED_ELYTRA, CustomLeftElytraEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(TWO_RIGHT_WINGED_ELYTRA, CustomRightElytraEntityModel::getTexturedModelData);
    }

    public static void loadCapes(AnimatedCapeItem cape) {
        TextureLoader.applyAnimatedCape(cape);
    }
}
