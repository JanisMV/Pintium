package fr.janis.pintium.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import fr.janis.pintium.entities.RatelEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class RatelModel<T extends RatelEntity> extends EntityModel<RatelEntity> {
    private final ModelRenderer head;
    private final ModelRenderer body;
    private final ModelRenderer body_rotation;
    private final ModelRenderer leg1;
    private final ModelRenderer leg2;
    private final ModelRenderer leg3;
    private final ModelRenderer leg4;
    private final ModelRenderer tail;
    private final ModelRenderer tail_rotation;

    public RatelModel() {
        texWidth = 64;
        texHeight = 64;

        head = new ModelRenderer(this);
        head.setPos(1.0F, 16.5F, -3.0F);
        head.texOffs(0, 0).addBox(-5.0F, -2.0F, -5.0F, 8.0F, 6.0F, 6.0F, 0.0F, false);
        head.texOffs(20, 12).addBox(-3.0F, 2.0F, -8.0F, 4.0F, 2.0F, 3.0F, 0.0F, false);

        body = new ModelRenderer(this);
        body.setPos(0.0F, 7.5F, 3.5F);


        body_rotation = new ModelRenderer(this);
        body_rotation.setPos(0.0F, 9.5F, -1.5F);
        body.addChild(body_rotation);
        setRotationAngle(body_rotation, 1.5708F, 0.0F, 0.0F);
        body_rotation.texOffs(0, 12).addBox(-3.0F, -4.5F, -2.0F, 6.0F, 11.0F, 4.0F, 0.0F, false);

        leg1 = new ModelRenderer(this);
        leg1.setPos(5.0F, 17.5F, 7.0F);
        leg1.texOffs(8, 27).addBox(-8.001F, 0.5F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

        leg2 = new ModelRenderer(this);
        leg2.setPos(1.0F, 17.5F, 7.0F);
        leg2.texOffs(0, 27).addBox(0.001F, 0.5F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

        leg3 = new ModelRenderer(this);
        leg3.setPos(5.0F, 17.5F, 0.0F);
        leg3.texOffs(26, 24).addBox(-8.001F, 0.5F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

        leg4 = new ModelRenderer(this);
        leg4.setPos(1.0F, 17.5F, 0.0F);
        leg4.texOffs(18, 26).addBox(0.001F, 0.5F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

        tail = new ModelRenderer(this);
        tail.setPos(4.0F, 13.0F, 10.0F);


        tail_rotation = new ModelRenderer(this);
        tail_rotation.setPos(-4.0F, 2.5F, 1.5F);
        tail.addChild(tail_rotation);
        setRotationAngle(tail_rotation, 1.5708F, 0.0F, 0.0F);
        tail_rotation.texOffs(20, 17).addBox(-1.0F, -3.5F, -2.5F, 2.0F, 7.0F, 2.0F, 0.0F, false);
    }

    @Override
    public void setupAnim(RatelEntity p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {
        this.head.xRot = p_225597_6_ * ((float)Math.PI / 180F);
        this.head.yRot = p_225597_5_ * ((float)Math.PI / 180F);
        this.leg1.xRot = MathHelper.cos(p_225597_2_ * 0.6662F) * 1.4F * p_225597_3_;
        this.leg2.xRot = MathHelper.cos(p_225597_2_ * 0.6662F + (float)Math.PI) * 1.4F * p_225597_3_;
        this.leg3.xRot = MathHelper.cos(p_225597_2_ * 0.6662F + (float)Math.PI) * 1.4F * p_225597_3_;
        this.leg4.xRot = MathHelper.cos(p_225597_2_ * 0.6662F) * 1.4F * p_225597_3_;
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        leg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        leg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        leg3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        leg4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        tail.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}