package com.jab125.thonkutil.api.events.server;

import com.jab125.thonkutil.api.Tick;
import com.jab125.thonkutil.api.events.EventTaxiEvent;
import net.minecraft.server.MinecraftServer;

import java.util.Objects;

public final class ServerTickEvent extends EventTaxiEvent {
    private final MinecraftServer server;
    private final Tick.Phase phase;

    public ServerTickEvent(MinecraftServer server, Tick.Phase phase) {
        this.server = server;
        this.phase = phase;
    }

    public MinecraftServer server() {
        return server;
    }

    public Tick.Phase phase() {
        return phase;
    }
}
