package xyz.srgnis.bodyhealthsystem.body.player.parts;

import net.minecraft.entity.player.PlayerEntity;
import xyz.srgnis.bodyhealthsystem.body.BodyPart;
import xyz.srgnis.bodyhealthsystem.body.BodySide;
import xyz.srgnis.bodyhealthsystem.body.player.PlayerBodyParts;
import xyz.srgnis.bodyhealthsystem.config.Config;
import xyz.srgnis.bodyhealthsystem.constants.ArmorSlots;

public class ArmBodyPart extends BodyPart {

    private final BodySide side;

    public ArmBodyPart(BodySide side, PlayerEntity entity) {
        this(side, Config.armMaxHealth, Config.armMaxHealth, entity);
    }

    public ArmBodyPart(BodySide side, float maxHealth, float health, PlayerEntity entity) {
        super(maxHealth, health, entity,  side==BodySide.LEFT? PlayerBodyParts.LEFT_ARM : PlayerBodyParts.RIGHT_ARM);
        this.side = side;
        this.armorSlot = ArmorSlots.CHESTPLATE;
        this.armorList = entity.getInventory().armor;
        this.criticalThreshold = 0;

    }

    public BodySide getSide(){
        return side;
    }

}
