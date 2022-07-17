package fr.janis.pintium.items;

import fr.janis.pintium.utils.SoundEvents;
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
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;
import org.lwjgl.glfw.GLFW;

import javax.annotation.Nullable;
import java.util.List;
import java.util.concurrent.TimeUnit;

import net.minecraft.world.item.Item.Properties;

public class SuperJumpStick extends Item {

    public SuperJumpStick(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        if(InputConstants.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_LEFT_SHIFT)){
            tooltip.add(new TranslatableComponent("tooltip.jump_stick.hold_shift"));
        } else {
            tooltip.add(new TranslatableComponent("tooltip.jump_stick.not_hold_shift"));
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {

        if (!playerIn.getCooldowns().isOnCooldown(this) && !playerIn.getPersistentData().getBoolean("using_jump_stick")) {

            playerIn.addEffect(new MobEffectInstance(MobEffects.JUMP, 1, 19));
            playerIn.jumpFromGround();

            worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents.BOING.get(), SoundSource.BLOCKS, 1,1);

            playerIn.getPersistentData().putBoolean("using_jump_stick", true);

            playerIn.getMainHandItem().hurtAndBreak(8, playerIn, player -> player.broadcastBreakEvent(playerIn.getUsedItemHand()));

            playerIn.getCooldowns().addCooldown(this, 20*10);

            return InteractionResultHolder.pass(playerIn.getMainHandItem());
        }

        //ZombieEntity entity = new ZombieEntity(worldIn);
        //entity.setPosition(playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ());
        //worldIn.addEntity(entity);

        return InteractionResultHolder.fail(playerIn.getMainHandItem());
    }

}
