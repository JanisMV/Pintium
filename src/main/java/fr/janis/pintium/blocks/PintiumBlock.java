package fr.janis.pintium.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class PintiumBlock extends Block {
    public PintiumBlock() { // hardnessAndResistance - strength et IRON - METAL et setRequiresTool - Rien
        super(AbstractBlock.Properties.of(Material.METAL).strength(6f, 30f).harvestTool(ToolType.PICKAXE).harvestLevel(2));
    }
}
