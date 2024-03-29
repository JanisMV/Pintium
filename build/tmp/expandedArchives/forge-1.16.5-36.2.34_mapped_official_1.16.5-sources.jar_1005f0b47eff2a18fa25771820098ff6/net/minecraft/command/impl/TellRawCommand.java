package net.minecraft.command.impl;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.ComponentArgument;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.Util;
import net.minecraft.util.text.TextComponentUtils;

public class TellRawCommand {
   public static void register(CommandDispatcher<CommandSource> p_198818_0_) {
      p_198818_0_.register(Commands.literal("tellraw").requires((p_198820_0_) -> {
         return p_198820_0_.hasPermission(2);
      }).then(Commands.argument("targets", EntityArgument.players()).then(Commands.argument("message", ComponentArgument.textComponent()).executes((p_198819_0_) -> {
         int i = 0;

         for(ServerPlayerEntity serverplayerentity : EntityArgument.getPlayers(p_198819_0_, "targets")) {
            serverplayerentity.sendMessage(TextComponentUtils.updateForEntity(p_198819_0_.getSource(), ComponentArgument.getComponent(p_198819_0_, "message"), serverplayerentity, 0), Util.NIL_UUID);
            ++i;
         }

         return i;
      }))));
   }
}
