package xyz.srgnis.bodyhealthsystem.body;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;
import net.minecraft.world.event.GameEvent;
import xyz.srgnis.bodyhealthsystem.BHSMain;
import xyz.srgnis.bodyhealthsystem.mixin.ModifyAppliedDamageInvoker;
import xyz.srgnis.bodyhealthsystem.util.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public abstract class Body {
    protected final HashMap<Identifier, BodyPart> parts = new HashMap<>();
    protected HashMap<Identifier, BodyPart> noCriticalParts = new HashMap<>();
    protected LivingEntity entity;

    public void addPart(Identifier identifier, BodyPart part){
        parts.put(identifier, part);
    }

    public BodyPart getPart(Identifier identifier){
        return parts.get(identifier);
    }

    public void removePart(Identifier identifier){
        parts.remove(identifier);
    }

    public ArrayList<BodyPart> getParts(){
        return new ArrayList<>(parts.values());
    }
    public ArrayList<Identifier> getPartsIdentifiers(){
        return new ArrayList<>(parts.keySet());
    }
    public ArrayList<BodyPart> getNoCriticalParts(){
        return new ArrayList<>(noCriticalParts.values());
    }
    public ArrayList<Identifier> getNoCriticalIdentifiers(){
        return new ArrayList<>(noCriticalParts.keySet());
    }

    public void healAll(){
        for(BodyPart part : getParts()){
            part.heal();
        }
    }

    public void heal(float amount){
        if(amount > 0) {
            ArrayList<BodyPart> parts_l = getParts();
            Collections.shuffle(parts_l);
            for (BodyPart part : parts_l) {
                if (amount <= 0) {
                    break;
                }
                amount = part.heal(amount);
            }
        }
    }
    public void heal(float amount, BodyPart part){
        part.heal(amount);
    }

    public void applyDamageBySource(float amount, DamageSource source){
        //Here we se the default way
        applyDamageLocalRandom(amount, source);
    }

    //TODO: remaining no critical part damage application
    //Applies the damage to a single part
    public void applyDamageLocal(float amount, DamageSource source, BodyPart part){
        takeDamage(amount, source, part);
    }

    //Applies the damage to a random part
    public void applyDamageLocalRandom(float amount, DamageSource source){
        takeDamage(amount, source, getNoCriticalParts().get(entity.getRandom().nextInt(noCriticalParts.size())) );
    }

    //Splits the damage into all parts
    public void applyDamageGeneral(float amount, DamageSource source){ applyDamageList(amount, source, getParts()); }

    //Randomly splits the damage into all parts
    public void applyDamageGeneralRandom(float amount, DamageSource source){ applyDamageListRandom(amount, source, getParts()); }

    //Splits the damage into list of parts
    public void applyDamageList(float amount, DamageSource source, List<BodyPart> parts){
        float split_amount = amount/parts.size();

        for(BodyPart bodyPart : parts){
            takeDamage(split_amount, source, bodyPart);
        }
    }

    //Randomly splits the damage into list of parts
    public void applyDamageListRandom(float amount, DamageSource source, List<BodyPart> parts){
        List<Float> damages = Utils.n_random(amount, parts.size());

        int i = 0;
        for(BodyPart bodyPart : parts){
            takeDamage(damages.get(i), source, bodyPart);
            i++;
        }
    }

    //Splits the damage into a random list of parts
    public void applyDamageRandomList(float amount, DamageSource source){
        List<BodyPart> randomlist = Utils.random_sublist(getNoCriticalParts(), entity.getRandom().nextInt(getNoCriticalParts().size() + 1));
        applyDamageList(amount, source, randomlist);
    }

    //Randomly splits the damage into a random list of parts
    public void applyDamageFullRandom(float amount, DamageSource source){
        List<BodyPart> randomlist = Utils.random_sublist(getNoCriticalParts(), entity.getRandom().nextInt(getNoCriticalParts().size() + 1));
        applyDamageListRandom(amount, source, randomlist);
    }

    public void updateHealth(){
        float max_health = 0;
        float actual_health = 0;
        for( BodyPart part : this.getParts()){
            max_health += part.getMaxHealth();
            actual_health += part.getHealth();
        }
        if (isAlive()){
            entity.setHealth(entity.getMaxHealth() * ( actual_health / max_health ));
        }else {
            entity.setHealth(0);
        }
    }

    public abstract boolean isAlive();

    public float takeDamage(float amount, DamageSource source, BodyPart part){

        amount = applyArmorToDamage(source, amount, part);
        float f = amount = ((ModifyAppliedDamageInvoker)entity).invokeModifyAppliedDamage(source, amount);
        amount = Math.max(amount - entity.getAbsorptionAmount(), 0.0f);
        entity.setAbsorptionAmount(entity.getAbsorptionAmount() - (f - amount));
        float g = f - amount;
        if (g > 0.0f && g < 3.4028235E37f && source.getAttacker() instanceof ServerPlayerEntity) {
            ((ServerPlayerEntity)source.getAttacker()).increaseStat(Stats.DAMAGE_DEALT_ABSORBED, Math.round(g * 10.0f));
        }
        if (amount == 0.0f) {
            return amount;
        }

        float h = part.getHealth();
        float sub = h - amount;
        part.setHealth(Math.max(0, sub));

        entity.getDamageTracker().onDamage(source, h, amount);
        entity.setAbsorptionAmount(entity.getAbsorptionAmount() - amount);
        entity.emitGameEvent(GameEvent.ENTITY_DAMAGE);

        return Math.max(0, -sub);
    }

    public abstract float applyArmorToDamage(DamageSource source, float amount, BodyPart part);

    public void checkNoCritical(BodyPart part){
        if(part.getHealth() > 0) {
            noCriticalParts.putIfAbsent(part.getIdentifier(), part);
        }else{
            noCriticalParts.remove(part.getIdentifier());
        }
    }

    public abstract void applyCriticalPartsEffect();

    public void writeToNbt (NbtCompound nbt){
        NbtCompound new_nbt = new NbtCompound();
        for(BodyPart part : getParts()){
            part.writeToNbt(new_nbt);
        }
        nbt.put(BHSMain.MOD_ID, new_nbt);
    }

    //TODO: Is this the best way of handling not found parts?
    public void readFromNbt (NbtCompound nbt) {
        NbtCompound bodyNbt = nbt.getCompound(BHSMain.MOD_ID);
        if (!bodyNbt.isEmpty()) {
            noCriticalParts.clear();
            for (Identifier partId : getPartsIdentifiers()) {
                if(!bodyNbt.getCompound(partId.toString()).isEmpty()) {
                    BodyPart part = getPart(partId);
                    part.readFromNbt(bodyNbt.getCompound(partId.toString()));
                    if(part.getHealth()>0){
                        noCriticalParts.put(part.getIdentifier(), part);
                    }
                }
            }
        }
    }

    @Override
    public String toString(){
        String s = "Body of player: TODO \n";
        for (BodyPart p : getParts()) {
            s = s + p.toString();
        }
        return s;
    }
}
