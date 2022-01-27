package com.jab125.thonkutil.api.events.server;

import com.jab125.thonkutil.api.events.EventTaxiEvent;
import net.minecraft.server.MinecraftServer;

public class ServerStartEvent extends EventTaxiEvent {
    private final MinecraftServer server;
    public ServerStartEvent(MinecraftServer server) {
        this.server = server;
    }

    public MinecraftServer server() {
        return server;
    }
}
