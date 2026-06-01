package net.feara.tyrant.network;

import net.feara.tyrant.client.ClientIdentityCache;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.UUID;

public final class IdentitySyncPayloadHandler {
    private IdentitySyncPayloadHandler() {}

    public static void handleClient(IdentitySyncPayload payload, IPayloadContext context) {
        context.enqueueWork(() -> {
            System.out.println("Received identity sync: " + payload.playerUuid() + " -> " + payload.identityId());

            UUID uuid = UUID.fromString(payload.playerUuid());

            if ("tyrant:none".equals(payload.identityId())) {
                ClientIdentityCache.clear(uuid);
            } else {
                ClientIdentityCache.set(uuid, ResourceLocation.parse(payload.identityId()));
            }
        });
    }
}