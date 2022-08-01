package xyz.srgnis.bodyhealthsystem.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import xyz.srgnis.bodyhealthsystem.TheInterface;
import xyz.srgnis.bodyhealthsystem.body.Identifiers;

@Mixin(PlayerEntity.class)
public abstract class OnDamageMixin extends LivingEntity {
    @Shadow public abstract boolean damage(DamageSource source, float amount);

    protected OnDamageMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Redirect(method = "damage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;damage(Lnet/minecraft/entity/damage/DamageSource;F)Z"))
    public boolean redirectDamageToCustomLogic(LivingEntity livingEntity, DamageSource source, float amount) {
        if(livingEntity instanceof PlayerEntity){
            PlayerEntity pe = (PlayerEntity)livingEntity;
            if(getRandom().nextDouble() < 0.5){
                ((TheInterface)pe).getThe_body().getPart(Identifiers.HEAD).takeDamage(amount);
            }
            else {
                ((TheInterface)pe).getThe_body().getPart(Identifiers.BODY).takeDamage(amount);
            }
        }

        return false;
    }
}
