package net.minecraft.world.gen.feature.template;

import com.mojang.serialization.Codec;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;

public class LavaSubmergingProcessor extends StructureProcessor {
   public static final Codec<LavaSubmergingProcessor> CODEC;
   public static final LavaSubmergingProcessor INSTANCE = new LavaSubmergingProcessor();

   @Nullable
   public Template.BlockInfo processBlock(IWorldReader p_230386_1_, BlockPos p_230386_2_, BlockPos p_230386_3_, Template.BlockInfo p_230386_4_, Template.BlockInfo p_230386_5_, PlacementSettings p_230386_6_) {
      BlockPos blockpos = p_230386_5_.pos;
      boolean flag = p_230386_1_.getBlockState(blockpos).is(Blocks.LAVA);
      return flag && !Block.isShapeFullBlock(p_230386_5_.state.getShape(p_230386_1_, blockpos)) ? new Template.BlockInfo(blockpos, Blocks.LAVA.defaultBlockState(), p_230386_5_.nbt) : p_230386_5_;
   }

   protected IStructureProcessorType<?> getType() {
      return IStructureProcessorType.LAVA_SUBMERGED_BLOCK;
   }

   static {
      CODEC = Codec.unit(() -> {
         return INSTANCE;
      });
   }
}
