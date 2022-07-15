package fr.janis.pintium.items;

import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.client.util.InputMappings;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import org.lwjgl.glfw.GLFW;

import javax.annotation.Nullable;
import java.util.List;

public class StickOfGod extends Item {
    public StickOfGod(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        if(InputMappings.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_LEFT_SHIFT)){
            tooltip.add(new TranslationTextComponent("tooltip.stick_of_god.hold_shift"));
        } else {
            tooltip.add(new TranslationTextComponent("tooltip.stick_of_god.not_hold_shift"));
        }
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {

        if (!playerIn.getCooldowns().isOnCooldown(this)) {
            playerIn.addEffect(new EffectInstance(Effects.ABSORPTION, 20 * 180, 4));
            playerIn.addEffect(new EffectInstance(Effects.REGENERATION, 20 * 3, 0));
            playerIn.addEffect(new EffectInstance(Effects.SATURATION, 20 * 10, 1));
            playerIn.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, 20 * 180, 1));
            playerIn.addEffect(new EffectInstance(Effects.DAMAGE_BOOST, 20*30, 1));

            playerIn.getMainHandItem().hurtAndBreak(8, playerIn, player -> player.broadcastBreakEvent(playerIn.getUsedItemHand()));

            playerIn.getCooldowns().addCooldown(this, 20*300);
            return ActionResult.pass(playerIn.getMainHandItem());
        }

        ZombieEntity entity = new ZombieEntity(worldIn);
        entity.setPos(playerIn.getX(), playerIn.getY(), playerIn.getZ());
        worldIn.addFreshEntity(entity);

        return ActionResult.fail(playerIn.getMainHandItem());
    }
}
