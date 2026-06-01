package net.feara.tyrant.client.input;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.common.util.Lazy;
import org.lwjgl.glfw.GLFW;

public class YellowedKeyHandler {
    // Key mapping is lazily initialized so it doesn't exist until it is registered
    public static final Lazy<KeyMapping> TOGGLE_SPECTATE = Lazy.of(() -> new KeyMapping(
            "key.tyrant.toggle_spectate",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_V,
            "key.categories.tyrant"
    ));

    public static final Lazy<KeyMapping> CYCLE_TARGET = Lazy.of(() ->
            new KeyMapping(
                    "key.tyrant.cycle_target",
                    InputConstants.Type.KEYSYM,
                    GLFW.GLFW_KEY_B,
                    "key.categories.tyrant"
            )
    );
    @SubscribeEvent
    public static void registerBindings(RegisterKeyMappingsEvent event) {
        event.register(TOGGLE_SPECTATE.get());
        event.register(CYCLE_TARGET.get());
    }

}
