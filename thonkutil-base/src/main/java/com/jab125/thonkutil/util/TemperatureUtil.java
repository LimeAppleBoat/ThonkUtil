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
package com.jab125.thonkutil.util;

import net.minecraft.entity.Entity;
import net.minecraft.world.biome.Biome;

public class TemperatureUtil {
    public static float getTemperature(Entity entity) {
        if (entity.world.isClient()) return 0;
        System.out.println(entity.world.getBiome(entity.getBlockPos()).value().getTemperature());
        return entity.world.getBiome(entity.getBlockPos()).value().getTemperature();
    }

    public static float getTemperatureAlt(Entity entity) {
        if (entity.getWorld().isClient()) return 0;
        if (entity.getWorld().getDimension().ultrawarm()) return 100;
        return getEntityBiome(entity).getTemperature() * 30;
    }

    private static Biome getEntityBiome(Entity entity) {
        //i know it works on client, but eh
        if (entity.world.isClient) throw new RuntimeException("RUN ON SERVER");
        return entity.world.getBiome(entity.getBlockPos()).value();
    }
}
