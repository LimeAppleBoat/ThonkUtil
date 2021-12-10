package com.jab125.thonkutil.api;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public interface ThonkUtilEvents {
    Event<OnConfigChanged> ON_CONFIG_CHANGED = EventFactory.createArrayBacked(OnConfigChanged.class,
            (listeners) ->
                    (key, value) -> {
        for (OnConfigChanged listener : listeners) {
            listener.onChange(key, value);
        }
                    });

    interface OnConfigChanged {
        void onChange(String key, Object value);
    }
}
