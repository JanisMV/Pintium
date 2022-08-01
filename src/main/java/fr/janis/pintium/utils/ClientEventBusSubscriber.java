package fr.janis.pintium.utils;

import fr.janis.pintium.client.model.BananoFishModel;
import fr.janis.pintium.client.model.BananosaurModel;
import fr.janis.pintium.client.model.RatelModel;
import fr.janis.pintium.client.model.TunaModel;
import fr.janis.pintium.client.render.*;
import fr.janis.pintium.entities.*;
import fr.janis.pintium.event.loot.PintiumSeedAdditionModifier;
import fr.janis.pintium.init.PintiumEntities;
import fr.janis.pintium.main;
import fr.janis.pintium.recipe.ExtractorMachineRecipe;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(modid = main.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {
    @SubscribeEvent
    public static void addEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(PintiumEntities.RATEL.get(), RatelEntity.setCustomAttributes().build());
        event.put(PintiumEntities.RATEL_BODY_GUARD.get(), RatelBodyGuardEntity.setCustomAttributes().build());
        event.put(PintiumEntities.ZOMBIE_BODY_GUARD.get(), ZombieBodyGuardEntity.setCustomAttributes().build());
        event.put(PintiumEntities.SKELETON_BODY_GUARD.get(), SkeletonBodyGuardEntity.setCustomAttributes().build());
        event.put(PintiumEntities.CREEPER_BODY_GUARD.get(), CreeperBodyGuardEntity.setCustomAttributes().build());
        event.put(PintiumEntities.BANANOSAUR.get(), BananosaurEntity.setCustomAttributes().build());
        event.put(PintiumEntities.TUNA.get(), TunaEntity.setCustomAttributes().build());
        event.put(PintiumEntities.BANANOFISH.get(), BananoFishEntity.setCustomAttributes().build());
        event.put(PintiumEntities.ENDERMAN_BODY_GUARD.get(), EndermanBodyGuardEntity.setCustomAttributes().build());
    }

    @SubscribeEvent
    public static void onRegisterRenderer(EntityRenderersEvent.RegisterRenderers e){
        e.registerEntityRenderer(PintiumEntities.RATEL.get(), RatelRenderer::new);
        e.registerEntityRenderer(PintiumEntities.RATEL_BODY_GUARD.get(), RatelRenderer::new);
        e.registerEntityRenderer(PintiumEntities.ZOMBIE_BODY_GUARD.get(), ZombieRenderer::new);
        e.registerEntityRenderer(PintiumEntities.SKELETON_BODY_GUARD.get(), SkeletonRenderer::new);
        e.registerEntityRenderer(PintiumEntities.CREEPER_BODY_GUARD.get(), CreeperRenderer::new);
        e.registerEntityRenderer(PintiumEntities.ENDERMAN_BODY_GUARD.get(), EndermanRenderer::new);
        e.registerEntityRenderer(PintiumEntities.TUNA.get(), TunaRenderer::new);
        e.registerEntityRenderer(PintiumEntities.BANANOSAUR.get(), BananosaurRenderer::new);
        e.registerEntityRenderer(PintiumEntities.BANANOFISH.get(), BananoFishRenderer::new);
    }

    @SubscribeEvent
    public static void onRegisterLayer(EntityRenderersEvent.RegisterLayerDefinitions e){
        e.registerLayerDefinition(RatelModel.LAYER_LOCATION, RatelModel::createBodyLayer);
        e.registerLayerDefinition(TunaModel.LAYER_LOCATION, TunaModel::createBodyLayer);
        e.registerLayerDefinition(BananosaurModel.LAYER_LOCATION, BananosaurModel::createBodyLayer);
        e.registerLayerDefinition(BananoFishModel.LAYER_LOCATION, BananoFishModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerModifierSerializers(@Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>>
                                                           event) {
        event.getRegistry().registerAll(
                new PintiumSeedAdditionModifier.Serializer().setRegistryName
                        (new ResourceLocation(main.MODID,"pintium_seed_in_grass"))
        );
    }

    @SubscribeEvent
    public static void registerRecipeTypes(final RegistryEvent.Register<RecipeSerializer<?>> event){
        Registry.register(Registry.RECIPE_TYPE, ExtractorMachineRecipe.Type.ID, ExtractorMachineRecipe.Type.INSTANCE);
    }
}
