package fr.janis.pintium.items;

import com.mojang.math.Vector3d;
import fr.janis.pintium.main;
import fr.janis.pintium.network.Network;
import fr.janis.pintium.network.packet.TPlayerPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.item.TooltipFlag;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
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
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player player, InteractionHand handIn) {
        BlockHitResult blockhitresult = getPlayerPOVHitResult(worldIn, player, ClipContext.Fluid.SOURCE_ONLY);
        Vec3 vec31 = blockhitresult.getLocation();
        BlockState b = worldIn.getBlockState(new BlockPos(vec31.x, vec31.y - 1, vec31.z));
        if (!b.getBlock().toString().equals("Block{minecraft:air}")){

            Network.CHANNEL.sendToServer(new TPlayerPacket(vec31.x, vec31.y, vec31.z));

            for (int i = 0; i < 10; i++) {
                worldIn.addParticle(ParticleTypes.PORTAL, vec31.x, vec31.y + worldIn.random.nextDouble() * 2.0D, vec31.z, worldIn.random.nextGaussian(), 0.0D, worldIn.random.nextGaussian());
            }

            worldIn.playSound(null, vec31.x, vec31.y, vec31.z, SoundEvents.ENDERMAN_TELEPORT, SoundSource.BLOCKS, 1, 1);

            player.getMainHandItem().hurtAndBreak(1, player, playerIn -> playerIn.broadcastBreakEvent(playerIn.getUsedItemHand()));

            player.getCooldowns().addCooldown(this, 20*2);

        } else {
            player.displayClientMessage(new TranslatableComponent("pintium.tpobject.air"), true);
        }

        return InteractionResultHolder.pass(player.getMainHandItem());
    }

    protected static BlockHitResult getPlayerPOVHitResult(Level p_41436_, Player p_41437_, ClipContext.Fluid p_41438_) {
        float f = p_41437_.getXRot();
        float f1 = p_41437_.getYRot();
        Vec3 vec3 = p_41437_.getEyePosition();
        float f2 = Mth.cos(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
        float f3 = Mth.sin(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
        float f4 = -Mth.cos(-f * ((float)Math.PI / 180F));
        float f5 = Mth.sin(-f * ((float)Math.PI / 180F));
        float f6 = f3 * f4;
        float f7 = f2 * f4;
        double d0 = 100;
        Vec3 vec31 = vec3.add((double)f6 * d0, (double)f5 * d0, (double)f7 * d0);
        return p_41436_.clip(new ClipContext(vec3, vec31, ClipContext.Block.OUTLINE, p_41438_, p_41437_));
    }
}
