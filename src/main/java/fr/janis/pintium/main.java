package fr.janis.pintium;

import fr.janis.pintium.blocks.entity.BlockEntities;
import fr.janis.pintium.event.onEntityDeathEvent;
import fr.janis.pintium.event.*;
import fr.janis.pintium.gui.ExtractorMachineScreen;
import fr.janis.pintium.gui.PintiumMenuTypes;
import fr.janis.pintium.init.*;

import fr.janis.pintium.keybind.KeyBinds;
import fr.janis.pintium.network.Network;
import fr.janis.pintium.utils.SoundEvents;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/* Prochaine Maj : Plus de Mobs Tameable

recipe a voir

JEI compatibility
Crush cobble pour graine de pintium
Achievements

Changelog:

Code :
x.y.z
X : Ajout de fonctionnalité majeur (dimension)
Y : Ajout de fonctionnalité mineur (item)
Z : Modification de fonctionnalité

TameAbleMobs:
Ajouter le goal au Ratel
XBodyGuardEntity.java
TameXPacket
PintiumEntities l'enregistrer
Lajouter au LifeStick le packet
Register les attributs
onEntityDeathEvent
onRegisterRenderer

Succès : https://misode.github.io/advancement/
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
        BlockEntities.BLOCK_ENTITIES.register(bus);
        SoundEvents.SOUND_EVENTS.register(bus);
        PintiumMenuTypes.register(bus);
    }

    private void setup(FMLCommonSetupEvent e){

        Network.registerNetworkPackets();

        MinecraftForge.EVENT_BUS.register(new FallingEvent());
        MinecraftForge.EVENT_BUS.register(new ArmorEvents());
        MinecraftForge.EVENT_BUS.register(new TickEvent());
        MinecraftForge.EVENT_BUS.register(new ServerTickEvent());
        MinecraftForge.EVENT_BUS.register(new UsedItemEvent());
        MinecraftForge.EVENT_BUS.register(new onEntityDeathEvent());

        KeyBinds.register();

        MinecraftForge.EVENT_BUS.register(new KeyBindEvent());
    }

    private void clientSetup(final FMLClientSetupEvent e){
        ItemBlockRenderTypes.setRenderLayer(PintiumBlocks.PINTIUM_CROP.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(PintiumBlocks.CANNABIS_CROP.get(), RenderType.cutout());

        MenuScreens.register(PintiumMenuTypes.EXTRACTOR_MACHINE_MENU.get(), ExtractorMachineScreen::new);
    }
}