package fr.janis.pintium.event;

import fr.janis.pintium.init.PintiumItems;
import fr.janis.pintium.main;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;



public class ArmorEvents {
    @SubscribeEvent
    public void onPlayerEquipmentChange(final LivingEquipmentChangeEvent e){
        if(e.getEntityLiving() instanceof Player)
        {
            Player player = ((Player) e.getEntityLiving());

            if (!player.getPersistentData().getBoolean("inertium_is_used")) {
                if (e.getEntityLiving().getItemBySlot(EquipmentSlot.HEAD).getItem() == PintiumItems.PINTIUM_HELMET.get() && Minecraft.getInstance().options.gamma == 0.0D) {
                    Minecraft.getInstance().options.gamma = 9.0D;
                } else if (e.getFrom().getItem() == PintiumItems.PINTIUM_HELMET.get() && e.getEntityLiving().getItemBySlot(EquipmentSlot.HEAD).isEmpty() && Minecraft.getInstance().options.gamma == 9.0D) {
                    Minecraft.getInstance().options.gamma = 0.0D;
                }

                if (e.getEntityLiving().getItemBySlot(EquipmentSlot.CHEST).getItem() == PintiumItems.PINTIUM_CHESTPLATE.get() && !player.hasEffect(MobEffects.DAMAGE_BOOST)) {
                    player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 20 * 99999999, 0, true, false));
                } else if (e.getFrom().getItem() == PintiumItems.PINTIUM_CHESTPLATE.get() && e.getEntityLiving().getItemBySlot(EquipmentSlot.CHEST).isEmpty() && player.hasEffect(MobEffects.DAMAGE_BOOST)) {
                    player.removeEffect(MobEffects.DAMAGE_BOOST);
                }

                if (e.getEntityLiving().getItemBySlot(EquipmentSlot.LEGS).getItem() == PintiumItems.PINTIUM_LEGGINGS.get() && !player.hasEffect(MobEffects.DIG_SPEED)) {
                    player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 20 * 99999999, 0, true, false));
                } else if (e.getFrom().getItem() == PintiumItems.PINTIUM_LEGGINGS.get() && e.getEntityLiving().getItemBySlot(EquipmentSlot.LEGS).isEmpty() && player.hasEffect(MobEffects.DIG_SPEED)) {
                    player.removeEffect(MobEffects.DIG_SPEED);
                }

                if (e.getEntityLiving().getItemBySlot(EquipmentSlot.FEET).getItem() == PintiumItems.PINTIUM_BOOTS.get() && !player.hasEffect(MobEffects.MOVEMENT_SPEED)) {
                    player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20 * 99999999, 0, true, false));
                } else if (e.getFrom().getItem() == PintiumItems.PINTIUM_BOOTS.get() && e.getEntityLiving().getItemBySlot(EquipmentSlot.FEET).isEmpty() && player.hasEffect(MobEffects.MOVEMENT_SPEED)) {
                    player.removeEffect(MobEffects.MOVEMENT_SPEED);
                }

                if (e.getEntityLiving().getItemBySlot(EquipmentSlot.HEAD).getItem() == PintiumItems.PINTIUM_HELMET.get()
                        && e.getEntityLiving().getItemBySlot(EquipmentSlot.CHEST).getItem() == PintiumItems.PINTIUM_CHESTPLATE.get()
                        && e.getEntityLiving().getItemBySlot(EquipmentSlot.LEGS).getItem() == PintiumItems.PINTIUM_LEGGINGS.get()
                        && e.getEntityLiving().getItemBySlot(EquipmentSlot.FEET).getItem() == PintiumItems.PINTIUM_BOOTS.get()
                        && !player.hasEffect(MobEffects.INVISIBILITY)) {
                    player.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 20 * 99999999, 0, true, false));
                } else if (e.getFrom().getItem() == PintiumItems.PINTIUM_HELMET.get() && e.getEntityLiving().getItemBySlot(EquipmentSlot.HEAD).isEmpty() && player.hasEffect(MobEffects.INVISIBILITY) ||
                        e.getFrom().getItem() == PintiumItems.PINTIUM_CHESTPLATE.get() && e.getEntityLiving().getItemBySlot(EquipmentSlot.CHEST).isEmpty() && player.hasEffect(MobEffects.INVISIBILITY) ||
                        e.getFrom().getItem() == PintiumItems.PINTIUM_LEGGINGS.get() && e.getEntityLiving().getItemBySlot(EquipmentSlot.LEGS).isEmpty() && player.hasEffect(MobEffects.INVISIBILITY) ||
                        e.getFrom().getItem() == PintiumItems.PINTIUM_BOOTS.get() && e.getEntityLiving().getItemBySlot(EquipmentSlot.FEET).isEmpty() && player.hasEffect(MobEffects.INVISIBILITY)) {
                    player.removeEffect(MobEffects.INVISIBILITY);
                }
            }
        }
    }
}