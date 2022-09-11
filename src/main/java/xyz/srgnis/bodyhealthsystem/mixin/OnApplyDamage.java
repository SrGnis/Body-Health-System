package xyz.srgnis.bodyhealthsystem.mixin;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import xyz.srgnis.bodyhealthsystem.body.player.PlayerBody;
import xyz.srgnis.bodyhealthsystem.body.player.PlayerBodyProvider;
import xyz.srgnis.bodyhealthsystem.network.ServerNetworking;

@Mixin(PlayerEntity.class)
public class OnApplyDamage {

    //The method signature is needed to be able to access the source parameter.
    @ModifyVariable(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    public float handleHealthChange(float amount, DamageSource source) {
        PlayerBody body = ((PlayerBodyProvider)this).getBody();
        body.applyDamageBySource(amount, source);
        body.updateHealth();
        ServerNetworking.syncBody((PlayerEntity)(Object)this);
        return 0;
    }
}