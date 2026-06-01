package net.feara.tyrant.network;

import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;

public final class ModNetwork {
    private ModNetwork() {}

    public static void register(RegisterPayloadHandlersEvent event) {
        System.out.println("Registering identity sync payload");

        var registrar = event.registrar("1");

        registrar.playToClient(
                IdentitySyncPayload.TYPE,
                IdentitySyncPayload.STREAM_CODEC,
                IdentitySyncPayloadHandler::handleClient
        );
    }
}