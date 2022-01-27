package com.jab125.thonkutil.api.events;

public abstract class EventTaxiReturnableEvent<T> extends EventTaxiEvent {
    public abstract T getResult();
}
