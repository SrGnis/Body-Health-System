package xyz.srgnis.bodyhealthsystem.body;

import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Body {
    private final HashMap<Identifier, BodyPart> parts = new HashMap<>();

    public void addPart(Identifier identifier, BodyPart part){
        parts.put(identifier, part);
    }

    public BodyPart getPart(Identifier identifier){
        return parts.get(identifier);
    }

    public void removePart(Identifier identifier){
        parts.remove(identifier);
    }

    public ArrayList<BodyPart> getparts(){
        return new ArrayList<>(parts.values());
    }
}
