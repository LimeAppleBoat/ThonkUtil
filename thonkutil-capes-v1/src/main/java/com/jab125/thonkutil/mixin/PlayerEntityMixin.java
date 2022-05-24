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

import com.jab125.thonkutil.impl.ItemCapeRenderer;
import com.jab125.thonkutil.impl.ItemElytraRenderer;
import com.jab125.thonkutil.impl.TwoWingedItemElytraRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@net.fabricmc.api.Environment(net.fabricmc.api.EnvType.CLIENT)
@Mixin(PlayerEntityRenderer.class)
public abstract class PlayerEntityMixin<T extends LivingEntity, M extends EntityModel<T>> {
    @Inject(method = "<init>", at = @At("TAIL"))
    @SuppressWarnings("unchecked")
    public void initInject(EntityRendererFactory.Context ctx, boolean slim, CallbackInfo ci) {
        ((LivingEntityRendererAccessor) (Object) this).callAddFeature(new ItemCapeRenderer((PlayerEntityRenderer) (Object) this, ctx.getModelLoader()));
        ((LivingEntityRendererAccessor) (Object) this).callAddFeature(new ItemElytraRenderer((PlayerEntityRenderer) (Object) this, ctx.getModelLoader()));
        ((LivingEntityRendererAccessor) (Object) this).callAddFeature(new TwoWingedItemElytraRenderer((PlayerEntityRenderer) (Object) this, ctx.getModelLoader()));
    }
}