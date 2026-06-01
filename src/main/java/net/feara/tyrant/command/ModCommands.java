package net.feara.tyrant.command;

import net.neoforged.neoforge.event.RegisterCommandsEvent;

public class ModCommands {
    public static void register(RegisterCommandsEvent event) {
        IdentityCommand.register(event.getDispatcher());
        ClearIdentityCommand.register(event.getDispatcher());
        PingCommand.register(event.getDispatcher());
        IdentityInfoCommand.register(event.getDispatcher());
    }
}
