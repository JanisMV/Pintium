package fr.janis.pintium.data;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.core.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

import javax.annotation.Nullable;

public class CapabilityEntityKilled {
    @CapabilityInject(IEntityKilled.class)
    public static Capability<IEntityKilled> ENTITY_KILLED_CAPABILITY = null;

    public static void register(){
        CapabilityManager.INSTANCE.register(IEntityKilled.class, new Storage(), EntityKilled::new);
    }

    public static class Storage implements Capability.IStorage<IEntityKilled> {

        @Nullable
        @Override
        public Tag writeNBT(Capability<IEntityKilled> capability, IEntityKilled instance, Direction side) {
            CompoundTag tag = new CompoundTag();
            if (instance.getName() != null){
                tag.putString("name", instance.getName());
            }
            return tag;
        }

        @Override
        public void readNBT(Capability<IEntityKilled> capability, IEntityKilled instance, Direction side, Tag nbt) {
            String name = ((CompoundTag) nbt).getString("name");
            if (!name.isEmpty()){
                instance.setName(name);
            }
        }
    }
}
