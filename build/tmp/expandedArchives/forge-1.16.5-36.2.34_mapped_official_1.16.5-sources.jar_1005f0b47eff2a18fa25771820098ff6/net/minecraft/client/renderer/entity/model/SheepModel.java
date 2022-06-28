package net.minecraft.client.renderer.entity.model;

import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SheepModel<T extends SheepEntity> extends QuadrupedModel<T> {
   private float headXRot;

   public SheepModel() {
      super(12, 0.0F, false, 8.0F, 4.0F, 2.0F, 2.0F, 24);
      this.head = new ModelRenderer(this, 0, 0);
      this.head.addBox(-3.0F, -4.0F, -6.0F, 6.0F, 6.0F, 8.0F, 0.0F);
      this.head.setPos(0.0F, 6.0F, -8.0F);
      this.body = new ModelRenderer(this, 28, 8);
      this.body.addBox(-4.0F, -10.0F, -7.0F, 8.0F, 16.0F, 6.0F, 0.0F);
      this.body.setPos(0.0F, 5.0F, 2.0F);
   }

   public void prepareMobModel(T p_212843_1_, float p_212843_2_, float p_212843_3_, float p_212843_4_) {
      super.prepareMobModel(p_212843_1_, p_212843_2_, p_212843_3_, p_212843_4_);
      this.head.y = 6.0F + p_212843_1_.getHeadEatPositionScale(p_212843_4_) * 9.0F;
      this.headXRot = p_212843_1_.getHeadEatAngleScale(p_212843_4_);
   }

   public void setupAnim(T p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {
      super.setupAnim(p_225597_1_, p_225597_2_, p_225597_3_, p_225597_4_, p_225597_5_, p_225597_6_);
      this.head.xRot = this.headXRot;
   }
}
