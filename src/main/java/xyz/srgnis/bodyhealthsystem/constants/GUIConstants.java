package xyz.srgnis.bodyhealthsystem.constants;

public class GUIConstants {

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


    //SCALED
    public static int SCALE_FACTOR = 2;

    public static int  SCALED_BORDER_SIZE = BORDER_SIZE*SCALE_FACTOR;
    public static int  SCALED_HEAD_WIDTH = HEAD_WIDTH*SCALE_FACTOR;
    public static int  SCALED_HEAD_HEIGHT = HEAD_HEIGHT*SCALE_FACTOR;

    public static int  SCALED_TORSO_WIDTH = TORSO_WIDTH*SCALE_FACTOR;
    public static int  SCALED_TORSO_HEIGHT = TORSO_HEIGHT*SCALE_FACTOR;

    public static int  SCALED_LEFT_ARM_WIDTH = LEFT_ARM_WIDTH*SCALE_FACTOR;
    public static int  SCALED_LEFT_ARM_HEIGHT = LEFT_ARM_HEIGHT*SCALE_FACTOR;

    public static int  SCALED_RIGHT_ARM_WIDTH = RIGHT_ARM_WIDTH*SCALE_FACTOR;
    public static int  SCALED_RIGHT_ARM_HEIGHT = RIGHT_ARM_HEIGHT*SCALE_FACTOR;

    public static int  SCALED_LEFT_LEG_WIDTH = LEFT_LEG_WIDTH*SCALE_FACTOR;
    public static int  SCALED_LEFT_LEG_HEIGHT = LEFT_LEG_HEIGHT*SCALE_FACTOR;

    public static int  SCALED_RIGHT_LEG_WIDTH = RIGHT_LEG_WIDTH*SCALE_FACTOR;
    public static int  SCALED_RIGHT_LEG_HEIGHT = RIGHT_LEG_HEIGHT*SCALE_FACTOR;

    public static int  SCALED_LEFT_FOOT_WIDTH = LEFT_FOOT_WIDTH*SCALE_FACTOR;
    public static int  SCALED_LEFT_FOOT_HEIGHT = LEFT_FOOT_HEIGHT*SCALE_FACTOR;

    public static int  SCALED_RIGHT_FOOT_WIDTH = RIGHT_FOOT_WIDTH*SCALE_FACTOR;
    public static int  SCALED_RIGHT_FOOT_HEIGHT = RIGHT_FOOT_HEIGHT*SCALE_FACTOR;

    public static int  SCALED_BODY_WIDTH = SCALED_LEFT_ARM_WIDTH+SCALED_TORSO_WIDTH+SCALED_RIGHT_ARM_WIDTH;
    public static int  SCALED_BODY_HEIGHT = SCALED_HEAD_HEIGHT+SCALED_TORSO_HEIGHT+SCALED_LEFT_LEG_HEIGHT+SCALED_LEFT_FOOT_HEIGHT;

    public static int  SCALED_HEAD_X_OFFSET = SCALED_LEFT_ARM_WIDTH;
    public static int  SCALED_HEAD_Y_OFFSET = 0;


    public static int  SCALED_TORSO_X_OFFSET = SCALED_LEFT_ARM_WIDTH;
    public static int  SCALED_TORSO_Y_OFFSET = SCALED_HEAD_HEIGHT;

    public static int  SCALED_LEFT_ARM_X_OFFSET = 0;
    public static int  SCALED_LEFT_ARM_Y_OFFSET = SCALED_HEAD_HEIGHT;


    public static int  SCALED_RIGHT_ARM_X_OFFSET = SCALED_TORSO_X_OFFSET+SCALED_TORSO_WIDTH;
    public static int  SCALED_RIGHT_ARM_Y_OFFSET = SCALED_HEAD_HEIGHT;


    public static int  SCALED_LEFT_LEG_X_OFFSET = SCALED_TORSO_X_OFFSET;
    public static int  SCALED_LEFT_LEG_Y_OFFSET = SCALED_TORSO_Y_OFFSET+SCALED_TORSO_HEIGHT;


    public static int  SCALED_RIGHT_LEG_X_OFFSET = SCALED_LEFT_LEG_X_OFFSET+SCALED_LEFT_LEG_WIDTH;
    public static int  SCALED_RIGHT_LEG_Y_OFFSET = SCALED_LEFT_LEG_Y_OFFSET;


    public static int  SCALED_LEFT_FOOT_X_OFFSET = SCALED_LEFT_LEG_X_OFFSET;
    public static int  SCALED_LEFT_FOOT_Y_OFFSET = SCALED_LEFT_LEG_Y_OFFSET+SCALED_LEFT_LEG_HEIGHT;


    public static int  SCALED_RIGHT_FOOT_X_OFFSET = SCALED_LEFT_FOOT_X_OFFSET+SCALED_LEFT_FOOT_WIDTH;
    public static int  SCALED_RIGHT_FOOT_Y_OFFSET = SCALED_LEFT_FOOT_Y_OFFSET;


}
