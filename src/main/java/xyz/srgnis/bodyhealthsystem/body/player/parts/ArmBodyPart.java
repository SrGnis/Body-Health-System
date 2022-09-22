package xyz.srgnis.bodyhealthsystem.body.player.parts;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import xyz.srgnis.bodyhealthsystem.body.BodyPart;
import xyz.srgnis.bodyhealthsystem.body.BodySide;
import xyz.srgnis.bodyhealthsystem.body.player.PlayerBodyParts;
import xyz.srgnis.bodyhealthsystem.constants.ArmorSlots;

public class ArmBodyPart extends BodyPart {

    private static final float defaultMaxHealth = 4;

    private final BodySide side;

    public ArmBodyPart(BodySide side, PlayerEntity entity) {
        this(side, defaultMaxHealth, defaultMaxHealth, entity);
    }

    public ArmBodyPart(BodySide side, float maxHealth, float health, PlayerEntity entity) {
        super(maxHealth, health, entity,  side==BodySide.LEFT? PlayerBodyParts.LEFT_ARM : PlayerBodyParts.RIGHT_ARM);
        this.side = side;
        this.armorSlot = ArmorSlots.CHESTPLATE;
        this.armorList = entity.getInventory().armor;

    }

    public BodySide getSide(){
        return side;
    }

}
