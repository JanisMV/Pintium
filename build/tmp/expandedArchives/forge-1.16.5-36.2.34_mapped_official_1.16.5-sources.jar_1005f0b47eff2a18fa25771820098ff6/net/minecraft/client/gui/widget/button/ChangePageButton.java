package net.minecraft.client.gui.widget.button;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SimpleSound;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.screen.ReadBookScreen;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ChangePageButton extends Button {
   private final boolean isForward;
   private final boolean playTurnSound;

   public ChangePageButton(int p_i51079_1_, int p_i51079_2_, boolean p_i51079_3_, Button.IPressable p_i51079_4_, boolean p_i51079_5_) {
      super(p_i51079_1_, p_i51079_2_, 23, 13, StringTextComponent.EMPTY, p_i51079_4_);
      this.isForward = p_i51079_3_;
      this.playTurnSound = p_i51079_5_;
   }

   public void renderButton(MatrixStack p_230431_1_, int p_230431_2_, int p_230431_3_, float p_230431_4_) {
      RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
      Minecraft.getInstance().getTextureManager().bind(ReadBookScreen.BOOK_LOCATION);
      int i = 0;
      int j = 192;
      if (this.isHovered()) {
         i += 23;
      }

      if (!this.isForward) {
         j += 13;
      }

      this.blit(p_230431_1_, this.x, this.y, i, j, 23, 13);
   }

   public void playDownSound(SoundHandler p_230988_1_) {
      if (this.playTurnSound) {
         p_230988_1_.play(SimpleSound.forUI(SoundEvents.BOOK_PAGE_TURN, 1.0F));
      }

   }
}
