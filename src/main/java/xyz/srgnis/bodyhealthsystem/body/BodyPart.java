package xyz.srgnis.bodyhealthsystem.body;

import net.minecraft.entity.Entity;
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

    public void takeDamage(float amount){
        BHSMain.LOGGER.info("Damage taken by: " + entity.getName() + " on " + identifier + " Amount: " + amount);
        if(amount > health){
            setHealth(0);
        }else {
            setHealth(health - amount);
        }
        BHSMain.LOGGER.info("Health of " + identifier + " is: " + health);
    }

    public void setHealth(float health) {
        this.health = health;
    }
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
}
