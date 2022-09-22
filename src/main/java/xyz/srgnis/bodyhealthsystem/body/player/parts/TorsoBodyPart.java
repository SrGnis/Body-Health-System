package xyz.srgnis.bodyhealthsystem.body.player.parts;

import net.minecraft.entity.player.PlayerEntity;
import xyz.srgnis.bodyhealthsystem.body.BodyPart;
import xyz.srgnis.bodyhealthsystem.body.player.PlayerBodyParts;
import xyz.srgnis.bodyhealthsystem.constants.ArmorSlots;

public class TorsoBodyPart extends BodyPart {
    private static final float defaultMaxHealth = 6;

    public TorsoBodyPart(PlayerEntity entity) {
        this(defaultMaxHealth, defaultMaxHealth, entity);
    }

    public TorsoBodyPart(float maxHealth, float health, PlayerEntity entity) {
        super(maxHealth, health, entity, PlayerBodyParts.TORSO);
        this.armorSlot = ArmorSlots.CHESTPLATE;
        this.armorList = entity.getInventory().armor;
    }
}
