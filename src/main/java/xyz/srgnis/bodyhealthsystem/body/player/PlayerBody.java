package xyz.srgnis.bodyhealthsystem.body.player;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import xyz.srgnis.bodyhealthsystem.BHSMain;
import xyz.srgnis.bodyhealthsystem.body.Body;
import xyz.srgnis.bodyhealthsystem.body.BodyPart;
import xyz.srgnis.bodyhealthsystem.body.BodySide;
import xyz.srgnis.bodyhealthsystem.body.player.parts.*;
import xyz.srgnis.bodyhealthsystem.effects.MorphineStatusEffect;
import xyz.srgnis.bodyhealthsystem.registry.ModStatusEffects;

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
        //TODO: handle more damage sources
        //TODO: starvation overpowered?
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

    //TODO: blindness on head critical?
    public void applyCriticalPartsEffect(){
        if(entity.getStatusEffect(ModStatusEffects.MORPHINE_EFFECT) == null) {
            int amplifier;
            //legs and foot
            amplifier = -1;
            amplifier += getAmplifier(getPart(RIGHT_FOOT));
            amplifier += getAmplifier(getPart(LEFT_FOOT));
            amplifier += getAmplifier(getPart(RIGHT_LEG));
            amplifier += getAmplifier(getPart(LEFT_LEG));
            applyStatusEffectWithAmplifier(StatusEffects.SLOWNESS, amplifier);

            //arms
            amplifier = -1;
            amplifier += getAmplifier(getPart(RIGHT_ARM));
            amplifier += getAmplifier(getPart(LEFT_ARM));
            applyStatusEffectWithAmplifier(StatusEffects.MINING_FATIGUE, amplifier);

            //torso
            amplifier = -1;
            amplifier += getAmplifier(getPart(TORSO));
            amplifier += getAmplifier(getPart(HEAD));
            applyStatusEffectWithAmplifier(StatusEffects.WEAKNESS, amplifier);
        }
    }

    //TODO: Utility function?
    public int getAmplifier(BodyPart part){
        if(part.getHealth() <= part.getCriticalThreshold()){
            return 1;
        }
        return 0;
    }

    public void applyStatusEffectWithAmplifier(StatusEffect effect, int amplifier){
        if(amplifier >= 0){
            StatusEffectInstance s = entity.getStatusEffect(effect);
            if(s == null){
                entity.addStatusEffect(new StatusEffectInstance(effect, 40, amplifier));
            }else if(s.getDuration() <= 5 || s.getAmplifier() != amplifier){
                entity.addStatusEffect(new StatusEffectInstance(effect, 40, amplifier));
            }
        }
    }
}
