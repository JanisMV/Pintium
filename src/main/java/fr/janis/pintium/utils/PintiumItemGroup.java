package fr.janis.pintium.utils;

import fr.janis.pintium.init.PintiumItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class PintiumItemGroup {
    public static final CreativeModeTab PINTIUM_TAB = new CreativeModeTab("pintium_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(PintiumItems.PINTIUM_INGOT.get());
        }
    };
}
