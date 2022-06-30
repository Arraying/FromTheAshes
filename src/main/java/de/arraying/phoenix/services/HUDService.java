package de.arraying.phoenix.services;

import de.arraying.phoenix.models.HUDBox;
import de.arraying.phoenix.models.HUDEntry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Direction;

import java.util.function.Function;

public class HUDService extends Service {

    private static final float MARGIN_VERTICAL_SPACING = 0.02f;
    private final HUDBox[] topLeftHUDs;
    private boolean shouldRender = true;

    // Load everything here for performance.
    public HUDService() {
        topLeftHUDs = new HUDBox[] {
            // Performance related statistics.
            new HUDBox(
                new HUDEntry(
                    "FPS",
                    () -> {
                        String fps = MinecraftClient.getInstance().fpsDebugString;
                        // FPS contains some debug information that is not relevant, strip.
                        if (fps.indexOf(' ') != -1) {
                            fps = fps.substring(0, fps.indexOf(' '));
                        }
                        return fps;
                    }
                )
            ),
            // Coordinate and world information.
            new HUDBox(
                new HUDEntry(
                    "X",
                    () -> playerToString(Entity::getBlockX)
                ),
                new HUDEntry(
                    "Y",
                    () -> playerToString(Entity::getBlockY)
                ),
                new HUDEntry(
                    "Z",
                    () -> playerToString(Entity::getBlockZ)
                ),
                new HUDEntry(
                    "D",
                    () -> playerToString(player -> switch (player.getHorizontalFacing()) {
                        case NORTH -> "North";
                        case EAST -> "East "; // Pad so it doesn't look terrible.
                        case SOUTH -> "South";
                        case WEST -> "West "; // Pad again.
                        default -> "???"; // Will never be hit.
                    })
                ),
                new HUDEntry(
                    "A",
                    () -> playerToString(player -> {
                        Direction direction = player.getHorizontalFacing();
                        String sign = switch (direction.getDirection()) {
                            case POSITIVE -> "+";
                            case NEGATIVE -> "-";
                        };
                        return sign + direction.getAxis();
                    })
                )
            )
        };
    }

    public void renderHUD(MatrixStack matrices) {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (player == null || !shouldRender || MinecraftClient.getInstance().options.debugEnabled) {
            return;
        }
        // Render all the HUDs.
        int scaledHeight = MinecraftClient.getInstance().getWindow().getScaledHeight();
        int offsetTopLeft = Math.round(MARGIN_VERTICAL_SPACING * scaledHeight);
        for (HUDBox hudBox : topLeftHUDs) {
            offsetTopLeft = hudBox.render(0, offsetTopLeft, matrices);
        }

//        TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
//        int height = textRenderer.fontHeight;
//        String fps = MinecraftClient.getInstance().fpsDebugString;
//        if (fps.indexOf(' ') != -1) {
//            fps = fps.substring(0, fps.indexOf(' '));
//        }
//        textRenderer.draw(
//            matrices,
//            "FPS: " + fps,
//            5,
//            VERTICAL_OFFSET + height,
//            -1
//        );
//        textRenderer.draw(
//            matrices,
//            "X: " + player.getBlockX(),
//            5,
//            VERTICAL_OFFSET + 2 * height,
//            -1
//        );
//        textRenderer.draw(
//            matrices,
//            "Y: " + player.getBlockY(),
//            5,
//            VERTICAL_OFFSET + 3 * height,
//            -1
//        );
//        textRenderer.draw(
//            matrices,
//            "Z: " + player.getBlockZ(),
//            5,
//            VERTICAL_OFFSET + 4 * height,
//            -1
//        );
    }

    public void toggleHUD() {
        this.shouldRender = !shouldRender;
    }

    private String playerToString(Function<ClientPlayerEntity, Object> function) {
        return String.valueOf(function.apply(MinecraftClient.getInstance().player));
    }
}
