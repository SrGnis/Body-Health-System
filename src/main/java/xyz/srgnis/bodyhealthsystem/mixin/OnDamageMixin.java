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
import xyz.srgnis.bodyhealthsystem.body.player.PlayerBodyProvider;
import xyz.srgnis.bodyhealthsystem.body.player.PlayerBody;
import xyz.srgnis.bodyhealthsystem.network.ServerNetworking;

@Mixin(PlayerEntity.class)
public abstract class OnDamageMixin extends LivingEntity {
    @Shadow public abstract boolean damage(DamageSource source, float amount);

    protected OnDamageMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Redirect(method = "damage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;damage(Lnet/minecraft/entity/damage/DamageSource;F)Z"))
    public boolean redirectDamageToCustomLogic(LivingEntity livingEntity, DamageSource source, float amount) {
        if(livingEntity instanceof PlayerEntity){
            PlayerBody body = ((PlayerBodyProvider)livingEntity).getBody();
            body.damage(amount);
            ServerNetworking.syncBody((PlayerEntity)livingEntity);
        }

        return false;
    }
}
