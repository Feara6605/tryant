package net.feara.tyrant.client;

import net.feara.tyrant.identity.ModIdentities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RenderPlayerEvent;

public final class ClientRenderEvents {
    private ClientRenderEvents() {}
    @SubscribeEvent
    public static void onRenderPlayer(RenderPlayerEvent.Pre event) {
        //System.out.println("Render event fired for: " + event.getEntity().getName().getString());
        LocalPlayer viewer = Minecraft.getInstance().player;
        if (viewer == null) return;

        Player target = event.getEntity();
        if (target == viewer) return;

        ResourceLocation id = ClientIdentityCache.get(target.getUUID());
        if (id == null || !id.equals(ModIdentities.YELLOWED.id())) return;
        //System.out.println("ID: " + id);
        var camera = Minecraft.getInstance()
                .gameRenderer
                .getMainCamera()
                .getPosition();

        var look = viewer.getLookAngle();

        var toTarget = target.getEyePosition()
                .subtract(camera)
                .normalize();

        double dot = look.normalize().dot(toTarget);

        if (dot > 0.65) {
            event.setCanceled(true);
        }
    }
}
