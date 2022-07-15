package fr.janis.pintium.utils;

import fr.janis.pintium.client.render.*;
import fr.janis.pintium.commands.ReturnHomeCommand;
import fr.janis.pintium.commands.SetHomeCommand;
import fr.janis.pintium.entities.*;
import fr.janis.pintium.event.loot.PintiumSeedAdditionModifier;
import fr.janis.pintium.init.PintiumEntities;
import fr.janis.pintium.items.PintiumSpawnEggItem;
import fr.janis.pintium.main;
import net.minecraft.client.renderer.entity.CreeperRenderer;
import net.minecraft.client.renderer.entity.SkeletonRenderer;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.server.command.ConfigCommand;

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
    }
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent e){
        RenderingRegistry.registerEntityRenderingHandler(PintiumEntities.RATEL.get(), RatelRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PintiumEntities.RATEL_BODY_GUARD.get(), RatelRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PintiumEntities.ZOMBIE_BODY_GUARD.get(), ZombieRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PintiumEntities.SKELETON_BODY_GUARD.get(), SkeletonRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PintiumEntities.CREEPER_BODY_GUARD.get(), CreeperRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PintiumEntities.BANANOSAUR.get(), BananosaurRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PintiumEntities.TUNA.get(), TunaRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PintiumEntities.BANANOFISH.get(), BananoFishRenderer::new);
    }

    @SubscribeEvent
    public static void onRegisterEntities(final RegistryEvent.Register<EntityType<?>> e){
        PintiumSpawnEggItem.initSpawnEggs();
    }

    @SubscribeEvent
    public static void registerModifierSerializers(@Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>>
                                                           event) {
        event.getRegistry().registerAll(
                new PintiumSeedAdditionModifier.Serializer().setRegistryName
                        (new ResourceLocation(main.MODID,"pintium_seed_in_grass"))
        );
    }
}
