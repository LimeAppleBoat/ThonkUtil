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
package com.jab125.limeappleboat.thonkutil.difficulty.v1.impl.mixin;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.level.LevelInfo;
import net.minecraft.world.level.LevelProperties;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LevelProperties.class)
public class LevelPropertiesMixin {
    @Shadow private LevelInfo levelInfo;

    @Redirect(method = "updateProperties", at = @At(value = "INVOKE", target = "Lnet/minecraft/nbt/NbtCompound;putByte(Ljava/lang/String;B)V"))
    private void thonkutil$modifyOptions(NbtCompound instance, String key, byte value) {
        //levelNbt.putByte("Difficulty", (byte)this.levelInfo.getDifficulty().getId());
        instance.putString("Difficulty", this.levelInfo.getDifficulty().getName());
    }
}
