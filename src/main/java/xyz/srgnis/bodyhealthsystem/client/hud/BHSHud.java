package xyz.srgnis.bodyhealthsystem.client.hud;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import xyz.srgnis.bodyhealthsystem.body.player.BodyProvider;
import xyz.srgnis.bodyhealthsystem.body.player.PlayerBodyParts;
import xyz.srgnis.bodyhealthsystem.config.Config;
import xyz.srgnis.bodyhealthsystem.constants.GUIConstants;

import static xyz.srgnis.bodyhealthsystem.util.Draw.drawHealthRectangle;
import static xyz.srgnis.bodyhealthsystem.util.Draw.selectHealthColor;

public class BHSHud implements HudRenderCallback {
    private static int startX;
    private static int startY;

    //TODO: select color in the parts
    @Override
    public void onHudRender(DrawContext drawContext, float v) {
        setHudCords();
        BodyProvider player = (BodyProvider)MinecraftClient.getInstance().player;

        if (!(player == null || MinecraftClient.getInstance().options.debugEnabled)) {
            int color;
            drawContext.getMatrices().push();
            //head
            color = selectHealthColor(player.getBody().getPart(PlayerBodyParts.HEAD));
            drawHealthRectangle(drawContext, startX+ GUIConstants.HEAD_X_OFFSET, startY+ GUIConstants.HEAD_Y_OFFSET, GUIConstants.HEAD_WIDTH, GUIConstants.HEAD_HEIGHT, color);
            //arm
            color = selectHealthColor(player.getBody().getPart(PlayerBodyParts.LEFT_ARM));
            drawHealthRectangle(drawContext, startX+ GUIConstants.LEFT_ARM_X_OFFSET, startY+ GUIConstants.LEFT_ARM_Y_OFFSET, GUIConstants.LEFT_ARM_WIDTH, GUIConstants.LEFT_ARM_HEIGHT, color);
            //torso
            color = selectHealthColor(player.getBody().getPart(PlayerBodyParts.TORSO));
            drawHealthRectangle(drawContext, startX+ GUIConstants.TORSO_X_OFFSET, startY+ GUIConstants.TORSO_Y_OFFSET, GUIConstants.TORSO_WIDTH, GUIConstants.TORSO_HEIGHT, color);
            //arm
            color = selectHealthColor(player.getBody().getPart(PlayerBodyParts.RIGHT_ARM));
            drawHealthRectangle(drawContext, startX+ GUIConstants.RIGHT_ARM_X_OFFSET, startY+ GUIConstants.RIGHT_ARM_Y_OFFSET, GUIConstants.RIGHT_ARM_WIDTH, GUIConstants.RIGHT_ARM_HEIGHT, color);
            //legs
            color = selectHealthColor(player.getBody().getPart(PlayerBodyParts.LEFT_LEG));
            drawHealthRectangle(drawContext, startX+ GUIConstants.LEFT_LEG_X_OFFSET, startY+ GUIConstants.LEFT_LEG_Y_OFFSET, GUIConstants.LEFT_LEG_WIDTH, GUIConstants.LEFT_LEG_HEIGHT, color);
            color = selectHealthColor(player.getBody().getPart(PlayerBodyParts.RIGHT_LEG));
            drawHealthRectangle(drawContext, startX+ GUIConstants.RIGHT_LEG_X_OFFSET, startY+ GUIConstants.RIGHT_LEG_Y_OFFSET, GUIConstants.RIGHT_LEG_WIDTH, GUIConstants.RIGHT_LEG_HEIGHT, color);
            //foot
            color = selectHealthColor(player.getBody().getPart(PlayerBodyParts.LEFT_FOOT));
            drawHealthRectangle(drawContext, startX+ GUIConstants.LEFT_FOOT_X_OFFSET, startY+ GUIConstants.LEFT_FOOT_Y_OFFSET, GUIConstants.LEFT_FOOT_WIDTH, GUIConstants.LEFT_FOOT_HEIGHT, color);
            color = selectHealthColor(player.getBody().getPart(PlayerBodyParts.RIGHT_FOOT));
            drawHealthRectangle(drawContext, startX+ GUIConstants.RIGHT_FOOT_X_OFFSET, startY+ GUIConstants.RIGHT_FOOT_Y_OFFSET, GUIConstants.RIGHT_FOOT_WIDTH, GUIConstants.RIGHT_FOOT_HEIGHT, color);
            drawContext.getMatrices().pop();
        }
    }

    private static void setHudCords(){
        switch (Config.hudPosition){
            case TOP_LEFT:
                startX = Config.hudXOffset;
                startY = Config.hudYOffset;
                break;
            case TOP_RIGHT:
                startX = MinecraftClient.getInstance().getWindow().getScaledWidth()- GUIConstants.BODY_WIDTH-Config.hudXOffset;
                startY = Config.hudYOffset;
                break;
            case BOTTOM_LEFT:
                startX = Config.hudXOffset;
                startY = MinecraftClient.getInstance().getWindow().getScaledHeight()- GUIConstants.BODY_HEIGHT-Config.hudYOffset;
                break;
            case BOTTOM_RIGHT:
                startX = MinecraftClient.getInstance().getWindow().getScaledWidth()- GUIConstants.BODY_WIDTH-Config.hudXOffset;
                startY = MinecraftClient.getInstance().getWindow().getScaledHeight()- GUIConstants.BODY_HEIGHT-Config.hudYOffset;
                break;
        }
    }
}
