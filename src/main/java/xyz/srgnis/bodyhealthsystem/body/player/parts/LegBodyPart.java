package xyz.srgnis.bodyhealthsystem.body.player.parts;

import net.minecraft.entity.player.PlayerEntity;
import xyz.srgnis.bodyhealthsystem.body.BodyPart;
import xyz.srgnis.bodyhealthsystem.body.BodySide;
import xyz.srgnis.bodyhealthsystem.body.player.PlayerBodyParts;
import xyz.srgnis.bodyhealthsystem.config.Config;
import xyz.srgnis.bodyhealthsystem.constants.ArmorSlots;

public class LegBodyPart extends BodyPart {
    private final BodySide side;

    public LegBodyPart(BodySide side, PlayerEntity entity) {
        this(side, Config.legMaxHealth, Config.legMaxHealth, entity);
    }

    public LegBodyPart(BodySide side, float maxHealth, float health, PlayerEntity entity) {
        super(maxHealth, health, entity,  side==BodySide.LEFT? PlayerBodyParts.LEFT_LEG : PlayerBodyParts.RIGHT_LEG);
        this.side = side;
        this.armorSlot = ArmorSlots.LEGGINGS;
        this.armorList = entity.getInventory().armor;
        this.criticalThreshold = 0;
    }

    public BodySide getSide(){
        return side;
    }
}
