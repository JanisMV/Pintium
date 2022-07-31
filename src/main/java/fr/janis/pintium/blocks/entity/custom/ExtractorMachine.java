package fr.janis.pintium.blocks.entity.custom;

import fr.janis.pintium.blocks.entity.BlockEntities;
import fr.janis.pintium.gui.ExtractorMachineMenu;
import fr.janis.pintium.init.PintiumItems;
import fr.janis.pintium.main;
import fr.janis.pintium.network.Network;
import fr.janis.pintium.network.packet.BlockEntityCooldown;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.Random;

public class ExtractorMachine extends BlockEntity implements MenuProvider {

    // 4 nombre slot machine a changer dans machinemenu :)
    public final ItemStackHandler itemHandler = new ItemStackHandler(3) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    public boolean isACooldownWorkingOn = false;

    public ExtractorMachine(BlockPos p_155229_, BlockState p_155230_) {
        super(BlockEntities.EXTRACTOR_MACHINE.get(), p_155229_, p_155230_);
    }

    @Override
    public Component getDisplayName() {
        return new TranslatableComponent("pintium.extractor.name");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int p_39954_, Inventory p_39955_, Player p_39956_) {
        return new ExtractorMachineMenu(p_39954_, p_39955_, this);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @javax.annotation.Nullable Direction side){
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps()  {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        tag.put("inventory", itemHandler.serializeNBT());
        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }


    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, ExtractorMachine pBlockEntity) {
        if(hasRecipe(pBlockEntity) && hasNotReachedStackLimit(pBlockEntity)) {
            craftItem(pBlockEntity);
        }
    }

    private static void craftItem(ExtractorMachine entity) {
        if (!entity.isACooldownWorkingOn) {
            entity.itemHandler.extractItem(0, 1, false);
            entity.itemHandler.getStackInSlot(1).hurt(1, new Random(), null);
            if (entity.itemHandler.getStackInSlot(1).getDamageValue() == 10) {
                entity.itemHandler.extractItem(1, 1, false);
            }
            Network.CHANNEL.sendToServer(new BlockEntityCooldown(entity.getBlockPos().getX(), entity.getBlockPos().getY(), entity.getBlockPos().getZ()));
            entity.isACooldownWorkingOn = true;
        }
    }

    private static boolean hasRecipe(ExtractorMachine entity) {
        boolean hasItemInFirstSlot = entity.itemHandler.getStackInSlot(0).getItem() == PintiumItems.CANNABIS_FOOD.get();
        boolean hasItemInSecondSlot = entity.itemHandler.getStackInSlot(1).getItem() == PintiumItems.CRUSHER.get();

        return hasItemInFirstSlot && hasItemInSecondSlot;
    }

    private static boolean hasNotReachedStackLimit(ExtractorMachine entity) {
        return entity.itemHandler.getStackInSlot(2).getCount() < entity.itemHandler.getStackInSlot(2).getMaxStackSize();
    }
}
