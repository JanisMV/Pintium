package fr.janis.pintium.blocks;

import fr.janis.pintium.init.PintiumBlocks;
import fr.janis.pintium.init.PintiumEntities;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;

public class BananaBlock extends Block {
    public BananaBlock() { // create - of et setRequiresTool - Rien
        super(AbstractBlock.Properties.of(Material.CACTUS).harvestTool(ToolType.SHOVEL));
    }
    // onBlockPlacedBy - setPlacedBy
    public void setPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(worldIn, pos, state, placer, stack);
        }
    }

