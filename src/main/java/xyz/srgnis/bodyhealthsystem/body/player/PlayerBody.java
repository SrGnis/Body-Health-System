package xyz.srgnis.bodyhealthsystem.body.player;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.srgnis.bodyhealthsystem.body.Body;
import xyz.srgnis.bodyhealthsystem.body.player.parts.*;

import javax.swing.text.html.parser.Entity;

public class PlayerBody extends Body {

    public PlayerBody(PlayerEntity player) {
        this.entity = player;
        this.addPart(PlayerBodyParts.HEAD, new HeadBodyPart(player));
        this.addPart(PlayerBodyParts.TORSO, new TorsoBodyPart(player));
        this.addPart(PlayerBodyParts.LEFT_ARM, new ArmBodyPart("left",player));
        this.addPart(PlayerBodyParts.RIGHT_ARM, new ArmBodyPart("right",player));
        this.addPart(PlayerBodyParts.LEFT_FOOT, new FootBodyPart("left",player));
        this.addPart(PlayerBodyParts.RIGHT_FOOT, new FootBodyPart("right",player));
        this.addPart(PlayerBodyParts.LEFT_LEG, new LegBodyPart("left",player));
        this.addPart(PlayerBodyParts.RIGHT_LEG, new LegBodyPart("right",player));
    }
}
