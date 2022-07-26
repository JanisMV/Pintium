package fr.janis.pintium.world.feature;

import fr.janis.pintium.init.PintiumBlocks;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;

import java.util.List;

public class ConfiguredFeatures {
    public static final List<OreConfiguration.TargetBlockState> OVERWORLD_PINTIUM_ORES = List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, PintiumBlocks.PINTIUM_OVERWORLD_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, PintiumBlocks.PINTIUM_DEEPSLATE_ORE.get().defaultBlockState())
    );

    public static final List<OreConfiguration.TargetBlockState> NETHER_PINTIUM_ORES = List.of(
            OreConfiguration.target(OreFeatures.NETHER_ORE_REPLACEABLES, PintiumBlocks.PINTIUM_NETHER_ORE.get().defaultBlockState())
    );

    public static final List<OreConfiguration.TargetBlockState> TERBIUM_ORES = List.of(
            OreConfiguration.target(new BlockMatchTest(Blocks.END_STONE), PintiumBlocks.TERBIUM_ORE.get().defaultBlockState())
    );

    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> PINTIUM_ORE = FeatureUtils.register("pintium_ore",
            Feature.ORE, new OreConfiguration(OVERWORLD_PINTIUM_ORES, 6));

    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> NETHER_PINTIUM_ORE = FeatureUtils.register("nether_pintium_ore",
            Feature.ORE, new OreConfiguration(NETHER_PINTIUM_ORES, 6)); //nb minerais dans filon

    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> TERBIUM_ORE = FeatureUtils.register("terbium_ore",
            Feature.ORE, new OreConfiguration(TERBIUM_ORES, 9));
}