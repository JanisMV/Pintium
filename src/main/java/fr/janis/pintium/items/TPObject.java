package fr.janis.pintium.items;

import fr.janis.pintium.gui.TPObjectGUI;
import net.minecraft.client.Minecraft;
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

import net.minecraft.world.item.Item.Properties;

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

        Minecraft.getInstance().setScreen(new TPObjectGUI());

        return InteractionResultHolder.pass(playerIn.getMainHandItem());
    }
}
