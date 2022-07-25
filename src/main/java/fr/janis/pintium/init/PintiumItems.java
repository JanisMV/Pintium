package fr.janis.pintium.init;

import fr.janis.pintium.entities.TunaEntity;
import fr.janis.pintium.items.*;
import fr.janis.pintium.main;
import fr.janis.pintium.utils.CustomPintiumArmorMaterials;
import fr.janis.pintium.utils.CustomPintiumTiers;
import fr.janis.pintium.utils.PintiumItemGroup;
import fr.janis.pintium.utils.SoundEvents;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.RecordItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwordItem;
import net.minecraftforge.registries.RegistryObject;

public class PintiumItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, main.MODID);

    public static final RegistryObject<Item> PINTIUM_INGOT = ITEMS.register("pintium_ingot", () -> new Item(new Item.Properties().tab(PintiumItemGroup.PINTIUM_TAB).fireResistant()));

    public static final RegistryObject<ForgeSpawnEggItem> RATEL_SPAWN_EGG = ITEMS.register("ratel_spawn_egg", () -> new ForgeSpawnEggItem(PintiumEntities.RATEL, 0x000000, 0xFFFFFF, new Item.Properties().tab(PintiumItemGroup.PINTIUM_TAB).fireResistant()));
    public static final RegistryObject<ForgeSpawnEggItem> BANANOSAUR_SPAWN_EGG = ITEMS.register("bananosaur_spawn_egg", () -> new ForgeSpawnEggItem(PintiumEntities.BANANOSAUR, 0xFFE300, 0xFFFFFF, new Item.Properties().tab(PintiumItemGroup.PINTIUM_TAB).fireResistant()));
    public static final RegistryObject<ForgeSpawnEggItem> TUNA_SPAWN_EGG = ITEMS.register("tuna_spawn_egg", () -> new ForgeSpawnEggItem(PintiumEntities.TUNA, 0xFFAD00, 0x47FF00, new Item.Properties().tab(PintiumItemGroup.PINTIUM_TAB).fireResistant()));
    public static final RegistryObject<ForgeSpawnEggItem> BANANOFISH_SPAWN_EGG = ITEMS.register("bananofish_spawn_egg", () -> new ForgeSpawnEggItem(PintiumEntities.BANANOFISH, 0xE1FF00, 0xEEF0DC, new Item.Properties().tab(PintiumItemGroup.PINTIUM_TAB).fireResistant()));

    public static final RegistryObject<Item> PINTIUM_SWORD = ITEMS.register("pintium_sword", () -> new SwordItem(CustomPintiumTiers.PINTIUM, 6, -2.0f, new Item.Properties().stacksTo(1).tab(PintiumItemGroup.PINTIUM_TAB).fireResistant()));
    public static final RegistryObject<Item> PINTIUM_PICKAXE = ITEMS.register("pintium_pickaxe", () -> new PickaxeItem(CustomPintiumTiers.PINTIUM, 2, -2.4f, new Item.Properties().stacksTo(1).tab(PintiumItemGroup.PINTIUM_TAB).fireResistant()));
    public static final RegistryObject<Item> PINTIUM_AXE = ITEMS.register("pintium_axe", () -> new AxeItem(CustomPintiumTiers.PINTIUM, 8, -2.6f, new Item.Properties().stacksTo(1).tab(PintiumItemGroup.PINTIUM_TAB).fireResistant()));
    public static final RegistryObject<Item> PINTIUM_SHOVEL = ITEMS.register("pintium_shovel", () -> new ShovelItem(CustomPintiumTiers.PINTIUM, 3, -2.6f, new Item.Properties().stacksTo(1).tab(PintiumItemGroup.PINTIUM_TAB).fireResistant()));
    public static final RegistryObject<Item> PINTIUM_HOE = ITEMS.register("pintium_hoe", () -> new HoeItem(CustomPintiumTiers.PINTIUM, -5, 0.0f, new Item.Properties().stacksTo(1).tab(PintiumItemGroup.PINTIUM_TAB).fireResistant()));
    public static final RegistryObject<Item> PINTIUM_HAMMER = ITEMS.register("pintium_hammer", () -> new PintiumHammer(new Item.Properties().stacksTo(1).tab(PintiumItemGroup.PINTIUM_TAB).fireResistant()));
    
    public static final RegistryObject<Item> PINTIUM_HELMET = ITEMS.register("pintium_helmet", () -> new ArmorItem(CustomPintiumArmorMaterials.PINTIUM_ARMOR, EquipmentSlot.HEAD, new Item.Properties().tab(PintiumItemGroup.PINTIUM_TAB).stacksTo(1).fireResistant()));
    public static final RegistryObject<Item> PINTIUM_CHESTPLATE = ITEMS.register("pintium_chestplate", () -> new ArmorItem(CustomPintiumArmorMaterials.PINTIUM_ARMOR, EquipmentSlot.CHEST, new Item.Properties().tab(PintiumItemGroup.PINTIUM_TAB).stacksTo(1).fireResistant()));
    public static final RegistryObject<Item> PINTIUM_LEGGINGS = ITEMS.register("pintium_leggings", () -> new ArmorItem(CustomPintiumArmorMaterials.PINTIUM_ARMOR, EquipmentSlot.LEGS, new Item.Properties().tab(PintiumItemGroup.PINTIUM_TAB).stacksTo(1).fireResistant()));
    public static final RegistryObject<Item> PINTIUM_BOOTS = ITEMS.register("pintium_boots", () -> new ArmorItem(CustomPintiumArmorMaterials.PINTIUM_ARMOR, EquipmentSlot.FEET, new Item.Properties().tab(PintiumItemGroup.PINTIUM_TAB).stacksTo(1).fireResistant()));

    public static final RegistryObject<Item> PINTIUM_SEEDS =
            ITEMS.register("pintium_seeds", () -> new BlockItem(PintiumBlocks.PINTIUM_CROP.get(), new Item.Properties().tab(PintiumItemGroup.PINTIUM_TAB).fireResistant()));

    public static final RegistryObject<Item> CANNABIS_FOOD =
            ITEMS.register("cannabis_food", () -> new BlockItem(PintiumBlocks.CANNABIS_CROP.get(), new Item.Properties().tab(PintiumItemGroup.PINTIUM_TAB).food(
                    new FoodProperties.Builder()
                            .nutrition(3)
                            .saturationMod(1.4F)
                    .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 20*30, 0), 1.0F)
                    .build()
            )));

    public static final RegistryObject<Item> PINTIUM_NUGGET = ITEMS.register("pintium_nugget", () -> new Item(new Item.Properties().tab(PintiumItemGroup.PINTIUM_TAB).fireResistant()));

    public static final RegistryObject<Item> PINTIUM_STICK = ITEMS.register("pintium_stick", () -> new Item(new Item.Properties().tab(PintiumItemGroup.PINTIUM_TAB).fireResistant()));

    public static final RegistryObject<StickOfGod> STICK_OF_GOD = ITEMS.register("stick_of_god",  () -> new StickOfGod(new Item.Properties().tab(PintiumItemGroup.PINTIUM_TAB).fireResistant().stacksTo(1).durability(800).defaultDurability(800)));
    public static final RegistryObject<JumpStick> JUMP_STICK = ITEMS.register("jump_stick",  () -> new JumpStick(new Item.Properties().tab(PintiumItemGroup.PINTIUM_TAB).fireResistant().stacksTo(1).durability(80).defaultDurability(80)));
    public static final RegistryObject<SpeedStick> SPEED_STICK = ITEMS.register("speed_stick",  () -> new SpeedStick(new Item.Properties().tab(PintiumItemGroup.PINTIUM_TAB).fireResistant().stacksTo(1).durability(80).defaultDurability(80)));
    public static final RegistryObject<StrengthStick> STRENGTH_STICK = ITEMS.register("strength_stick",  () -> new StrengthStick(new Item.Properties().tab(PintiumItemGroup.PINTIUM_TAB).fireResistant().stacksTo(1).durability(80).defaultDurability(80)));
    public static final RegistryObject<SuperJumpStick> SUPER_JUMP_STICK = ITEMS.register("super_jump_stick",  () -> new SuperJumpStick(new Item.Properties().tab(PintiumItemGroup.PINTIUM_TAB).fireResistant().stacksTo(1).durability(80).defaultDurability(80)));
    public static final RegistryObject<HealStick> HEAL_STICK = ITEMS.register("heal_stick",  () -> new HealStick(new Item.Properties().tab(PintiumItemGroup.PINTIUM_TAB).fireResistant().stacksTo(1).durability(80).defaultDurability(80)));

    public static final RegistryObject<Item> COMPRESSED_PINTIUM = ITEMS.register("compressed_pintium", () -> new Item(new Item.Properties().tab(PintiumItemGroup.PINTIUM_TAB).fireResistant()));
    public static final RegistryObject<Item> HEAL_ORB = ITEMS.register("heal_orb", () -> new Item(new Item.Properties().tab(PintiumItemGroup.PINTIUM_TAB).fireResistant()));
    public static final RegistryObject<Item> JUMP_ORB = ITEMS.register("jump_orb", () -> new Item(new Item.Properties().tab(PintiumItemGroup.PINTIUM_TAB).fireResistant()));
    public static final RegistryObject<Item> SPEED_ORB = ITEMS.register("speed_orb", () -> new Item(new Item.Properties().tab(PintiumItemGroup.PINTIUM_TAB).fireResistant()));
    public static final RegistryObject<Item> STRENGTH_ORB = ITEMS.register("strength_orb", () -> new Item(new Item.Properties().tab(PintiumItemGroup.PINTIUM_TAB).fireResistant()));

    public static final RegistryObject<Item> PINTIUM_APPLE = ITEMS.register("pintium_apple", () -> new Item(new Item.Properties()
            .tab(PintiumItemGroup.PINTIUM_TAB)
            .fireResistant()
            .food(new FoodProperties.Builder()
                    .nutrition(6)
                    .saturationMod(1.5F)
                    .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 20*30, 0), 1.0F)
                    .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 20*600, 4), 1.0F)
                    .effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 20*600, 1), 1.0F)
                    .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 20*600, 0), 1.0F)
                    .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 20*600, 1), 1.0F)
                    .alwaysEat()
                    .build())));

    public static final RegistryObject<Item> BANANA = ITEMS.register("banana", () -> new Item(new Item.Properties().tab(PintiumItemGroup.PINTIUM_TAB).fireResistant().food(
            new FoodProperties.Builder()
            .nutrition(8)
            .saturationMod(2.0F)
            .alwaysEat()
            .build()
    )));

    public static final RegistryObject<Item> POLONIUM = ITEMS.register("polonium", () -> new Item(new Item.Properties().tab(PintiumItemGroup.PINTIUM_TAB).fireResistant().food(
            new FoodProperties.Builder()
            .nutrition(1)
            .saturationMod(0.0F)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 20*180, 500), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 20*30, 0), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 20*180, 500), 1.0F)
            .alwaysEat().build()
    )));

    public static final RegistryObject<Item> DELICIOUS_TUNA = ITEMS.register("delicious_tuna", () -> new Item(new Item.Properties().tab(PintiumItemGroup.PINTIUM_TAB).food(
            new FoodProperties.Builder()
            .nutrition(1)
            .saturationMod(0.5F)
            .alwaysEat()
            .build()
    )));

    public static final RegistryObject<Item> COOKED_TUNA = ITEMS.register("cooked_tuna", () -> new Item(new Item.Properties().tab(PintiumItemGroup.PINTIUM_TAB).food(
            new FoodProperties.Builder()
            .nutrition(8)
            .saturationMod(1.5F)
            .alwaysEat()
            .build()
    )));

    public static final RegistryObject<Item> TUNA_BUCKET = ITEMS.register("tuna_bucket", () -> new BucketItem(/*PintiumEntities.TUNA,*/ () -> Fluids.WATER, new Item.Properties().tab(PintiumItemGroup.PINTIUM_TAB).stacksTo(1)));
    public static final RegistryObject<Item> BANANOFISH_BUCKET = ITEMS.register("bananofish_bucket", () -> new BucketItem(/*PintiumEntities.BANANOFISH,*/ () -> Fluids.WATER, new Item.Properties().tab(PintiumItemGroup.PINTIUM_TAB).stacksTo(1)));

    public static final RegistryObject<Item> LIFE_STICK = ITEMS.register("life_stick", () -> new LifeStick(new Item.Properties().tab(PintiumItemGroup.PINTIUM_TAB).fireResistant().stacksTo(1).durability(80).defaultDurability(80)));

    public static final RegistryObject<Item> SYNTHWAVE_VIBE_MUSIC_DISC = ITEMS.register("synthwave_vibe_music_disc", () -> new RecordItem(1, () -> SoundEvents.SYNTHWAVE_VIBE.get(),new Item.Properties().tab(PintiumItemGroup.PINTIUM_TAB).stacksTo(1)));

    public static final RegistryObject<Item> TP_OBJECT = ITEMS.register("tp_object", () -> new TPObject(new Item.Properties().tab(PintiumItemGroup.PINTIUM_TAB).fireResistant().stacksTo(1).durability(80).defaultDurability(80)));
}