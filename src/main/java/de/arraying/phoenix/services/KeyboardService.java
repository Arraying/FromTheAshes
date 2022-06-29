package de.arraying.phoenix.services;

import org.lwjgl.glfw.GLFW;

public class KeyboardService extends Service {

    public void keypress(int key, int action) {
        if (action == GLFW.GLFW_RELEASE && key == GLFW.GLFW_KEY_RIGHT_SHIFT) {
            phoenix.getHudService().toggleHUD();
        }
        if (key == GLFW.GLFW_KEY_C) {
            if (action == GLFW.GLFW_PRESS) {
                phoenix.getZoomService().zoom();
            } else if (action == GLFW.GLFW_RELEASE) {
                phoenix.getZoomService().reset();
            }
        }
    }

}
