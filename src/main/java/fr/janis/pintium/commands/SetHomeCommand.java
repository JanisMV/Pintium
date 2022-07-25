package fr.janis.pintium.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import fr.janis.pintium.main;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;

public class SetHomeCommand {

    public SetHomeCommand(CommandDispatcher<CommandSourceStack> dispatcher){
        dispatcher.register(Commands.literal("set").then(Commands.literal("home").executes((command) -> {
            return setHome(command.getSource());
        })));
    }

    private int setHome(CommandSourceStack s) throws CommandSyntaxException {
        ServerPlayer p = s.getPlayerOrException();
        BlockPos pos = p.blockPosition();

        String strpos = "X: " + pos.getX() + " Y: " + pos.getY() + " Z: " + pos.getZ();

        p.getPersistentData().putIntArray(main.MODID + "homepos",
                new int[]{pos.getX(), pos.getY(), pos.getZ()});

        s.sendSuccess(new TextComponent(new TranslatableComponent("pintium.sethomecommand.success") + strpos), true);
        return 1;
    }

}
