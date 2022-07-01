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

/**
 * Responsible for all HUD operations.
 */
public class HUDService extends Service {

    private static final float MARGIN_VERTICAL_SPACING = 0.02f;
    private final HUDBox[] topLeftHUDs;
    private boolean shouldRender = true;

    /**
     * Pre-loads the entire HUD for performance reasons.
     * That way, there do not need to be instantiations at every frame.
     */
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

    /**
     * Renders the HUD.
     * This just renders all the HUD boxes.
     * @param matrices The matrix stack.
     */
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
    }

    /**
     * Toggles the HUD.
     */
    public void toggleHUD() {
        this.shouldRender = !shouldRender;
    }

    /**
     * Helper function to get from a player object to whatever information as a string.
     * @param function A function to map.
     * @return A string representation of something the player has/does.
     */
    private String playerToString(Function<ClientPlayerEntity, Object> function) {
        return String.valueOf(function.apply(MinecraftClient.getInstance().player));
    }
}
