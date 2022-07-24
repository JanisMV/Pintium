package fr.janis.pintium.init;
import fr.janis.pintium.blocks.*;
import fr.janis.pintium.main;

import fr.janis.pintium.utils.PintiumItemGroup;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class PintiumBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, main.MODID);

    public static final RegistryObject<Block> PINTIUM_BLOCK = createBlock("pintium_block", PintiumBlock::new);

    public static final RegistryObject<Block> BANANA_BLOCK = createBlock("banana_block", BananaBlock::new);

    public static final RegistryObject<Block> PINTIUM_OVERWORLD_ORE = createBlock("pintium_overworld_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(7f, 15f).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> PINTIUM_NETHER_ORE = createBlock("pintium_nether_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(4f, 15f).sound(SoundType.NETHER_ORE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> PINTIUM_DEEPSLATE_ORE = createBlock("pintium_deepslate_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(10f, 15f).sound(SoundType.DEEPSLATE).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> PINTIUM_CROP =
            BLOCKS.register("pintium_crop", () -> new PintiumCrop(BlockBehaviour.Properties.of(Blocks.WHEAT.defaultBlockState().getMaterial()).noCollission().noOcclusion()));

    public static final RegistryObject<Block> CANNABIS_CROP =
            BLOCKS.register("cannabis_crop", () -> new CannabisCrop(BlockBehaviour.Properties.of(Blocks.WHEAT.defaultBlockState().getMaterial()).noCollission().noOcclusion()));

    private static <T extends Block>RegistryObject<T> createBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        PintiumItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(PintiumItemGroup.PINTIUM_TAB).fireResistant()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
