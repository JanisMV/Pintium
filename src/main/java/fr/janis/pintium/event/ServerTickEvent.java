package fr.janis.pintium.event;

import fr.janis.pintium.entities.BananoFishEntity;
import fr.janis.pintium.entities.BananosaurEntity;
import fr.janis.pintium.entities.SkeletonBodyGuardEntity;
import fr.janis.pintium.init.PintiumEntities;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.boss.wither.WitherBoss;
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
import net.minecraftforge.server.ServerLifecycleHooks;

import java.time.Instant;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ServerTickEvent {

    @SubscribeEvent
    public void onServerTickEvent(final TickEvent.ServerTickEvent e) throws InterruptedException {

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

                    int relative = dice.nextInt(2);

                    Horse horse = new Horse(EntityType.HORSE, p.getLevel());
                    if (relative == 1) {
                        horse.setPos(p.getX() + posX, p.getY() + posY, p.getZ() + posZ);
                    }
                    else {
                        horse.setPos(p.getX() - posX, p.getY() - posY, p.getZ() - posZ);
                    }
                    p.getLevel().addFreshEntity(horse);

                    TimeUnit.MILLISECONDS.sleep(10);

                    horse.remove(Entity.RemovalReason.DISCARDED);

                    relative = dice.nextInt(2);

                    Creeper creeper = new Creeper(EntityType.CREEPER, p.getLevel());
                    if (relative == 1) {
                        creeper.setPos(p.getX() + posX, p.getY() + posY, p.getZ() + posZ);
                    }
                    else {
                        creeper.setPos(p.getX() - posX, p.getY() - posY, p.getZ() - posZ);
                    }
                    p.getLevel().addFreshEntity(creeper);

                    TimeUnit.MILLISECONDS.sleep(10);

                    creeper.remove(Entity.RemovalReason.DISCARDED);

                    relative = dice.nextInt(2);
                    
                    SkeletonBodyGuardEntity skeleton = new SkeletonBodyGuardEntity(EntityType.SKELETON, p.getLevel());
                    if (relative == 1) {
                        skeleton.setPos(p.getX() + posX, p.getY() + posY, p.getZ() + posZ);
                    }
                    else {
                        skeleton.setPos(p.getX() - posX, p.getY() - posY, p.getZ() - posZ);
                    }
                    p.getLevel().addFreshEntity(skeleton);

                    TimeUnit.MILLISECONDS.sleep(10);

                    skeleton.remove(Entity.RemovalReason.DISCARDED);

                    BananosaurEntity b = new BananosaurEntity(PintiumEntities.BANANOSAUR.get(), p.getLevel());
                    if (relative == 1) {
                        b.setPos(p.getX() + posX, p.getY() + posY, p.getZ() + posZ);
                    }
                    else {
                        b.setPos(p.getX() - posX, p.getY() - posY, p.getZ() - posZ);
                    }
                    p.getLevel().addFreshEntity(b);

                    TimeUnit.MILLISECONDS.sleep(10);

                    b.remove(Entity.RemovalReason.DISCARDED);

                    BananoFishEntity bf = new BananoFishEntity(PintiumEntities.BANANOFISH.get(), p.getLevel());
                    if (relative == 1) {
                        bf.setPos(p.getX() + posX, p.getY() + posY, p.getZ() + posZ);
                    }
                    else {
                        bf.setPos(p.getX() - posX, p.getY() - posY, p.getZ() - posZ);
                    }
                    p.getLevel().addFreshEntity(bf);

                    TimeUnit.MILLISECONDS.sleep(10);

                    bf.remove(Entity.RemovalReason.DISCARDED);

                    WitherBoss w = new WitherBoss(EntityType.WITHER, p.getLevel());
                    if (relative == 1) {
                        w.setPos(p.getX() + posX, p.getY() + posY, p.getZ() + posZ);
                    }
                    else {
                        w.setPos(p.getX() - posX, p.getY() - posY, p.getZ() - posZ);
                    }
                    p.getLevel().addFreshEntity(w);

                    TimeUnit.MILLISECONDS.sleep(10);

                    w.remove(Entity.RemovalReason.DISCARDED);

                    int cannabis_used = p.getPersistentData().getInt("is_using_cannabis_for");

                    p.getPersistentData().putInt("is_using_cannabis_for", cannabis_used + 1);

                    p.displayClientMessage(Component.nullToEmpty(String.valueOf(p.getPersistentData().getInt("is_using_cannabis_for"))), true);
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
