package de.arraying.phoenix.services;

import de.arraying.phoenix.PhoenixMod;
import org.lwjgl.glfw.GLFW;

public class KeyboardService extends Service {

    public void keypress(int key, int action) {
        if (action == GLFW.GLFW_RELEASE && key == GLFW.GLFW_KEY_LEFT_ALT) {
            PhoenixMod.getInstance().getHudService().toggleHUD();
        }
    }

}
