package com.jab125.thonkutil.api.events.server;

import com.jab125.thonkutil.api.events.EventTaxiEvent;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.server.command.ServerCommandSource;

public class RegisterCommandEvent extends EventTaxiEvent {
    private final CommandDispatcher<ServerCommandSource> source;
    private final boolean dedicated;

    public RegisterCommandEvent(CommandDispatcher<ServerCommandSource> source, boolean dedicated) {
        this.source = source;
        this.dedicated = dedicated;
    }

    public CommandDispatcher<ServerCommandSource> source() {
        return source;
    }

    public boolean dedicated() {
        return dedicated;
    }
}
