package xyz.srgnis.bodyhealthsystem.body;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import xyz.srgnis.bodyhealthsystem.BHSMain;
import xyz.srgnis.bodyhealthsystem.util.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public abstract class Body {
    private final HashMap<Identifier, BodyPart> parts = new HashMap<>();
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

    public void healAll(){
        for(BodyPart part : getParts()){
            part.heal();
        }
    }

    public void heal(float amount){
        ArrayList<BodyPart> parts_l = getParts();
        Collections.shuffle(parts_l);
        for(BodyPart part : parts_l){
            if(amount <= 0){break;}
            amount = part.heal(amount);
        }
    }
    public void heal(float amount, BodyPart part){
        part.heal(amount);
    }

    public void applyDamageBySource(float amount, DamageSource source){
        //Here we se the default way
        applyDamageLocalRandom(amount);
    }

    //Applies the damage to a single part
    public void applyDamageLocal(float amount, BodyPart part){
        part.takeDamage(amount);
    }

    //Applies the damage to a random part
    public void applyDamageLocalRandom(float amount){
        getParts().get(entity.getRandom().nextInt(parts.size())).takeDamage(amount);
    }

    //Splits the damage into all parts
    public void applyDamageGeneral(float amount){ applyDamageList(amount, getParts()); }

    //Randomly splits the damage into all parts
    public void applyDamageGeneralRandom(float amount){ applyDamageListRandom(amount, getParts()); }

    //Splits the damage into list of parts
    public void applyDamageList(float amount, List<BodyPart> parts){
        float split_amount = amount/parts.size();

        for(BodyPart bodyPart : parts){
            bodyPart.takeDamage(split_amount);
        }
    }

    //Randomly splits the damage into list of parts
    public void applyDamageListRandom(float amount, List<BodyPart> parts){
        List<Float> damages = Utils.n_random(amount, parts.size());

        int i = 0;
        for(BodyPart bodyPart : parts){
            bodyPart.takeDamage(damages.get(i));
            i++;
        }
    }

    //Splits the damage into a random list of parts
    public void applyDamageRandomList(float amount){
        List<BodyPart> randomlist = Utils.random_sublist(getParts(), entity.getRandom().nextInt(parts.size() + 1));
        applyDamageList(amount, randomlist);
    }

    //Randomly splits the damage into a random list of parts
    public void applyDamageFullRandom(float amount){
        List<BodyPart> randomlist = Utils.random_sublist(getParts(), entity.getRandom().nextInt(parts.size() + 1));
        applyDamageListRandom(amount,randomlist);
    }

    public void updateHealth(){
        float max_health = 0;
        float actual_health = 0;
        for( BodyPart part : this.getParts()){
            max_health += part.getMaxHealth();
            actual_health += part.getHealth();
        }
        entity.setHealth(entity.getMaxHealth() * ( actual_health / max_health ) );
    }

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
            for (Identifier partId : getPartsIdentifiers()) {
                if(!bodyNbt.getCompound(partId.toString()).isEmpty()) {
                    getPart(partId).readFromNbt(bodyNbt.getCompound(partId.toString()));
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
