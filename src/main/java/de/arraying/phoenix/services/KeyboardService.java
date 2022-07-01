package de.arraying.phoenix.services;

import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;

/**
 * Responsible for key presses.
 */
public class KeyboardService extends Service {

    /**
     * When the keyboard is pressed/released.
     * @param key The key that was pressed/released.
     * @param action The action (press/release).
     */
    public void keypress(int key, int action) {
        MinecraftClient client = MinecraftClient.getInstance();
        // Allowed everywhere.
        if (action == GLFW.GLFW_RELEASE && key == GLFW.GLFW_KEY_RIGHT_SHIFT) {
            phoenix.getHudService().toggleHUD();
        }
        if (action == GLFW.GLFW_RELEASE && key == GLFW.GLFW_KEY_G) {
            phoenix.getHudService().toggleGamma();
        }
        // Only zoom if they have no screen (i.e. they are in game, no furnace, inventory, etc.).
        if (key == GLFW.GLFW_KEY_C && client.currentScreen == null) {
            if (action == GLFW.GLFW_PRESS) {
                phoenix.getZoomService().zoom();
            } else if (action == GLFW.GLFW_RELEASE) {
                phoenix.getZoomService().reset();
            }
        }
    }

}
