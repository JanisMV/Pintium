package fr.janis.pintium.world;

import fr.janis.pintium.init.PintiumEntities;
import fr.janis.pintium.world.gen.OreGeneration;
import fr.janis.pintium.main;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = main.MODID)
public class WorldGenerationEvents {
    @SubscribeEvent
    public static void biomeLoadingEvent(final BiomeLoadingEvent event) {
        OreGeneration.generateOres(event);

        if (event.getCategory() != Biome.BiomeCategory.NETHER && event.getCategory() != Biome.BiomeCategory.THEEND) {
            if (event.getCategory() != Biome.BiomeCategory.OCEAN && event.getCategory() != Biome.BiomeCategory.RIVER){
                event.getSpawns().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(PintiumEntities.RATEL.get(), 100, 5, 5));
                event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(PintiumEntities.BANANOSAUR.get(), 1, 1, 5));
            }
            else {
                event.getSpawns().getSpawner(MobCategory.WATER_CREATURE).add(new MobSpawnSettings.SpawnerData(PintiumEntities.TUNA.get(), 5, 5, 5));
                event.getSpawns().getSpawner(MobCategory.WATER_CREATURE).add(new MobSpawnSettings.SpawnerData(PintiumEntities.BANANOFISH.get(), 5, 5, 5));
            }
        }
    }
}
