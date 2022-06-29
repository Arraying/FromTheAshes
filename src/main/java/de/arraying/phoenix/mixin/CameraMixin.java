package de.arraying.phoenix.mixin;

import de.arraying.phoenix.PhoenixMod;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameRenderer.class)
public class CameraMixin {

    @Inject(at = @At("RETURN"), method = "getFov", cancellable = true)
    public void onFOV(Camera camera, float tickDelta, boolean changingFov, CallbackInfoReturnable<Double> callbackInfo) {
        double before = callbackInfo.getReturnValueD();
        callbackInfo.setReturnValue(PhoenixMod.getInstance().getZoomService().getUpdatedFOV(before));
    }

}
