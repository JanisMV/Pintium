package fr.janis.pintium.data;

import fr.janis.pintium.entities.RatelEntity;
import fr.janis.pintium.main;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.SkeletonEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class onEntityDeathEvent {
    @SubscribeEvent
    public void onDeathEvent(net.minecraftforge.event.entity.living.LivingDeathEvent e){
        String name = e.getEntityLiving().getName().getString();
        if (e.getSource().getEntity() instanceof PlayerEntity){
            PlayerEntity p = (PlayerEntity) e.getSource().getEntity();
            if (e.getEntityLiving() instanceof RatelEntity)
            {
                p.getCapability(CapabilityEntityKilled.ENTITY_KILLED_CAPABILITY).ifPresent(h -> {
                    h.setName(name);
                });
            }

            else if (e.getEntityLiving() instanceof ZombieEntity)
            {
                p.getCapability(CapabilityEntityKilled.ENTITY_KILLED_CAPABILITY).ifPresent(h -> {
                    h.setName(name);
                });
            }

            else if (e.getEntityLiving() instanceof SkeletonEntity)
            {
                p.getCapability(CapabilityEntityKilled.ENTITY_KILLED_CAPABILITY).ifPresent(h -> {
                    h.setName(name);
                });
            }

            else if (e.getEntityLiving() instanceof CreeperEntity)
            {
                p.getCapability(CapabilityEntityKilled.ENTITY_KILLED_CAPABILITY).ifPresent(h -> {
                    h.setName(name);
                });
            }
        }
    }
}
