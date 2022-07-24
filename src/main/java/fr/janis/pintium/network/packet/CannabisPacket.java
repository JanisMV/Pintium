package fr.janis.pintium.network.packet;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fmllegacy.network.NetworkEvent;

import java.time.Instant;
import java.util.function.Supplier;
import java.util.Random;

public class CannabisPacket {

    public CannabisPacket()
    {
    }

    public static void encode(CannabisPacket packet, FriendlyByteBuf buffer)
    {
    }

    public static CannabisPacket decode(FriendlyByteBuf buffer)
    {
        return new CannabisPacket();
    }

    public static void handle(CannabisPacket packet, Supplier<NetworkEvent.Context> ctxProvider) {

        Random dice = new Random();
        int posX;
        int posY;
        int posZ;

        posX = dice.nextInt(6);
        posY = dice.nextInt(6);
        posZ = dice.nextInt(6);

        ServerPlayer p = ctxProvider.get().getSender();
        p.getPersistentData().putLong("cannabis_use", Instant.now().getEpochSecond());

        if (p.getPersistentData().getLong("cannabis_cooldown") <= p.getPersistentData().getLong("cannabis_use")) {
            p.getPersistentData().putLong("cannabis_cooldown", Instant.now().getEpochSecond() + 30);

            Horse horse = new Horse(EntityType.HORSE, p.getLevel());
            horse.setPos(p.getX() + posX, p.getY() + posY, p.getZ() + posZ);
            p.getLevel().addFreshEntity(horse);
            p.getLevel().removeEntity(horse);

            Creeper creeper = new Creeper(EntityType.CREEPER, p.getLevel());
            creeper.setPos(p.getX() - posX, p.getY() - posY, p.getZ() - posZ);
            p.getLevel().addFreshEntity(creeper);
            p.getLevel().removeEntity(creeper);

            p.getPersistentData().putBoolean("is_using_cannabis", true);
            p.getPersistentData().putInt("is_using_cannabis_for", 1);

            ctxProvider.get().setPacketHandled(true);
        }
        ctxProvider.get().setPacketHandled(true);
    }
}