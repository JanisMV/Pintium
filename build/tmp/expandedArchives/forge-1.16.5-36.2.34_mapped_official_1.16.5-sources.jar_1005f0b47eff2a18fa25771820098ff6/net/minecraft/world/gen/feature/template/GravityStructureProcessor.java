package net.minecraft.world.gen.feature.template;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import javax.annotation.Nullable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.server.ServerWorld;

public class GravityStructureProcessor extends StructureProcessor {
   public static final Codec<GravityStructureProcessor> CODEC = RecordCodecBuilder.create((p_237082_0_) -> {
      return p_237082_0_.group(Heightmap.Type.CODEC.fieldOf("heightmap").orElse(Heightmap.Type.WORLD_SURFACE_WG).forGetter((p_237084_0_) -> {
         return p_237084_0_.heightmap;
      }), Codec.INT.fieldOf("offset").orElse(0).forGetter((p_237083_0_) -> {
         return p_237083_0_.offset;
      })).apply(p_237082_0_, GravityStructureProcessor::new);
   });
   private final Heightmap.Type heightmap;
   private final int offset;

   public GravityStructureProcessor(Heightmap.Type p_i51328_1_, int p_i51328_2_) {
      this.heightmap = p_i51328_1_;
      this.offset = p_i51328_2_;
   }

   @Nullable
   public Template.BlockInfo processBlock(IWorldReader p_230386_1_, BlockPos p_230386_2_, BlockPos p_230386_3_, Template.BlockInfo p_230386_4_, Template.BlockInfo p_230386_5_, PlacementSettings p_230386_6_) {
      Heightmap.Type heightmap$type;
      if (p_230386_1_ instanceof ServerWorld) {
         if (this.heightmap == Heightmap.Type.WORLD_SURFACE_WG) {
            heightmap$type = Heightmap.Type.WORLD_SURFACE;
         } else if (this.heightmap == Heightmap.Type.OCEAN_FLOOR_WG) {
            heightmap$type = Heightmap.Type.OCEAN_FLOOR;
         } else {
            heightmap$type = this.heightmap;
         }
      } else {
         heightmap$type = this.heightmap;
      }

      int i = p_230386_1_.getHeight(heightmap$type, p_230386_5_.pos.getX(), p_230386_5_.pos.getZ()) + this.offset;
      int j = p_230386_4_.pos.getY();
      return new Template.BlockInfo(new BlockPos(p_230386_5_.pos.getX(), i + j, p_230386_5_.pos.getZ()), p_230386_5_.state, p_230386_5_.nbt);
   }

   protected IStructureProcessorType<?> getType() {
      return IStructureProcessorType.GRAVITY;
   }
}
