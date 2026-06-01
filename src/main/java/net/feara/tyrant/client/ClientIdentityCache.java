package net.feara.tyrant.client;

import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class ClientIdentityCache {
    private static final Map<UUID, ResourceLocation> IDS = new HashMap<>();

    public static void set(UUID uuid, ResourceLocation id) {
        IDS.put(uuid, id);
    }

    public static void clear(UUID uuid) {
        IDS.remove(uuid);
    }

    public static @Nullable ResourceLocation get(UUID uuid) {
        return IDS.get(uuid);
    }
}