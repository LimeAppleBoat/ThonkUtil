package com.jab125.thonkutil.mixin;

import com.jab125.thonkutil.impl.ItemCapeRenderer;
import com.jab125.thonkutil.impl.ItemElytraRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.feature.CapeFeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@net.fabricmc.api.Environment(net.fabricmc.api.EnvType.CLIENT)
@Mixin(PlayerEntityRenderer.class)
public abstract class PlayerEntityMixin<T extends LivingEntity, M extends EntityModel<T>> {
    @Inject(method = "<init>", at = @At("TAIL"))
    @SuppressWarnings("unchecked")
    public void initInject(EntityRendererFactory.Context ctx, boolean slim, CallbackInfo ci) {
        ((LivingEntityRendererAccessor) (Object)this).callAddFeature(new ItemCapeRenderer((PlayerEntityRenderer)(Object)this, ctx.getModelLoader()));
        ((LivingEntityRendererAccessor) (Object)this).callAddFeature(new ItemElytraRenderer((PlayerEntityRenderer)(Object)this, ctx.getModelLoader()));
    }
}