package fr.janis.pintium.blocks;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.ToolType;

public class PintiumBlock extends Block {
    public PintiumBlock() { // hardnessAndResistance - strength et IRON - METAL et setRequiresTool - Rien
        super(BlockBehaviour.Properties.of(Material.METAL).strength(6f, 30f).(ToolType.PICKAXE).harvestLevel(2));
    }
}
