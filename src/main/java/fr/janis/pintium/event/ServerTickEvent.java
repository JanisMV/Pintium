package fr.janis.pintium.event;

import fr.janis.pintium.entities.SkeletonBodyGuardEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.item.ItemFrameEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effects;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

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
        for(ServerPlayerEntity p : plist.getPlayers()) {
            if (p.getPersistentData().getBoolean("is_using_cannabis")){
                if (p.getPersistentData().getInt("is_using_cannabis_for") != 20*30){
                    HorseEntity horse = new HorseEntity(EntityType.HORSE, Objects.requireNonNull(p.getLevel()));
                    horse.setPos(p.getX() + posX, p.getY() + posY, p.getZ() + posZ);
                    Objects.requireNonNull(p.getLevel()).addFreshEntity(horse);
                    horse.remove();

                    CreeperEntity creeper = new CreeperEntity(EntityType.CREEPER, Objects.requireNonNull(p.getLevel()));
                    creeper.setPos(p.getX() - posX, p.getY() - posY, p.getZ() - posZ);
                    p.getLevel().addFreshEntity(creeper);
                    creeper.remove();

                    SkeletonBodyGuardEntity skeleton = new SkeletonBodyGuardEntity(EntityType.SKELETON, Objects.requireNonNull(p.getLevel()));
                    skeleton.setPos(p.getX() - posX + 5, p.getY() - posY, p.getZ() - posZ + 5);
                    p.getLevel().addFreshEntity(skeleton);
                    skeleton.remove();

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

                    p.removeEffect(Effects.INVISIBILITY);
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

                        p.removeEffect(Effects.INVISIBILITY);
                    }
                }
            }
        }

    }
}   
