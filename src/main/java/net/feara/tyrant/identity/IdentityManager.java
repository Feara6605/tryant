package net.feara.tyrant.identity;

import net.feara.tyrant.ModAttachments;
import net.feara.tyrant.network.IdentitySyncPayload;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.function.Supplier;

public final class IdentityManager {
    private static final Supplier<AttachmentType<PlayerIdentityData>> PLAYER_IDENTITY =
            ModAttachments.PLAYER_IDENTITY;

    public static PlayerIdentityData get(ServerPlayer player) {
        return player.getData(PLAYER_IDENTITY);
    }
    private static void applyIdentityEffects(ServerPlayer player, ResourceLocation id) {
        IdentityType type = IdentityRegistry.get(id);
        if (type == null) return;
        player.sendSystemMessage(
                Component.literal("Applied Identity: " + id)
        );
    }
    public static void setIdentity(ServerPlayer player, ResourceLocation id) {
        PlayerIdentityData data = player.getData(PLAYER_IDENTITY);
        data.setIdentityId(id);
        applyIdentityEffects(player, id);
        PacketDistributor.sendToAllPlayers(
                new IdentitySyncPayload(player.getUUID().toString(), id.toString())
        );
    }

    public static boolean is(Player player, ResourceLocation id) {
        PlayerIdentityData data = player.getData(PLAYER_IDENTITY);
        return data.getIdentityId().equals(id);
    }
    public static ResourceLocation getIdentity(Player player) {
        return player.getData(PLAYER_IDENTITY).getIdentityId();
    }

    public static void clearIdentity(ServerPlayer player) {
        player.getData(PLAYER_IDENTITY).clear();

        PacketDistributor.sendToAllPlayers(
                new IdentitySyncPayload(
                        player.getUUID().toString(),
                        "tyrant:none"
                )
        );
    }

    private IdentityManager() {}
}
