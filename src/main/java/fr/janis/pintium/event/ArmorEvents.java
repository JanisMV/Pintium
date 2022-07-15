package fr.janis.pintium.event;

import fr.janis.pintium.init.PintiumItems;
import fr.janis.pintium.main;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;



public class ArmorEvents {
    @SubscribeEvent
    public void onPlayerEquipmentChange(final LivingEquipmentChangeEvent e){
        if(e.getEntityLiving() instanceof PlayerEntity)
        {
            PlayerEntity player = ((PlayerEntity) e.getEntityLiving());

            if (!player.getPersistentData().getBoolean("inertium_is_used")) {
                if (e.getEntityLiving().getItemBySlot(EquipmentSlotType.HEAD).getItem() == PintiumItems.PINTIUM_HELMET.get() && Minecraft.getInstance().options.gamma == 0.0D) {
                    Minecraft.getInstance().options.gamma = 9.0D;
                } else if (e.getFrom().getItem() == PintiumItems.PINTIUM_HELMET.get() && e.getEntityLiving().getItemBySlot(EquipmentSlotType.HEAD).isEmpty() && Minecraft.getInstance().options.gamma == 9.0D) {
                    Minecraft.getInstance().options.gamma = 0.0D;
                }

                if (e.getEntityLiving().getItemBySlot(EquipmentSlotType.CHEST).getItem() == PintiumItems.PINTIUM_CHESTPLATE.get() && !player.hasEffect(Effects.DAMAGE_BOOST)) {
                    player.addEffect(new EffectInstance(Effects.DAMAGE_BOOST, 20 * 99999999, 0, true, false));
                } else if (e.getFrom().getItem() == PintiumItems.PINTIUM_CHESTPLATE.get() && e.getEntityLiving().getItemBySlot(EquipmentSlotType.CHEST).isEmpty() && player.hasEffect(Effects.DAMAGE_BOOST)) {
                    player.removeEffect(Effects.DAMAGE_BOOST);
                }

                if (e.getEntityLiving().getItemBySlot(EquipmentSlotType.LEGS).getItem() == PintiumItems.PINTIUM_LEGGINGS.get() && !player.hasEffect(Effects.DIG_SPEED)) {
                    player.addEffect(new EffectInstance(Effects.DIG_SPEED, 20 * 99999999, 0, true, false));
                } else if (e.getFrom().getItem() == PintiumItems.PINTIUM_LEGGINGS.get() && e.getEntityLiving().getItemBySlot(EquipmentSlotType.LEGS).isEmpty() && player.hasEffect(Effects.DIG_SPEED)) {
                    player.removeEffect(Effects.DIG_SPEED);
                }

                if (e.getEntityLiving().getItemBySlot(EquipmentSlotType.FEET).getItem() == PintiumItems.PINTIUM_BOOTS.get() && !player.hasEffect(Effects.MOVEMENT_SPEED)) {
                    player.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, 20 * 99999999, 0, true, false));
                } else if (e.getFrom().getItem() == PintiumItems.PINTIUM_BOOTS.get() && e.getEntityLiving().getItemBySlot(EquipmentSlotType.FEET).isEmpty() && player.hasEffect(Effects.MOVEMENT_SPEED)) {
                    player.removeEffect(Effects.MOVEMENT_SPEED);
                }

                if (e.getEntityLiving().getItemBySlot(EquipmentSlotType.HEAD).getItem() == PintiumItems.PINTIUM_HELMET.get()
                        && e.getEntityLiving().getItemBySlot(EquipmentSlotType.CHEST).getItem() == PintiumItems.PINTIUM_CHESTPLATE.get()
                        && e.getEntityLiving().getItemBySlot(EquipmentSlotType.LEGS).getItem() == PintiumItems.PINTIUM_LEGGINGS.get()
                        && e.getEntityLiving().getItemBySlot(EquipmentSlotType.FEET).getItem() == PintiumItems.PINTIUM_BOOTS.get()
                        && !player.hasEffect(Effects.INVISIBILITY)) {
                    player.addEffect(new EffectInstance(Effects.INVISIBILITY, 20 * 99999999, 0, true, false));
                } else if (e.getFrom().getItem() == PintiumItems.PINTIUM_HELMET.get() && e.getEntityLiving().getItemBySlot(EquipmentSlotType.HEAD).isEmpty() && player.hasEffect(Effects.INVISIBILITY) ||
                        e.getFrom().getItem() == PintiumItems.PINTIUM_CHESTPLATE.get() && e.getEntityLiving().getItemBySlot(EquipmentSlotType.CHEST).isEmpty() && player.hasEffect(Effects.INVISIBILITY) ||
                        e.getFrom().getItem() == PintiumItems.PINTIUM_LEGGINGS.get() && e.getEntityLiving().getItemBySlot(EquipmentSlotType.LEGS).isEmpty() && player.hasEffect(Effects.INVISIBILITY) ||
                        e.getFrom().getItem() == PintiumItems.PINTIUM_BOOTS.get() && e.getEntityLiving().getItemBySlot(EquipmentSlotType.FEET).isEmpty() && player.hasEffect(Effects.INVISIBILITY)) {
                    player.removeEffect(Effects.INVISIBILITY);
                }
            }
        }
    }
}