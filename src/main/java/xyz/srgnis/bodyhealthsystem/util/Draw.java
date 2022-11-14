package xyz.srgnis.bodyhealthsystem.util;

import net.minecraft.client.util.math.MatrixStack;
import xyz.srgnis.bodyhealthsystem.body.BodyPart;
import xyz.srgnis.bodyhealthsystem.constants.GUIConstants;

import static net.minecraft.client.gui.DrawableHelper.fill;

public class Draw {
    static final int dark_green = 0xff38761d;
    static final int green = 0xff8fce00;
    static final int red = 0xffb70000;
    static final int black = 0xff191919;
    static final int yellow = 0xffffd966;
    static final int orange = 0xfff87c00;
    static final int gray = 0xff5b5b5b;

    public static int selectHealthColor(BodyPart part){
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
        fill(matrixStack, startX+ GUIConstants.BORDER_SIZE, startY+ GUIConstants.BORDER_SIZE, endX- GUIConstants.BORDER_SIZE, endY- GUIConstants.BORDER_SIZE, color);
    }
}
