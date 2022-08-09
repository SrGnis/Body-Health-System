package xyz.srgnis.bodyhealthsystem.body.player.parts;

import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import xyz.srgnis.bodyhealthsystem.body.BodyPart;
import xyz.srgnis.bodyhealthsystem.body.player.PlayerBodyParts;

public class ArmBodyPart extends BodyPart {

    private static float defaultMaxHealth = 4;

    //TODO: think other way to do this
    public static Identifier getSide(String side){
        if(side == "left") {
            return PlayerBodyParts.LEFT_ARM;
        }else{
            return PlayerBodyParts.RIGHT_ARM;
        }
    }

    public ArmBodyPart(String side, Entity entity) {
        super(defaultMaxHealth, defaultMaxHealth, entity, getSide(side));
    }

    public ArmBodyPart(String side, float maxHealth, float health, Entity entity) {
        super(maxHealth, health, entity,  getSide(side));
    }

}
