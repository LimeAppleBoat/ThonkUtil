package com.jab125.thonkutil.api.events.server.world;

import com.jab125.thonkutil.api.events.EventTaxiEvent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;

public class ServerWorldLoadEvent extends EventTaxiEvent {
    private final MinecraftServer server;
    private final ServerWorld world;
    public ServerWorldLoadEvent(MinecraftServer server, ServerWorld world) {
        this.server = server;
        this.world = world;
    }

    public MinecraftServer server() {
        return server;
    }

    public ServerWorld world() {
        return world;
    }
}
