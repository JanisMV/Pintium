package fr.janis.pintium.keybind;

import net.minecraft.client.KeyMapping;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.fmlclient.registry.ClientRegistry;
import org.lwjgl.glfw.GLFW;

public class KeyBinds {
    public static final KeyMapping SPELLS = new KeyMapping("key.spells", KeyConflictContext.UNIVERSAL, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_K, "key.categories.pintium");
    public static void register()
    {
        ClientRegistry.registerKeyBinding(SPELLS);
    }

}
