package fr.janis.pintium.init;

import fr.janis.pintium.entities.*;
import fr.janis.pintium.main;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class PintiumEntities {

    public static DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, main.MODID);

    // Entity Types
    public static final RegistryObject<EntityType<RatelEntity>> RATEL = ENTITY_TYPES.register("ratel",
            () -> EntityType.Builder.of(RatelEntity::new, MobCategory.MONSTER)
                    .sized(1.0F, 1.0F)
                    .build(new ResourceLocation(main.MODID, "ratel").toString())
    );
    
    public static final RegistryObject<EntityType<RatelBodyGuardEntity>> RATEL_BODY_GUARD = ENTITY_TYPES.register(
            "ratel_body_guard",
            () -> EntityType.Builder.of(RatelBodyGuardEntity::new, MobCategory.MONSTER)
                .sized(1.0F, 1.0F)
            .build(new ResourceLocation(main.MODID, "ratel").toString())
    );

    public static final RegistryObject<EntityType<ZombieBodyGuardEntity>> ZOMBIE_BODY_GUARD = ENTITY_TYPES.register(
            "zombie_body_guard",
            () -> EntityType.Builder.of(ZombieBodyGuardEntity::new, MobCategory.MONSTER)
                    .sized(1.0F, 1.0F)
                    .build(new ResourceLocation(main.MODID, "zombie").toString())
    );

    public static final RegistryObject<EntityType<SkeletonBodyGuardEntity>> SKELETON_BODY_GUARD = ENTITY_TYPES.register(
            "skeleton_body_guard",
            () -> EntityType.Builder.of(SkeletonBodyGuardEntity::new, MobCategory.MONSTER)
                    .sized(1.0F, 1.0F)
                    .build(new ResourceLocation(main.MODID, "skeleton").toString())
    );

    public static final RegistryObject<EntityType<CreeperBodyGuardEntity>> CREEPER_BODY_GUARD = ENTITY_TYPES.register(
            "creeper_body_guard",
            () -> EntityType.Builder.of(CreeperBodyGuardEntity::new, MobCategory.MONSTER)
                    .sized(1.0F, 1.0F)
                    .build(new ResourceLocation(main.MODID, "creeper").toString())
    );

    public static final RegistryObject<EntityType<EndermanBodyGuardEntity>> ENDERMAN_BODY_GUARD = ENTITY_TYPES.register(
            "enderman_body_guard",
            () -> EntityType.Builder.of(EndermanBodyGuardEntity::new, MobCategory.MONSTER)
                    .sized(1.0F, 1.0F)
                    .build(new ResourceLocation(main.MODID, "enderman").toString())
    );

    public static final RegistryObject<EntityType<BananosaurEntity>> BANANOSAUR = ENTITY_TYPES.register("bananosaur",
            () -> EntityType.Builder.of(BananosaurEntity::new, MobCategory.CREATURE)
            .sized(2.0F, 2.0F)
            .build(new ResourceLocation(main.MODID, "bananosaur").toString())
    );

    public static final RegistryObject<EntityType<TunaEntity>> TUNA = ENTITY_TYPES.register("tuna",
            () -> EntityType.Builder.of(TunaEntity::new, MobCategory.WATER_CREATURE)
            .sized(1.0F, 1.0F)
            .build(new ResourceLocation(main.MODID, "tuna").toString())
    );

    public static final RegistryObject<EntityType<BananoFishEntity>> BANANOFISH = ENTITY_TYPES.register("bananofish",
            () -> EntityType.Builder.of(BananoFishEntity::new, MobCategory.WATER_CREATURE)
            .sized(1.0F, 1.0F)
            .build(new ResourceLocation(main.MODID, "bananofish").toString())
    );
}
