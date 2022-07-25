package fr.janis.pintium.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import fr.janis.pintium.main;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.TextComponent;

public class ReturnHomeCommand {
    public ReturnHomeCommand(CommandDispatcher<CommandSourceStack> dispatcher){
        dispatcher.register(Commands.literal("home").executes((command) -> {
            return returnHome(command.getSource());
        }));
    }

    private int returnHome(CommandSourceStack s) throws CommandSyntaxException{
        ServerPlayer p = s.getPlayerOrException();
        boolean hasHome = p.getPersistentData().getIntArray(main.MODID + "homepos").length != 0;
        double x;
        double z;

        if(hasHome){
            int [] ppos = p.getPersistentData().getIntArray(main.MODID + "homepos");

            x = ppos[0];
            z = ppos[2];

            if (ppos[0] > 0) {
                x = ppos[0] + 0.5;
            }else {
                x = ppos[0] - 0.5;
            }

            if (ppos[2] > 0) {
                z = ppos[2] + 0.5;
            }else {
                z = ppos[2] - 0.5;
            }

            p.teleportTo(x, ppos[1], z);

            s.sendSuccess(new TranslatableComponent( "pintium.returnhomecommand.success"), true);
            return 1;
        }else {
            s.sendSuccess(new TranslatableComponent("pintium.homecommand.error"), true);
            return -1;
        }

    }

}
