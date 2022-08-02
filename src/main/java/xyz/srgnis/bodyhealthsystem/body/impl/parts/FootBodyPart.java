package xyz.srgnis.bodyhealthsystem.body.impl.parts;

import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import xyz.srgnis.bodyhealthsystem.body.BodyPart;
import xyz.srgnis.bodyhealthsystem.body.impl.PlayerBodyParts;

public class FootBodyPart extends BodyPart {
    private static float defaultMaxHealth = 4;

    //TODO: think other way to do this
    public static Identifier getSide(String side){
        if(side == "left") {
            return PlayerBodyParts.LEFT_FOOT;
        }else{
            return PlayerBodyParts.RIGHT_FOOT;
        }
    }

    public FootBodyPart(String side, Entity entity) {
        super(defaultMaxHealth, defaultMaxHealth, entity, getSide(side));
    }

    public FootBodyPart(String side, float maxHealth, float health, Entity entity) {
        super(maxHealth, health, entity,  getSide(side));
    }
}
