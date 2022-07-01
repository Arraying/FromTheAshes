package de.arraying.phoenix.services;

import net.minecraft.client.MinecraftClient;

/**
 * Responsible for zoom.
 */
public class ZoomService {

    private static final double FACTOR = 0.33;
    private boolean zooming;

    /**
     * Gets the new FOV.
     * If zooming is enabled, it will scale.
     * @param previous The Minecraft-set FOV that may or may not be adjusted.
     * @return Possibly an adjusted FOV.
     */
    public double getUpdatedFOV(double previous) {
        return zooming ? previous * FACTOR : previous;
    }

    /**
     * Enables zoom mode.
     */
    public void zoom() {
        this.zooming = true;
        MinecraftClient.getInstance().options.smoothCameraEnabled = true;
    }

    /**
     * Disables zoom mode.
     */
    public void reset() {
        this.zooming = false;
        MinecraftClient.getInstance().options.smoothCameraEnabled = false;
    }

}
