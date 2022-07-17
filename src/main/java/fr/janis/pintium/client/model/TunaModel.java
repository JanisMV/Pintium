package fr.janis.pintium.client.model;// Made with Blockbench 3.8.2
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import fr.janis.pintium.entities.TunaEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;


public class TunaModel<T extends TunaEntity> extends EntityModel<TunaEntity> {
    private final ModelPart body_front;
    private final ModelPart bone;
    private final ModelPart bone2;
    private final ModelPart body_back;
    private final ModelPart dick;
    private final ModelPart tail;
    private final ModelPart head;
    private final ModelPart fin_back_1;
    private final ModelPart fin_back_2;
    private final ModelPart fin_left;
    private final ModelPart fin_right;

    public TunaModel() {
        texWidth = 32;
        texHeight = 32;

        body_front = new ModelPart(this);
        body_front.setPos(0.0F, 18.0F, -4.0F);
        body_front.texOffs(0, 0).addBox(-1.5F, -2.5F, 0.0F, 3.0F, 5.0F, 8.0F, 0.0F, false);

        bone = new ModelPart(this);
        bone.setPos(-1.5F, 1.5F, 0.0F);
        body_front.addChild(bone);
        setRotationAngle(bone, -1.5708F, 0.0F, -0.7854F);
        bone.texOffs(0, 0).addBox(-2.0F, -2.0F, 0.0F, 2.0F, 2.0F, 0.0F, 0.0F, false);

        bone2 = new ModelPart(this);
        bone2.setPos(1.5F, 1.5F, 0.0F);
        body_front.addChild(bone2);
        setRotationAngle(bone2, -1.5708F, 0.0F, 0.7854F);
        bone2.texOffs(4, 0).addBox(0.0F, -2.0F, 0.0F, 2.0F, 2.0F, 0.0F, 0.0F, false);

        body_back = new ModelPart(this);
        body_back.setPos(0.0F, 18.0F, 4.0F);
        body_back.texOffs(0, 13).addBox(-1.5F, -2.5F, 0.0F, 3.0F, 5.0F, 8.0F, 0.0F, false);

        dick = new ModelPart(this);
        dick.setPos(0.0F, 6.0F, -4.0F);
        body_back.addChild(dick);
        dick.texOffs(0, 0).addBox(0.0F, -4.0F, 10.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        dick.texOffs(0, 0).addBox(0.0F, -4.0F, 6.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
        dick.texOffs(0, 0).addBox(-1.0F, -4.0F, 10.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        dick.texOffs(0, 0).addBox(1.0F, -4.0F, 10.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        tail = new ModelPart(this);
        tail.setPos(0.0F, 0.0F, 8.0F);
        body_back.addChild(tail);
        tail.texOffs(20, 10).addBox(0.0F, -2.5F, 0.0F, 0.0F, 5.0F, 6.0F, 0.0F, false);

        head = new ModelPart(this);
        head.setPos(0.0F, 18.0F, -4.0F);
        head.texOffs(22, 0).addBox(-1.0F, -2.0F, -3.0F, 2.0F, 4.0F, 3.0F, 0.0F, false);

        fin_back_1 = new ModelPart(this);
        fin_back_1.setPos(0.0F, 13.5F, 1.0F);
        fin_back_1.texOffs(4, 2).addBox(0.0F, 0.0F, 1.0F, 0.0F, 2.0F, 2.0F, 0.0F, false);

        fin_back_2 = new ModelPart(this);
        fin_back_2.setPos(0.0F, 13.5F, 3.0F);
        fin_back_2.texOffs(2, 3).addBox(0.0F, 0.0F, 1.0F, 0.0F, 2.0F, 3.0F, 0.0F, false);

        fin_left = new ModelPart(this);
        fin_left.setPos(0.0F, 24.0F, 0.0F);


        fin_right = new ModelPart(this);
        fin_right.setPos(0.0F, 24.0F, 0.0F);

    }

    @Override
    public void setupAnim(TunaEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
        float f = 1.0F;
        float f1 = 1.0F;
        if (!entity.isInWater()) {
            f = 1.3F;
            f1 = 1.7F;
        }

        this.body_back.yRot = -f * 0.25F * Mth.sin(f1 * 0.6F * ageInTicks);
    }

    @Override
    public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        body_front.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        body_back.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        fin_back_1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        fin_back_2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        fin_left.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        fin_right.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}