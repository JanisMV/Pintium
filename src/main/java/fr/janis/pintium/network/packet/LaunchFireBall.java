package fr.janis.pintium.network.packet;

import fr.janis.pintium.main;
import net.minecraft.dispenser.Position;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.network.NetworkEvent;

import java.time.Instant;
import java.util.Objects;
import java.util.function.Supplier;

public class LaunchFireBall {

    public LaunchFireBall()
    {
    }

    public static void encode(LaunchFireBall packet, PacketBuffer buffer)
    {
    }

    public static LaunchFireBall decode(PacketBuffer buffer)
    {
        return new LaunchFireBall();
    }

    public static void handle(LaunchFireBall packet, Supplier<NetworkEvent.Context> ctxProvider) {

        ServerPlayerEntity p = ctxProvider.get().getSender();
        assert p != null;
        LivingEntity livingEntity = p.getLastHurtMob();

        if (livingEntity == null) {
            String text = new TranslationTextComponent("pintium.guispells.ignis.no_entity").getString();
            p.displayClientMessage(ITextComponent.nullToEmpty((text)), true);
        }
        else {
            p.getPersistentData().putLong("ignis_use", Instant.now().getEpochSecond());

            if (p.getPersistentData().getLong("ignis_cooldown") <= p.getPersistentData().getLong("ignis_use")) {

                p.getPersistentData().putLong("ignis_cooldown", Instant.now().getEpochSecond() + 10);

                Vector3d vector3d = p.getViewVector(1.0F);
                double d2 = livingEntity.getX() - (p.getX() + vector3d.x * 4.0D);
                double d3 = livingEntity.getY(0.5D) - (0.5D + p.getY(0.5D));
                double d4 = livingEntity.getZ() - (p.getZ() + vector3d.z * 4.0D);

                if (!p.isSilent()) {
                    p.getLevel().levelEvent((PlayerEntity) null, 1016, p.blockPosition(), 0);
                }

                FireballEntity fireballentity = new FireballEntity(p.getLevel(), p, d2, d3, d4);
                fireballentity.explosionPower = 2;
                fireballentity.setPos(p.getX() + vector3d.x * 4.0D, p.getY(0.5D) + 0.5D, fireballentity.getZ() + vector3d.z * 4.0D);
                p.getLevel().addFreshEntity(fireballentity);

                ctxProvider.get().setPacketHandled(true);
            } else {
                String text = new TranslationTextComponent("pintium.guispells.cooldown_not_finished1").getString() + (p.getPersistentData().getLong("ignis_cooldown") - p.getPersistentData().getLong("ignis_use")) + new TranslationTextComponent("pintium.guispells.cooldown_not_finished2").getString();
                p.displayClientMessage(ITextComponent.nullToEmpty((text)), true);
            }
        }
    }
}