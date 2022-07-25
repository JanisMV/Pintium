package fr.janis.pintium.network.packet;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.phys.Vec3;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraftforge.network.NetworkEvent;

import java.time.Instant;
import java.util.function.Supplier;

public class LaunchFireBall {

    public LaunchFireBall()
    {
    }

    public static void encode(LaunchFireBall packet, FriendlyByteBuf buffer)
    {
    }

    public static LaunchFireBall decode(FriendlyByteBuf buffer)
    {
        return new LaunchFireBall();
    }

    public static void handle(LaunchFireBall packet, Supplier<NetworkEvent.Context> ctxProvider) {

        ServerPlayer p = ctxProvider.get().getSender();
        assert p != null;
        LivingEntity livingEntity = p.getLastHurtMob();

        if (livingEntity == null) {
            String text = new TranslatableComponent("pintium.ignis.no_entity").getString();
            p.displayClientMessage(Component.nullToEmpty((text)), true);
        }
        else {
            p.getPersistentData().putLong("ignis_use", Instant.now().getEpochSecond());

            if (p.getPersistentData().getLong("ignis_cooldown") <= p.getPersistentData().getLong("ignis_use")) {

                p.getPersistentData().putLong("ignis_cooldown", Instant.now().getEpochSecond() + 10);

                Vec3 vector3d = p.getViewVector(1.0F);
                double d2 = livingEntity.getX() - (p.getX() + vector3d.x * 4.0D);
                double d3 = livingEntity.getY(0.5D) - (0.5D + p.getY(0.5D));
                double d4 = livingEntity.getZ() - (p.getZ() + vector3d.z * 4.0D);

                if (!p.isSilent()) {
                    p.getLevel().levelEvent((Player) null, 1016, p.blockPosition(), 0);
                }

                LargeFireball fireballentity = new LargeFireball(p.getLevel(), p, d2, d3, d4, 1);
                fireballentity.setPos(p.getX() + vector3d.x * 4.0D, p.getY(0.5D) + 0.5D, fireballentity.getZ() + vector3d.z * 4.0D);
                p.getLevel().addFreshEntity(fireballentity);

                ctxProvider.get().setPacketHandled(true);
            } else {
                String text = new TranslatableComponent("pintium.cooldown_not_finished").getString() + (p.getPersistentData().getLong("ignis_cooldown") - p.getPersistentData().getLong("ignis_use")) + new TranslatableComponent("pintium.cooldown_not_finished2").getString();
                p.displayClientMessage(Component.nullToEmpty((text)), true);
            }
        }
    }
}