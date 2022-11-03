package xyz.srgnis.bodyhealthsystem.registry;

import net.minecraft.client.gui.screen.ingame.HandledScreens;
import xyz.srgnis.bodyhealthsystem.client.screen.HealScreen;

public class Screens {
    public static void registerScreens() {
        HandledScreens.register(ScreenHandlers.HEAL_SCREEN_HANDLER, HealScreen::new);
    }
}
