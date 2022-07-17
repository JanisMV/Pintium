package fr.janis.pintium.data;

import fr.janis.pintium.entities.RatelEntity;
import fr.janis.pintium.main;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class onEntityDeathEvent {
    @SubscribeEvent
    public void onDeathEvent(net.minecraftforge.event.entity.living.LivingDeathEvent e){
        String name = e.getEntityLiving().getName().getString();
        if (e.getSource().getEntity() instanceof Player){
            Player p = (Player) e.getSource().getEntity();
            if (e.getEntityLiving() instanceof RatelEntity)
            {
                p.getCapability(CapabilityEntityKilled.ENTITY_KILLED_CAPABILITY).ifPresent(h -> {
                    h.setName(name);
                });
            }

            else if (e.getEntityLiving() instanceof Zombie)
            {
                p.getCapability(CapabilityEntityKilled.ENTITY_KILLED_CAPABILITY).ifPresent(h -> {
                    h.setName(name);
                });
            }

            else if (e.getEntityLiving() instanceof Skeleton)
            {
                p.getCapability(CapabilityEntityKilled.ENTITY_KILLED_CAPABILITY).ifPresent(h -> {
                    h.setName(name);
                });
            }

            else if (e.getEntityLiving() instanceof Creeper)
            {
                p.getCapability(CapabilityEntityKilled.ENTITY_KILLED_CAPABILITY).ifPresent(h -> {
                    h.setName(name);
                });
            }
        }
    }
}
