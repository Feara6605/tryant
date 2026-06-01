package net.feara.tyrant.identity.effects;

import net.feara.tyrant.client.ClientIdentityCache;
import net.feara.tyrant.identity.IdentityLogicHelper;
import net.feara.tyrant.identity.ModIdentities;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

public class IdentityEffectHandler {
    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;
        if (player.tickCount % 20 != 0) return;
        var id = ClientIdentityCache.get(player.getUUID());

        if (id != null && !id.equals(ModIdentities.YELLOWED.id())) {
            boolean nearWatcher = IdentityLogicHelper.isIdentityNearby(player, ModIdentities.YELLOWED.id(), (10.0D * 10.0D));

            if (nearWatcher && player.getRandom().nextFloat() < 0.08f) {
                //player.sendSystemMessage(net.minecraft.network.chat.Component.literal("Nearby watcher detected"));
                if (!player.hasEffect(MobEffects.CONFUSION)) {
                    player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 80, 0, false, false, false));
                }
                if (player.getRandom().nextFloat() < 0.03f) {
                    player.addEffect(
                            new MobEffectInstance(
                                    MobEffects.DARKNESS,
                                    80,
                                    0,
                                    false,
                                    false,
                                    false
                            )
                    );
                }
            }
        }
    }
}
