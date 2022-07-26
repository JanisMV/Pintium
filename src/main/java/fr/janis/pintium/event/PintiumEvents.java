package fr.janis.pintium.event;

import fr.janis.pintium.commands.*;
import fr.janis.pintium.main;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;

@Mod.EventBusSubscriber(modid = main.MODID)
public class PintiumEvents {
    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent e){
        new SetHomeCommand(e.getDispatcher());
        new ReturnHomeCommand(e.getDispatcher());
        new ListHomesCommand(e.getDispatcher());
        new DelHomeCommand(e.getDispatcher());

        ConfigCommand.register(e.getDispatcher());
    }

    @SubscribeEvent
    public static void onPlayerCloneEvent(PlayerEvent.Clone e){
        if(!e.getOriginal().getCommandSenderWorld().isClientSide()){
            e.getPlayer().getPersistentData().putIntArray(main.MODID + "homepos",
                    e.getOriginal().getPersistentData().getIntArray(main.MODID + "homepos"));
        }
    }
}
