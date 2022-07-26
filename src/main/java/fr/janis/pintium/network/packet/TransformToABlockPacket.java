package fr.janis.pintium.network.packet;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.BlockItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.network.NetworkEvent;

import java.time.Instant;
import java.util.Objects;
import java.util.function.Supplier;

public class TransformToABlockPacket {
    public TransformToABlockPacket()
    {
    }

    public static void encode(TransformToABlockPacket packet, FriendlyByteBuf buffer){
    }

    public static TransformToABlockPacket decode(FriendlyByteBuf buffer)
    {
        return new TransformToABlockPacket();
    }

    public static void handle(TransformToABlockPacket packet, Supplier<NetworkEvent.Context> ctxProvider) {

        ServerPlayer p = ctxProvider.get().getSender();
        ServerLevel world = Objects.requireNonNull(ctxProvider.get().getSender()).getLevel();
        assert p != null;
        CompoundTag dataP = p.getPersistentData();

        p.getPersistentData().putLong("inertium_use", Instant.now().getEpochSecond());

        if (p.getPersistentData().getLong("inertium_cooldown") <= p.getPersistentData().getLong("inertium_use")) {
            if (p.getMainHandItem().getItem() instanceof BlockItem){
                if (p.getItemBySlot(EquipmentSlot.HEAD).getItem().getDescription().getString().equals("Air") && p.getItemBySlot(EquipmentSlot.CHEST).getItem().getDescription().getString().equals("Air") && p.getItemBySlot(EquipmentSlot.LEGS).getItem().getDescription().getString().equals("Air") && p.getItemBySlot(EquipmentSlot.FEET).getItem().getDescription().getString().equals("Air") && p.getItemBySlot(EquipmentSlot.OFFHAND).getItem().getDescription().getString().equals("Air")) {
                    dataP.putLong("inertium_cooldown", Instant.now().getEpochSecond() + 30);
                    dataP.putBoolean("inertium_is_used", true);
                    BlockPos posBlock = new BlockPos(p.getX() + 1, p.getY(), p.getZ() + 1);
                    dataP.putDouble("posX", p.getX());
                    dataP.putDouble("posY", p.getY());
                    dataP.putDouble("posZ", p.getZ());
                    dataP.putInt("ItemIDMH", BlockItem.getId(p.getMainHandItem().getItem()));
                    dataP.putInt("number_of_block", p.getMainHandItem().getCount());
                    world.setBlockAndUpdate(posBlock, ((BlockItem) p.getMainHandItem().getItem()).getBlock().defaultBlockState());

                    p.getInventory().removeItem(p.getMainHandItem());

                    p.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 20 * 99999999, 1, true, false));

                    ctxProvider.get().setPacketHandled(true);
                }
                else{
                    String text = new TranslatableComponent("pintium.inertium.has_armor").getString();
                    p.displayClientMessage(Component.nullToEmpty((text)), true);
                }
            }
            else {
                String text = new TranslatableComponent("pintium.inertium.no_block_in_hand").getString();
                p.displayClientMessage(Component.nullToEmpty((text)), true);
            }
        }
        else {
            String text = new TranslatableComponent("pintium.cooldown_not_finished").getString() + (p.getPersistentData().getLong("inertium_cooldown") - p.getPersistentData().getLong("inertium_use")) + new TranslatableComponent("pintium.cooldown_not_finished2").getString();
            p.displayClientMessage(Component.nullToEmpty((text)), true);
        }
        ctxProvider.get().setPacketHandled(true);
    }
}