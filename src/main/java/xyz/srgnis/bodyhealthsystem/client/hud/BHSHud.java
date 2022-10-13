package xyz.srgnis.bodyhealthsystem.client.hud;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import xyz.srgnis.bodyhealthsystem.body.BodyPart;
import xyz.srgnis.bodyhealthsystem.body.player.PlayerBodyParts;
import xyz.srgnis.bodyhealthsystem.body.player.BodyProvider;

import static net.minecraft.client.gui.DrawableHelper.fill;

public class BHSHud implements HudRenderCallback {
    int dark_green = 0xff38761d;
    int green = 0xff8fce00;
    int red = 0xffb70000;
    int black = 0xff191919;
    int yellow = 0xffffd966;
    int orange = 0xfff87c00;
    int gray = 0xff5b5b5b;

    //TODO: Position config, select color in the parts
    @Override
    public void onHudRender(MatrixStack matrixStack, float v) {
        BodyProvider player = (BodyProvider)MinecraftClient.getInstance().player;

        if (player != null) {
            int color;
            matrixStack.push();
            //head
            color = selectHealthColor(player.getBody().getPart(PlayerBodyParts.HEAD));
            fill(matrixStack, 10, 0, 30, 20, black);
            fill(matrixStack, 12, 2, 28, 18, color);
            //arm
            color = selectHealthColor(player.getBody().getPart(PlayerBodyParts.LEFT_ARM));
            fill(matrixStack, 0, 20, 10, 50, black);
            fill(matrixStack, 2, 22, 8, 48, color);
            //torso
            color = selectHealthColor(player.getBody().getPart(PlayerBodyParts.TORSO));
            fill(matrixStack, 10, 20, 30, 50, black);
            fill(matrixStack, 12, 22, 28, 48, color);
            //arm
            color = selectHealthColor(player.getBody().getPart(PlayerBodyParts.RIGHT_ARM));
            fill(matrixStack, 30, 20, 40, 50, black);
            fill(matrixStack, 32, 22, 38, 48, color);
            //legs
            color = selectHealthColor(player.getBody().getPart(PlayerBodyParts.LEFT_LEG));
            fill(matrixStack, 10, 50, 20, 70, black);
            fill(matrixStack, 12, 52, 18, 68, color);
            color = selectHealthColor(player.getBody().getPart(PlayerBodyParts.RIGHT_LEG));
            fill(matrixStack, 20, 50, 30, 70, black);
            fill(matrixStack, 22, 52, 28, 68, color);
            //foot
            color = selectHealthColor(player.getBody().getPart(PlayerBodyParts.LEFT_FOOT));
            fill(matrixStack, 10, 70, 20, 80, black);
            fill(matrixStack, 12, 72, 18, 78, color);
            color = selectHealthColor(player.getBody().getPart(PlayerBodyParts.RIGHT_FOOT));
            fill(matrixStack, 20, 70, 30, 80, black);
            fill(matrixStack, 22, 72, 28, 78, color);
            matrixStack.pop();
        }
    }

    public int selectHealthColor(BodyPart part){
        float percent = part.getHealth()/part.getMaxHealth();
        if(percent>=1){
            return dark_green;
        }
        if(percent>0.75){
            return green;
        }
        if(percent>0.5){
            return yellow;
        }
        if(percent>0.25){
            return orange;
        }
        if(percent>0){
            return red;
        }
        return gray;
    }
}
