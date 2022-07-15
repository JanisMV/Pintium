package fr.janis.pintium;

import fr.janis.pintium.data.CapabilityEntityKilled;
import fr.janis.pintium.data.CapabilityEventHandler;
import fr.janis.pintium.data.onEntityDeathEvent;
import fr.janis.pintium.entities.*;
import fr.janis.pintium.event.*;
import fr.janis.pintium.init.*;

import fr.janis.pintium.keybind.KeyBinds;
import fr.janis.pintium.network.Network;
import fr.janis.pintium.utils.SoundEvents;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/* Prochaine Maj : Plus de Mobs Tameable
Succès : https://misode.github.io/advancement/
1.16 -> 1.17 https://www.youtube.com/watch?v=9aJjI7UDHeI, https://www.youtube.com/watch?v=G-eQ8e4zJ8U
1.17 -> 1.18 https://www.youtube.com/watch?v=O8l5ANZSXnM
Y aussi l'OG :
1.17 -> 1.18 https://www.youtube.com/watch?v=5leVDDzU_0U
*/

@Mod(main.MODID)
public class main
{
    public static final String MODID = "pintium";
    public static final Logger LOGGER = LogManager.getLogger();

    public main(){

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        PintiumItems.ITEMS.register(bus);
        PintiumBlocks.BLOCKS.register(bus);
        PintiumEntities.ENTITY_TYPES.register(bus);
        SoundEvents.SOUND_EVENTS.register(bus);
    }

    private void setup(FMLCommonSetupEvent e){
        PintiumFeatures features = new PintiumFeatures();

        Network.registerNetworkPackets();
        CapabilityEntityKilled.register();

        features.init();

        MinecraftForge.EVENT_BUS.register(features);
        MinecraftForge.EVENT_BUS.register(new FallingEvent());
        MinecraftForge.EVENT_BUS.register(new ArmorEvents());
        MinecraftForge.EVENT_BUS.register(new TickEvent());
        MinecraftForge.EVENT_BUS.register(new ServerTickEvent());
        MinecraftForge.EVENT_BUS.register(new UsedItemEvent());
        MinecraftForge.EVENT_BUS.register(new onEntityDeathEvent());
        MinecraftForge.EVENT_BUS.register(new CapabilityEventHandler());

        KeyBinds.register();

        MinecraftForge.EVENT_BUS.register(new KeyBindEvent());
    }

    private void clientSetup(FMLClientSetupEvent e){
        RenderTypeLookup.setRenderLayer(PintiumBlocks.PINTIUM_CROP.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(PintiumBlocks.CANNABIS_CROP.get(), RenderType.cutout());
    }
}