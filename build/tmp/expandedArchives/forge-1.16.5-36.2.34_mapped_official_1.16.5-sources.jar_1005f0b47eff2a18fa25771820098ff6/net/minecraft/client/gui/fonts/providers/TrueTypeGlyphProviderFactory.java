package net.minecraft.client.gui.fonts.providers;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import javax.annotation.Nullable;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.resources.IResource;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.stb.STBTTFontinfo;
import org.lwjgl.stb.STBTruetype;
import org.lwjgl.system.MemoryUtil;

@OnlyIn(Dist.CLIENT)
public class TrueTypeGlyphProviderFactory implements IGlyphProviderFactory {
   private static final Logger LOGGER = LogManager.getLogger();
   private final ResourceLocation location;
   private final float size;
   private final float oversample;
   private final float shiftX;
   private final float shiftY;
   private final String skip;

   public TrueTypeGlyphProviderFactory(ResourceLocation p_i49753_1_, float p_i49753_2_, float p_i49753_3_, float p_i49753_4_, float p_i49753_5_, String p_i49753_6_) {
      this.location = p_i49753_1_;
      this.size = p_i49753_2_;
      this.oversample = p_i49753_3_;
      this.shiftX = p_i49753_4_;
      this.shiftY = p_i49753_5_;
      this.skip = p_i49753_6_;
   }

   public static IGlyphProviderFactory fromJson(JsonObject p_211624_0_) {
      float f = 0.0F;
      float f1 = 0.0F;
      if (p_211624_0_.has("shift")) {
         JsonArray jsonarray = p_211624_0_.getAsJsonArray("shift");
         if (jsonarray.size() != 2) {
            throw new JsonParseException("Expected 2 elements in 'shift', found " + jsonarray.size());
         }

         f = JSONUtils.convertToFloat(jsonarray.get(0), "shift[0]");
         f1 = JSONUtils.convertToFloat(jsonarray.get(1), "shift[1]");
      }

      StringBuilder stringbuilder = new StringBuilder();
      if (p_211624_0_.has("skip")) {
         JsonElement jsonelement = p_211624_0_.get("skip");
         if (jsonelement.isJsonArray()) {
            JsonArray jsonarray1 = JSONUtils.convertToJsonArray(jsonelement, "skip");

            for(int i = 0; i < jsonarray1.size(); ++i) {
               stringbuilder.append(JSONUtils.convertToString(jsonarray1.get(i), "skip[" + i + "]"));
            }
         } else {
            stringbuilder.append(JSONUtils.convertToString(jsonelement, "skip"));
         }
      }

      return new TrueTypeGlyphProviderFactory(new ResourceLocation(JSONUtils.getAsString(p_211624_0_, "file")), JSONUtils.getAsFloat(p_211624_0_, "size", 11.0F), JSONUtils.getAsFloat(p_211624_0_, "oversample", 1.0F), f, f1, stringbuilder.toString());
   }

   @Nullable
   public IGlyphProvider create(IResourceManager p_211246_1_) {
      STBTTFontinfo stbttfontinfo = null;
      ByteBuffer bytebuffer = null;

      try (IResource iresource = p_211246_1_.getResource(new ResourceLocation(this.location.getNamespace(), "font/" + this.location.getPath()))) {
         LOGGER.debug("Loading font {}", (Object)this.location);
         stbttfontinfo = STBTTFontinfo.malloc();
         bytebuffer = TextureUtil.readResource(iresource.getInputStream());
         ((Buffer)bytebuffer).flip();
         LOGGER.debug("Reading font {}", (Object)this.location);
         if (!STBTruetype.stbtt_InitFont(stbttfontinfo, bytebuffer)) {
            throw new IOException("Invalid ttf");
         } else {
            return new TrueTypeGlyphProvider(bytebuffer, stbttfontinfo, this.size, this.oversample, this.shiftX, this.shiftY, this.skip);
         }
      } catch (Exception exception) {
         LOGGER.error("Couldn't load truetype font {}", this.location, exception);
         if (stbttfontinfo != null) {
            stbttfontinfo.free();
         }

         MemoryUtil.memFree(bytebuffer);
         return null;
      }
   }
}
