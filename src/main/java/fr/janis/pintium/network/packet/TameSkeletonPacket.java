package fr.janis.pintium.network.packet;

import fr.janis.pintium.entities.SkeletonBodyGuardEntity;
import fr.janis.pintium.init.PintiumEntities;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fmllegacy.network.NetworkEvent;

import java.util.function.Supplier;

public class TameSkeletonPacket {

    public TameSkeletonPacket()
    {
    }

    public static void encode(TameSkeletonPacket packet, FriendlyByteBuf buffer)
    {
    }

    public static TameSkeletonPacket decode(FriendlyByteBuf buffer)
    {
        return new TameSkeletonPacket();
    }

    public static void handle(TameSkeletonPacket packet, Supplier<NetworkEvent.Context> ctxProvider) {

        SkeletonBodyGuardEntity skeleton = new SkeletonBodyGuardEntity(PintiumEntities.SKELETON_BODY_GUARD.get(), ctxProvider.get().getSender().getLevel());

        skeleton.setPos(ctxProvider.get().getSender().getX(), ctxProvider.get().getSender().getY(), ctxProvider.get().getSender().getZ());

        ctxProvider.get().getSender().getLevel().addFreshEntity(skeleton);

        ctxProvider.get().setPacketHandled(true);
    }
}