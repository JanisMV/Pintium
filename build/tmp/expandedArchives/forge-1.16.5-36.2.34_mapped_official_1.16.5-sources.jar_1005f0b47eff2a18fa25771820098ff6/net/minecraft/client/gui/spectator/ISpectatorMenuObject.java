package net.minecraft.client.gui.spectator;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public interface ISpectatorMenuObject {
   void selectItem(SpectatorMenu p_178661_1_);

   ITextComponent getName();

   void renderIcon(MatrixStack p_230485_1_, float p_230485_2_, int p_230485_3_);

   boolean isEnabled();
}
