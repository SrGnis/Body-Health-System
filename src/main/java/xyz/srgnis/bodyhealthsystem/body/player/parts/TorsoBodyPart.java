package xyz.srgnis.bodyhealthsystem.body.player.parts;

import net.minecraft.entity.player.PlayerEntity;
import xyz.srgnis.bodyhealthsystem.body.BodyPart;
import xyz.srgnis.bodyhealthsystem.body.player.PlayerBodyParts;
import xyz.srgnis.bodyhealthsystem.constants.ArmorSlots;

import static xyz.srgnis.bodyhealthsystem.BHSMain.CONFIG;

public class TorsoBodyPart extends BodyPart {
    public TorsoBodyPart(PlayerEntity entity) {
        this(CONFIG.TORSO_MAX_HEALTH, CONFIG.TORSO_MAX_HEALTH, entity);
    }

    public TorsoBodyPart(float maxHealth, float health, PlayerEntity entity) {
        super(maxHealth, health, entity, PlayerBodyParts.TORSO);
        this.armorSlot = ArmorSlots.CHESTPLATE;
        this.armorList = entity.getInventory().armor;
        this.criticalThreshold = maxHealth/3;
    }
}
