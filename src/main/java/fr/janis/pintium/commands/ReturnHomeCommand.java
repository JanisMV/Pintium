package fr.janis.pintium.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import fr.janis.pintium.main;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;

public class ReturnHomeCommand {
    public ReturnHomeCommand(CommandDispatcher<CommandSource> dispatcher){
        dispatcher.register(Commands.literal("home").executes((command) -> {
            return returnHome(command.getSource());
        }));
    }

    private int returnHome(CommandSource s) throws CommandSyntaxException{
        ServerPlayerEntity p = s.getPlayerOrException();
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

            s.sendSuccess(new StringTextComponent( "You returned home"), true);
            return 1;
        }else {
            s.sendSuccess(new StringTextComponent("No home set"), true);
            return -1;
        }

    }

}
