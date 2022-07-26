package fr.janis.pintium.world.gen;

import fr.janis.pintium.world.feature.PlacedFeatures;
import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.List;

public class OreGeneration {
    public static void generateOres(final BiomeLoadingEvent event) {
        List<Holder<PlacedFeature>> base =
                event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES);

        base.add(PlacedFeatures.PINTIUM_ORE_PLACED);

        if (event.getCategory() == Biome.BiomeCategory.NETHER){
            base.add(PlacedFeatures.NETHER_PINTIUM_ORE_PLACED);
        }

        if (event.getCategory() == Biome.BiomeCategory.THEEND){
            base.add(PlacedFeatures.TERBIUM_ORE_PLACED);
        }
    }
}