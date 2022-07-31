package fr.janis.pintium.blocks.entity;

import fr.janis.pintium.blocks.entity.custom.ExtractorMachine;
import fr.janis.pintium.init.PintiumBlocks;
import fr.janis.pintium.main;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, main.MODID);

    public static final RegistryObject<BlockEntityType<ExtractorMachine>> EXTRACTOR_MACHINE =
            BLOCK_ENTITIES.register("extractor_machine", () ->
                    BlockEntityType.Builder.of(ExtractorMachine::new,
                            PintiumBlocks.EXTRACTOR_MACHINE_BLOCK.get()).build(null));


    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
