package de.arraying.phoenix.services;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.util.math.MatrixStack;

public class HUDService extends Service {

    private static final int VERTICAL_OFFSET = 10;
    private boolean shouldRender = true;

    public void renderHUD(MatrixStack matrices) {
        TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (player == null || !shouldRender || MinecraftClient.getInstance().options.debugEnabled) {
            return;
        }
        int height = textRenderer.fontHeight;
        String fps = MinecraftClient.getInstance().fpsDebugString;
        if (fps.indexOf(' ') != -1) {
            fps = fps.substring(0, fps.indexOf(' '));
        }
        textRenderer.draw(
            matrices,
            "FPS: " + fps,
            5,
            VERTICAL_OFFSET + height,
            -1
        );
        textRenderer.draw(
            matrices,
            "X: " + player.getBlockX(),
            5,
            VERTICAL_OFFSET + 2 * height,
            -1
        );
        textRenderer.draw(
            matrices,
            "Y: " + player.getBlockY(),
            5,
            VERTICAL_OFFSET + 3 * height,
            -1
        );
        textRenderer.draw(
            matrices,
            "Z: " + player.getBlockZ(),
            5,
            VERTICAL_OFFSET + 4 * height,
            -1
        );
    }

    public void toggleHUD() {
        this.shouldRender = !shouldRender;
    }

}
