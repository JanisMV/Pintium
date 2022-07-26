package fr.janis.pintium;

import fr.janis.pintium.event.onEntityDeathEvent;
import fr.janis.pintium.event.*;
import fr.janis.pintium.init.*;

import fr.janis.pintium.keybind.KeyBinds;
import fr.janis.pintium.network.Network;
import fr.janis.pintium.utils.SoundEvents;
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
Changelog :
Bouclier demandé a enlever
baisse des bananosaurs ratels (a tester)
cannabis always eat
fix globallootmodifers
fix bucket item
drop cannabis from tuna (modifier les deux descriptions)
drop rate of getting 2 nuggets from pintium seeds
Tp object updated
Pintium hammer can destroy new blocks

Code :
x.y.z
X : Changement majeur (dimension)
Y : Changement mineur (item)
Z : Polissage des changements

Succès : https://misode.github.io/advancement/
1.18 -> 1.19 https://www.youtube.com/watch?v=tYAC5zRh12A&ab_channel=ModdingbyKaupenjoe
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

    private void clientSetup(FMLClientSetupEvent e){
        ItemBlockRenderTypes.setRenderLayer(PintiumBlocks.PINTIUM_CROP.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(PintiumBlocks.CANNABIS_CROP.get(), RenderType.cutout());
    }
}