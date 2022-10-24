package com.jab125.limeappleboat.thonkutil.axolotl.v1.impl.mixin;

import com.jab125.limeappleboat.thonkutil.axolotl.v1.impl.AxolotlTypeExtension;
import com.jab125.limeappleboat.thonkutil.axolotl.v1.impl.ThonkUtilAxolotlVariant;
import net.minecraft.entity.passive.AxolotlEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AxolotlEntity.Variant.class)
public class AxolotlTypeMixin implements AxolotlTypeExtension {
    @Unique
    private ThonkUtilAxolotlVariant metadata;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void thonkutil$init(String string, int i, int id, String name, boolean natural, CallbackInfo ci) {
        metadata = ThonkUtilAxolotlVariant.make((AxolotlEntity.Variant) (Object) this);
    }
    @Override
    public ThonkUtilAxolotlVariant thonkutil$thonkutil_metadata() {
        return metadata;
    }


    @Inject(method = "getName", at = @At(("HEAD")), cancellable = true)
    public void thonkutil$getName(CallbackInfoReturnable<String> cir) {
        if (ThonkUtilAxolotlVariant.p && metadata.isCustom()) cir.setReturnValue("car");
    }
}
