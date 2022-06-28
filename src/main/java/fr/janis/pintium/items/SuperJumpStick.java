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
import java.util.concurrent.TimeUnit;

public class SuperJumpStick extends Item {

    public SuperJumpStick(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        if(InputMappings.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_LEFT_SHIFT)){
            tooltip.add(new TranslationTextComponent("tooltip.jump_stick.hold_shift"));
        } else {
            tooltip.add(new TranslationTextComponent("tooltip.jump_stick.not_hold_shift"));
        }
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {

        if (!playerIn.getCooldowns().isOnCooldown(this) && !playerIn.getPersistentData().getBoolean("using_jump_stick")) {

            playerIn.addEffect(new EffectInstance(Effects.JUMP, 1, 19));
            playerIn.jumpFromGround();

            playerIn.getPersistentData().putBoolean("using_jump_stick", true);

            playerIn.getMainHandItem().setDamageValue(8);

            playerIn.getCooldowns().addCooldown(this, 20*10);

            return ActionResult.pass(playerIn.getMainHandItem());
        }

        //ZombieEntity entity = new ZombieEntity(worldIn);
        //entity.setPosition(playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ());
        //worldIn.addEntity(entity);

        return ActionResult.fail(playerIn.getMainHandItem());
    }

}
