package fr.janis.pintium.world.feature;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class PlacedFeatures {
    public static final Holder<PlacedFeature> PINTIUM_ORE_PLACED = PlacementUtils.register("pintium_ore_placed",
            ConfiguredFeatures.PINTIUM_ORE, OrePlacement.commonOrePlacement(2, // VeinsPerChunk
                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(12))));
}
