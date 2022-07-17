package fr.janis.pintium.entities;

import fr.janis.pintium.init.PintiumEntities;
import fr.janis.pintium.init.PintiumItems;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;

import javax.annotation.Nullable;

import net.minecraft.world.entity.AgableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.FollowParentGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;

public class BananosaurEntity extends Animal {

    public static final Ingredient TEMPTATION_ITEMS = Ingredient.of(PintiumItems.BANANA.get());

    public BananosaurEntity(EntityType<? extends Animal> type, Level worldIn) {
        super(type, worldIn);
    }

    // registerAttributes
    public static AttributeSupplier.Builder setCustomAttributes(){
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 40.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.50D)
                .add(Attributes.ATTACK_DAMAGE, 4)
                .add(Attributes.ATTACK_SPEED, 1)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new FollowParentGoal(this, 0.50D));
        this.goalSelector.addGoal(2, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(3, new BreedGoal(this, 1D));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1D, TEMPTATION_ITEMS, false));
        this.goalSelector.addGoal(5, new RandomStrollGoal(this, 0.75D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }

    @Override
    protected int getExperienceReward(Player player){
        return 1 + this.level.random.nextInt(4);
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.SALMON_AMBIENT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.SALMON_DEATH;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.SALMON_HURT;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        super.playSound(SoundEvents.SHEEP_STEP, 0.15F, 1.0F);
    }

    @Nullable
    @Override
    public AgableMob getBreedOffspring(ServerLevel world, AgableMob mate) {
        return PintiumEntities.BANANOSAUR.get().create(this.level);
    }
}
