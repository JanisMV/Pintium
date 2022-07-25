package fr.janis.pintium.world.feature;

import fr.janis.pintium.init.PintiumBlocks;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;

import java.util.List;

public class ConfiguredFeatures {
    public static final List<OreConfiguration.TargetBlockState> OVERWORLD_PINTIUM_ORES = List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, PintiumBlocks.PINTIUM_OVERWORLD_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, PintiumBlocks.PINTIUM_DEEPSLATE_ORE.get().defaultBlockState()));

    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> PINTIUM_ORE = FeatureUtils.register("pintium_ore",
            Feature.ORE, new OreConfiguration(OVERWORLD_PINTIUM_ORES, 6));

}