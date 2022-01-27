package com.jab125.thonkutil.api.events.world;

import com.jab125.thonkutil.api.Tick;
import com.jab125.thonkutil.api.events.EventTaxiEvent;
import net.minecraft.world.World;

public final class WorldTickEvent extends EventTaxiEvent {
    private final World world;
    private final Tick.Phase phase;

    public WorldTickEvent(World world, Tick.Phase phase) {
        this.world = world;
        this.phase = phase;
    }

    public World world() {
        return world;
    }

    public Tick.Phase phase() {
        return phase;
    }

    public boolean isClient() {
        return world.isClient();
    }
}
