package fr.janis.pintium.init;

import net.minecraft.data.worldgen.Features;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class PintiumFeatures {

    public ConfiguredFeature<?, ?> PINTIUM_OVERWORLD_ORE;
    public ConfiguredFeature<?, ?> PINTIUM_NETHER_ORE;

    public void init(){

        PINTIUM_OVERWORLD_ORE = register("pintium_overworld_ore", Feature.ORE.configured(new OreConfiguration(OreConfiguration.Predicates.NATURAL_STONE, PintiumBlocks.PINTIUM_OVERWORLD_ORE.get().defaultBlockState(), 6))
                .squared()
        .rangeUniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(12)) //Couche ou l'on le trouve
        .count(1)); // Nombre de filons par chunk

        PINTIUM_NETHER_ORE = register("pintium_nether_ore", Feature.ORE.configured(new OreConfiguration(OreConfiguration.Predicates.NETHERRACK, PintiumBlocks.PINTIUM_NETHER_ORE.get().defaultBlockState(), 6))
        .squared()
        .rangeUniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(20))
        .count(1));
    }

    public <FC extends FeatureConfiguration>ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> feature){

        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, name, feature);

    }

    @SubscribeEvent
    public void biomeLoading(BiomeLoadingEvent e){

        BiomeGenerationSettingsBuilder generation = e.getGeneration();
        if(e.getCategory() != Biome.BiomeCategory.NETHER && e.getCategory() != Biome.BiomeCategory.THEEND){
            generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PINTIUM_OVERWORLD_ORE);
        }
        else if (e.getCategory() != Biome.BiomeCategory.THEEND){
            generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PINTIUM_NETHER_ORE);
        }

        if (e.getCategory() == Biome.BiomeCategory.NETHER) {}

        else if (e.getCategory() == Biome.BiomeCategory.THEEND) {}

        else {
            if (e.getCategory() != Biome.BiomeCategory.OCEAN && e.getCategory() != Biome.BiomeCategory.RIVER){
                e.getSpawns().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(PintiumEntities.RATEL.get(), 100, 5, 5));
                e.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(PintiumEntities.BANANOSAUR.get(), 1, 1, 5));
            }
            else {
                e.getSpawns().getSpawner(MobCategory.WATER_CREATURE).add(new MobSpawnSettings.SpawnerData(PintiumEntities.TUNA.get(), 5, 5, 5));
                e.getSpawns().getSpawner(MobCategory.WATER_CREATURE).add(new MobSpawnSettings.SpawnerData(PintiumEntities.BANANOFISH.get(), 5, 5, 5));
            }
        }
    }
}
