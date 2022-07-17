package fr.janis.pintium.utils;

import fr.janis.pintium.init.PintiumItems;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.util.LazyLoadedValue;


public enum CustomPintiumTiers implements Tier {
    PINTIUM(5, 3000, 14.0F, 5.0F, 22, new LazyLoadedValue<>(() -> {
        return Ingredient.of(PintiumItems.PINTIUM_INGOT.get());
    }));

    private final int harvestLevel;
    private final int maxUses;
    private final float efficiency;
    private final float attackDamage;
    private final int enchantability;
    private final LazyLoadedValue<Ingredient> repairMaterial;

    CustomPintiumTiers(int harvestLevel, int maxUses, float efficiency, float attackDamage, int enchantability, LazyLoadedValue<Ingredient> repairMaterial) {
        this.harvestLevel = harvestLevel;
        this.maxUses = maxUses;
        this.efficiency = efficiency;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairMaterial = repairMaterial;
    }

    @Override
    public int getUses() {
        return maxUses;
    }

    @Override
    public float getSpeed() {
        return efficiency;
    }

    @Override
    public float getAttackDamageBonus() {
        return attackDamage;
    }

    @Override
    public int getLevel() {
        return harvestLevel;
    }

    @Override
    public int getEnchantmentValue() {
        return enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairMaterial.get();
    }
}
