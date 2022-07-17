package fr.janis.pintium.event;

import fr.janis.pintium.entities.SkeletonBodyGuardEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.players.PlayerList;
import net.minecraft.core.BlockPos;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fmllegacy.server.ServerLifecycleHooks;

import java.time.Instant;
import java.util.Objects;
import java.util.Random;

public class ServerTickEvent {

    @SubscribeEvent
    public void onServerTickEvent(final TickEvent.ServerTickEvent e){

        Random dice = new Random();

        int posX;
        int posY;
        int posZ;

        posX = dice.nextInt(6);
        posY = dice.nextInt(6);
        posZ = dice.nextInt(6);

        MinecraftServer MServer = ServerLifecycleHooks.getCurrentServer();
        PlayerList plist = MServer.getPlayerList();
        for(ServerPlayer p : plist.getPlayers()) {
            if (p.getPersistentData().getBoolean("is_using_cannabis")){
                if (p.getPersistentData().getInt("is_using_cannabis_for") != 20*30){
                    Horse horse = new Horse(EntityType.HORSE, Objects.requireNonNull(p.getLevel()));
                    horse.setPos(p.getX() + posX, p.getY() + posY, p.getZ() + posZ);
                    Objects.requireNonNull(p.getLevel()).addFreshEntity(horse);
                    horse.remove(Entity.RemovalReason.DISCARDED);

                    Creeper creeper = new Creeper(EntityType.CREEPER, Objects.requireNonNull(p.getLevel()));
                    creeper.setPos(p.getX() - posX, p.getY() - posY, p.getZ() - posZ);
                    p.getLevel().addFreshEntity(creeper);
                    creeper.remove(Entity.RemovalReason.DISCARDED);

                    SkeletonBodyGuardEntity skeleton = new SkeletonBodyGuardEntity(EntityType.SKELETON, Objects.requireNonNull(p.getLevel()));
                    skeleton.setPos(p.getX() - posX + 5, p.getY() - posY, p.getZ() - posZ + 5);
                    p.getLevel().addFreshEntity(skeleton);
                    skeleton.remove(Entity.RemovalReason.DISCARDED);

                    int cannabis_used = p.getPersistentData().getInt("is_using_cannabis_for");

                    p.getPersistentData().putInt("is_using_cannabis_for", cannabis_used + 1);
                }
                else {
                    p.getPersistentData().putBoolean("is_using_cannabis", false);
                }
            }

            if (p.getPersistentData().getBoolean("inertium_is_used")) {
                if (p.getX() != p.getPersistentData().getDouble("posX") || p.getY() != p.getPersistentData().getDouble("posY") || p.getZ() != p.getPersistentData().getDouble("posZ")){
                    BlockPos pos = new BlockPos(p.getPersistentData().getDouble("posX") + 1, p.getPersistentData().getDouble("posY"), p.getPersistentData().getDouble("posZ") + 1);
                    p.getLevel().removeBlock(pos, false);

                    Item ItemMainHand = BlockItem.byId(p.getPersistentData().getInt("ItemIDMH"));
                    ItemStack ItemMainHandStack = new ItemStack(ItemMainHand);
                    ItemMainHandStack.setCount(p.getPersistentData().getInt("number_of_block"));
                    p.addItem(ItemMainHandStack);

                    p.removeEffect(MobEffects.INVISIBILITY);
                    p.getPersistentData().putBoolean("inertium_is_used", false);
                }
                else {
                    p.getPersistentData().putLong("inertium_use", Instant.now().getEpochSecond());
                    if (p.getPersistentData().getLong("inertium_cooldown") <= p.getPersistentData().getLong("inertium_use")) {
                        p.getPersistentData().putBoolean("inertium_is_used", false);

                        BlockPos pos = new BlockPos(p.getPersistentData().getDouble("posX") + 1, p.getPersistentData().getDouble("posY"), p.getPersistentData().getDouble("posZ") + 1);
                        p.getLevel().removeBlock(pos, false);

                        Item ItemMainHand = BlockItem.byId(p.getPersistentData().getInt("ItemIDMH"));

                        ItemStack ItemMainHandStack = new ItemStack(ItemMainHand);
                        ItemMainHandStack.setCount(p.getPersistentData().getInt("number_of_block"));
                        p.addItem(ItemMainHandStack);

                        p.removeEffect(MobEffects.INVISIBILITY);
                    }
                }
            }
        }

    }
}   
