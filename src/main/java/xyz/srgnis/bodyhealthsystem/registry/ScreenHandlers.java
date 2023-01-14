package xyz.srgnis.bodyhealthsystem.registry;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import xyz.srgnis.bodyhealthsystem.client.screen.HealScreenHandler;

import static xyz.srgnis.bodyhealthsystem.BHSMain.id;

public class ScreenHandlers {

    public static final ScreenHandlerType<HealScreenHandler> HEAL_SCREEN_HANDLER = new ExtendedScreenHandlerType<>(HealScreenHandler::new);

    public static void registerScreenHandlers() {
        Registry.register(Registries.SCREEN_HANDLER, id("medkit"), HEAL_SCREEN_HANDLER);
    }
}
