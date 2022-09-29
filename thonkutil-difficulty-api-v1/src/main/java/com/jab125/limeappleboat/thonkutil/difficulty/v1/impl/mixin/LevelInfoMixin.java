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

import com.mojang.serialization.Dynamic;
import net.minecraft.resource.DataPackSettings;
import net.minecraft.world.Difficulty;
import net.minecraft.world.GameMode;
import net.minecraft.world.GameRules;
import net.minecraft.world.level.LevelInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(LevelInfo.class)
public class LevelInfoMixin {
    /**
     * @author
     */
    @Overwrite
    public static LevelInfo fromDynamic(Dynamic<?> dynamic, DataPackSettings dataPackSettings) {
        GameMode gameMode = GameMode.byId(dynamic.get("GameType").asInt(0));
        return new LevelInfo(dynamic.get("LevelName").asString(""), gameMode, dynamic.get("hardcore").asBoolean(false), dynamic.get("Difficulty").asString().map(Difficulty::byName).result().orElse(Difficulty.NORMAL), dynamic.get("allowCommands").asBoolean(gameMode == GameMode.CREATIVE), new GameRules(dynamic.get("GameRules")), dataPackSettings);
    }
}
