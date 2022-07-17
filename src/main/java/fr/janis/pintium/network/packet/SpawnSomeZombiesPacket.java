package fr.janis.pintium.network.packet;

import fr.janis.pintium.entities.ZombieBodyGuardEntity;
import fr.janis.pintium.init.PintiumEntities;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraftforge.fmllegacy.network.NetworkEvent;

import java.time.Instant;
import java.util.function.Supplier;

public class SpawnSomeZombiesPacket {

    public SpawnSomeZombiesPacket()
    {
    }

    public static void encode(SpawnSomeZombiesPacket packet, FriendlyByteBuf buffer)
    {
    }

    public static SpawnSomeZombiesPacket decode(FriendlyByteBuf buffer)
    {
        return new SpawnSomeZombiesPacket();
    }

    public static void handle(SpawnSomeZombiesPacket packet, Supplier<NetworkEvent.Context> ctxProvider) {

        ServerPlayer p = ctxProvider.get().getSender();
        p.getPersistentData().putLong("zombium_use", Instant.now().getEpochSecond());

        if (p.getPersistentData().getLong("zombium_cooldown") <= p.getPersistentData().getLong("zombium_use")) {

            p.getPersistentData().putLong("zombium_cooldown", Instant.now().getEpochSecond() + 30);

            int i;
            for (i = 0; i < 1; i++) {

                ZombieBodyGuardEntity entity = new ZombieBodyGuardEntity(PintiumEntities.ZOMBIE_BODY_GUARD.get(), p.getLevel());
                entity.setPos(p.getX(), p.getY(), p.getZ());
                entity.setCustomName(new TranslatableComponent("pintium.guispells.zombium.zombieName"));
                entity.setBaby(true);
                entity.setTarget(p.getLastHurtMob());
                p.getLevel().addFreshEntity(entity);
            }
            ctxProvider.get().setPacketHandled(true);
        }
        else {
            String text = new TranslatableComponent("pintium.guispells.cooldown_not_finished1").getString() + (p.getPersistentData().getLong("zombium_cooldown") - p.getPersistentData().getLong("zombium_use")) + new TranslatableComponent("pintium.guispells.cooldown_not_finished2").getString();
            p.displayClientMessage(Component.nullToEmpty((text)), true);
        }
        ctxProvider.get().setPacketHandled(true);
    }
}