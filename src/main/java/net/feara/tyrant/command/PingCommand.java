package net.feara.tyrant.command;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import static net.minecraft.commands.Commands.literal;

public class PingCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                literal("ping")
                        .executes(context -> {
                            context.getSource().sendSuccess(
                                    () -> Component.literal("Pong!"),
                                    false
                            );
                            return 1;
                        })
        );
    }
}
