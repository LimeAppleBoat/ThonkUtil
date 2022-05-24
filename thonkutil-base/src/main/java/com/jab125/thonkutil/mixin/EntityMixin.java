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
package com.jab125.thonkutil.mixin;

import com.jab125.thonkutil.api.events.EventTaxi;
import com.jab125.thonkutil.api.events.server.entity.TotemUseEvent;
import com.jab125.thonkutil.impl.RegistryUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class EntityMixin implements RegistryUtil {
    @Inject(method = "tryUseTotem", at = @At("HEAD"), cancellable = true)
    private void thonkutil$tryUseTotem(DamageSource source, CallbackInfoReturnable<Boolean> cir) {
        if (((LivingEntity) (Object) this).world.isClient) return;
        boolean shouldSavePlayer = (boolean) EventTaxi.executeEventTaxi(new TotemUseEvent((LivingEntity) (Object) this, source));
        //System.out.println("Saving player: " + shouldSavePlayer);
        if (shouldSavePlayer) {
            cir.setReturnValue(true);
        }
    }
}
