package xyz.srgnis.bodyhealthsystem.registry;

import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import xyz.srgnis.bodyhealthsystem.BHSMain;
import xyz.srgnis.bodyhealthsystem.client.screen.HealScreenHandler;

public class CustomScreenHandler {
    public static ScreenHandlerType<? extends HealScreenHandler> BAG_SCREEN_HANDLER;

    public static void registerScreenHandlers() {
        BAG_SCREEN_HANDLER = Registry.register(Registry.SCREEN_HANDLER, new Identifier(BHSMain.MOD_ID, "medkit"), new ScreenHandlerType<>(HealScreenHandler::new));
    }
}
