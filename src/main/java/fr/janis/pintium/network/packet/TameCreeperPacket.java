package fr.janis.pintium.network.packet;

import fr.janis.pintium.entities.CreeperBodyGuardEntity;
import fr.janis.pintium.init.PintiumEntities;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fmllegacy.network.NetworkEvent;

import java.util.function.Supplier;

public class TameCreeperPacket {

    public TameCreeperPacket()
    {
    }

    public static void encode(TameCreeperPacket packet, FriendlyByteBuf buffer)
    {
    }

    public static TameCreeperPacket decode(FriendlyByteBuf buffer)
    {
        return new TameCreeperPacket();
    }

    public static void handle(TameCreeperPacket packet, Supplier<NetworkEvent.Context> ctxProvider) {

        CreeperBodyGuardEntity creeper = new CreeperBodyGuardEntity(PintiumEntities.CREEPER_BODY_GUARD.get(), ctxProvider.get().getSender().getLevel());

        creeper.setPos(ctxProvider.get().getSender().getX(), ctxProvider.get().getSender().getY(), ctxProvider.get().getSender().getZ());

        ctxProvider.get().getSender().getLevel().addFreshEntity(creeper);

        ctxProvider.get().setPacketHandled(true);
    }
}