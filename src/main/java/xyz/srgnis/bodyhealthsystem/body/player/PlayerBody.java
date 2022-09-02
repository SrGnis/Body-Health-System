package xyz.srgnis.bodyhealthsystem.body.player;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import xyz.srgnis.bodyhealthsystem.body.Body;
import xyz.srgnis.bodyhealthsystem.body.player.parts.*;

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

    @Override
    public void applyDamageBySource(float amount, DamageSource source){
        if(source==null){
            super.applyDamageBySource(amount,source);
        }
        switch (source.getName()){
            case "fall":
                applyFallDamage(amount);
                break;
        }

    }

    //Progressive application of the damage from foot to torso
    public void applyFallDamage(float amount){
        amount = amount/2;
        float remaining;
        remaining = this.getPart(PlayerBodyParts.RIGHT_FOOT).takeDamage(amount);
        if(remaining > 0){remaining = this.getPart(PlayerBodyParts.RIGHT_LEG).takeDamage(remaining);}
        if(remaining > 0){this.getPart(PlayerBodyParts.TORSO).takeDamage(remaining);}

        remaining = this.getPart(PlayerBodyParts.LEFT_FOOT).takeDamage(amount);
        if(remaining > 0){remaining = this.getPart(PlayerBodyParts.LEFT_LEG).takeDamage(remaining);}
        if(remaining > 0){this.getPart(PlayerBodyParts.TORSO).takeDamage(remaining);}
    }
}
