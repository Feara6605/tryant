package net.feara.tyrant.identity;

import net.minecraft.resources.ResourceLocation;

public class ModIdentities {
    public static final IdentityType YELLOWED =
            new IdentityType(ResourceLocation.parse("tyrant:yellowed"), "yellowed");
    public static final IdentityType NONE =
            new IdentityType(ResourceLocation.parse("tyrant:none"), "none");
    public static void init() {
        IdentityRegistry.register(YELLOWED);
        IdentityRegistry.register(NONE);
    }
}
