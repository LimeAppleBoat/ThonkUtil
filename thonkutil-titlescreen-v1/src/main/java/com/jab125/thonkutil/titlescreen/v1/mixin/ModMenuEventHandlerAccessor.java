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
