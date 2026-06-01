package net.feara.tyrant.identity;

import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public final class IdentityRegistry {
    private static final Map<ResourceLocation, IdentityType> IDENTITIES = new HashMap<>();

    public static void register(IdentityType type) {
        IDENTITIES.put(type.id(), type);
    }

    public static @Nullable IdentityType get(ResourceLocation id) {
        return IDENTITIES.get(id);
    }

    public static boolean exists(ResourceLocation id) {
        return IDENTITIES.containsKey(id);
    }
    public static Collection<IdentityType> all() {
        return IDENTITIES.values();
    }
}
