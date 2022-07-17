package fr.janis.pintium.network.packet;

import fr.janis.pintium.entities.RatelBodyGuardEntity;
import fr.janis.pintium.init.PintiumEntities;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fmllegacy.network.NetworkEvent;

import java.util.function.Supplier;

public class TameRatelPacket {

    public TameRatelPacket()
    {
    }

    public static void encode(TameRatelPacket packet, FriendlyByteBuf buffer)
    {
    }

    public static TameRatelPacket decode(FriendlyByteBuf buffer)
    {
        return new TameRatelPacket();
    }

    public static void handle(TameRatelPacket packet, Supplier<NetworkEvent.Context> ctxProvider) {

        RatelBodyGuardEntity ratel = new RatelBodyGuardEntity(PintiumEntities.RATEL_BODY_GUARD.get(), ctxProvider.get().getSender().getLevel());

        ratel.setPos(ctxProvider.get().getSender().getX(), ctxProvider.get().getSender().getY(), ctxProvider.get().getSender().getZ());

        ctxProvider.get().getSender().getLevel().addFreshEntity(ratel);

        ctxProvider.get().setPacketHandled(true);
    }
}