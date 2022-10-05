package xyz.srgnis.bodyhealthsystem.body;

import net.minecraft.entity.DamageUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import xyz.srgnis.bodyhealthsystem.BHSMain;
import xyz.srgnis.bodyhealthsystem.body.player.PlayerBodyProvider;
import xyz.srgnis.bodyhealthsystem.mixin.ModifyAppliedDamageInvoker;
import xyz.srgnis.bodyhealthsystem.util.Utils;


public abstract class BodyPart {
    private float maxHealth;
    private float health;

    protected float criticalThreshold;
    private LivingEntity entity;
    private Identifier identifier;
    protected int armorSlot;
    protected DefaultedList<ItemStack> armorList;

    public BodyPart(float maxHealth, float health, LivingEntity entity, Identifier identifier) {
        this.maxHealth = maxHealth;
        this.health = health;
        this.entity = entity;
        this.identifier = identifier;
        //TODO: add body to parts
    }

    //TODO: I don't like casting to PlayerEntity in BodyPart.takeDamage()
    public float takeDamage(float amount, DamageSource source){
        PlayerEntity player = (PlayerEntity)entity;
        //applyArmor
        if(getAffectedArmor().getItem() instanceof ArmorItem) {
            if (!source.bypassesArmor()) {
                ArmorItem armorItem = ((ArmorItem) getAffectedArmor().getItem());
                player.getInventory().damageArmor(source,amount,new int[]{armorSlot});
                amount = DamageUtil.getDamageLeft(amount, Utils.modifyProtection(armorItem,armorSlot), Utils.modifyToughness(armorItem,armorSlot));
            }
        }
        //TODO: The protection enchantment is applied globally, is this ok?
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

        float sub = health - amount;
        setHealth(Math.max(0, sub));

        return Math.max(0, -sub);
    }

    public void heal(){
        setHealth(maxHealth);
    }

    public float heal(float amount){
        float add = health + amount;
        setHealth(Math.min(maxHealth, add));

        return add - health;
    }

    public ItemStack getAffectedArmor(){
        return armorList.get(armorSlot);
    }

    public void setHealth(float health) {
        this.health = health;
        ((PlayerBodyProvider)entity).getBody().checkNoCritical(this);
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
    public LivingEntity getEntity() {
        return entity;
    }
    public void setEntity(LivingEntity entity) {
        this.entity = entity;
    }
    public Identifier getIdentifier() {
        return identifier;
    }
    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    public float getCriticalThreshold() {
        return criticalThreshold;
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
