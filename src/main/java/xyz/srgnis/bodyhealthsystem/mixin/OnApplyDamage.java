package xyz.srgnis.bodyhealthsystem.mixin;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.DamageTypeTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import xyz.srgnis.bodyhealthsystem.BHSMain;
import xyz.srgnis.bodyhealthsystem.body.Body;
import xyz.srgnis.bodyhealthsystem.body.player.BodyProvider;
import xyz.srgnis.bodyhealthsystem.network.ServerNetworking;

@Mixin(PlayerEntity.class)
public class OnApplyDamage {

    //NOTE: The method signature is needed to be able to access the source parameter.
    @ModifyVariable(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    public float handleHealthChange(float amount, DamageSource source) {
        if(source.isIn(DamageTypeTags.BYPASSES_INVULNERABILITY)){
            //If is out of world (/kill) just return the damage to kill the player
            return amount;
        }
        if (!((PlayerEntity) (Object)this).isInvulnerableTo(source)) {
            Body body = ((BodyProvider)this).getBody();
            body.applyDamageBySource(amount, source);
            body.updateHealth();
            ServerNetworking.syncBody((PlayerEntity)(Object)this);
        }
        return 0;
    }
}