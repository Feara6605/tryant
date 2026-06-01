package net.feara.tyrant.command;

import com.mojang.brigadier.CommandDispatcher;
import net.feara.tyrant.client.ClientIdentityCache;
import net.feara.tyrant.identity.PlayerIdentityData;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import static net.feara.tyrant.ModAttachments.PLAYER_IDENTITY;
import static net.minecraft.commands.Commands.literal;

public class ClearIdentityCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                literal("clearidentity")
                        .requires(source -> source.hasPermission(0))
                        .executes(context -> {
                            ServerPlayer player = context.getSource().getPlayerOrException();
                            PlayerIdentityData data = player.getData(PLAYER_IDENTITY);
                            data.clear();
                            ClientIdentityCache.clear(player.getUUID());

                            context.getSource().sendSuccess(
                                    () -> Component.literal("Identity Cleared!"),
                                    false
                            );
                            return 1;
                        })
        );
    }
}
