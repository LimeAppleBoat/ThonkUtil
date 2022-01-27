package com.jab125.thonkutil.api.events.server;

import com.jab125.thonkutil.api.events.EventTaxiEvent;
import net.minecraft.server.MinecraftServer;

public class ServerStopEvent extends EventTaxiEvent {
    private final MinecraftServer server;
    public ServerStopEvent(MinecraftServer server) {
        this.server = server;
    }

    public MinecraftServer server() {
        return server;
    }
}
