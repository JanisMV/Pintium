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
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.client.util.InputMappings;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import org.lwjgl.glfw.GLFW;

import javax.annotation.Nullable;
import java.util.List;

public class LifeStick extends Item {
    public LifeStick(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        if(InputMappings.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_LEFT_SHIFT)){
            tooltip.add(new TranslationTextComponent("tooltip.life_stick.hold_shift"));
        } else {
            tooltip.add(new TranslationTextComponent("tooltip.life_stick.not_hold_shift"));
        }
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        playerIn.getCapability(CapabilityEntityKilled.ENTITY_KILLED_CAPABILITY).ifPresent(h -> {
            if (h.getName() != null){
                if (h.getName().equals(PintiumEntities.RATEL.get().getDescription().getString()))
                {
                    if (playerIn.inventory.contains(new ItemStack(PintiumItems.HEAL_ORB.get()))) {
                        playerIn.inventory.removeItem(new ItemStack(PintiumItems.HEAL_ORB.get()));

                        String text = new TranslationTextComponent("pintium.life_stick.entity_revived").getString() + "ratel !";
                        playerIn.displayClientMessage(ITextComponent.nullToEmpty(text), true);

                        Network.CHANNEL.sendToServer(new TameRatelPacket());
                        h.setName(null);
                    }
                    else {
                        String text = new TranslationTextComponent("pintium.life_stick.no_orb").getString();
                        playerIn.displayClientMessage(ITextComponent.nullToEmpty((text)), true);
                    }
                }
                else if (h.getName().equals(EntityType.ZOMBIE.getDescription().getString())){
                    if (playerIn.inventory.contains(new ItemStack(PintiumItems.HEAL_ORB.get()))) {
                        playerIn.inventory.removeItem(new ItemStack(PintiumItems.HEAL_ORB.get()));

                        String text = new TranslationTextComponent("pintium.life_stick.entity_revived").getString() + "zombie !";
                        playerIn.displayClientMessage(ITextComponent.nullToEmpty((text)), true);

                        Network.CHANNEL.sendToServer(new TameZombiePacket());
                        h.setName(null);
                    }
                    else {
                        String text = new TranslationTextComponent("pintium.life_stick.no_orb").getString();
                        playerIn.displayClientMessage(ITextComponent.nullToEmpty((text)), true);
                    }
                }

                else if (h.getName().equals(EntityType.SKELETON.getDescription().getString())){
                    if (playerIn.inventory.contains(new ItemStack(PintiumItems.HEAL_ORB.get()))){
                        playerIn.inventory.removeItem(new ItemStack(PintiumItems.HEAL_ORB.get()));

                        String text = new TranslationTextComponent("pintium.life_stick.entity_revived").getString() + "skeleton !";
                        playerIn.displayClientMessage(ITextComponent.nullToEmpty((text)), true);

                        Network.CHANNEL.sendToServer(new TameSkeletonPacket());
                        h.setName(null);
                    }
                    else {
                        String text = new TranslationTextComponent("pintium.life_stick.no_orb").getString();
                        playerIn.displayClientMessage(ITextComponent.nullToEmpty((text)), true);
                    }
                }
                
                else if (h.getName().equals(EntityType.CREEPER.getDescription().getString())){
                    if (playerIn.inventory.contains(new ItemStack(PintiumItems.HEAL_ORB.get()))){
                        playerIn.inventory.removeItem(new ItemStack(PintiumItems.HEAL_ORB.get()));

                        String text = new TranslationTextComponent("pintium.life_stick.entity_revived").getString() + "creeper !";
                        playerIn.displayClientMessage(ITextComponent.nullToEmpty((text)), true);

                        Network.CHANNEL.sendToServer(new TameCreeperPacket());
                        h.setName(null);
                    }
                    else {
                        String text = new TranslationTextComponent("pintium.life_stick.no_orb").getString();
                        playerIn.displayClientMessage(ITextComponent.nullToEmpty((text)), true);
                    }
                }

            }

            else {
                String text = new TranslationTextComponent("pintium.life_stick.cannot_revive").getString();
                playerIn.displayClientMessage(ITextComponent.nullToEmpty((text)), true);
            }
        });

        return ActionResult.pass(playerIn.getMainHandItem());
    }

}
