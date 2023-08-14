package xyz.srgnis.bodyhealthsystem.config;

public class Config extends MidnightConfig {

    @Comment public static Comment comment_maxHealth;
    @Entry public static float headMaxHealth = 4;
    @Entry public static float torsoMaxHealth = 6;
    @Entry public static float legMaxHealth = 4;
    @Entry public static float armMaxHealth = 4;
    @Entry public static float footMaxHealth = 4;

    @Comment public static Comment comment_armorMult;
    @Entry public static float headArmorMult = 6;
    @Entry public static float torsoArmorMult = 2.5f;
    @Entry public static float legArmorMult = 3;
    @Entry public static float footArmorMult = 6;

    @Comment public static Comment comment_armorOffset;
    @Entry public static float headArmorOffset = 1;
    @Entry public static float torsoArmorOffset = 0;
    @Entry public static float legArmorOffset = 0;
    @Entry public static float footArmorOffset = 0;

    @Comment public static Comment comment_toughMult;
    @Entry public static float headToughMult = 4;
    @Entry public static float torsoToughMult = 3;
    @Entry public static float legToughMult = 3;
    @Entry public static float footToughMult = 3.5f;

    @Comment public static Comment comment_toughOffset;
    @Entry public static float headToughOffset = 0;
    @Entry public static float torsoToughOffset = 0;
    @Entry public static float legToughOffset = 0;
    @Entry public static float footToughOffset = 0;

    @Comment public static Comment comment_damage;
    @Entry public static float drownStarveDamage = 0.5F;

    @Comment public static Comment comment_HUDConfig;
    @Entry public static HudPosition hudPosition = HudPosition.TOP_LEFT;
    @Entry public static int hudXOffset = 0;
    @Entry public static int hudYOffset = 0;
    @Entry public static boolean hiddeVanillaHealth = true;

    public enum HudPosition {
        TOP_RIGHT,
        TOP_LEFT,
        BOTTOM_RIGHT,
        BOTTOM_LEFT;
    }

}
