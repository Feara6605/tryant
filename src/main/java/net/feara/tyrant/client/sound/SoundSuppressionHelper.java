package net.feara.tyrant.client.sound;

import net.feara.tyrant.client.ClientIdentityCache;
import net.feara.tyrant.identity.ModIdentities;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;


public class SoundSuppressionHelper {
    private static int radius = 10;

    public static boolean isYellowed(Entity player) {
        var id = ClientIdentityCache.get(player.getUUID());
        if (id != null) {
            return id.equals(ModIdentities.YELLOWED.id());
        }
        return false;
    }

    public static boolean shouldSuppress(Player listener, Entity source) {
        return (source != null && listener != null) &&  isYellowed(source) && (listener.distanceTo(source) <= radius); //source != listener &&
    }
}
