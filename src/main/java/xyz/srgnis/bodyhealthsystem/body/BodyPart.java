package xyz.srgnis.bodyhealthsystem.body;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import xyz.srgnis.bodyhealthsystem.BHSMain;


public abstract class BodyPart {
    private float maxHealth;
    private float health;
    private Entity entity;
    private Identifier identifier;

    public BodyPart(float maxHealth, float health, Entity entity, Identifier identifier) {
        this.maxHealth = maxHealth;
        this.health = health;
        this.entity = entity;
        this.identifier = identifier;
    }

    public float takeDamage(float amount){
        BHSMain.LOGGER.info("Damage taken by: " + entity.getName() + " on " + identifier + " Amount: " + amount);
        float sub = health - amount;
        health = Math.max(0, sub);
        BHSMain.LOGGER.info("Health of " + identifier + " is: " + health);

        return Math.max(0, -sub);
    }

    public void heal(){
        health = maxHealth;
    }

    public void heal(float amount){
        health = Math.min(maxHealth, health + amount);
    }

    public void setHealth(float health) { this.health = health;}
    public float getHealth() {
        return health;
    }
    public float getMaxHealth() {
        return maxHealth;
    }
    public void setMaxHealth(float maxHealth) {
        this.maxHealth = maxHealth;
    }
    public Entity getEntity() {
        return entity;
    }
    public void setEntity(Entity entity) {
        this.entity = entity;
    }
    public Identifier getIdentifier() {
        return identifier;
    }
    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    public void writeToNbt(NbtCompound nbt){
        NbtCompound new_nbt = new NbtCompound();
        new_nbt.putFloat("health", health);
        new_nbt.putFloat("maxHealth", maxHealth);
        nbt.put(identifier.toString(), new_nbt);
    }

    public void readFromNbt(NbtCompound nbt){
        health = nbt.getFloat("health");
        maxHealth = nbt.getFloat("maxHealth");
    }

    @Override
    public String toString() {
        return identifier + " MaxHP: " + maxHealth + " HP " + health + "\n";
    }
}
