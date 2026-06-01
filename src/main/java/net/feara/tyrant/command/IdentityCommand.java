package net.feara.tyrant.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.feara.tyrant.identity.IdentityManager;
import net.feara.tyrant.identity.IdentityRegistry;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

import static net.minecraft.commands.Commands.argument;
import static net.minecraft.commands.Commands.literal;

public class IdentityCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                literal("setidentity")
                        .requires(source -> source.hasPermission(0))
                        .then(argument("identity", StringArgumentType.word())
                        .executes(context -> {
                            ServerPlayer player = context.getSource().getPlayerOrException();
                            String rawId = StringArgumentType.getString(context, "identity");
                            ResourceLocation id = ResourceLocation.fromNamespaceAndPath("tyrant", rawId);
                            if (!IdentityRegistry.exists(id)) {
                                context.getSource().sendFailure(Component.literal("Unknown Identity: " + rawId));
                                return 0;
                            }
                            IdentityManager.setIdentity(player, id);

                            context.getSource().sendSuccess(
                                    () -> Component.literal("Identity set to " + rawId), //§k for glitch text+
                                    false
                            );
                            return 1;
                        })
                )
        );
    }
}
