package xyz.srgnis.bodyhealthsystem.body;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import xyz.srgnis.bodyhealthsystem.BHSMain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

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

    public void applyDamageBySource(float amount, DamageSource source){
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
    public void applyDamageGeneral(float amount){
        float split_amount = amount/getParts().size();
        getParts().forEach(new Consumer<BodyPart>() {
            @Override
            public void accept(BodyPart bodyPart) {
                bodyPart.takeDamage(split_amount);
            }
        });
    }

    //Randomly splits the damage into all parts
    public void applyDamageGeneralRandom(float amount){

    }

    //Splits the damage into list of parts
    public void applyDamageList(float amount, List<BodyPart> parts){

    }

    //Randomly splits the damage into list of parts
    public void applyDamageListRandom(float amount, List<BodyPart> parts){

    }

    //Splits the damage into a random list of parts
    public void applyDamageRandomList(float amount){

    }

    //Randomly splits the damage into a random list of parts
    public void applyDamageFullRandom(float amount){

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
