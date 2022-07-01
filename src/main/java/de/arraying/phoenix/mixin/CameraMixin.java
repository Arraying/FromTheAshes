package de.arraying.phoenix.mixin;

import de.arraying.phoenix.PhoenixMod;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Mixin related to all the camera functionality.
 */
@Mixin(GameRenderer.class)
public class CameraMixin {

    /**
     * When the FOV is calculated.
     * Needs to be at the return level, such that it can be appropriately modified.
     * @param camera The camera instance.
     * @param tickDelta The tick delta.
     * @param changingFov Whether it is changing.
     * @param callbackInfo The return value.
     */
    @Inject(at = @At("RETURN"), method = "getFov", cancellable = true)
    public void onFOV(Camera camera, float tickDelta, boolean changingFov, CallbackInfoReturnable<Double> callbackInfo) {
        double before = callbackInfo.getReturnValueD();
        callbackInfo.setReturnValue(PhoenixMod.getInstance().getZoomService().getUpdatedFOV(before));
    }

}
