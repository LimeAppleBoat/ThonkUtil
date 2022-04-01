package com.jab125.thonkutil.cosmetics.exclamationmark;// Made with Blockbench 4.2.1, template by Jab125
// Exported for Minecraft version 1.18.2 with Yarn mappings
// Paste this class into your mod and generate all required imports

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;

public class ExclamationMarkModel<T extends Entity> extends EntityModel<T> {

	//public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(new Identifier("thonkutil", "exclaimation_mark"), "main");
	private final ModelPart bb_main;

	public ExclamationMarkModel(ModelPart root) {
		this.bb_main = root.getChild("bb_main");
	}

	public static TexturedModelData createTexturedModelData() {
		ModelData modeldata = new ModelData();
		ModelPartData modelpartdata = modeldata.getRoot();

		ModelPartData bb_main = modelpartdata.addChild("bb_main", ModelPartBuilder.create().uv(8, 0).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-1.0F, -12.0F, -1.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		return TexturedModelData.of(modeldata, 16, 16);
	}

	@Override
	public void setAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.bb_main.setAngles(0,0,0);
	}

	@Override
	public void render(MatrixStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		bb_main.render(poseStack, buffer, packedLight, packedOverlay);
	}

	@Override
	public void animateModel(T entity, float limbAngle, float limbDistance, float tickDelta) {
		super.animateModel(entity, limbAngle, limbDistance, tickDelta);
	}
}