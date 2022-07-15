package fr.janis.pintium.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import fr.janis.pintium.main;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;

public class SetHomeCommand {

    public SetHomeCommand(CommandDispatcher<CommandSource> dispatcher){
        dispatcher.register(Commands.literal("set").then(Commands.literal("home").executes((command) -> {
            return setHome(command.getSource());
        })));
    }

    private int setHome(CommandSource s) throws CommandSyntaxException {
        ServerPlayerEntity p = s.getPlayerOrException();
        BlockPos pos = p.blockPosition();

        String strpos = "X: " + pos.getX() + " Y: " + pos.getY() + " Z: " + pos.getZ();

        p.getPersistentData().putIntArray(main.MODID + "homepos",
                new int[]{pos.getX(), pos.getY(), pos.getZ()});

        s.sendSuccess(new StringTextComponent("Set home at " + strpos), true);
        return 1;
    }

}
