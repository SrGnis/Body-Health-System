package xyz.srgnis.bodyhealthsystem;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import xyz.srgnis.bodyhealthsystem.client.hud.BHSHud;
import xyz.srgnis.bodyhealthsystem.network.ClientNetworking;
import xyz.srgnis.bodyhealthsystem.registry.CustomScreen;

public class BHSClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HudRenderCallback.EVENT.register(new BHSHud());
        ClientNetworking.initialize();
        CustomScreen.registerScreens();
    }
}
