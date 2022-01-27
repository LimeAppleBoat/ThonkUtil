package com.jab125.thonkutil.api.events;

public abstract class EventTaxiBooleanReturnableEvent extends EventTaxiReturnableEvent<Boolean> {
    public abstract boolean getBoolean();

    @Override
    public final Boolean getResult() {
        return getBoolean();
    }
}
