package fr.janis.pintium.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import fr.janis.pintium.main;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.coordinates.Coordinates;
import net.minecraft.commands.arguments.coordinates.Vec3Argument;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;

public class GetCoordinatesCommand {
    public GetCoordinatesCommand(CommandDispatcher<CommandSourceStack> dispatcher){
        dispatcher.register(Commands.literal("setcos").then(Commands.argument("cos", Vec3Argument.vec3()).executes((command) -> {
            return getCoordinates(command.getSource(), Vec3Argument.getCoordinates(command, "cos"));
        })));
    }

    private int getCoordinates(CommandSourceStack s, Coordinates cos) throws CommandSyntaxException {
        ServerPlayer p = s.getPlayerOrException();
        p.getPersistentData().putDouble(main.MODID + "x", cos.getPosition(s).x);
        p.getPersistentData().putDouble(main.MODID + "y", cos.getPosition(s).y);
        p.getPersistentData().putDouble(main.MODID + "z", cos.getPosition(s).z);
        s.sendSuccess(new TranslatableComponent("pintium.getcoordinates.done"), true);
        return 1;
    }
}
