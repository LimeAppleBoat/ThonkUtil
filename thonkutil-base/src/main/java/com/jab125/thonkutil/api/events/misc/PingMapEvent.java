package com.jab125.thonkutil.api.events.misc;

import com.jab125.thonkutil.api.events.EventTaxiEvent;
import com.jab125.thonkutil.util.PingMap;

import java.util.Objects;

public final class PingMapEvent extends EventTaxiEvent {
    private final String key;
    private final Object value;
    private final PingMap map;

    public PingMapEvent(PingMap map, String key, Object value) {
        this.map = map;
        this.key = key;
        this.value = value;
    }

    public PingMap map() {
        return map;
    }

    public String key() {
        return key;
    }

    public Object value() {
        return value;
    }
}
