package fr.janis.pintium.event;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class FallingEvent {
    @SubscribeEvent
    public void onPlayerFall(final LivingFallEvent e)
    {
        if( e.getEntityLiving() instanceof Player )
        {
            Player player = ((Player) e.getEntityLiving());
            if(player.getPersistentData().getBoolean("using_jump_stick") )
            {
                e.setCanceled(true);
                player.getPersistentData().putBoolean("using_jump_stick", false);
            }
        }
    }
}