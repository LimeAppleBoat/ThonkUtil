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
package com.jab125.thonkutil.titlescreen.v1.mixin;

import com.terraformersmc.modmenu.event.ModMenuEventHandler;
import net.minecraft.client.gui.widget.ClickableWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ModMenuEventHandler.class)
public interface ModMenuEventHandlerAccessor {
    @Invoker
    static void callShiftButtons(ClickableWidget button, boolean shiftUp, int spacing) {
        throw new UnsupportedOperationException();
    }

    @Invoker
    static boolean callButtonHasText(ClickableWidget button, String translationKey) {
        throw new UnsupportedOperationException();
    }
}
