package fr.janis.pintium.network.packet;

import fr.janis.pintium.main;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class BlockEntityCooldown {

    public double x;
    public double y;
    public double z;
    public BlockEntityCooldown(double x, double y, double z)
    {
        this.x=x;
        this.y=y;
        this.z=z;
    }

    public static void encode(BlockEntityCooldown packet, FriendlyByteBuf buffer)
    {
        buffer.writeDouble(packet.x);
        buffer.writeDouble(packet.y);
        buffer.writeDouble(packet.z);
    }

    public static BlockEntityCooldown decode(FriendlyByteBuf buffer)
    {
        double x = buffer.readDouble();
        double y = buffer.readDouble();
        double z = buffer.readDouble();
        return new BlockEntityCooldown(x, y, z);
    }

    public static void handle(BlockEntityCooldown packet, Supplier<NetworkEvent.Context> ctxProvider) {

        ServerPlayer p = ctxProvider.get().getSender();

        assert p != null;
        if (!p.getLevel().isClientSide) {
            p.getPersistentData().putBoolean(main.MODID+"extractor_cooldown", true);
            p.getPersistentData().putDouble(main.MODID + "extractor_cooldown_x", packet.x);
            p.getPersistentData().putDouble(main.MODID + "extractor_cooldown_y", packet.y);
            p.getPersistentData().putDouble(main.MODID + "extractor_cooldown_z", packet.z);
        }
        ctxProvider.get().setPacketHandled(true);
    }
}