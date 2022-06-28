package fr.janis.pintium.items;

import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.client.util.InputMappings;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import org.lwjgl.glfw.GLFW;

import javax.annotation.Nullable;
import java.util.List;

public class SpeedStick extends Item {
    public SpeedStick(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        if(InputMappings.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_LEFT_SHIFT)){
            tooltip.add(new TranslationTextComponent("tooltip.speed_stick.hold_shift"));
        } else {
            tooltip.add(new TranslationTextComponent("tooltip.speed_stick.not_hold_shift"));
        }
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {

        if (!playerIn.getCooldowns().isOnCooldown(this)) {

            playerIn.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, 20*30, 2));

            playerIn.getMainHandItem().setDamageValue(8);

            playerIn.getCooldowns().addCooldown(this, 20*120);

            return ActionResult.pass(playerIn.getMainHandItem());
        }

        return ActionResult.fail(playerIn.getMainHandItem());
    }

}
