package fr.janis.pintium.items;

import net.minecraft.client.Minecraft;
import net.minecraft.world.item.TooltipFlag;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
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

public class StickOfGod extends Item {
    public StickOfGod(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        if(InputConstants.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_LEFT_SHIFT)){
            tooltip.add(new TranslatableComponent("tooltip.stick_of_god.hold_shift"));
        } else {
            tooltip.add(new TranslatableComponent("tooltip.stick_of_god.not_hold_shift"));
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {

        if (!playerIn.getCooldowns().isOnCooldown(this)) {
            playerIn.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 20 * 180, 4));
            playerIn.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 20 * 3, 0));
            playerIn.addEffect(new MobEffectInstance(MobEffects.SATURATION, 20 * 10, 1));
            playerIn.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20 * 180, 1));
            playerIn.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 20*30, 1));

            playerIn.getMainHandItem().hurtAndBreak(8, playerIn, player -> player.broadcastBreakEvent(playerIn.getUsedItemHand()));

            playerIn.getCooldowns().addCooldown(this, 20*300);
            return InteractionResultHolder.pass(playerIn.getMainHandItem());
        }

        Zombie entity = new Zombie(worldIn);
        entity.setPos(playerIn.getX(), playerIn.getY(), playerIn.getZ());
        worldIn.addFreshEntity(entity);

        return InteractionResultHolder.fail(playerIn.getMainHandItem());
    }
}
