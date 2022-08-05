package xyz.srgnis.bodyhealthsystem.client.hud;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.util.math.MatrixStack;
import xyz.srgnis.bodyhealthsystem.body.BodyPart;
import xyz.srgnis.bodyhealthsystem.body.PlayerBodyProvider;

public class BHSHud implements HudRenderCallback {
    int green = 0xff8fce00;
    int red = 0xffb70000;

    @Override
    public void onHudRender(MatrixStack matrixStack, float v) {
        PlayerBodyProvider player = (PlayerBodyProvider)MinecraftClient.getInstance().player;
        TextRenderer renderer = MinecraftClient.getInstance().textRenderer;

        if (player != null) {
            int i = 0;
            for (BodyPart p : player.getBody().getParts()) {
                renderer.draw(matrixStack, p.toString(), 0.0F, renderer.fontHeight*i, 14737632);
                i++;
            }
        }
    }
}
