package com.jab125.thonkutil.mixin;

import com.jab125.thonkutil.impl.ItemCapeRenderer;
import com.jab125.thonkutil.impl.ItemElytraRenderer;
import com.jab125.thonkutil.impl.TwoWingedItemElytraRenderer;
import net.minecraft.client.render.entity.ArmorStandEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ArmorStandEntityRenderer.class)
public class ArmorStandRendererMixin {
    @Inject(method = "<init>", at = @At("TAIL"))
    @SuppressWarnings("unchecked")
    public void initInject(EntityRendererFactory.Context ctx, CallbackInfo ci) {
        ((LivingEntityRendererAccessor) (Object) this).callAddFeature(new ItemCapeRenderer((ArmorStandEntityRenderer) (Object) this, ctx.getModelLoader()));
        ((LivingEntityRendererAccessor) (Object) this).callAddFeature(new ItemElytraRenderer((ArmorStandEntityRenderer) (Object) this, ctx.getModelLoader()));
    //    ((LivingEntityRendererAccessor) (Object) this).callAddFeature(new ItemElytraRenderer((PlayerEntityRenderer) (Object) this, ctx.getModelLoader()));
    //    ((LivingEntityRendererAccessor) (Object) this).callAddFeature(new TwoWingedItemElytraRenderer((PlayerEntityRenderer) (Object) this, ctx.getModelLoader()));
    }
}
