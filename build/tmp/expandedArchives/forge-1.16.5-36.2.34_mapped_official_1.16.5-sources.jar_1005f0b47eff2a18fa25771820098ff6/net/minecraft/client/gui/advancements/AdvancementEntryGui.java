package net.minecraft.client.gui.advancements;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.util.IReorderingProcessor;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.CharacterManager;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.ITextProperties;
import net.minecraft.util.text.LanguageMap;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentUtils;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AdvancementEntryGui extends AbstractGui {
   private static final ResourceLocation WIDGETS_LOCATION = new ResourceLocation("textures/gui/advancements/widgets.png");
   private static final int[] TEST_SPLIT_OFFSETS = new int[]{0, 10, -10, 25, -25};
   private final AdvancementTabGui tab;
   private final Advancement advancement;
   private final DisplayInfo display;
   private final IReorderingProcessor title;
   private final int width;
   private final List<IReorderingProcessor> description;
   private final Minecraft minecraft;
   private AdvancementEntryGui parent;
   private final List<AdvancementEntryGui> children = Lists.newArrayList();
   private AdvancementProgress progress;
   private final int x;
   private final int y;

   public AdvancementEntryGui(AdvancementTabGui p_i47385_1_, Minecraft p_i47385_2_, Advancement p_i47385_3_, DisplayInfo p_i47385_4_) {
      this.tab = p_i47385_1_;
      this.advancement = p_i47385_3_;
      this.display = p_i47385_4_;
      this.minecraft = p_i47385_2_;
      this.title = LanguageMap.getInstance().getVisualOrder(p_i47385_2_.font.substrByWidth(p_i47385_4_.getTitle(), 163));
      this.x = MathHelper.floor(p_i47385_4_.getX() * 28.0F);
      this.y = MathHelper.floor(p_i47385_4_.getY() * 27.0F);
      int i = p_i47385_3_.getMaxCriteraRequired();
      int j = String.valueOf(i).length();
      int k = i > 1 ? p_i47385_2_.font.width("  ") + p_i47385_2_.font.width("0") * j * 2 + p_i47385_2_.font.width("/") : 0;
      int l = 29 + p_i47385_2_.font.width(this.title) + k;
      this.description = LanguageMap.getInstance().getVisualOrder(this.findOptimalLines(TextComponentUtils.mergeStyles(p_i47385_4_.getDescription().copy(), Style.EMPTY.withColor(p_i47385_4_.getFrame().getChatColor())), l));

      for(IReorderingProcessor ireorderingprocessor : this.description) {
         l = Math.max(l, p_i47385_2_.font.width(ireorderingprocessor));
      }

      this.width = l + 3 + 5;
   }

   private static float getMaxWidth(CharacterManager p_238693_0_, List<ITextProperties> p_238693_1_) {
      return (float)p_238693_1_.stream().mapToDouble(p_238693_0_::stringWidth).max().orElse(0.0D);
   }

   private List<ITextProperties> findOptimalLines(ITextComponent p_238694_1_, int p_238694_2_) {
      CharacterManager charactermanager = this.minecraft.font.getSplitter();
      List<ITextProperties> list = null;
      float f = Float.MAX_VALUE;

      for(int i : TEST_SPLIT_OFFSETS) {
         List<ITextProperties> list1 = charactermanager.splitLines(p_238694_1_, p_238694_2_ - i, Style.EMPTY);
         float f1 = Math.abs(getMaxWidth(charactermanager, list1) - (float)p_238694_2_);
         if (f1 <= 10.0F) {
            return list1;
         }

         if (f1 < f) {
            f = f1;
            list = list1;
         }
      }

      return list;
   }

   @Nullable
   private AdvancementEntryGui getFirstVisibleParent(Advancement p_191818_1_) {
      do {
         p_191818_1_ = p_191818_1_.getParent();
      } while(p_191818_1_ != null && p_191818_1_.getDisplay() == null);

      return p_191818_1_ != null && p_191818_1_.getDisplay() != null ? this.tab.getWidget(p_191818_1_) : null;
   }

   public void drawConnectivity(MatrixStack p_238692_1_, int p_238692_2_, int p_238692_3_, boolean p_238692_4_) {
      if (this.parent != null) {
         int i = p_238692_2_ + this.parent.x + 13;
         int j = p_238692_2_ + this.parent.x + 26 + 4;
         int k = p_238692_3_ + this.parent.y + 13;
         int l = p_238692_2_ + this.x + 13;
         int i1 = p_238692_3_ + this.y + 13;
         int j1 = p_238692_4_ ? -16777216 : -1;
         if (p_238692_4_) {
            this.hLine(p_238692_1_, j, i, k - 1, j1);
            this.hLine(p_238692_1_, j + 1, i, k, j1);
            this.hLine(p_238692_1_, j, i, k + 1, j1);
            this.hLine(p_238692_1_, l, j - 1, i1 - 1, j1);
            this.hLine(p_238692_1_, l, j - 1, i1, j1);
            this.hLine(p_238692_1_, l, j - 1, i1 + 1, j1);
            this.vLine(p_238692_1_, j - 1, i1, k, j1);
            this.vLine(p_238692_1_, j + 1, i1, k, j1);
         } else {
            this.hLine(p_238692_1_, j, i, k, j1);
            this.hLine(p_238692_1_, l, j, i1, j1);
            this.vLine(p_238692_1_, j, i1, k, j1);
         }
      }

      for(AdvancementEntryGui advancemententrygui : this.children) {
         advancemententrygui.drawConnectivity(p_238692_1_, p_238692_2_, p_238692_3_, p_238692_4_);
      }

   }

   public void draw(MatrixStack p_238688_1_, int p_238688_2_, int p_238688_3_) {
      if (!this.display.isHidden() || this.progress != null && this.progress.isDone()) {
         float f = this.progress == null ? 0.0F : this.progress.getPercent();
         AdvancementState advancementstate;
         if (f >= 1.0F) {
            advancementstate = AdvancementState.OBTAINED;
         } else {
            advancementstate = AdvancementState.UNOBTAINED;
         }

         this.minecraft.getTextureManager().bind(WIDGETS_LOCATION);
         this.blit(p_238688_1_, p_238688_2_ + this.x + 3, p_238688_3_ + this.y, this.display.getFrame().getTexture(), 128 + advancementstate.getIndex() * 26, 26, 26);
         this.minecraft.getItemRenderer().renderAndDecorateFakeItem(this.display.getIcon(), p_238688_2_ + this.x + 8, p_238688_3_ + this.y + 5);
      }

      for(AdvancementEntryGui advancemententrygui : this.children) {
         advancemententrygui.draw(p_238688_1_, p_238688_2_, p_238688_3_);
      }

   }

   public void setProgress(AdvancementProgress p_191824_1_) {
      this.progress = p_191824_1_;
   }

   public void addChild(AdvancementEntryGui p_191822_1_) {
      this.children.add(p_191822_1_);
   }

   public void drawHover(MatrixStack p_238689_1_, int p_238689_2_, int p_238689_3_, float p_238689_4_, int p_238689_5_, int p_238689_6_) {
      boolean flag = p_238689_5_ + p_238689_2_ + this.x + this.width + 26 >= this.tab.getScreen().width;
      String s = this.progress == null ? null : this.progress.getProgressText();
      int i = s == null ? 0 : this.minecraft.font.width(s);
      boolean flag1 = 113 - p_238689_3_ - this.y - 26 <= 6 + this.description.size() * 9;
      float f = this.progress == null ? 0.0F : this.progress.getPercent();
      int j = MathHelper.floor(f * (float)this.width);
      AdvancementState advancementstate;
      AdvancementState advancementstate1;
      AdvancementState advancementstate2;
      if (f >= 1.0F) {
         j = this.width / 2;
         advancementstate = AdvancementState.OBTAINED;
         advancementstate1 = AdvancementState.OBTAINED;
         advancementstate2 = AdvancementState.OBTAINED;
      } else if (j < 2) {
         j = this.width / 2;
         advancementstate = AdvancementState.UNOBTAINED;
         advancementstate1 = AdvancementState.UNOBTAINED;
         advancementstate2 = AdvancementState.UNOBTAINED;
      } else if (j > this.width - 2) {
         j = this.width / 2;
         advancementstate = AdvancementState.OBTAINED;
         advancementstate1 = AdvancementState.OBTAINED;
         advancementstate2 = AdvancementState.UNOBTAINED;
      } else {
         advancementstate = AdvancementState.OBTAINED;
         advancementstate1 = AdvancementState.UNOBTAINED;
         advancementstate2 = AdvancementState.UNOBTAINED;
      }

      int k = this.width - j;
      this.minecraft.getTextureManager().bind(WIDGETS_LOCATION);
      RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
      RenderSystem.enableBlend();
      int l = p_238689_3_ + this.y;
      int i1;
      if (flag) {
         i1 = p_238689_2_ + this.x - this.width + 26 + 6;
      } else {
         i1 = p_238689_2_ + this.x;
      }

      int j1 = 32 + this.description.size() * 9;
      if (!this.description.isEmpty()) {
         if (flag1) {
            this.render9Sprite(p_238689_1_, i1, l + 26 - j1, this.width, j1, 10, 200, 26, 0, 52);
         } else {
            this.render9Sprite(p_238689_1_, i1, l, this.width, j1, 10, 200, 26, 0, 52);
         }
      }

      this.blit(p_238689_1_, i1, l, 0, advancementstate.getIndex() * 26, j, 26);
      this.blit(p_238689_1_, i1 + j, l, 200 - k, advancementstate1.getIndex() * 26, k, 26);
      this.blit(p_238689_1_, p_238689_2_ + this.x + 3, p_238689_3_ + this.y, this.display.getFrame().getTexture(), 128 + advancementstate2.getIndex() * 26, 26, 26);
      if (flag) {
         this.minecraft.font.drawShadow(p_238689_1_, this.title, (float)(i1 + 5), (float)(p_238689_3_ + this.y + 9), -1);
         if (s != null) {
            this.minecraft.font.drawShadow(p_238689_1_, s, (float)(p_238689_2_ + this.x - i), (float)(p_238689_3_ + this.y + 9), -1);
         }
      } else {
         this.minecraft.font.drawShadow(p_238689_1_, this.title, (float)(p_238689_2_ + this.x + 32), (float)(p_238689_3_ + this.y + 9), -1);
         if (s != null) {
            this.minecraft.font.drawShadow(p_238689_1_, s, (float)(p_238689_2_ + this.x + this.width - i - 5), (float)(p_238689_3_ + this.y + 9), -1);
         }
      }

      if (flag1) {
         for(int k1 = 0; k1 < this.description.size(); ++k1) {
            this.minecraft.font.draw(p_238689_1_, this.description.get(k1), (float)(i1 + 5), (float)(l + 26 - j1 + 7 + k1 * 9), -5592406);
         }
      } else {
         for(int l1 = 0; l1 < this.description.size(); ++l1) {
            this.minecraft.font.draw(p_238689_1_, this.description.get(l1), (float)(i1 + 5), (float)(p_238689_3_ + this.y + 9 + 17 + l1 * 9), -5592406);
         }
      }

      this.minecraft.getItemRenderer().renderAndDecorateFakeItem(this.display.getIcon(), p_238689_2_ + this.x + 8, p_238689_3_ + this.y + 5);
   }

   protected void render9Sprite(MatrixStack p_238691_1_, int p_238691_2_, int p_238691_3_, int p_238691_4_, int p_238691_5_, int p_238691_6_, int p_238691_7_, int p_238691_8_, int p_238691_9_, int p_238691_10_) {
      this.blit(p_238691_1_, p_238691_2_, p_238691_3_, p_238691_9_, p_238691_10_, p_238691_6_, p_238691_6_);
      this.renderRepeating(p_238691_1_, p_238691_2_ + p_238691_6_, p_238691_3_, p_238691_4_ - p_238691_6_ - p_238691_6_, p_238691_6_, p_238691_9_ + p_238691_6_, p_238691_10_, p_238691_7_ - p_238691_6_ - p_238691_6_, p_238691_8_);
      this.blit(p_238691_1_, p_238691_2_ + p_238691_4_ - p_238691_6_, p_238691_3_, p_238691_9_ + p_238691_7_ - p_238691_6_, p_238691_10_, p_238691_6_, p_238691_6_);
      this.blit(p_238691_1_, p_238691_2_, p_238691_3_ + p_238691_5_ - p_238691_6_, p_238691_9_, p_238691_10_ + p_238691_8_ - p_238691_6_, p_238691_6_, p_238691_6_);
      this.renderRepeating(p_238691_1_, p_238691_2_ + p_238691_6_, p_238691_3_ + p_238691_5_ - p_238691_6_, p_238691_4_ - p_238691_6_ - p_238691_6_, p_238691_6_, p_238691_9_ + p_238691_6_, p_238691_10_ + p_238691_8_ - p_238691_6_, p_238691_7_ - p_238691_6_ - p_238691_6_, p_238691_8_);
      this.blit(p_238691_1_, p_238691_2_ + p_238691_4_ - p_238691_6_, p_238691_3_ + p_238691_5_ - p_238691_6_, p_238691_9_ + p_238691_7_ - p_238691_6_, p_238691_10_ + p_238691_8_ - p_238691_6_, p_238691_6_, p_238691_6_);
      this.renderRepeating(p_238691_1_, p_238691_2_, p_238691_3_ + p_238691_6_, p_238691_6_, p_238691_5_ - p_238691_6_ - p_238691_6_, p_238691_9_, p_238691_10_ + p_238691_6_, p_238691_7_, p_238691_8_ - p_238691_6_ - p_238691_6_);
      this.renderRepeating(p_238691_1_, p_238691_2_ + p_238691_6_, p_238691_3_ + p_238691_6_, p_238691_4_ - p_238691_6_ - p_238691_6_, p_238691_5_ - p_238691_6_ - p_238691_6_, p_238691_9_ + p_238691_6_, p_238691_10_ + p_238691_6_, p_238691_7_ - p_238691_6_ - p_238691_6_, p_238691_8_ - p_238691_6_ - p_238691_6_);
      this.renderRepeating(p_238691_1_, p_238691_2_ + p_238691_4_ - p_238691_6_, p_238691_3_ + p_238691_6_, p_238691_6_, p_238691_5_ - p_238691_6_ - p_238691_6_, p_238691_9_ + p_238691_7_ - p_238691_6_, p_238691_10_ + p_238691_6_, p_238691_7_, p_238691_8_ - p_238691_6_ - p_238691_6_);
   }

   protected void renderRepeating(MatrixStack p_238690_1_, int p_238690_2_, int p_238690_3_, int p_238690_4_, int p_238690_5_, int p_238690_6_, int p_238690_7_, int p_238690_8_, int p_238690_9_) {
      for(int i = 0; i < p_238690_4_; i += p_238690_8_) {
         int j = p_238690_2_ + i;
         int k = Math.min(p_238690_8_, p_238690_4_ - i);

         for(int l = 0; l < p_238690_5_; l += p_238690_9_) {
            int i1 = p_238690_3_ + l;
            int j1 = Math.min(p_238690_9_, p_238690_5_ - l);
            this.blit(p_238690_1_, j, i1, p_238690_6_, p_238690_7_, k, j1);
         }
      }

   }

   public boolean isMouseOver(int p_191816_1_, int p_191816_2_, int p_191816_3_, int p_191816_4_) {
      if (!this.display.isHidden() || this.progress != null && this.progress.isDone()) {
         int i = p_191816_1_ + this.x;
         int j = i + 26;
         int k = p_191816_2_ + this.y;
         int l = k + 26;
         return p_191816_3_ >= i && p_191816_3_ <= j && p_191816_4_ >= k && p_191816_4_ <= l;
      } else {
         return false;
      }
   }

   public void attachToParent() {
      if (this.parent == null && this.advancement.getParent() != null) {
         this.parent = this.getFirstVisibleParent(this.advancement);
         if (this.parent != null) {
            this.parent.addChild(this);
         }
      }

   }

   public int getY() {
      return this.y;
   }

   public int getX() {
      return this.x;
   }
}
