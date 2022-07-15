package fr.janis.pintium.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import fr.janis.pintium.main;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;

public class ListHomesCommand {
    public ListHomesCommand(CommandDispatcher<CommandSource> dispatcher){
        dispatcher.register(Commands.literal("homes").executes((command) -> {
            return listhomes(command.getSource());
        }));
    }

    private int listhomes(CommandSource s) throws CommandSyntaxException {
        ServerPlayerEntity p = s.getPlayerOrException();
        boolean hasHome = p.getPersistentData().getIntArray(main.MODID + "homepos").length != 0;

        if(hasHome){
            int [] ppos = p.getPersistentData().getIntArray(main.MODID + "homepos");
            String strpos = "X: " + ppos[0] + " Y: " + ppos[1] + " Z: " + ppos[2];

            s.sendSuccess(new StringTextComponent("Your home at " + strpos), true);
            return 1;
        }else {
            s.sendSuccess(new StringTextComponent("No home set"), true);
            return -1;
        }

    }
}
