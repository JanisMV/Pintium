package net.minecraft.client.renderer.entity.model;

import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.passive.PolarBearEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PolarBearModel<T extends PolarBearEntity> extends QuadrupedModel<T> {
   public PolarBearModel() {
      super(12, 0.0F, true, 16.0F, 4.0F, 2.25F, 2.0F, 24);
      this.texWidth = 128;
      this.texHeight = 64;
      this.head = new ModelRenderer(this, 0, 0);
      this.head.addBox(-3.5F, -3.0F, -3.0F, 7.0F, 7.0F, 7.0F, 0.0F);
      this.head.setPos(0.0F, 10.0F, -16.0F);
      this.head.texOffs(0, 44).addBox(-2.5F, 1.0F, -6.0F, 5.0F, 3.0F, 3.0F, 0.0F);
      this.head.texOffs(26, 0).addBox(-4.5F, -4.0F, -1.0F, 2.0F, 2.0F, 1.0F, 0.0F);
      ModelRenderer modelrenderer = this.head.texOffs(26, 0);
      modelrenderer.mirror = true;
      modelrenderer.addBox(2.5F, -4.0F, -1.0F, 2.0F, 2.0F, 1.0F, 0.0F);
      this.body = new ModelRenderer(this);
      this.body.texOffs(0, 19).addBox(-5.0F, -13.0F, -7.0F, 14.0F, 14.0F, 11.0F, 0.0F);
      this.body.texOffs(39, 0).addBox(-4.0F, -25.0F, -7.0F, 12.0F, 12.0F, 10.0F, 0.0F);
      this.body.setPos(-2.0F, 9.0F, 12.0F);
      int i = 10;
      this.leg0 = new ModelRenderer(this, 50, 22);
      this.leg0.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 8.0F, 0.0F);
      this.leg0.setPos(-3.5F, 14.0F, 6.0F);
      this.leg1 = new ModelRenderer(this, 50, 22);
      this.leg1.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 8.0F, 0.0F);
      this.leg1.setPos(3.5F, 14.0F, 6.0F);
      this.leg2 = new ModelRenderer(this, 50, 40);
      this.leg2.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 6.0F, 0.0F);
      this.leg2.setPos(-2.5F, 14.0F, -7.0F);
      this.leg3 = new ModelRenderer(this, 50, 40);
      this.leg3.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 6.0F, 0.0F);
      this.leg3.setPos(2.5F, 14.0F, -7.0F);
      --this.leg0.x;
      ++this.leg1.x;
      this.leg0.z += 0.0F;
      this.leg1.z += 0.0F;
      --this.leg2.x;
      ++this.leg3.x;
      --this.leg2.z;
      --this.leg3.z;
   }

   public void setupAnim(T p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {
      super.setupAnim(p_225597_1_, p_225597_2_, p_225597_3_, p_225597_4_, p_225597_5_, p_225597_6_);
      float f = p_225597_4_ - (float)p_225597_1_.tickCount;
      float f1 = p_225597_1_.getStandingAnimationScale(f);
      f1 = f1 * f1;
      float f2 = 1.0F - f1;
      this.body.xRot = ((float)Math.PI / 2F) - f1 * (float)Math.PI * 0.35F;
      this.body.y = 9.0F * f2 + 11.0F * f1;
      this.leg2.y = 14.0F * f2 - 6.0F * f1;
      this.leg2.z = -8.0F * f2 - 4.0F * f1;
      this.leg2.xRot -= f1 * (float)Math.PI * 0.45F;
      this.leg3.y = this.leg2.y;
      this.leg3.z = this.leg2.z;
      this.leg3.xRot -= f1 * (float)Math.PI * 0.45F;
      if (this.young) {
         this.head.y = 10.0F * f2 - 9.0F * f1;
         this.head.z = -16.0F * f2 - 7.0F * f1;
      } else {
         this.head.y = 10.0F * f2 - 14.0F * f1;
         this.head.z = -16.0F * f2 - 3.0F * f1;
      }

      this.head.xRot += f1 * (float)Math.PI * 0.15F;
   }
}
