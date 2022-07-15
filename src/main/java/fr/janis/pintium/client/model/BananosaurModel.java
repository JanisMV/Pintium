package fr.janis.pintium.client.model;


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import fr.janis.pintium.entities.BananosaurEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class BananosaurModel<T extends BananosaurEntity> extends EntityModel<BananosaurEntity> {
    private final ModelRenderer body;
    private final ModelRenderer rotation;
    private final ModelRenderer head;
    private final ModelRenderer leg1;
    private final ModelRenderer leg2;
    private final ModelRenderer leg3;
    private final ModelRenderer leg4;
    private final ModelRenderer body2;
    private final ModelRenderer tail;
    private final ModelRenderer strange_thing_of_banana1;
    private final ModelRenderer strange_thing_of_banana2;
    private final ModelRenderer strange_thing_of_banana3;

    public BananosaurModel() {
        texWidth = 64;
        texHeight = 32;

        body = new ModelRenderer(this);
        body.setPos(0.0F, 5.0F, 2.0F);


        rotation = new ModelRenderer(this);
        rotation.setPos(0.0F, -3.0F, -3.0F);
        body.addChild(rotation);
        setRotationAngle(rotation, 0.9163F, 0.0F, 0.0F);
        rotation.texOffs(18, 4).addBox(-2.0F, -12.0F, -10.0F, 5.0F, 18.0F, 5.0F, 0.0F, false);

        head = new ModelRenderer(this);
        head.setPos(0.0F, 4.0F, -8.0F);
        head.texOffs(0, 0).addBox(-4.0F, -9.0F, -13.0F, 8.0F, 8.0F, 6.0F, 0.0F, false);

        leg1 = new ModelRenderer(this);
        leg1.setPos(4.0F, 12.0F, 7.0F);
        leg1.texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

        leg2 = new ModelRenderer(this);
        leg2.setPos(-4.0F, 12.0F, 7.0F);
        leg2.texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

        leg3 = new ModelRenderer(this);
        leg3.setPos(4.0F, 12.0F, -6.0F);
        leg3.texOffs(0, 16).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

        leg4 = new ModelRenderer(this);
        leg4.setPos(-4.0F, 12.0F, -6.0F);
        leg4.texOffs(0, 16).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

        body2 = new ModelRenderer(this);
        body2.setPos(0.0F, 24.0F, 0.0F);
        body2.texOffs(0, 0).addBox(-3.0F, -14.0F, -4.0F, 7.0F, 4.0F, 15.0F, 0.0F, false);

        tail = new ModelRenderer(this);
        tail.setPos(-0.5F, 12.5F, 11.5F);
        setRotationAngle(tail, 0.6545F, 0.0F, 0.0F);
        tail.texOffs(0, 0).addBox(-2.5F, -1.5F, -0.5F, 7.0F, 3.0F, 18.0F, 0.0F, false);
        tail.texOffs(0, 0).addBox(-2.0F, -2.0F, 16.0F, 6.0F, 1.0F, 4.0F, 0.0F, false);
        tail.texOffs(0, 0).addBox(-2.0F, 1.0F, 16.0F, 6.0F, 1.0F, 4.0F, 0.0F, false);
        tail.texOffs(0, 0).addBox(4.0F, -2.0F, 16.0F, 1.0F, 4.0F, 4.0F, 0.0F, false);
        tail.texOffs(0, 0).addBox(-3.0F, -2.0F, 16.0F, 1.0F, 4.0F, 4.0F, 0.0F, false);

        strange_thing_of_banana1 = new ModelRenderer(this);
        strange_thing_of_banana1.setPos(0.0F, 25.0F, -11.0F);
        setRotationAngle(strange_thing_of_banana1, -0.48F, 0.0F, 0.0F);
        strange_thing_of_banana1.texOffs(0, 0).addBox(-2.0F, -26.0F, -9.0F, 5.0F, 5.0F, 1.0F, 0.0F, false);

        strange_thing_of_banana2 = new ModelRenderer(this);
        strange_thing_of_banana2.setPos(4.0F, 1.0F, -10.0F);
        setRotationAngle(strange_thing_of_banana2, 0.0F, 0.0F, 0.829F);
        strange_thing_of_banana2.texOffs(0, 0).addBox(-1.0F, -3.0F, -5.0F, 1.0F, 4.0F, 5.0F, 0.0F, false);

        strange_thing_of_banana3 = new ModelRenderer(this);
        strange_thing_of_banana3.setPos(-3.5F, 0.0F, -12.0F);
        setRotationAngle(strange_thing_of_banana3, 0.0F, 0.0F, -0.829F);
        strange_thing_of_banana3.texOffs(0, 0).addBox(-0.5F, -2.0F, -2.0F, 1.0F, 4.0F, 4.0F, 0.0F, false);
    }

    @Override
    public void setupAnim(BananosaurEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
        this.head.xRot = headPitch * ((float)Math.PI / 180F);
        this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
        this.leg1.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.leg2.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.leg3.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.leg4.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        leg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        leg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        leg3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        leg4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        body2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        tail.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        strange_thing_of_banana1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        strange_thing_of_banana2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        strange_thing_of_banana3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}