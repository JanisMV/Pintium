package fr.janis.pintium.entities;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.monster.piglin.PiglinEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public class RatelBodyGuardEntity extends RatelEntity {

    public RatelBodyGuardEntity(EntityType<? extends RatelEntity> type, World worldIn) {
        super(type, worldIn);
    }

    // registerAttributes
    public static AttributeModifierMap.MutableAttribute setCustomAttributes(){
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 30.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.50D)
                .add(Attributes.ATTACK_DAMAGE, 4)
                .add(Attributes.ATTACK_SPEED, 1)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0);
    }

    @Override
    protected void registerGoals() {
        //super.registerGoals();
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, PiglinEntity.class, true));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, CowEntity.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, SheepEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, PigEntity.class, true));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, SkeletonEntity.class, true));
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, ZombieEntity.class, true));
        this.targetSelector.addGoal(6, new NearestAttackableTargetGoal<>(this, CreeperEntity.class, true));
        this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, SpiderEntity.class, true));
        this.targetSelector.addGoal(8, new NearestAttackableTargetGoal<>(this, BananosaurEntity.class, true));
        this.targetSelector.addGoal(9, new NearestAttackableTargetGoal<>(this, PillagerEntity.class, true));
        this.targetSelector.addGoal(10, new NearestAttackableTargetGoal<>(this, WitchEntity.class, true));
        this.targetSelector.addGoal(11, new NearestAttackableTargetGoal<>(this, VillagerEntity.class, true));
        this.targetSelector.addGoal(12, new NearestAttackableTargetGoal<>(this, HorseEntity.class, true));
        this.targetSelector.addGoal(13, new NearestAttackableTargetGoal<>(this, RatelEntity.class, true));
        this.targetSelector.addGoal(14, new NearestAttackableTargetGoal<>(this, BlazeEntity.class, true));
        this.targetSelector.addGoal(15, new NearestAttackableTargetGoal<>(this, CaveSpiderEntity.class, true));
        this.targetSelector.addGoal(16, new NearestAttackableTargetGoal<>(this, EndermanEntity.class, true));
        this.targetSelector.addGoal(17, new NearestAttackableTargetGoal<>(this, ElderGuardianEntity.class, true));
        this.targetSelector.addGoal(18, new NearestAttackableTargetGoal<>(this, EndermiteEntity.class, true));
        this.targetSelector.addGoal(19, new NearestAttackableTargetGoal<>(this, EvokerEntity.class, true));
        this.targetSelector.addGoal(20, new NearestAttackableTargetGoal<>(this, GhastEntity.class, true));
        this.targetSelector.addGoal(21, new NearestAttackableTargetGoal<>(this, HoglinEntity.class, true));
        this.targetSelector.addGoal(22, new NearestAttackableTargetGoal<>(this, HuskEntity.class, true));
        this.targetSelector.addGoal(23, new NearestAttackableTargetGoal<>(this, IllusionerEntity.class, true));
        this.targetSelector.addGoal(24, new NearestAttackableTargetGoal<>(this, MagmaCubeEntity.class, true));
        this.targetSelector.addGoal(25, new NearestAttackableTargetGoal<>(this, PhantomEntity.class, true));
        this.targetSelector.addGoal(26, new NearestAttackableTargetGoal<>(this, RavagerEntity.class, true));
        this.targetSelector.addGoal(27, new NearestAttackableTargetGoal<>(this, ShulkerEntity.class, true));
        this.targetSelector.addGoal(28, new NearestAttackableTargetGoal<>(this, SilverfishEntity.class, true));
        this.targetSelector.addGoal(29, new NearestAttackableTargetGoal<>(this, SlimeEntity.class, true));
        this.targetSelector.addGoal(30, new NearestAttackableTargetGoal<>(this, WitherSkeletonEntity.class, true));
        this.targetSelector.addGoal(31, new NearestAttackableTargetGoal<>(this, ZoglinEntity.class, true));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1, false));
        this.goalSelector.addGoal(2, new RandomWalkingGoal(this, 0.75D));
        this.goalSelector.addGoal(3, new LookRandomlyGoal(this));
    }

    @Override
    protected int getExperienceReward(PlayerEntity player){
        return 0;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.CAT_AMBIENT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.FOX_DEATH;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.FOX_HURT;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        super.playSound(SoundEvents.SHEEP_STEP, 0.15F, 1.0F);
    }

    @Override
    public void tick() {
        super.tick();
    }

}