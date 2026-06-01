package net.feara.tyrant.identity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;

public class PlayerIdentityData {
    private ResourceLocation identityId = ResourceLocation.parse("tyrant:none");

    public ResourceLocation getIdentityId() { return identityId; }
    public void setIdentityId(ResourceLocation identityId) { this.identityId = identityId; }
    public void clear() { this.identityId = ResourceLocation.parse("tyrant:none"); }
    public CompoundTag save() {
        CompoundTag tag = new CompoundTag();
        tag.putString("IdentityId", identityId.toString());
        return tag;
    }
    public void load(CompoundTag tag) {
        if (tag.contains("IdentityId")) {
            identityId = ResourceLocation.parse(
                    tag.getString("IdentityId")
            );
        }
    }
}
