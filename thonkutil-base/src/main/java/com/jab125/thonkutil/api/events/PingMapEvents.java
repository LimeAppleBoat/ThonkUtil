package com.jab125.thonkutil.api.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

/**
 * Reports changes of {@link com.jab125.thonkutil.util.PingMap}
 */
public interface PingMapEvents {
    Event<OnUpdate> ON_UPDATE = EventFactory.createArrayBacked(OnUpdate.class,
            (listeners) ->
                    (key, value) -> {
        for (OnUpdate listener : listeners) {
            listener.onChange(key, value);
        }
                    });

    interface OnUpdate {
        void onChange(String key, Object value);
    }
}
