package xyz.srgnis.bodyhealthsystem.body.player;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import xyz.srgnis.bodyhealthsystem.BHSMain;
import xyz.srgnis.bodyhealthsystem.body.Body;
import xyz.srgnis.bodyhealthsystem.body.BodyPart;
import xyz.srgnis.bodyhealthsystem.body.BodySide;
import xyz.srgnis.bodyhealthsystem.body.player.parts.*;
import static xyz.srgnis.bodyhealthsystem.body.player.PlayerBodyParts.*;

public class PlayerBody extends Body {

    public PlayerBody(PlayerEntity player) {
        this.entity = player;
        this.addPart(HEAD, new HeadBodyPart(player));
        this.addPart(TORSO, new TorsoBodyPart(player));
        this.addPart(LEFT_ARM, new ArmBodyPart(BodySide.LEFT,player));
        this.addPart(RIGHT_ARM, new ArmBodyPart(BodySide.RIGHT,player));
        this.addPart(LEFT_FOOT, new FootBodyPart(BodySide.LEFT,player));
        this.addPart(RIGHT_FOOT, new FootBodyPart(BodySide.RIGHT,player));
        this.addPart(LEFT_LEG, new LegBodyPart(BodySide.LEFT,player));
        this.addPart(RIGHT_LEG, new LegBodyPart(BodySide.RIGHT,player));
        this.noCriticalParts.putAll(this.parts);
    }

    @Override
    public void applyDamageBySource(float amount, DamageSource source){
        if(source==null){
            super.applyDamageBySource(amount,source);
        }
        //TODO: apply armor protection
        //TODO: handle more damage sources
        //TODO: starvation over powered?
        switch (source.getName()){
            case "fall":
            case "hotFloor":
                applyFallDamage(amount, source);
                break;
            case "lightningBolt":
            case "lava":
            case "fireball":
            case "explosion":
            case "explosion.player":
                applyDamageFullRandom(amount, source);
                break;
            case "drown":
            case "starve":
                applyDamageLocal(amount, source, this.getPart(TORSO));
                break;
            case "flyIntoWall":
            case "anvil":
            case "fallingBlock":
            case "fallingStalactite":
                applyDamageLocal(amount, source, this.getPart(HEAD));
                break;
            default:
                applyDamageLocalRandom(amount, source);
        }

    }

    //Progressive application of the damage from foot to torso
    public void applyFallDamage(float amount, DamageSource source){
        amount = amount/2;
        float remaining;
        remaining = this.getPart(RIGHT_FOOT).takeDamage(amount, source);
        if(remaining > 0){remaining = this.getPart(RIGHT_LEG).takeDamage(remaining, source);}
        if(remaining > 0){this.getPart(TORSO).takeDamage(remaining, source);}

        remaining = this.getPart(LEFT_FOOT).takeDamage(amount, source);
        if(remaining > 0){remaining = this.getPart(LEFT_LEG).takeDamage(remaining, source);}
        if(remaining > 0){this.getPart(TORSO).takeDamage(remaining, source);}
    }

    @Override
    public void updateHealth(){
        float max_health = 0;
        float actual_health = 0;
        for( BodyPart part : this.getParts()){
            max_health += part.getMaxHealth();
            actual_health += part.getHealth();
        }
        entity.setHealth(entity.getMaxHealth() * ( actual_health / max_health ) * isAlive() );
    }

    public int isAlive(){
        return getPart(TORSO).getHealth() <= 0 || getPart(HEAD).getHealth() <= 0 ? 0 : 1;
    }
}
