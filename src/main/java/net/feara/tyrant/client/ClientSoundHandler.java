package net.feara.tyrant.client;

import net.feara.tyrant.identity.IdentityLogicHelper;
import net.feara.tyrant.identity.ModIdentities;
import net.minecraft.client.Minecraft;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;

public class ClientSoundHandler {
    private static boolean suppressionActive;
    private static int soundCooldown = 0;
    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        if (soundCooldown > 0) {
            soundCooldown--;
        }
        Minecraft mc = Minecraft.getInstance();
        Player localPlayer = mc.player;
        if (localPlayer == null) return;
        boolean yellowedNearby = IdentityLogicHelper.isIdentityNearby(localPlayer, ModIdentities.YELLOWED.id(), (10.0D * 10.0D));
        if (yellowedNearby) {
            //suppressionActive = true;

            if (mc.level.random.nextFloat() < 0.01f) {
                if (soundCooldown <= 0) {
                    float pitch = 0.7f + mc.level.random.nextFloat() * 0.3f;
                    mc.player.playSound(
                            SoundEvents.AMBIENT_CAVE.value(),
                            0.5f,
                            pitch
                    );
                    mc.player.sendSystemMessage(net.minecraft.network.chat.Component.literal("Yellow scary!"));
                    soundCooldown = 500 + mc.level.random.nextInt(500);
                }
            }
        }
        //else {
        //    suppressionActive = false;
        //}
    }
}
