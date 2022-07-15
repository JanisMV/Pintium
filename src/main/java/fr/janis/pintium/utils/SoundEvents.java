package fr.janis.pintium.utils;

import fr.janis.pintium.main;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SoundEvents {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, main.MODID);

    public static final RegistryObject<SoundEvent> SYNTHWAVE_VIBE = registerSoundEvent("synthwave_vibe");
    public static final RegistryObject<SoundEvent> BOING = registerSoundEvent("boing");

    private static RegistryObject<SoundEvent> registerSoundEvent(String name){
        return SOUND_EVENTS.register(name, () -> new SoundEvent(new ResourceLocation(main.MODID, name)));
    }

    public static void register(IEventBus e){
        SOUND_EVENTS.register(e);
    }
}