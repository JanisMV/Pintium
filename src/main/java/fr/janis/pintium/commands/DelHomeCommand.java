package fr.janis.pintium.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import fr.janis.pintium.main;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;

public class DelHomeCommand {
    public DelHomeCommand(CommandDispatcher<CommandSource> dispatcher){
        dispatcher.register(Commands.literal("delhome").executes((command) -> {
            return delhome(command.getSource());
        }));
    }

    private int delhome(CommandSource s) throws CommandSyntaxException {
        ServerPlayerEntity p = s.getPlayerOrException();
        boolean hasHome = p.getPersistentData().getIntArray(main.MODID + "homepos").length != 0;

        if(hasHome){
            p.getPersistentData().putIntArray(main.MODID + "homepos", new int[]{});

            s.sendSuccess(new StringTextComponent("Home deleted"), true);
            return 1;
        }else {
            s.sendSuccess(new StringTextComponent("No home set"), true);
            return -1;
        }

    }
}
