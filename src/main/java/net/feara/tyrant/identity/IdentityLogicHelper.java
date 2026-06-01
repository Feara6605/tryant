package net.feara.tyrant.identity;

import net.feara.tyrant.client.ClientIdentityCache;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

public class IdentityLogicHelper {
    public static boolean isIdentityNearby(Player source, ResourceLocation identity, double radius) {
        Minecraft mc = Minecraft.getInstance();
        for (Player other : mc.level.players()) {

            if (other == source) continue;

            var id = ClientIdentityCache.get(other.getUUID());

            if (id == null) continue;

            if (!id.equals(identity)) continue;
            float distance = other.distanceTo(source);

            if (distance <= radius) {
                return true;
            }
        }
        return false;
    }

    public static float getNearestIdentityDistance(Player source, ResourceLocation identity, double radius) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null) return Float.MAX_VALUE;
        float closest = Float.MAX_VALUE;

        for (Player other : mc.level.players()) {
            if (other == source) continue;

            var id = ClientIdentityCache.get(other.getUUID());

            if (id == null || !id.equals(identity)) continue;

            float distance = other.distanceTo(source);

            if (distance <= radius) {
                closest = Math.min(closest, distance);
            }
        }

        return closest;
    }

}
