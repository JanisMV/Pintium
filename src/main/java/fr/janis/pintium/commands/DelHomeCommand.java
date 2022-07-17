package fr.janis.pintium.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import fr.janis.pintium.main;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.TextComponent;

public class DelHomeCommand {
    public DelHomeCommand(CommandDispatcher<CommandSourceStack> dispatcher){
        dispatcher.register(Commands.literal("delhome").executes((command) -> {
            return delhome(command.getSource());
        }));
    }

    private int delhome(CommandSourceStack s) throws CommandSyntaxException {
        ServerPlayer p = s.getPlayerOrException();
        boolean hasHome = p.getPersistentData().getIntArray(main.MODID + "homepos").length != 0;

        if(hasHome){
            p.getPersistentData().putIntArray(main.MODID + "homepos", new int[]{});

            s.sendSuccess(new TextComponent("Home deleted"), true);
            return 1;
        }else {
            s.sendSuccess(new TextComponent("No home set"), true);
            return -1;
        }

    }
}
