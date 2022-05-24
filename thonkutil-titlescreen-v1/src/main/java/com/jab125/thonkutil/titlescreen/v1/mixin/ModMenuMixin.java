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

import com.jab125.thonkutil.titlescreen.v1.config.ThonkUtilTitleScreenConfig;
import com.terraformersmc.modmenu.api.ModMenuApi;
import com.terraformersmc.modmenu.config.ModMenuConfig;
import com.terraformersmc.modmenu.event.ModMenuEventHandler;
import com.terraformersmc.modmenu.gui.widget.ModMenuButtonWidget;
import net.fabricmc.fabric.api.client.screen.v1.Screens;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ClickableWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ModMenuEventHandler.class)
public class ModMenuMixin {

    @Inject(method = "afterTitleScreenInit", at = @At("HEAD"), cancellable = true)
    private static void thonkutil$injectTitleScreenInit(Screen screen, CallbackInfo ci) {
        if (!ThonkUtilTitleScreenConfig.MODIFY_TITLE_SCREEN.getValue()) return;
        final List<ClickableWidget> buttons = Screens.getButtons(screen);
        if (ModMenuConfig.MODIFY_TITLE_SCREEN.getValue()) {
            int modsButtonIndex = -1;
            final int spacing = 24;
            int buttonsY = screen.height / 4 + 48;
            for (int i = 0; i < buttons.size(); i++) {
                ClickableWidget button = buttons.get(i);
                if (ModMenuConfig.MODS_BUTTON_STYLE.getValue() == ModMenuConfig.ModsButtonStyle.CLASSIC && false) {
                    if (button.visible) {
                        if (modsButtonIndex == -1) {
                            buttonsY = button.y;
                        }
                    }
                }
                if (ModMenuEventHandlerAccessor.callButtonHasText(button, "menu.online")) {
                    button.x = screen.width / 2 + 2;
                    button.setWidth(98);
                    modsButtonIndex = i + 1;
                    if (button.visible) {
                        buttonsY = button.y;
                    }
                }
            }
            if (modsButtonIndex != -1) {
                buttons.add(modsButtonIndex, new ModMenuButtonWidget(screen.width / 2 - 100, buttonsY, 98, 20, ModMenuApi.createModsButtonText(), screen));
            }
        }
        ci.cancel();
    }
}
