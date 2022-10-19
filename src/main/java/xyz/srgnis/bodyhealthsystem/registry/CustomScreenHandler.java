package xyz.srgnis.bodyhealthsystem.registry;

import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import xyz.srgnis.bodyhealthsystem.BHSMain;
import xyz.srgnis.bodyhealthsystem.client.GenericScreenHandler;
import xyz.srgnis.bodyhealthsystem.client.GenericScreenHandlerImpl;

public class CustomScreenHandler {
    public static ScreenHandlerType<? extends GenericScreenHandler> BAG_SCREEN_HANDLER;

    public static void registerScreenHandlers() {
        BAG_SCREEN_HANDLER = Registry.register(Registry.SCREEN_HANDLER, new Identifier(BHSMain.MOD_ID, "bag"), new ScreenHandlerType<>(GenericScreenHandlerImpl::new));
    }
}
