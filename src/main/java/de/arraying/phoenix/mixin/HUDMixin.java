package de.arraying.phoenix.mixin;

import de.arraying.phoenix.PhoenixMod;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Mixin related to all HUD functionality.
 */
@Mixin(InGameHud.class)
public class HUDMixin {

    /**
     * When things get rendered.
     * Can be put anywhere, since the method never returns.
     * @param matrices The matrix stack.
     * @param tickDelta The tick delta.
     * @param callbackInfo Ignored.
     */
    @Inject(at = @At("RETURN"), method = "render")
    public void onRender(MatrixStack matrices, float tickDelta, CallbackInfo callbackInfo) {
        PhoenixMod.getInstance().getHudService().renderHUD(matrices);
    }

}
