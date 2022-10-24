/*
 * Copyright (c) 2021, 2022 Jab125 & LimeAppleBoat
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
