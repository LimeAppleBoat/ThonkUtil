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

public class ObjectUnitTests {
    ObjectUnitTests() {
        EventTaxi.registerEventTaxiSubscriber(ObjectUnitTests.class);
        EventTaxi.registerEventTaxiSubscriber(this);
        EventTaxi.executeEventTaxi(new Static());
        EventTaxi.executeEventTaxi(new NonStatic());
        EventTaxi.executeEventTaxi(new Cancellable());
    }
    public static void main(String[] args) {
        new ObjectUnitTests();
    }

    @SubscribeEvent
    public void staticTest(Static event) {
        System.out.println(event);
    }

    @SubscribeEvent
    public void nonStaticTest(NonStatic event) {
        System.out.println(event);
    }

    @SubscribeEvent
    public void staticCancel(Cancellable event) {
        System.out.println(event);
        System.out.println("Cancelled Event!");
        event.cancel();
    }

    @SubscribeEvent
    public void nonStaticCancel(Cancellable event) {
        System.out.println(event);
        throw new RuntimeException("Should be cancelled!");
    }

    public static class Static extends EventTaxiEvent {

    }

    public static class NonStatic extends EventTaxiEvent {

    }

    public static class Cancellable extends EventTaxiEvent {
        @Override
        public void cancel() {
            super.cancel();
        }
    }
}
