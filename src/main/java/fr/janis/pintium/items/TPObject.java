package fr.janis.pintium.items;

import fr.janis.pintium.main;
import fr.janis.pintium.network.Network;
import fr.janis.pintium.network.packet.TPlayerPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.TooltipFlag;
import com.mojang.blaze3d.platform.InputConstants;
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


public class TPObject extends Item {
    public TPObject(Properties p){super(p);}

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        if(InputConstants.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_LEFT_SHIFT)){
            tooltip.add(new TranslatableComponent("tooltip.tpobject.hold_shift"));
        } else {
            tooltip.add(new TranslatableComponent("tooltip.tpobject.not_hold_shift"));
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        boolean hasCos = playerIn.getPersistentData().getDouble(main.MODID + "x") != 0.0;
        if (hasCos) {
            double x = playerIn.getPersistentData().getDouble(main.MODID + "x");
            double y = playerIn.getPersistentData().getDouble(main.MODID + "y");
            double z = playerIn.getPersistentData().getDouble(main.MODID + "z");

            if (x > 0) {
                x = x + 0.5;
            }else {
                x = x - 0.5;
            }

            if (z > 0) {
                z = z + 0.5;
            }else {
                z = z - 0.5;
            }

            Network.CHANNEL.sendToServer(new TPlayerPacket(x, y, z));

            playerIn.getPersistentData().putDouble(main.MODID + "x", 0.0);
            playerIn.getPersistentData().putDouble(main.MODID + "y", 0.0);
            playerIn.getPersistentData().putDouble(main.MODID + "z", 0.0);
        }
        return InteractionResultHolder.pass(playerIn.getMainHandItem());
    }
}
