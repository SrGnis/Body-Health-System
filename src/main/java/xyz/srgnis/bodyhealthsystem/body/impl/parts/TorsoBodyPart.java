package xyz.srgnis.bodyhealthsystem.body.impl.parts;

import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import xyz.srgnis.bodyhealthsystem.body.BodyPart;
import xyz.srgnis.bodyhealthsystem.body.impl.PlayerBodyParts;

public class TorsoBodyPart extends BodyPart {
    private static float defaultMaxHealth = 6;

    public TorsoBodyPart(Entity entity) {
        super(defaultMaxHealth, defaultMaxHealth, entity, PlayerBodyParts.TORSO);
    }

    public TorsoBodyPart(String side, float maxHealth, float health, Entity entity) {
        super(maxHealth, health, entity, PlayerBodyParts.TORSO);
    }
}
