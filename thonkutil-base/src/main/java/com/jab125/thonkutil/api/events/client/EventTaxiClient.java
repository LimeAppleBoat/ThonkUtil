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
package com.jab125.thonkutil.api.events.client;

import com.jab125.thonkutil.api.events.EventTaxi;
import com.jab125.thonkutil.api.events.client.screen.ScreenEvent;
import com.jab125.thonkutil.api.events.client.screen.ScreenRenderEvent;
import com.jab125.thonkutil.api.events.client.screen.TitleScreenEvent;
import com.jab125.thonkutil.api.events.client.screen.TitleScreenRenderEvent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public class EventTaxiClient {
    @Environment(EnvType.CLIENT)
    public static void registerClientTaxis() {
        ScreenEvents.AFTER_INIT.register(((client, screen, scaledWidth, scaledHeight) -> {
            EventTaxi.executeEventTaxi(new ScreenEvent(screen, client, scaledWidth, scaledHeight));
            ScreenEvents.afterRender(screen).register(((screen1, matrices, mouseX, mouseY, tickDelta) -> {
                EventTaxi.executeEventTaxi(new ScreenRenderEvent(screen, matrices, mouseX, mouseY, tickDelta));
            }));
            if (screen instanceof TitleScreen titleScreen) {
                EventTaxi.executeEventTaxi(new TitleScreenEvent(screen, client, scaledWidth, scaledHeight));
                ScreenEvents.afterRender(screen).register(((screen1, matrices, mouseX, mouseY, tickDelta) -> {
                    float f = titleScreen.doBackgroundFade ? (float) (Util.getMeasuringTimeMs() - titleScreen.backgroundFadeStart) / 1000.0F : 1.0F;
                    float g = titleScreen.doBackgroundFade ? MathHelper.clamp(f - 1.0F, 0.0F, 1.0F) : 1.0F;
                    int l = MathHelper.ceil(g * 255.0F) << 24;
                    if ((l & -67108864) != 0) {
                        // for (int i = 0; i < 4; i++) {
                        EventTaxi.executeEventTaxi(new TitleScreenRenderEvent(titleScreen, matrices, mouseX, mouseY, tickDelta, l));
                        // }
                    }
                }));
            }
        }));
    }
}
