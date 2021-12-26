package com.jab125.thonkutil.mixin;

import com.jab125.thonkutil.impl.PlayerEntityExtension;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Function;

@Mixin(PlayerEntityModel.class)
public class PlayerEntityModelMixin<T extends LivingEntity> extends BipedEntityModel<T> implements PlayerEntityExtension {
    private static final String ITEM_CAPE = "item_cape";

    @Mutable
    @Final
    private ModelPart item_cape;
    public PlayerEntityModelMixin(ModelPart root) {
        super(root);
    }

    public PlayerEntityModelMixin(ModelPart root, Function<Identifier, RenderLayer> renderLayerFactory) {
        super(root, renderLayerFactory);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void injectInit(ModelPart root, boolean thinArms, CallbackInfo ci) {
        this.item_cape = root.getChild("item_cape");
    }

    @Override
    public ModelPart thonkutil_getItemCape() {
        return item_cape;
    }

    @Inject(method = "getTexturedModelData", at = @At("TAIL"), cancellable = true)
    private static void modify(Dilation dilation, boolean slim, CallbackInfoReturnable<ModelData> cir) {
        var a = cir.getReturnValue();
        a.getRoot().addChild("item_cape", ModelPartBuilder.create().uv(0, 0).cuboid(-5.0F, 0.0F, -1.0F, 10.0F, 16.0F, 1.0F, dilation, 1.0F, 0.5F), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        cir.setReturnValue(a);
    }

    @Override
    public void renderItemCape(MatrixStack matrices, VertexConsumer vertices, int light, int overlay) {
        this.item_cape.render(matrices, vertices, light, overlay);
    }
}
