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
package com.jab125.thonkutil.unittests;

import com.jab125.thonkutil.api.annotations.SubscribeEvent;
import com.jab125.thonkutil.api.events.EventTaxi;
import com.jab125.thonkutil.api.events.EventTaxiEvent;

public class PriorityUnitTests {
    public static void main(String[] args) {
        EventTaxi.registerEventTaxiSubscriber(PriorityUnitTests.class);
        EventTaxi.executeEventTaxi(new PriorityEvent());
    }

    @SubscribeEvent(priority = SubscribeEvent.Priority.LOW)
    public static void execute1(PriorityEvent event) {
        System.out.println(1);
    }

    @SubscribeEvent
    public static void execute2(PriorityEvent event) {
        System.out.println(2);
    }

    @SubscribeEvent
    public static void execute3(PriorityEvent event) {
        System.out.println(3);
    }

    public static class PriorityEvent extends EventTaxiEvent {
    }
}
