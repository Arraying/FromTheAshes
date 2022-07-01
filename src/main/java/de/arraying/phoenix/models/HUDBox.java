package de.arraying.phoenix.models;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;

/**
 * Represents a HUD box that groups rows of information (items).
 */
public class HUDBox {

    private static final float MARGIN_HORIZONTAL_SPACING = 0.02f;
    private static final int MARGIN_VERTICAL_SPACING = 5;
    private final HUDEntry[] items;

    /**
     * Creates a new HUD box.
     * @param items A list of non-null items to display.
     * @throws IllegalArgumentException If 0 items have been provided.
     */
    public HUDBox(HUDEntry... items) throws IllegalArgumentException {
        if (items.length == 0) {
            throw new IllegalArgumentException("at least one item required");
        }
        this.items = items;
    }

    /**
     * Renders the box.
     * @param horizontalOrigin The X coordinate to start rendering from.
     * @param verticalOrigin The Y coordinate to start rendering from.
     * @param matrices The matrix stack.
     * @return The Y coordinate of the end of the box, such that renders can be chained.
     */
    public int render(int horizontalOrigin, int verticalOrigin, MatrixStack matrices) {
        MinecraftClient client = MinecraftClient.getInstance();
        TextRenderer textRenderer = client.textRenderer;
        String[] text = getText();
        // First, figure out the scale at which everything is used.
        int scaledWidth = client.getWindow().getScaledWidth();
        // Horizontally, give it a little margin.
        int startBoxX = horizontalOrigin + Math.round(MARGIN_HORIZONTAL_SPACING * scaledWidth);
        // Vertically, start at the origin and add some constant padding.
        // This should not change otherwise it "rips apart" on bigger displays.
        int startBoxY = verticalOrigin + MARGIN_VERTICAL_SPACING;
        // Padding on every side should be half the font height, since it is odd, it will floor.
        int padding = textRenderer.fontHeight / 2;
        // Make it as wide as it needs to be.
        int boxWidth = textRenderer.getWidth(getLongestText(text));
        // The height is determined by the number of items to render.
        int boxHeight = items.length * textRenderer.fontHeight;
        // Define the ending coordinates which take into account the padding.
        int endBoxX = startBoxX + padding + boxWidth + padding;
        // Here we need to offset by one in the Y. I'm not sure why, I think it's because of how the font is rendered.
        int endBoxY = startBoxY + padding + boxHeight + (padding - 1);
        // Render the box.
        DrawableHelper.fill(
            matrices,
            startBoxX,
            startBoxY,
            endBoxX,
            endBoxY,
            0x80_00_00_00 // 50% transparent black.
        );
        // Determine where to draw every text.
        int startTextX = startBoxX + padding;
        int startTextY = startBoxY + padding;
        // Go through every item, draw, update Y coordinate.
        for (String info : text) {
            textRenderer.draw(matrices, info, startTextX, startTextY, -1);
            startTextY += textRenderer.fontHeight;
        }
        return endBoxY;
    }

    /**
     * Gets the text to render from all items as string.
     * @return The text.
     */
    private String[] getText() {
        String[] text = new String[items.length];
        for (int i = 0; i < text.length; i++) {
            text[i] = items[i].format();
        }
        return text;
    }

    /**
     * Gets the longest string from all strings.
     * @param text An array of string.
     * @return The longest text.
     */
    private String getLongestText(String[] text) {
        String maximum = "";
        for (String info : text) {
            if (info.length() > maximum.length()) {
                maximum = info;
            }
        }
        return maximum;
    }

}
