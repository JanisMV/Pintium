package fr.janis.pintium.client.model;// Made with Blockbench 3.8.2
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import fr.janis.pintium.entities.TunaEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;


public class TunaModel<T extends TunaEntity> extends EntityModel<TunaEntity> {
    private final ModelRenderer body_front;
    private final ModelRenderer bone;
    private final ModelRenderer bone2;
    private final ModelRenderer body_back;
    private final ModelRenderer dick;
    private final ModelRenderer tail;
    private final ModelRenderer head;
    private final ModelRenderer fin_back_1;
    private final ModelRenderer fin_back_2;
    private final ModelRenderer fin_left;
    private final ModelRenderer fin_right;

    public TunaModel() {
        texWidth = 32;
        texHeight = 32;

        body_front = new ModelRenderer(this);
        body_front.setPos(0.0F, 18.0F, -4.0F);
        body_front.texOffs(0, 0).addBox(-1.5F, -2.5F, 0.0F, 3.0F, 5.0F, 8.0F, 0.0F, false);

        bone = new ModelRenderer(this);
        bone.setPos(-1.5F, 1.5F, 0.0F);
        body_front.addChild(bone);
        setRotationAngle(bone, -1.5708F, 0.0F, -0.7854F);
        bone.texOffs(0, 0).addBox(-2.0F, -2.0F, 0.0F, 2.0F, 2.0F, 0.0F, 0.0F, false);

        bone2 = new ModelRenderer(this);
        bone2.setPos(1.5F, 1.5F, 0.0F);
        body_front.addChild(bone2);
        setRotationAngle(bone2, -1.5708F, 0.0F, 0.7854F);
        bone2.texOffs(4, 0).addBox(0.0F, -2.0F, 0.0F, 2.0F, 2.0F, 0.0F, 0.0F, false);

        body_back = new ModelRenderer(this);
        body_back.setPos(0.0F, 18.0F, 4.0F);
        body_back.texOffs(0, 13).addBox(-1.5F, -2.5F, 0.0F, 3.0F, 5.0F, 8.0F, 0.0F, false);

        dick = new ModelRenderer(this);
        dick.setPos(0.0F, 6.0F, -4.0F);
        body_back.addChild(dick);
        dick.texOffs(0, 0).addBox(0.0F, -4.0F, 10.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        dick.texOffs(0, 0).addBox(0.0F, -4.0F, 6.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
        dick.texOffs(0, 0).addBox(-1.0F, -4.0F, 10.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        dick.texOffs(0, 0).addBox(1.0F, -4.0F, 10.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        tail = new ModelRenderer(this);
        tail.setPos(0.0F, 0.0F, 8.0F);
        body_back.addChild(tail);
        tail.texOffs(20, 10).addBox(0.0F, -2.5F, 0.0F, 0.0F, 5.0F, 6.0F, 0.0F, false);

        head = new ModelRenderer(this);
        head.setPos(0.0F, 18.0F, -4.0F);
        head.texOffs(22, 0).addBox(-1.0F, -2.0F, -3.0F, 2.0F, 4.0F, 3.0F, 0.0F, false);

        fin_back_1 = new ModelRenderer(this);
        fin_back_1.setPos(0.0F, 13.5F, 1.0F);
        fin_back_1.texOffs(4, 2).addBox(0.0F, 0.0F, 1.0F, 0.0F, 2.0F, 2.0F, 0.0F, false);

        fin_back_2 = new ModelRenderer(this);
        fin_back_2.setPos(0.0F, 13.5F, 3.0F);
        fin_back_2.texOffs(2, 3).addBox(0.0F, 0.0F, 1.0F, 0.0F, 2.0F, 3.0F, 0.0F, false);

        fin_left = new ModelRenderer(this);
        fin_left.setPos(0.0F, 24.0F, 0.0F);


        fin_right = new ModelRenderer(this);
        fin_right.setPos(0.0F, 24.0F, 0.0F);

    }

    @Override
    public void setupAnim(TunaEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
        //previously the render function, render code was moved to a method below
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        body_front.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        body_back.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        fin_back_1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        fin_back_2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        fin_left.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        fin_right.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}