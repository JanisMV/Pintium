package fr.janis.pintium.network.packet;

import fr.janis.pintium.main;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.network.NetworkEvent;

import java.time.Instant;
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

    public static void encode(TPlayerPacket packet, PacketBuffer buffer)
    {
        buffer.writeDouble(packet.x);
        buffer.writeDouble(packet.y);
        buffer.writeDouble(packet.z);
    }

    public static TPlayerPacket decode(PacketBuffer buffer)
    {
        double x = buffer.readDouble();
        double y = buffer.readDouble();
        double z = buffer.readDouble();
        return new TPlayerPacket(x, y, z);
    }

    public static void handle(TPlayerPacket packet, Supplier<NetworkEvent.Context> ctxProvider) {

        ServerPlayerEntity p = ctxProvider.get().getSender();

        assert p != null;
        if (!p.getLevel().isClientSide) {
            p.teleportTo(packet.x, packet.y, packet.z);
            p.getMainHandItem().hurtAndBreak(4, p, player -> player.broadcastBreakEvent(p.getUsedItemHand()));
        }
        ctxProvider.get().setPacketHandled(true);
    }
}