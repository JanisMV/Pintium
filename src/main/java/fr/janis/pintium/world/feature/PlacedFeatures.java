package fr.janis.pintium.world.feature;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class PlacedFeatures {
    public static final Holder<PlacedFeature> PINTIUM_ORE_PLACED = PlacementUtils.register("pintium_ore_placed",
            ConfiguredFeatures.PINTIUM_ORE, OrePlacement.commonOrePlacement(5, // VeinsPerChunk
                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));

    public static final Holder<PlacedFeature> NETHER_PINTIUM_ORE_PLACED = PlacementUtils.register("nether_pintium_ore_placed",
            ConfiguredFeatures.NETHER_PINTIUM_ORE, OrePlacement.commonOrePlacement(5,
                    HeightRangePlacement.uniform(VerticalAnchor.absolute(8), VerticalAnchor.absolute(20))));

    public static final Holder<PlacedFeature> TERBIUM_ORE_PLACED = PlacementUtils.register("terbium_ore_placed",
            ConfiguredFeatures.TERBIUM_ORE, OrePlacement.commonOrePlacement(12,
                    HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));

}