package com.jab125.thonkutil.api.events;

import java.util.Objects;

public final class PingMapEvent extends EventTaxiEvent {
    private final String key;
    private final Object value;

    public PingMapEvent(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }
}
