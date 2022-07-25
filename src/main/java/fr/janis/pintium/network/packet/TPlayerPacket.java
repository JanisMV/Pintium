package fr.janis.pintium.network.packet;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class TPlayerPacket {

    public double x;
    public double y;
    public double z;
    public TPlayerPacket(double x, double y, double z)
    {
        this.x=x;
        this.y=y;
        this.z=z;
    }

    public static void encode(TPlayerPacket packet, FriendlyByteBuf buffer)
    {
        buffer.writeDouble(packet.x);
        buffer.writeDouble(packet.y);
        buffer.writeDouble(packet.z);
    }

    public static TPlayerPacket decode(FriendlyByteBuf buffer)
    {
        double x = buffer.readDouble();
        double y = buffer.readDouble();
        double z = buffer.readDouble();
        return new TPlayerPacket(x, y, z);
    }

    public static void handle(TPlayerPacket packet, Supplier<NetworkEvent.Context> ctxProvider) {

        ServerPlayer p = ctxProvider.get().getSender();

        assert p != null;
        if (!p.getLevel().isClientSide) {
            p.teleportTo(packet.x, packet.y, packet.z);
            p.getMainHandItem().hurtAndBreak(4, p, player -> player.broadcastBreakEvent(p.getUsedItemHand()));
        }
        ctxProvider.get().setPacketHandled(true);
    }
}