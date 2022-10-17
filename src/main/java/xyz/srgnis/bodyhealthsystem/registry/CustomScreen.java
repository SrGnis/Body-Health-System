package xyz.srgnis.bodyhealthsystem.registry;

import net.minecraft.client.gui.screen.ingame.HandledScreens;
import xyz.srgnis.bodyhealthsystem.client.GenericScreen;

public class CustomScreen {
    public static void registerScreens() {
        HandledScreens.register(CustomScreenHandler.BAG_SCREEN_HANDLER, GenericScreen::new);
    }
}
