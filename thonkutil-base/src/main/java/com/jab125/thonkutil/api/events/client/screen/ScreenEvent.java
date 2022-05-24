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
package com.jab125.thonkutil.api.events.client.screen;

import com.jab125.thonkutil.api.events.EventTaxiEvent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;

@Environment(EnvType.CLIENT)
public class ScreenEvent extends EventTaxiEvent {
    private final Screen screen;
    private final MinecraftClient client;
    private final int scaledWidth;
    private final int scaledHeight;

    public ScreenEvent(Screen screen, MinecraftClient client, int scaledWidth, int scaledHeight) {
        this.client = client;
        this.screen = screen;
        this.scaledHeight = scaledHeight;
        this.scaledWidth = scaledWidth;
    }

    public MinecraftClient getClient() {
        return client;
    }

    public Screen getScreen() {
        return screen;
    }

    public int getScaledHeight() {
        return scaledHeight;
    }

    public int getScaledWidth() {
        return scaledWidth;
    }
}
