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
package com.jab125.thonkutil.api.events.server;

import com.jab125.thonkutil.api.events.EventTaxiEvent;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.server.command.ServerCommandSource;

public class RegisterCommandEvent extends EventTaxiEvent {
    private final CommandDispatcher<ServerCommandSource> source;
    private final boolean dedicated;

    public RegisterCommandEvent(CommandDispatcher<ServerCommandSource> source, boolean dedicated) {
        this.source = source;
        this.dedicated = dedicated;
    }

    public CommandDispatcher<ServerCommandSource> source() {
        return source;
    }

    public boolean dedicated() {
        return dedicated;
    }
}
