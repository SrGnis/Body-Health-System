package xyz.srgnis.bodyhealthsystem.client.hud;

public class HUDConstants {

    public static int BORDER_SIZE= 2;
    public static int HEAD_WIDTH = 20;
    public static int HEAD_HEIGHT = 20;

    public static int TORSO_WIDTH = 20;
    public static int TORSO_HEIGHT = 30;

    public static int LEFT_ARM_WIDTH = 10;
    public static int LEFT_ARM_HEIGHT = 30;

    public static int RIGHT_ARM_WIDTH = 10;
    public static int RIGHT_ARM_HEIGHT = 30;

    public static int LEFT_LEG_WIDTH = 10;
    public static int LEFT_LEG_HEIGHT = 20;

    public static int RIGHT_LEG_WIDTH = 10;
    public static int RIGHT_LEG_HEIGHT = 20;

    public static int LEFT_FOOT_WIDTH = 10;
    public static int LEFT_FOOT_HEIGHT = 10;

    public static int RIGHT_FOOT_WIDTH = 10;
    public static int RIGHT_FOOT_HEIGHT = 10;

    public static int BODY_WIDTH = LEFT_ARM_WIDTH+TORSO_WIDTH+RIGHT_ARM_WIDTH;
    public static int BODY_HEIGHT = HEAD_HEIGHT+TORSO_HEIGHT+LEFT_LEG_HEIGHT+LEFT_FOOT_HEIGHT;

    public static int HEAD_X_OFFSET = LEFT_ARM_WIDTH;
    public static int HEAD_Y_OFFSET = 0;


    public static int TORSO_X_OFFSET = LEFT_ARM_WIDTH;
    public static int TORSO_Y_OFFSET = HEAD_HEIGHT;

    public static int LEFT_ARM_X_OFFSET = 0;
    public static int LEFT_ARM_Y_OFFSET = HEAD_HEIGHT;


    public static int RIGHT_ARM_X_OFFSET = TORSO_X_OFFSET+TORSO_WIDTH;
    public static int RIGHT_ARM_Y_OFFSET = HEAD_HEIGHT;


    public static int LEFT_LEG_X_OFFSET = TORSO_X_OFFSET;
    public static int LEFT_LEG_Y_OFFSET = TORSO_Y_OFFSET+TORSO_HEIGHT;


    public static int RIGHT_LEG_X_OFFSET = LEFT_LEG_X_OFFSET+LEFT_LEG_WIDTH;
    public static int RIGHT_LEG_Y_OFFSET = LEFT_LEG_Y_OFFSET;


    public static int LEFT_FOOT_X_OFFSET = LEFT_LEG_X_OFFSET;
    public static int LEFT_FOOT_Y_OFFSET = LEFT_LEG_Y_OFFSET+LEFT_LEG_HEIGHT;


    public static int RIGHT_FOOT_X_OFFSET = LEFT_FOOT_X_OFFSET+LEFT_FOOT_WIDTH;
    public static int RIGHT_FOOT_Y_OFFSET = LEFT_FOOT_Y_OFFSET;



}
