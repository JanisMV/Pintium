package fr.janis.pintium.keybind;

import net.minecraft.client.KeyMapping;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class KeyBinds {
    public static final KeyMapping SPAWN_BOAT = new KeyMapping("key.spawn_boat", KeyConflictContext.UNIVERSAL, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_K, "key.categories.pintium");
    public static final KeyMapping HIDE_BLOCK = new KeyMapping("key.hide_block", KeyConflictContext.UNIVERSAL, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_J, "key.categories.pintium");
    public static final KeyMapping SPAWN_ZOMBIE = new KeyMapping("key.spawn_zombie", KeyConflictContext.UNIVERSAL, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_I, "key.categories.pintium");
    public static final KeyMapping LAUNCH_FIREBALL = new KeyMapping("key.launch_fireball", KeyConflictContext.UNIVERSAL, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_M, "key.categories.pintium");
    public static void register()
    {
        ClientRegistry.registerKeyBinding(SPAWN_BOAT);
        ClientRegistry.registerKeyBinding(HIDE_BLOCK);
        ClientRegistry.registerKeyBinding(SPAWN_ZOMBIE);
        ClientRegistry.registerKeyBinding(LAUNCH_FIREBALL);
    }

}
