package net.feara.tyrant.identity;

import net.minecraft.resources.ResourceLocation;

public class IdentityType {
    private final ResourceLocation id;
    private final String displayName;
    public IdentityType(ResourceLocation id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }

    public ResourceLocation id() {
        return id;
    }
    public String displayName() {
        return displayName;
    }
}
