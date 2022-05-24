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
package com.jab125.thonkutil.api.events.server.world;

import com.jab125.thonkutil.api.events.EventTaxiEvent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;

public class ServerWorldUnloadEvent extends EventTaxiEvent {
    private final MinecraftServer server;
    private final ServerWorld world;

    public ServerWorldUnloadEvent(MinecraftServer server, ServerWorld world) {
        this.server = server;
        this.world = world;
    }

    public MinecraftServer server() {
        return server;
    }

    public ServerWorld world() {
        return world;
    }
}
