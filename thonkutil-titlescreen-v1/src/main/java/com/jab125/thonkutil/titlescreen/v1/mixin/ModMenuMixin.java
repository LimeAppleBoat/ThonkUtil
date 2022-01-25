package com.jab125.thonkutil.titlescreen.v1.mixin;

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
