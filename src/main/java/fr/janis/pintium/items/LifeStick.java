package fr.janis.pintium.items;

import fr.janis.pintium.data.CapabilityEntityKilled;
import fr.janis.pintium.init.PintiumEntities;
import fr.janis.pintium.init.PintiumItems;
import fr.janis.pintium.main;
import fr.janis.pintium.network.Network;
import fr.janis.pintium.network.packet.TameCreeperPacket;
import fr.janis.pintium.network.packet.TameRatelPacket;
import fr.janis.pintium.network.packet.TameSkeletonPacket;
import fr.janis.pintium.network.packet.TameZombiePacket;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.TooltipFlag;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;
import org.lwjgl.glfw.GLFW;

import javax.annotation.Nullable;
import java.util.List;

import net.minecraft.world.item.Item.Properties;

public class LifeStick extends Item {
    public LifeStick(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        if(InputConstants.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_LEFT_SHIFT)){
            tooltip.add(new TranslatableComponent("tooltip.life_stick.hold_shift"));
        } else {
            tooltip.add(new TranslatableComponent("tooltip.life_stick.not_hold_shift"));
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        playerIn.getCapability(CapabilityEntityKilled.ENTITY_KILLED_CAPABILITY).ifPresent(h -> {
            if (h.getName() != null){
                if (h.getName().equals(PintiumEntities.RATEL.get().getDescription().getString()))
                {
                    if (playerIn.inventory.contains(new ItemStack(PintiumItems.HEAL_ORB.get()))) {
                        playerIn.inventory.removeItem(new ItemStack(PintiumItems.HEAL_ORB.get()));

                        String text = new TranslatableComponent("pintium.life_stick.entity_revived").getString() + "ratel !";
                        playerIn.displayClientMessage(Component.nullToEmpty(text), true);

                        Network.CHANNEL.sendToServer(new TameRatelPacket());
                        h.setName(null);
                    }
                    else {
                        String text = new TranslatableComponent("pintium.life_stick.no_orb").getString();
                        playerIn.displayClientMessage(Component.nullToEmpty((text)), true);
                    }
                }
                else if (h.getName().equals(EntityType.ZOMBIE.getDescription().getString())){
                    if (playerIn.inventory.contains(new ItemStack(PintiumItems.HEAL_ORB.get()))) {
                        playerIn.inventory.removeItem(new ItemStack(PintiumItems.HEAL_ORB.get()));

                        String text = new TranslatableComponent("pintium.life_stick.entity_revived").getString() + "zombie !";
                        playerIn.displayClientMessage(Component.nullToEmpty((text)), true);

                        Network.CHANNEL.sendToServer(new TameZombiePacket());
                        h.setName(null);
                    }
                    else {
                        String text = new TranslatableComponent("pintium.life_stick.no_orb").getString();
                        playerIn.displayClientMessage(Component.nullToEmpty((text)), true);
                    }
                }

                else if (h.getName().equals(EntityType.SKELETON.getDescription().getString())){
                    if (playerIn.inventory.contains(new ItemStack(PintiumItems.HEAL_ORB.get()))){
                        playerIn.inventory.removeItem(new ItemStack(PintiumItems.HEAL_ORB.get()));

                        String text = new TranslatableComponent("pintium.life_stick.entity_revived").getString() + "skeleton !";
                        playerIn.displayClientMessage(Component.nullToEmpty((text)), true);

                        Network.CHANNEL.sendToServer(new TameSkeletonPacket());
                        h.setName(null);
                    }
                    else {
                        String text = new TranslatableComponent("pintium.life_stick.no_orb").getString();
                        playerIn.displayClientMessage(Component.nullToEmpty((text)), true);
                    }
                }
                
                else if (h.getName().equals(EntityType.CREEPER.getDescription().getString())){
                    if (playerIn.inventory.contains(new ItemStack(PintiumItems.HEAL_ORB.get()))){
                        playerIn.inventory.removeItem(new ItemStack(PintiumItems.HEAL_ORB.get()));

                        String text = new TranslatableComponent("pintium.life_stick.entity_revived").getString() + "creeper !";
                        playerIn.displayClientMessage(Component.nullToEmpty((text)), true);

                        Network.CHANNEL.sendToServer(new TameCreeperPacket());
                        h.setName(null);
                    }
                    else {
                        String text = new TranslatableComponent("pintium.life_stick.no_orb").getString();
                        playerIn.displayClientMessage(Component.nullToEmpty((text)), true);
                    }
                }

            }

            else {
                String text = new TranslatableComponent("pintium.life_stick.cannot_revive").getString();
                playerIn.displayClientMessage(Component.nullToEmpty((text)), true);
            }
        });

        return InteractionResultHolder.pass(playerIn.getMainHandItem());
    }

}
