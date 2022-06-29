package de.arraying.phoenix.services;

import net.minecraft.client.MinecraftClient;

public class ZoomService {

    private static final double FACTOR = 0.33;
    private boolean zooming;

    public double getUpdatedFOV(double previous) {
        return zooming ? previous * FACTOR : previous;
    }

    public void zoom() {
        this.zooming = true;
        MinecraftClient.getInstance().options.smoothCameraEnabled = true;
    }

    public void reset() {
        this.zooming = false;
        MinecraftClient.getInstance().options.smoothCameraEnabled = false;
    }

}
