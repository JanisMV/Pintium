package fr.janis.pintium.network.packet;

import fr.janis.pintium.init.PintiumItems;
import fr.janis.pintium.main;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BlockItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkEvent;

import java.time.Instant;
import java.util.function.Supplier;

public class TransformToABlockPacket {
    public TransformToABlockPacket()
    {
    }

    public static void encode(TransformToABlockPacket packet, PacketBuffer buffer){
    }

    public static TransformToABlockPacket decode(PacketBuffer buffer)
    {
        return new TransformToABlockPacket();
    }

    public static void handle(TransformToABlockPacket packet, Supplier<NetworkEvent.Context> ctxProvider) {

        ServerPlayerEntity p = ctxProvider.get().getSender();
        ServerWorld world = ctxProvider.get().getSender().getLevel();
        CompoundNBT dataP = p.getPersistentData();

        if (world != null && p != null){
            p.getPersistentData().putLong("inertium_use", Instant.now().getEpochSecond());

            if (p.getPersistentData().getLong("inertium_cooldown") <= p.getPersistentData().getLong("inertium_use")) {
                if (p.getMainHandItem().getItem() instanceof BlockItem){
                    main.LOGGER.debug(p.getItemBySlot(EquipmentSlotType.HEAD).getItem().getDescription().getString());
                    if (p.getItemBySlot(EquipmentSlotType.HEAD).getItem().getDescription().getString().equals("Air") && p.getItemBySlot(EquipmentSlotType.CHEST).getItem().getDescription().getString().equals("Air") && p.getItemBySlot(EquipmentSlotType.LEGS).getItem().getDescription().getString().equals("Air") && p.getItemBySlot(EquipmentSlotType.FEET).getItem().getDescription().getString().equals("Air")) {
                        dataP.putLong("inertium_cooldown", Instant.now().getEpochSecond() + 30);
                        dataP.putBoolean("inertium_is_used", true);
                        BlockPos posBlock = new BlockPos(p.getX() + 1, p.getY(), p.getZ() + 1);
                        dataP.putDouble("posX", p.getX());
                        dataP.putDouble("posY", p.getY());
                        dataP.putDouble("posZ", p.getZ());
                        dataP.putInt("ItemIDMH", BlockItem.getId(p.getMainHandItem().getItem()));
                        dataP.putInt("number_of_block", p.getMainHandItem().getCount());
                        world.setBlockAndUpdate(posBlock, ((BlockItem) p.getMainHandItem().getItem()).getBlock().defaultBlockState());

                        p.inventory.removeItem(p.getMainHandItem().getStack());

                        p.addEffect(new EffectInstance(Effects.INVISIBILITY, 20 * 99999999, 1, true, false));

                        ctxProvider.get().setPacketHandled(true);
                    }
                    else{
                        String text = new TranslationTextComponent("pintium.guispells.inertium.has_armor").getString();
                        p.displayClientMessage(ITextComponent.nullToEmpty((text)), true);
                    }
                }
                else {
                    String text = new TranslationTextComponent("pintium.guispells.inertium.no_block_in_hand").getString();
                    p.displayClientMessage(ITextComponent.nullToEmpty((text)), true);
                }
            }
            else {
                String text = new TranslationTextComponent("pintium.guispells.cooldown_not_finished1").getString() + (p.getPersistentData().getLong("inertium_cooldown") - p.getPersistentData().getLong("inertium_use")) + new TranslationTextComponent("pintium.guispells.cooldown_not_finished2").getString();
                p.displayClientMessage(ITextComponent.nullToEmpty((text)), true);
            }
        ctxProvider.get().setPacketHandled(true);
        }
    }
}