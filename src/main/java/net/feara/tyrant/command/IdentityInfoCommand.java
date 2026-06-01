package net.feara.tyrant.command;

import com.mojang.brigadier.CommandDispatcher;
import net.feara.tyrant.identity.IdentityRegistry;
import net.feara.tyrant.identity.IdentityType;
import net.feara.tyrant.identity.PlayerIdentityData;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

import static net.feara.tyrant.ModAttachments.PLAYER_IDENTITY;
import static net.minecraft.commands.Commands.literal;

public class IdentityInfoCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                literal("identityinfo")
                        .requires(source -> source.hasPermission(0))
                        .executes(context -> {
                            ServerPlayer player = context.getSource().getPlayerOrException();
                            PlayerIdentityData data = player.getData(PLAYER_IDENTITY);
                            IdentityType identity;
                            if (data.getIdentityId() != null) {
                                ResourceLocation id = data.getIdentityId();
                                identity = IdentityRegistry.get(id);
                            }
                            else {
                                identity = null;
                                return 0;
                            }

                            assert identity != null;
                            if (identity.displayName() != null) {
                                context.getSource().sendSuccess(
                                        () -> Component.literal("Current Identity: " + identity.displayName()),
                                        false
                                );
                            }
                            else {
                                context.getSource().sendSuccess(
                                        () -> Component.literal("Current Identity: Null"),
                                        false
                                );
                            }

                            return 1;
                        })
        );
    }
}
