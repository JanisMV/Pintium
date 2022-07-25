package fr.janis.pintium.network.packet;

import fr.janis.pintium.entities.ZombieBodyGuardEntity;
import fr.janis.pintium.init.PintiumEntities;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class TameZombiePacket {

    public TameZombiePacket()
    {
    }

    public static void encode(TameZombiePacket packet, FriendlyByteBuf buffer)
    {
    }

    public static TameZombiePacket decode(FriendlyByteBuf buffer)
    {
        return new TameZombiePacket();
    }

    public static void handle(TameZombiePacket packet, Supplier<NetworkEvent.Context> ctxProvider) {

        ZombieBodyGuardEntity zombie = new ZombieBodyGuardEntity(PintiumEntities.ZOMBIE_BODY_GUARD.get(), ctxProvider.get().getSender().getLevel());

        zombie.setPos(ctxProvider.get().getSender().getX(), ctxProvider.get().getSender().getY(), ctxProvider.get().getSender().getZ());

        ctxProvider.get().getSender().getLevel().addFreshEntity(zombie);

        ctxProvider.get().setPacketHandled(true);
    }
}