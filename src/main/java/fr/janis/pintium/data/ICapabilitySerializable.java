package fr.janis.pintium.data;

import net.minecraft.nbt.Tag;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;

public interface ICapabilitySerializable<T extends Tag> extends ICapabilityProvider, INBTSerializable<T> { }