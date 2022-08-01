package fr.janis.pintium.blocks.entity.custom;

import fr.janis.pintium.blocks.entity.BlockEntities;
import fr.janis.pintium.gui.ExtractorMachineMenu;
import fr.janis.pintium.init.PintiumItems;
import fr.janis.pintium.recipe.ExtractorMachineRecipe;
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
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
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
import java.util.Optional;
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
    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 144;

    public ExtractorMachine(BlockPos p_155229_, BlockState p_155230_) {
        super(BlockEntities.EXTRACTOR_MACHINE.get(), p_155229_, p_155230_);
        this.data = new ContainerData() {
            public int get(int index) {
                switch (index) {
                    case 0: return ExtractorMachine.this.progress;
                    case 1: return ExtractorMachine.this.maxProgress;
                    default: return 0;
                }
            }

            public void set(int index, int value) {
                switch(index) {
                    case 0: ExtractorMachine.this.progress = value; break;
                    case 1: ExtractorMachine.this.maxProgress = value; break;
                }
            }

            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return new TranslatableComponent("pintium.extractor.name");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int p_39954_, Inventory p_39955_, Player p_39956_) {
        return new ExtractorMachineMenu(p_39954_, p_39955_, this, this.data);
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
        tag.putInt("extractor_machine", progress);
        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
        progress = nbt.getInt("extractor_machine");
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, ExtractorMachine pBlockEntity) {
        if(hasRecipe(pBlockEntity)) {
            pBlockEntity.progress++;
            setChanged(pLevel, pPos, pState);
            if(pBlockEntity.progress > pBlockEntity.maxProgress) {
                craftItem(pBlockEntity);
            }
        } else {
            pBlockEntity.resetProgress();
            setChanged(pLevel, pPos, pState);
        }
    }

    private static boolean hasRecipe(ExtractorMachine entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        Optional<ExtractorMachineRecipe> match = level.getRecipeManager()
                .getRecipeFor(ExtractorMachineRecipe.Type.INSTANCE, inventory, level);

        return match.isPresent() && canInsertAmountIntoOutputSlot(inventory)
                && canInsertItemIntoOutputSlot(inventory, match.get().getResultItem())
                && hasToolsInToolSlot(entity);
    }

    private static boolean hasToolsInToolSlot(ExtractorMachine entity) {
        return entity.itemHandler.getStackInSlot(1).getItem() == PintiumItems.CRUSHER.get() || entity.itemHandler.getStackInSlot(1).getItem() == PintiumItems.TERBIUM_CRUSHER.get() || entity.itemHandler.getStackInSlot(1).getItem() == PintiumItems.PINTIUM_CRUSHER.get();
    }

    private static void craftItem(ExtractorMachine entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        Optional<ExtractorMachineRecipe> match = level.getRecipeManager()
                .getRecipeFor(ExtractorMachineRecipe.Type.INSTANCE, inventory, level);

        if(match.isPresent()) {
            entity.itemHandler.extractItem(0,1, false);
            entity.itemHandler.getStackInSlot(1).hurt(1, new Random(), null);
            if (entity.itemHandler.getStackInSlot(1).getItem() == PintiumItems.CRUSHER.get() && entity.itemHandler.getStackInSlot(1).getDamageValue() == 10) {
                entity.itemHandler.extractItem(1, 1, false);
            }
            if (entity.itemHandler.getStackInSlot(1).getItem() == PintiumItems.TERBIUM_CRUSHER.get() && entity.itemHandler.getStackInSlot(1).getDamageValue() == 100) {
                entity.itemHandler.extractItem(1, 1, false);
            }
            if (entity.itemHandler.getStackInSlot(1).getItem() == PintiumItems.PINTIUM_CRUSHER.get() && entity.itemHandler.getStackInSlot(1).getDamageValue() == 1000) {
                entity.itemHandler.extractItem(1, 1, false);
            }

            Random r = new Random();

            if (match.get().getResultItem().getItem() == PintiumItems.POLONIUM.get()) {
                if (r.nextInt(1001) == 1000) {
                    entity.itemHandler.setStackInSlot(2, new ItemStack(match.get().getResultItem().getItem(),
                            entity.itemHandler.getStackInSlot(2).getCount() + 1));
                }
            }
            else if (match.get().getResultItem().getItem() == PintiumItems.PINTIUM_SEEDS.get()){
                if (r.nextFloat() > 0.98f){
                    entity.itemHandler.setStackInSlot(2, new ItemStack(match.get().getResultItem().getItem(),
                            entity.itemHandler.getStackInSlot(2).getCount() + 1));
                }
            }

            entity.resetProgress();
        }
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack output) {
        return inventory.getItem(2).getItem() == output.getItem() || inventory.getItem(3).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory) {
        return inventory.getItem(2).getMaxStackSize() > inventory.getItem(3).getCount();
    }
}
