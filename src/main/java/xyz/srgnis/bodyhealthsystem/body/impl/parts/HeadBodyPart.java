package xyz.srgnis.bodyhealthsystem.body.impl.parts;

import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import xyz.srgnis.bodyhealthsystem.body.BodyPart;
import xyz.srgnis.bodyhealthsystem.body.impl.PlayerBodyParts;

public class HeadBodyPart extends BodyPart {
    private static float defaultMaxHealth = 4;

    public HeadBodyPart(Entity entity) {
        super(defaultMaxHealth, defaultMaxHealth, entity, PlayerBodyParts.HEAD);
    }

    public HeadBodyPart(String side, float maxHealth, float health, Entity entity) {
        super(maxHealth, health, entity, PlayerBodyParts.HEAD);
    }
}
