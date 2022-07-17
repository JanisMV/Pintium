package fr.janis.pintium.entities;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.entity.monster.*;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.monster.CaveSpider;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.ElderGuardian;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Endermite;
import net.minecraft.world.entity.monster.Evoker;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.monster.Husk;
import net.minecraft.world.entity.monster.Illusioner;
import net.minecraft.world.entity.monster.MagmaCube;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.entity.monster.Pillager;
import net.minecraft.world.entity.monster.Ravager;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.entity.monster.Silverfish;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.monster.Witch;
import net.minecraft.world.entity.monster.WitherSkeleton;
import net.minecraft.world.entity.monster.Zoglin;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.hoglin.Hoglin;

public class RatelBodyGuardEntity extends RatelEntity {

    public RatelBodyGuardEntity(EntityType<? extends RatelEntity> type, Level worldIn) {
        super(type, worldIn);
    }

    // registerAttributes
    public static AttributeSupplier.Builder setCustomAttributes(){
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 30.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.50D)
                .add(Attributes.ATTACK_DAMAGE, 4)
                .add(Attributes.ATTACK_SPEED, 1)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0);
    }

    @Override
    protected void registerGoals() {
        //super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, Piglin.class, true));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Cow.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Sheep.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Pig.class, true));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Skeleton.class, true));
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, Zombie.class, true));
        this.targetSelector.addGoal(6, new NearestAttackableTargetGoal<>(this, Creeper.class, true));
        this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, Spider.class, true));
        this.targetSelector.addGoal(8, new NearestAttackableTargetGoal<>(this, BananosaurEntity.class, true));
        this.targetSelector.addGoal(9, new NearestAttackableTargetGoal<>(this, Pillager.class, true));
        this.targetSelector.addGoal(10, new NearestAttackableTargetGoal<>(this, Witch.class, true));
        this.targetSelector.addGoal(11, new NearestAttackableTargetGoal<>(this, Villager.class, true));
        this.targetSelector.addGoal(12, new NearestAttackableTargetGoal<>(this, Horse.class, true));
        this.targetSelector.addGoal(13, new NearestAttackableTargetGoal<>(this, RatelEntity.class, true));
        this.targetSelector.addGoal(14, new NearestAttackableTargetGoal<>(this, Blaze.class, true));
        this.targetSelector.addGoal(15, new NearestAttackableTargetGoal<>(this, CaveSpider.class, true));
        this.targetSelector.addGoal(16, new NearestAttackableTargetGoal<>(this, EnderMan.class, true));
        this.targetSelector.addGoal(17, new NearestAttackableTargetGoal<>(this, ElderGuardian.class, true));
        this.targetSelector.addGoal(18, new NearestAttackableTargetGoal<>(this, Endermite.class, true));
        this.targetSelector.addGoal(19, new NearestAttackableTargetGoal<>(this, Evoker.class, true));
        this.targetSelector.addGoal(20, new NearestAttackableTargetGoal<>(this, Ghast.class, true));
        this.targetSelector.addGoal(21, new NearestAttackableTargetGoal<>(this, Hoglin.class, true));
        this.targetSelector.addGoal(22, new NearestAttackableTargetGoal<>(this, Husk.class, true));
        this.targetSelector.addGoal(23, new NearestAttackableTargetGoal<>(this, Illusioner.class, true));
        this.targetSelector.addGoal(24, new NearestAttackableTargetGoal<>(this, MagmaCube.class, true));
        this.targetSelector.addGoal(25, new NearestAttackableTargetGoal<>(this, Phantom.class, true));
        this.targetSelector.addGoal(26, new NearestAttackableTargetGoal<>(this, Ravager.class, true));
        this.targetSelector.addGoal(27, new NearestAttackableTargetGoal<>(this, Shulker.class, true));
        this.targetSelector.addGoal(28, new NearestAttackableTargetGoal<>(this, Silverfish.class, true));
        this.targetSelector.addGoal(29, new NearestAttackableTargetGoal<>(this, Slime.class, true));
        this.targetSelector.addGoal(30, new NearestAttackableTargetGoal<>(this, WitherSkeleton.class, true));
        this.targetSelector.addGoal(31, new NearestAttackableTargetGoal<>(this, Zoglin.class, true));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1, false));
        this.goalSelector.addGoal(2, new RandomStrollGoal(this, 0.75D));
        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
    }

    @Override
    protected int getExperienceReward(Player player){
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
