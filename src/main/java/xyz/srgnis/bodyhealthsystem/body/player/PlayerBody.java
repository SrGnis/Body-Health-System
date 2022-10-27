package xyz.srgnis.bodyhealthsystem.body.player;

import net.minecraft.entity.DamageUtil;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.stat.Stats;
import xyz.srgnis.bodyhealthsystem.BHSMain;
import xyz.srgnis.bodyhealthsystem.body.Body;
import xyz.srgnis.bodyhealthsystem.body.BodyPart;
import xyz.srgnis.bodyhealthsystem.body.BodySide;
import xyz.srgnis.bodyhealthsystem.body.player.parts.*;
import xyz.srgnis.bodyhealthsystem.mixin.ModifyAppliedDamageInvoker;
import xyz.srgnis.bodyhealthsystem.registry.ModStatusEffects;
import xyz.srgnis.bodyhealthsystem.util.Utils;

import static xyz.srgnis.bodyhealthsystem.body.player.PlayerBodyParts.*;

public class PlayerBody extends Body {

    public PlayerBody(PlayerEntity player) {
        this.entity = player;
    }
    
    public void initParts(){
        PlayerEntity player = ((PlayerEntity) entity);
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
    //TODO: kill command don't kill;
    @Override
    public void applyDamageBySource(float amount, DamageSource source){
        if(source==null){
            super.applyDamageBySource(amount,source);
            return;
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
        remaining = takeDamage(amount, source, this.getPart(RIGHT_FOOT));
        if(remaining > 0){remaining = takeDamage(remaining, source, this.getPart(RIGHT_LEG));}
        if(remaining > 0){takeDamage(remaining, source, this.getPart(TORSO));}

        remaining = takeDamage(amount, source, this.getPart(LEFT_FOOT));
        if(remaining > 0){remaining = takeDamage(remaining, source, this.getPart(LEFT_LEG));}
        if(remaining > 0){takeDamage(remaining, source, this.getPart(TORSO));}
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

    @Override
    public float takeDamage(float amount, DamageSource source, BodyPart part){

        PlayerEntity player = (PlayerEntity)entity;
        //applyArmor
        amount = applyArmorToDamage(source, amount, part);
        float f = amount = ((ModifyAppliedDamageInvoker)entity).invokeModifyAppliedDamage(source, amount);

        //Copied from PlayerEntity.applyDamage
        amount = Math.max(amount - entity.getAbsorptionAmount(), 0.0f);
        entity.setAbsorptionAmount(entity.getAbsorptionAmount() - (f - amount));
        float g = f - amount;
        if (g > 0.0f && g < 3.4028235E37f) {
            player.increaseStat(Stats.DAMAGE_ABSORBED, Math.round(g * 10.0f));
        }
        if (amount == 0.0f) {
            return amount;
        }
        player.addExhaustion(source.getExhaustion());
        float h = entity.getHealth();
        player.getDamageTracker().onDamage(source, h, amount);
        if (amount < 3.4028235E37f) {
            player.increaseStat(Stats.DAMAGE_TAKEN, Math.round(amount * 10.0f));
        }

        float remaining;
        //TODO: This could mistake other magic damage as poison, is a better way of doing this?
        if(source.getName() == "magic" && entity.getStatusEffect(StatusEffects.POISON) != null) {
            remaining = part.damageWithoutKill(amount);
        }else{
            remaining = part.damage(amount);
        }
        return remaining;
    }

    public float applyArmorToDamage(DamageSource source, float amount, BodyPart part){
        if(part.getAffectedArmor().getItem() instanceof ArmorItem) {
            if (!source.bypassesArmor()) {
                PlayerEntity player = (PlayerEntity)entity;
                ArmorItem armorItem = ((ArmorItem) part.getAffectedArmor().getItem());
                player.getInventory().damageArmor(source,amount,new int[]{part.getArmorSlot()});
                amount = DamageUtil.getDamageLeft(amount, Utils.modifyProtection(armorItem, part.getArmorSlot()), Utils.modifyToughness(armorItem,part.getArmorSlot()));
            }
        }
        return amount;
    }
}
