package net.feara.tyrant.identity;

import net.feara.tyrant.network.IdentitySyncPayload;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.network.PacketDistributor;

public final class IdentityLoginSync {
    private IdentityLoginSync() {}

    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer joiningPlayer)) return;

        for (ServerPlayer onlinePlayer : joiningPlayer.server.getPlayerList().getPlayers()) {
            var identity = IdentityManager.get(onlinePlayer).getIdentityId();
            PacketDistributor.sendToPlayer(
                    joiningPlayer,
                    new IdentitySyncPayload(
                            onlinePlayer.getUUID().toString(),
                            identity.toString()
                    )
            );
        }
    }
}