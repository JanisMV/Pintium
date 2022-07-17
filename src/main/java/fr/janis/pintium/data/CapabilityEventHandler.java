package fr.janis.pintium.data;

import fr.janis.pintium.main;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CapabilityEventHandler {
    @SubscribeEvent
    public void onAttachCapabilitiesEvent(AttachCapabilitiesEvent<Entity> e){
        if (e.getObject() instanceof Player){

            EntityKilledProvider provider = new EntityKilledProvider();
            e.addCapability(new ResourceLocation(main.MODID, "name"), provider);
            e.addListener(provider::invalidate);
        }
    }
}
