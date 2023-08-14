package xyz.srgnis.bodyhealthsystem.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.srgnis.bodyhealthsystem.body.Body;
import xyz.srgnis.bodyhealthsystem.body.player.BodyProvider;
import xyz.srgnis.bodyhealthsystem.network.ServerNetworking;

@Mixin(LivingEntity.class)
public class OnTryUseTotemMixin {
    @Inject(method = "tryUseTotem", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;setHealth(F)V", shift = At.Shift.AFTER))
    public void handleHeal(DamageSource source, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity le = (LivingEntity)(Object)this;
        if(le instanceof PlayerEntity pe){
            Body body = ((BodyProvider)pe).getBody();
            body.applyTotem();
        }
    }
}
