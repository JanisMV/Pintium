package net.minecraft.client.renderer.model.multipart;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.mojang.datafixers.util.Pair;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.model.BlockModelDefinition;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.IModelTransform;
import net.minecraft.client.renderer.model.IUnbakedModel;
import net.minecraft.client.renderer.model.ModelBakery;
import net.minecraft.client.renderer.model.MultipartBakedModel;
import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.client.renderer.model.VariantList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class Multipart implements IUnbakedModel {
   private final StateContainer<Block, BlockState> definition;
   private final List<Selector> selectors;

   public Multipart(StateContainer<Block, BlockState> p_i49524_1_, List<Selector> p_i49524_2_) {
      this.definition = p_i49524_1_;
      this.selectors = p_i49524_2_;
   }

   public List<Selector> getSelectors() {
      return this.selectors;
   }

   public Set<VariantList> getMultiVariants() {
      Set<VariantList> set = Sets.newHashSet();

      for(Selector selector : this.selectors) {
         set.add(selector.getVariant());
      }

      return set;
   }

   public boolean equals(Object p_equals_1_) {
      if (this == p_equals_1_) {
         return true;
      } else if (!(p_equals_1_ instanceof Multipart)) {
         return false;
      } else {
         Multipart multipart = (Multipart)p_equals_1_;
         return Objects.equals(this.definition, multipart.definition) && Objects.equals(this.selectors, multipart.selectors);
      }
   }

   public int hashCode() {
      return Objects.hash(this.definition, this.selectors);
   }

   public Collection<ResourceLocation> getDependencies() {
      return this.getSelectors().stream().flatMap((p_209563_0_) -> {
         return p_209563_0_.getVariant().getDependencies().stream();
      }).collect(Collectors.toSet());
   }

   public Collection<RenderMaterial> getMaterials(Function<ResourceLocation, IUnbakedModel> p_225614_1_, Set<Pair<String, String>> p_225614_2_) {
      return this.getSelectors().stream().flatMap((p_228832_2_) -> {
         return p_228832_2_.getVariant().getMaterials(p_225614_1_, p_225614_2_).stream();
      }).collect(Collectors.toSet());
   }

   @Nullable
   public IBakedModel bake(ModelBakery p_225613_1_, Function<RenderMaterial, TextureAtlasSprite> p_225613_2_, IModelTransform p_225613_3_, ResourceLocation p_225613_4_) {
      MultipartBakedModel.Builder multipartbakedmodel$builder = new MultipartBakedModel.Builder();

      for(Selector selector : this.getSelectors()) {
         IBakedModel ibakedmodel = selector.getVariant().bake(p_225613_1_, p_225613_2_, p_225613_3_, p_225613_4_);
         if (ibakedmodel != null) {
            multipartbakedmodel$builder.add(selector.getPredicate(this.definition), ibakedmodel);
         }
      }

      return multipartbakedmodel$builder.build();
   }

   @OnlyIn(Dist.CLIENT)
   public static class Deserializer implements JsonDeserializer<Multipart> {
      private final BlockModelDefinition.ContainerHolder context;

      public Deserializer(BlockModelDefinition.ContainerHolder p_i49520_1_) {
         this.context = p_i49520_1_;
      }

      public Multipart deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
         return new Multipart(this.context.getDefinition(), this.getSelectors(p_deserialize_3_, p_deserialize_1_.getAsJsonArray()));
      }

      private List<Selector> getSelectors(JsonDeserializationContext p_188133_1_, JsonArray p_188133_2_) {
         List<Selector> list = Lists.newArrayList();

         for(JsonElement jsonelement : p_188133_2_) {
            list.add(p_188133_1_.deserialize(jsonelement, Selector.class));
         }

         return list;
      }
   }
}
