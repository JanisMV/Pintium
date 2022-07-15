package fr.janis.pintium.init;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class PintiumCrop extends CropsBlock {

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
    protected IItemProvider getBaseSeedId()
    {
        return PintiumItems.PINTIUM_SEEDS.get();
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context)
    {
        return SHAPES[state.getValue(this.getAgeProperty())];
    }

}
