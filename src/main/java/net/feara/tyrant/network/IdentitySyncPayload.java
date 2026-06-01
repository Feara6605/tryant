package net.feara.tyrant.network;

import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import io.netty.buffer.ByteBuf;

public record IdentitySyncPayload(String playerUuid, String identityId)
        implements CustomPacketPayload {

    public static final Type<IdentitySyncPayload> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(
                    "tyrant",
                    "identity_sync"
            ));

    public static final StreamCodec<ByteBuf, IdentitySyncPayload> STREAM_CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.STRING_UTF8,
                    IdentitySyncPayload::playerUuid,
                    ByteBufCodecs.STRING_UTF8,
                    IdentitySyncPayload::identityId,
                    IdentitySyncPayload::new
            );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}