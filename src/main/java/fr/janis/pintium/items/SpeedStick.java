package fr.janis.pintium.items;

import net.minecraft.client.Minecraft;
import net.minecraft.world.item.TooltipFlag;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.world.entity.player.Player;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;
import org.lwjgl.glfw.GLFW;

import javax.annotation.Nullable;
import java.util.List;

import net.minecraft.world.item.Item.Properties;

public class SpeedStick extends Item {
    public SpeedStick(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        if(InputConstants.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_LEFT_SHIFT)){
            tooltip.add(new TranslatableComponent("tooltip.speed_stick.hold_shift"));
        } else {
            tooltip.add(new TranslatableComponent("tooltip.speed_stick.not_hold_shift"));
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {

        if (!playerIn.getCooldowns().isOnCooldown(this)) {

            playerIn.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20*30, 2));

            playerIn.getMainHandItem().hurtAndBreak(8, playerIn, player -> player.broadcastBreakEvent(playerIn.getUsedItemHand()));

            playerIn.getCooldowns().addCooldown(this, 20*120);

            return InteractionResultHolder.pass(playerIn.getMainHandItem());
        }

        return InteractionResultHolder.fail(playerIn.getMainHandItem());
    }

}
