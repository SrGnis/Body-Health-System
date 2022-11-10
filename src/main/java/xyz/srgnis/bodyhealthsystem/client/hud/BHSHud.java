package xyz.srgnis.bodyhealthsystem.client.hud;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import xyz.srgnis.bodyhealthsystem.body.BodyPart;
import xyz.srgnis.bodyhealthsystem.body.player.BodyProvider;
import xyz.srgnis.bodyhealthsystem.body.player.PlayerBodyParts;

import static net.minecraft.client.gui.DrawableHelper.fill;

public class BHSHud implements HudRenderCallback {
    static final int dark_green = 0xff38761d;
    static final int green = 0xff8fce00;
    static final int red = 0xffb70000;
    static final int black = 0xff191919;
    static final int yellow = 0xffffd966;
    static final int orange = 0xfff87c00;
    static final int gray = 0xff5b5b5b;

    //TODO: Position config, select color in the parts
    @Override
    public void onHudRender(MatrixStack matrixStack, float v) {
        int startX = 0;
        int startY = 0;

        BodyProvider player = (BodyProvider)MinecraftClient.getInstance().player;

        if (player != null) {
            int color;
            matrixStack.push();
            //head
            color = selectHealthColor(player.getBody().getPart(PlayerBodyParts.HEAD));
            drawHealthRectangle(matrixStack, startX+ HUDConstants.HEAD_X_OFFSET, startY+HUDConstants.HEAD_Y_OFFSET, HUDConstants.HEAD_WIDTH, HUDConstants.HEAD_HEIGHT, color);
            //arm
            color = selectHealthColor(player.getBody().getPart(PlayerBodyParts.LEFT_ARM));
            drawHealthRectangle(matrixStack, startX+HUDConstants.LEFT_ARM_X_OFFSET, startY+HUDConstants.LEFT_ARM_Y_OFFSET, HUDConstants.LEFT_ARM_WIDTH, HUDConstants.LEFT_ARM_HEIGHT, color);
            //torso
            color = selectHealthColor(player.getBody().getPart(PlayerBodyParts.TORSO));
            drawHealthRectangle(matrixStack, startX+HUDConstants.TORSO_X_OFFSET, startY+HUDConstants.TORSO_Y_OFFSET, HUDConstants.TORSO_WIDTH, HUDConstants.TORSO_HEIGHT, color);
            //arm
            color = selectHealthColor(player.getBody().getPart(PlayerBodyParts.RIGHT_ARM));
            drawHealthRectangle(matrixStack, startX+HUDConstants.RIGHT_ARM_X_OFFSET, startY+HUDConstants.RIGHT_ARM_Y_OFFSET, HUDConstants.RIGHT_ARM_WIDTH, HUDConstants.RIGHT_ARM_HEIGHT, color);
            //legs
            color = selectHealthColor(player.getBody().getPart(PlayerBodyParts.LEFT_LEG));
            drawHealthRectangle(matrixStack, startX+HUDConstants.LEFT_LEG_X_OFFSET, startY+HUDConstants.LEFT_LEG_Y_OFFSET, HUDConstants.LEFT_LEG_WIDTH, HUDConstants.LEFT_LEG_HEIGHT, color);
            color = selectHealthColor(player.getBody().getPart(PlayerBodyParts.RIGHT_LEG));
            drawHealthRectangle(matrixStack, startX+HUDConstants.RIGHT_LEG_X_OFFSET, startY+HUDConstants.RIGHT_LEG_Y_OFFSET, HUDConstants.RIGHT_LEG_WIDTH, HUDConstants.RIGHT_LEG_HEIGHT, color);
            //foot
            color = selectHealthColor(player.getBody().getPart(PlayerBodyParts.LEFT_FOOT));
            drawHealthRectangle(matrixStack, startX+HUDConstants.LEFT_FOOT_X_OFFSET, startY+HUDConstants.LEFT_FOOT_Y_OFFSET, HUDConstants.LEFT_FOOT_WIDTH, HUDConstants.LEFT_FOOT_HEIGHT, color);
            color = selectHealthColor(player.getBody().getPart(PlayerBodyParts.RIGHT_FOOT));
            drawHealthRectangle(matrixStack, startX+HUDConstants.RIGHT_FOOT_X_OFFSET, startY+HUDConstants.RIGHT_FOOT_Y_OFFSET, HUDConstants.RIGHT_FOOT_WIDTH, HUDConstants.RIGHT_FOOT_HEIGHT, color);
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

    public static void drawHealthRectangle(MatrixStack matrixStack, int startX, int startY, int width, int height, int color){
        int endX = startX+width;
        int endY = startY+height;

        fill(matrixStack, startX, startY, endX, endY, black);
        fill(matrixStack, startX+HUDConstants.BORDER_SIZE, startY+HUDConstants.BORDER_SIZE, endX-HUDConstants.BORDER_SIZE, endY-HUDConstants.BORDER_SIZE, color);
    }
}
