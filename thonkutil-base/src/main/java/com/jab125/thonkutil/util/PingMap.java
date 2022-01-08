package com.jab125.thonkutil.util;

import com.jab125.thonkutil.ThonkUtilBaseClass;
import com.jab125.thonkutil.api.events.PingMapEvents;

import java.util.HashMap;

public class PingMap<K extends String, V> extends HashMap<K, V> implements ThonkUtilBaseClass {
    @Override
    public V put(K key, V value) {
        fireEvents(key, value);
        return super.put(key, value);
    }

    @Override
    public V putIfAbsent(K key, V value) {
        fireEvents(key, value);
        return super.putIfAbsent(key, value);
    }

    private void fireEvents(String key, V value) {
        PingMapEvents.ON_UPDATE.invoker().onChange(key, value);
    }
}
