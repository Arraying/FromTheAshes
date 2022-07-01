package de.arraying.phoenix.services;

import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;

public class KeyboardService extends Service {

    public void keypress(int key, int action) {
        MinecraftClient client = MinecraftClient.getInstance();
        // Allowed everywhere.
        if (action == GLFW.GLFW_RELEASE && key == GLFW.GLFW_KEY_RIGHT_SHIFT) {
            phoenix.getHudService().toggleHUD();
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
