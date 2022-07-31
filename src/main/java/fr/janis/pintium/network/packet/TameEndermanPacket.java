package fr.janis.pintium.network.packet;

import fr.janis.pintium.entities.EndermanBodyGuardEntity;
import fr.janis.pintium.init.PintiumEntities;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class TameEndermanPacket {

    public TameEndermanPacket()
    {
    }

    public static void encode(TameEndermanPacket packet, FriendlyByteBuf buffer)
    {
    }

    public static TameEndermanPacket decode(FriendlyByteBuf buffer)
    {
        return new TameEndermanPacket();
    }

    public static void handle(TameEndermanPacket packet, Supplier<NetworkEvent.Context> ctxProvider) {

        ctxProvider.get().getSender().displayClientMessage(Component.nullToEmpty("In the packet"), true);

        EndermanBodyGuardEntity enderman = new EndermanBodyGuardEntity(PintiumEntities.ENDERMAN_BODY_GUARD.get(), ctxProvider.get().getSender().getLevel());

        enderman.setPos(ctxProvider.get().getSender().getX(), ctxProvider.get().getSender().getY(), ctxProvider.get().getSender().getZ());

        ctxProvider.get().getSender().getLevel().addFreshEntity(enderman);

        ctxProvider.get().setPacketHandled(true);
    }
}