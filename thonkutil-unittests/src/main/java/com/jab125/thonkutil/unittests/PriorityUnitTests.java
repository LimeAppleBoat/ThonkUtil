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
