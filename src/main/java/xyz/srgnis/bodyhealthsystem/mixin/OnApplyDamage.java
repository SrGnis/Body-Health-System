package xyz.srgnis.bodyhealthsystem.mixin;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.srgnis.bodyhealthsystem.BHSMain;

@Mixin(PlayerEntity.class)
public class OnApplyDamage {

    //The method signature is needed to be able to access the source parameter.
    @ModifyVariable(method = "applyDamage(FLnet/minecraft/entity/damageDamageSource;)V", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    public float handleHealthChange(float amount, DamageSource source) {
        //TODO: handle damage
        return 0;
    }
}