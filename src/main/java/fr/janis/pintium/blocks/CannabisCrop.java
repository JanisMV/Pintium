package fr.janis.pintium.blocks;

import fr.janis.pintium.init.PintiumItems;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.ItemLike;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.BlockGetter;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class CannabisCrop extends CropBlock {

    private static final VoxelShape[] SHAPES = new VoxelShape[]
            {
                    Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D),
                    Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D),
                    Block.box(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D),
                    Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D),
                    Block.box(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D),
                    Block.box(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D),
                    Block.box(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D),
                    Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D)
            };

    public CannabisCrop(Properties builder)
    {
        super(builder);
    }

    @Override
    protected ItemLike getBaseSeedId()
    {
        return PintiumItems.CANNABIS_FOOD.get();
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context)
    {
        return SHAPES[state.getValue(this.getAgeProperty())];
    }

}
