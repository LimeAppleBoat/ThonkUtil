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
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.util.math.MatrixStack;

@Environment(EnvType.CLIENT)
public final class TitleScreenRenderEvent extends EventTaxiEvent {
    private final Screen screen;
    private final MatrixStack matrices;
    private final int mouseX;
    private final int mouseY;
    private final float tickDelta;
    private final int alpha;

    public TitleScreenRenderEvent(TitleScreen screen, MatrixStack matrices, int mouseX, int mouseY, float tickDelta, int alpha) {
        this.screen = screen;
        this.matrices = matrices;
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        this.tickDelta = tickDelta;
        this.alpha = alpha;
    }

    public Screen screen() {
        return screen;
    }

    public MatrixStack matrices() {
        return matrices;
    }

    public int mouseX() {
        return mouseX;
    }

    public int mouseY() {
        return mouseY;
    }

    public float tickDelta() {
        return tickDelta;
    }

    public int alpha() {
        return alpha;
    }
}
