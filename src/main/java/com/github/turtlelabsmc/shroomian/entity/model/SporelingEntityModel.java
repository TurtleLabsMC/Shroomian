package com.github.turtlelabsmc.shroomian.entity.model;

import com.github.turtlelabsmc.shroomian.entity.SporelingEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.util.math.MathHelper;

public class SporelingEntityModel extends SinglePartEntityModel<SporelingEntity>
{
	private static final TexturedModelData MODEL_DATA;
	private static final float DEG_RAD_2 = (float)Math.toRadians(2);
	private static final float DEG_RAD_20 = (float)Math.toRadians(20);

	private final ModelPart root;
	private final ModelPart cap;
	private final ModelPart body;
	private final ModelPart arm_left;
	private final ModelPart leg_left;
	private final ModelPart leg_right;
	private final ModelPart arm_right;

	public SporelingEntityModel()
	{
		this.root = MODEL_DATA.createModel();
		this.cap = root.getChild("cap");
		this.body = root.getChild("body");
		this.arm_left = root.getChild("arm_left");
		this.arm_right = root.getChild("arm_right");
		this.leg_right = root.getChild("leg_right");
		this.leg_left = root.getChild("leg_left");
	}

	@Override
	public ModelPart getPart()
	{
		return this.root;
	}

	@Override
	public void setAngles(SporelingEntity entity, float limbSwing, float limbSwingAmount, float animationProgress, float netHeadYaw, float headPitch){
		//if (entity.getNavigation().isFollowingPath()) {
			this.cap.roll = MathHelper.sin(-20 + animationProgress * 0.3f) * DEG_RAD_2;
			this.body.roll = MathHelper.sin(animationProgress * 0.3f) * DEG_RAD_2;
			this.arm_left.pitch = MathHelper.cos(animationProgress * 0.3f) * DEG_RAD_20;
			this.leg_left.pitch = MathHelper.cos(animationProgress * 0.3f) * -DEG_RAD_20;
			this.leg_right.pitch = MathHelper.cos(animationProgress * 0.3f) * DEG_RAD_20;
			this.arm_right.pitch = MathHelper.cos(animationProgress * 0.3f) * -DEG_RAD_20;
		/*} else {
			this.cap.roll = 0.0f;
			this.body.roll = 0.0f;
			this.arm_left.pitch = 0.0f;
			this.leg_left.pitch = 0.0f;
			this.leg_right.pitch = 0.0f;
			this.arm_right.pitch = 0.0f;
		}*/
	}

	static {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();

		modelPartData.addChild("cap", ModelPartBuilder.create().uv(0, 0).cuboid(-5.0F, -3.1F, -6.2F, 10.0F, 3.0F, 10.0F).uv(0, 13).cuboid(-3.0F, -0.5F, -4.2F, 6.0F, 1.0F, 6.0F), ModelTransform.pivot(0.0F, 18.6F, 0.2F));
		modelPartData.addChild("body", ModelPartBuilder.create().uv(0, 20).cuboid(-2.0F, -4.0F, -3.0F, 4.0F, 4.0F, 4.0F), ModelTransform.pivot(0.0F, 22.0F, 0.0F));
		modelPartData.addChild("arm_left", ModelPartBuilder.create().uv(0, 5).cuboid(-0.1F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F), ModelTransform.pivot(2.0F, 19.0F, -1.0F));
		modelPartData.addChild("leg_left", ModelPartBuilder.create().uv(12, 20).cuboid(-2.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F), ModelTransform.pivot(2.0F, 22.0F, -1.0F));
		modelPartData.addChild("leg_right", ModelPartBuilder.create().uv(18, 13).cuboid(0.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F), ModelTransform.pivot(-2.0F, 22.0F, -1.0F));
		modelPartData.addChild("arm_right", ModelPartBuilder.create().uv(0, 0).cuboid(-1.9F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F), ModelTransform.pivot(-2.0F, 19.0F, -1.0F));

		MODEL_DATA = TexturedModelData.of(modelData, 64, 64);
	}
}