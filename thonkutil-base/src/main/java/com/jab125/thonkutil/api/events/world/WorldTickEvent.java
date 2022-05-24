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
package com.jab125.thonkutil.api.events.world;

import com.jab125.thonkutil.api.Tick;
import com.jab125.thonkutil.api.events.EventTaxiEvent;
import net.minecraft.world.World;

public final class WorldTickEvent extends EventTaxiEvent {
    private final World world;
    private final Tick.Phase phase;

    public WorldTickEvent(World world, Tick.Phase phase) {
        this.world = world;
        this.phase = phase;
    }

    public World world() {
        return world;
    }

    public Tick.Phase phase() {
        return phase;
    }

    public boolean isClient() {
        return world.isClient();
    }
}
