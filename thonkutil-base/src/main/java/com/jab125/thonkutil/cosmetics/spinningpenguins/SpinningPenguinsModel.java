package com.jab125.thonkutil.cosmetics.spinningpenguins;// Made with Blockbench 4.2.1, template by Jab125
// Exported for Minecraft version 1.18.2 with Yarn mappings
// Paste this class into your mod and generate all required imports

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;

public class SpinningPenguinsModel<T extends Entity> extends EntityModel<T> {

	//public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(new Identifier("modid", "textures/entity/cosmetics/spinning_penguins.png"), "main");
	private final ModelPart penguin;

	public SpinningPenguinsModel(ModelPart root) {
		this.penguin = root.getChild("penguin");
	}

	public static TexturedModelData createTexturedModelData() {
		ModelData modeldata = new ModelData();
		ModelPartData modelpartdata = modeldata.getRoot();

		ModelPartData penguin = modelpartdata.addChild("penguin", ModelPartBuilder.create().uv(0, 11).cuboid(-0.5F, -1.0F, -4.0F, 1.0F, 1.0F, 4.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-2.0F, -5.0F, -8.0F, 4.0F, 5.0F, 4.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-0.5F, -3.25F, -8.5F, 1.0F, 1.0F, 1.0F, new Dilation(-0.25F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		return TexturedModelData.of(modeldata, 16, 16);
	}

	@Override
	public void setAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void render(MatrixStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		penguin.render(poseStack, buffer, packedLight, packedOverlay);
	}
}