package de.arraying.phoenix.mixin;

import de.arraying.phoenix.PhoenixMod;
import net.minecraft.client.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Mixin related to keyboard functionality.
 */
@Mixin(Keyboard.class)
public class KeyboardMixin {

    /**
     * When a key is pressed.
     * Should be at the end, such that some filter-out conditions have been handled.
     * @param window Ignored.
     * @param key The key that was pressed.
     * @param scancode Ignored.
     * @param action The type of key press.
     * @param modifiers Ignored.
     * @param callbackInfo Ignored.
     */
    @Inject(at = @At("TAIL"), method = "onKey")
    public void onKey(long window, int key, int scancode, int action, int modifiers, CallbackInfo callbackInfo) {
        PhoenixMod.getInstance().getKeyboardService().keypress(key, action);
    }

}
