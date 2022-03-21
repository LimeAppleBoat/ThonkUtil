package com.jab125.thonkutil.api.events;

public class EventTaxiEvent {
    private boolean cancelled = false;

    protected void cancel() {
        this.cancelled = true;
    }

    public boolean isCancelled() {
        return cancelled;
    }
}
