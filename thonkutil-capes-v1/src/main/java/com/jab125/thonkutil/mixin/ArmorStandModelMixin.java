package com.jab125.thonkutil.mixin;

import com.jab125.thonkutil.impl.ArmorStandModelExtension;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.ArmorStandArmorEntityModel;
import net.minecraft.client.render.entity.model.ArmorStandEntityModel;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.decoration.ArmorStandEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ArmorStandEntityModel.class)
public class ArmorStandModelMixin implements ArmorStandModelExtension {
    @Unique
    private static final String CLOAK = "thonkutilcloak";
    @Mutable
    @Unique
    @Final
    private ModelPart cloak;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void thonkutil$injectCape(ModelPart modelPart, CallbackInfo ci) {
        ((ModelPartAccessor)(Object)modelPart).getChildren().forEach(((s, modelPartData) -> {
            System.out.println("A: " + s);
        }));
        this.cloak = modelPart.getChild(CLOAK);
    }

    private static Dilation dilation;
    @Inject(method = "getTexturedModelData", at = @At(value = "HEAD"))
    private static void thonkutil$textureData(CallbackInfoReturnable<TexturedModelData> cir) {
        ArmorStandModelMixin.dilation = Dilation.NONE;
    }


    // ignore those BLARING warnings
    @ModifyVariable(method = "getTexturedModelData", at = @At(value = "STORE", target = "Lnet/minecraft/client/render/entity/model/ArmorStandArmorEntityModel;getTexturedModelData(Lnet/minecraft/client/model/Dilation;)Lnet/minecraft/client/model/TexturedModelData;"))
    private static ModelPartData thonkutil$getModelPartData(ModelPartData data) {
      //  System.out.println(data.toString());
        data.addChild(CLOAK, ModelPartBuilder.create().uv(0, 0).cuboid(-5.0F, 0.0F, -1.0F, 10.0F, 16.0F, 1.0F, dilation, 1.0F, 0.5F), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        //System.out.println("ADDED, CHILDREN:");
      //  ((ModelPartDataAccessor)data).getChildren().forEach(((s, modelPartData) -> System.out.println(s)));
        return data;
    }

    @Inject(method = "setAngles(Lnet/minecraft/entity/decoration/ArmorStandEntity;FFFFF)V", at = @At(value = "TAIL"))
    private void thonkutil$setAngles(ArmorStandEntity armorStandEntity, float f, float g, float h, float i, float j, CallbackInfo ci) {
        if (armorStandEntity.getEquippedStack(EquipmentSlot.CHEST).isEmpty()) {
            if (armorStandEntity.isInSneakingPose()) {
                this.cloak.pivotZ = 1.4F;
                this.cloak.pivotY = 1.85F;
            } else {
                this.cloak.pivotZ = 0.0F;
                this.cloak.pivotY = 0.0F;
            }
        } else if (armorStandEntity.isInSneakingPose()) {
            this.cloak.pivotZ = 0.3F;
            this.cloak.pivotY = 0.8F;
        } else {
            this.cloak.pivotZ = -1.1F;
            this.cloak.pivotY = -0.85F;
        }
    }

    @Override
    public void thonkutil$renderCape(MatrixStack matrices, VertexConsumer vertices, int light, int overlay) {
        this.cloak.render(matrices, vertices, light, overlay);
    }
}
