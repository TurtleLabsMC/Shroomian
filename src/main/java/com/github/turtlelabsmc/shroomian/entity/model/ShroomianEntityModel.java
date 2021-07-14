package com.github.turtlelabsmc.shroomian.entity.model;

import com.github.turtlelabsmc.shroomian.entity.ShroomianEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
public class ShroomianEntityModel extends SinglePartEntityModel<ShroomianEntity>
{
    private static final TexturedModelData MODEL_DATA;

    private final ModelPart root;
    private final ModelPart cap;
    private final ModelPart body;
    private final ModelPart arm_left;
    private final ModelPart leg_left;
    private final ModelPart leg_right;
    private final ModelPart arm_right;

    public ShroomianEntityModel()
    {
        this.root = MODEL_DATA.createModel();
        this.cap = root.getChild("cap");
        this.body = root.getChild("body");
        this.arm_left = root.getChild("arm_left");
        this.arm_right = root.getChild("arm_right");
        this.leg_right = root.getChild("leg_right");
        this.leg_left = root.getChild("leg_left");
    }

    public ModelPart getPart()
    {
        return this.root;
    }

    @Override
    public void setAngles(ShroomianEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch)
    {
        this.cap.setAngles(-0.5236F, 0.0F, 0.0F);

        //this.body.pitch = ((float) Math.PI / 2F);
        this.leg_right.pitch = MathHelper.cos(limbAngle * 0.6662F) * 1.4F * limbDistance;
        this.leg_left.pitch = MathHelper.cos(limbAngle * 0.6662F + (float) Math.PI) * 1.4F * limbDistance;
        this.arm_left.roll = animationProgress;
        this.arm_right.roll = -animationProgress;
    }

    static {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();

        modelPartData.addChild("cap", ModelPartBuilder.create().uv(0, 0).cuboid(-6.0F, -3.5F, -6.0F, 12.0F, 5.0F, 12.0F).cuboid(-6.0F, -3.5F, -6.0F, 12.0F, 5.0F, 12.0F).uv(0, 17).cuboid(-5.0F, 1.5F, -5.0F, 10.0F, 1.0F, 10.0F), ModelTransform.pivot(0.0F, 15.6F, 1.2F));
        modelPartData.addChild("body", ModelPartBuilder.create().uv(0, 28).cuboid(-3.0F, -8.0F, -3.0F, 6.0F, 6.0F, 6.0F), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
        modelPartData.addChild("arm_left", ModelPartBuilder.create().uv(0, 0).cuboid(-1.1F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F), ModelTransform.pivot(4.0F, 18.0F, 0.0F));
        modelPartData.addChild("arm_right", ModelPartBuilder.create().uv(0, 0).cuboid(-0.9F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F), ModelTransform.pivot(-4.0F, 18.0F, 0.0F));
        modelPartData.addChild("leg_left", ModelPartBuilder.create().uv(0, 6).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F), ModelTransform.pivot(2.0F, 22.0F, 0.0F));
        modelPartData.addChild("leg_right", ModelPartBuilder.create().uv(0, 6).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F), ModelTransform.pivot(-2.0F, 22.0F, 0.0F));

        MODEL_DATA = TexturedModelData.of(modelData, 64, 64);
    }
}
        /* cap.setPivot(0.0F, 15.6F, 1.2F);
        setRotationAngle(cap, -0.5236F, 0.0F, 0.0F);
        cap.setTextureOffset(0, 0).addCuboid(-6.0F, -3.5F, -6.0F, 12.0F, 5.0F, 12.0F, 0.0F, false);
        cap.setTextureOffset(0, 0).addCuboid(-6.0F, -3.5F, -6.0F, 12.0F, 5.0F, 12.0F, 0.0F, false);
        cap.setTextureOffset(0, 17).addCuboid(-5.0F, 1.5F, -5.0F, 10.0F, 1.0F, 10.0F, 0.0F, false);

        Body = new ModelPart(this);
        Body.setPivot(0.0F, 24.0F, 0.0F);
        Body.setTextureOffset(0, 28).addCuboid(-3.0F, -8.0F, -3.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);

        arm_left = new ModelPart(this);
        arm_left.setPivot(4.0F, 18.0F, 0.0F);
        arm_left.setTextureOffset(0, 0).addCuboid(-1.1F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

        leg_left = new ModelPart(this);
        leg_left.setPivot(2.0F, 22.0F, 0.0F);
        leg_left.setTextureOffset(0, 6).addCuboid(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        leg_right = new ModelPart(this);
        leg_right.setPivot(-2.0F, 22.0F, 0.0F);
        leg_right.setTextureOffset(0, 6).addCuboid(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, true);

        arm_right = new ModelPart(this);
        arm_right.setPivot(-4.0F, 18.0F, 0.0F);
        arm_right.setTextureOffset(0, 0).addCuboid(-0.9F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, true);
    }

    @Override
    public void render(MatrixStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){

        cap.render(matrixStack, buffer, packedLight, packedOverlay);
        Body.render(matrixStack, buffer, packedLight, packedOverlay);
        arm_left.render(matrixStack, buffer, packedLight, packedOverlay);
        leg_left.render(matrixStack, buffer, packedLight, packedOverlay);
        leg_right.render(matrixStack, buffer, packedLight, packedOverlay);
        arm_right.render(matrixStack, buffer, packedLight, packedOverlay);
    }
    public void setRotationAngle(ModelPart bone, float x, float y, float z) {
        bone.pitch = x;
        bone.yaw = y;
        bone.roll = z;
    } */



