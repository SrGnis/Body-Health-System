package xyz.srgnis.bodyhealthsystem.body.player.parts;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import xyz.srgnis.bodyhealthsystem.body.BodyPart;
import xyz.srgnis.bodyhealthsystem.body.BodySide;
import xyz.srgnis.bodyhealthsystem.body.player.PlayerBodyParts;
import xyz.srgnis.bodyhealthsystem.constants.ArmorSlots;

public class FootBodyPart extends BodyPart {
    private static final float defaultMaxHealth = 4;

    private final BodySide side;

    public FootBodyPart(BodySide side, PlayerEntity entity) {
        this(side, defaultMaxHealth, defaultMaxHealth, entity);
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
