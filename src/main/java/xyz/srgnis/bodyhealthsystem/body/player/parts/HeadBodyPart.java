package xyz.srgnis.bodyhealthsystem.body.player.parts;

import net.minecraft.entity.player.PlayerEntity;
import xyz.srgnis.bodyhealthsystem.body.BodyPart;
import xyz.srgnis.bodyhealthsystem.body.player.PlayerBodyParts;
import xyz.srgnis.bodyhealthsystem.config.Config;
import xyz.srgnis.bodyhealthsystem.constants.ArmorSlots;

public class HeadBodyPart extends BodyPart {

    public HeadBodyPart(PlayerEntity entity) {
        this(Config.headMaxHealth, Config.headMaxHealth, entity);
    }

    public HeadBodyPart(float maxHealth, float health, PlayerEntity entity) {
        super(maxHealth, health, entity, PlayerBodyParts.HEAD);
        this.armorSlot = ArmorSlots.HELMET;
        this.armorList = entity.getInventory().armor;
        this.criticalThreshold = maxHealth/3;
        this.isKillRequirement = true;
    }
}
