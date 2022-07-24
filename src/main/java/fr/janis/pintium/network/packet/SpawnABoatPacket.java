package fr.janis.pintium.network.packet;

import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraftforge.fmllegacy.network.NetworkEvent;

import java.time.Instant;
import java.util.function.Supplier;

public class SpawnABoatPacket {

    public SpawnABoatPacket()
    {
    }

    public static void encode(SpawnABoatPacket packet, FriendlyByteBuf buffer)
    {
    }

    public static SpawnABoatPacket decode(FriendlyByteBuf buffer)
    {
        return new SpawnABoatPacket();
    }

    public static void handle(SpawnABoatPacket packet, Supplier<NetworkEvent.Context> ctxProvider) {

        ServerPlayer p = ctxProvider.get().getSender();
        p.getPersistentData().putLong("batum_use", Instant.now().getEpochSecond());

        if (p.getPersistentData().getLong("batum_cooldown") <= p.getPersistentData().getLong("batum_use")) {

            p.getPersistentData().putLong("batum_cooldown", Instant.now().getEpochSecond() + 30);
            Boat entity = new Boat(p.getLevel(), p.getX() + 1, p.getY(), p.getZ());
            p.getLevel().addFreshEntity(entity);
            ctxProvider.get().setPacketHandled(true);

        }
        else {
            String text = new TranslatableComponent("pintium.cooldown_not_finished").getString() + (p.getPersistentData().getLong("batum_cooldown") - p.getPersistentData().getLong("batum_use")) + new TranslatableComponent("pintium.cooldown_not_finished2").getString();
            p.displayClientMessage(Component.nullToEmpty((text)), true);
        }
        ctxProvider.get().setPacketHandled(true);
    }
}