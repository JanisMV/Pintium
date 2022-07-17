package fr.janis.pintium.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import fr.janis.pintium.main;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.TextComponent;

public class ListHomesCommand {
    public ListHomesCommand(CommandDispatcher<CommandSourceStack> dispatcher){
        dispatcher.register(Commands.literal("homes").executes((command) -> {
            return listhomes(command.getSource());
        }));
    }

    private int listhomes(CommandSourceStack s) throws CommandSyntaxException {
        ServerPlayer p = s.getPlayerOrException();
        boolean hasHome = p.getPersistentData().getIntArray(main.MODID + "homepos").length != 0;

        if(hasHome){
            int [] ppos = p.getPersistentData().getIntArray(main.MODID + "homepos");
            String strpos = "X: " + ppos[0] + " Y: " + ppos[1] + " Z: " + ppos[2];

            s.sendSuccess(new TextComponent("Your home at " + strpos), true);
            return 1;
        }else {
            s.sendSuccess(new TextComponent("No home set"), true);
            return -1;
        }

    }
}
