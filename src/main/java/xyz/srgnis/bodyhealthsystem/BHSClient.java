package xyz.srgnis.bodyhealthsystem;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.gui.screen.ingame.Generic3x3ContainerScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import xyz.srgnis.bodyhealthsystem.client.hud.BHSHud;
import xyz.srgnis.bodyhealthsystem.network.ClientNetworking;

public class BHSClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HudRenderCallback.EVENT.register(new BHSHud());
        ClientNetworking.initialize();
        HandledScreens.register(BHSMain.BAG_SCREEN_HANDLER, Generic3x3ContainerScreen::new);
    }
}
