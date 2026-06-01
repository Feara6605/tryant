package net.feara.tyrant.client.sound;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.PlayLevelSoundEvent;

public class ClientSoundSuppressor {
    @SubscribeEvent
    public static void onPlaySound(PlayLevelSoundEvent.AtEntity event) {
        Entity source = event.getEntity();
        String soundName = event.getSound().value().getLocation().toString();

        if (source instanceof Player player) {

            if (SoundSuppressionHelper.isYellowed(source)) {
                Minecraft mc = Minecraft.getInstance();
                Player localPlayer = mc.player;
                System.out.println("Source: " + player.getName().getString());
                System.out.println("Yellowed? " + SoundSuppressionHelper.isYellowed(player));
                System.out.println("Cancelled? " + SoundSuppressionHelper.shouldSuppress(localPlayer, source));
                event.setCanceled(SoundSuppressionHelper.shouldSuppress(localPlayer, source));
            }
        }
        System.out.println("Sound fired: " + soundName);
        System.out.println("Entity: " + source);

    }
}
