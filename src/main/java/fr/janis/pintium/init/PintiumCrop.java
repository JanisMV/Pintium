package fr.janis.pintium.init;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.ItemLike;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.BlockGetter;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class PintiumCrop extends CropBlock {

    private static final VoxelShape[] SHAPES = new VoxelShape[]
            {
                    Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), //0
                    Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D), //1
                    Block.box(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D), //2
                    Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D), //3
                    Block.box(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D), //4
                    Block.box(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D), //5
                    Block.box(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D), //6
                    Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D) //7
            };

    public PintiumCrop(Properties builder)
    {
        super(builder);
    }

    @Override
    protected ItemLike getBaseSeedId()
    {
        return PintiumItems.PINTIUM_SEEDS.get();
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context)
    {
        return SHAPES[state.getValue(this.getAgeProperty())];
    }

}
