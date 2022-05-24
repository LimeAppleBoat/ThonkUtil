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
package com.jab125.thonkutil.util;

import com.jab125.thonkutil.ThonkUtilBaseClass;
import com.jab125.thonkutil.api.events.EventTaxi;
import com.jab125.thonkutil.api.events.misc.PingMapEvent;
import org.jetbrains.annotations.ApiStatus;

import java.util.HashMap;

@ApiStatus.Internal
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
        EventTaxi.executeEventTaxi(new PingMapEvent(this, key, value));
    }
}
