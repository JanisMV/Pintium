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
    public boolean crushing_cannabis;
    public BlockEntityCooldown(double x, double y, double z, boolean crushing_cannabis)
    {
        this.x=x;
        this.y=y;
        this.z=z;
        this.crushing_cannabis=crushing_cannabis;
    }

    public static void encode(BlockEntityCooldown packet, FriendlyByteBuf buffer)
    {
        buffer.writeDouble(packet.x);
        buffer.writeDouble(packet.y);
        buffer.writeDouble(packet.z);
        buffer.writeBoolean(packet.crushing_cannabis);
    }

    public static BlockEntityCooldown decode(FriendlyByteBuf buffer)
    {
        double x = buffer.readDouble();
        double y = buffer.readDouble();
        double z = buffer.readDouble();
        boolean crusing_cannabis = buffer.readBoolean();
        return new BlockEntityCooldown(x, y, z, crusing_cannabis);
    }

    public static void handle(BlockEntityCooldown packet, Supplier<NetworkEvent.Context> ctxProvider) {

        ServerPlayer p = ctxProvider.get().getSender();

        assert p != null;
        if (!p.getLevel().isClientSide) {
            p.getPersistentData().putBoolean(main.MODID+"extractor_cooldown", true);
            p.getPersistentData().putDouble(main.MODID + "extractor_cooldown_x", packet.x);
            p.getPersistentData().putDouble(main.MODID + "extractor_cooldown_y", packet.y);
            p.getPersistentData().putDouble(main.MODID + "extractor_cooldown_z", packet.z);
            p.getPersistentData().putBoolean(main.MODID + "crushing_cannabis", packet.crushing_cannabis);
        }
        ctxProvider.get().setPacketHandled(true);
    }
}