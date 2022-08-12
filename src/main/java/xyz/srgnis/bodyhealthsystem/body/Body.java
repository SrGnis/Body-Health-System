package xyz.srgnis.bodyhealthsystem.body;

import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import xyz.srgnis.bodyhealthsystem.BHSMain;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.HashMap;

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

    public void damage(float amount){
        ArrayList<Identifier> identifiers = getPartsIdentifiers();
        //Random damage application, this is not the final implementation
        getPart(identifiers.get(entity.getRandom().nextInt(identifiers.size()))).takeDamage(amount);
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
