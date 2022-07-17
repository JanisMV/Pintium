package fr.janis.pintium.blocks;

import fr.janis.pintium.init.PintiumBlocks;
import fr.janis.pintium.init.PintiumEntities;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;

public class BananaBlock extends Block {
    public BananaBlock() { // create - of et setRequiresTool - Rien
        super(BlockBehaviour.Properties.of(Material.CACTUS).harvestTool(ToolType.SHOVEL));
    }
    // onBlockPlacedBy - setPlacedBy
    public void setPlacedBy(Level worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(worldIn, pos, state, placer, stack);
        }
    }

