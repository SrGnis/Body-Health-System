package xyz.srgnis.bodyhealthsystem.body.player.parts;

import net.minecraft.entity.player.PlayerEntity;
import xyz.srgnis.bodyhealthsystem.body.BodyPart;
import xyz.srgnis.bodyhealthsystem.body.BodySide;
import xyz.srgnis.bodyhealthsystem.body.player.PlayerBodyParts;
import xyz.srgnis.bodyhealthsystem.constants.ArmorSlots;

import static xyz.srgnis.bodyhealthsystem.BHSMain.CONFIG;

public class FootBodyPart extends BodyPart {
    private final BodySide side;

    public FootBodyPart(BodySide side, PlayerEntity entity) {
        this(side, CONFIG.FOOT_MAX_HEALTH, CONFIG.FOOT_MAX_HEALTH, entity);
    }

    public FootBodyPart(BodySide side, float maxHealth, float health, PlayerEntity entity) {
        super(maxHealth, health, entity,  side==BodySide.LEFT? PlayerBodyParts.LEFT_FOOT : PlayerBodyParts.RIGHT_FOOT);
        this.side = side;
        this.armorSlot = ArmorSlots.BOOTS;
        this.armorList = entity.getInventory().armor;
        this.criticalThreshold = 0;
    }

    public BodySide getSide(){
        return side;
    }
}
