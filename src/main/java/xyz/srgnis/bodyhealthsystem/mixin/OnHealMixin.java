package xyz.srgnis.bodyhealthsystem.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import xyz.srgnis.bodyhealthsystem.body.Body;
import xyz.srgnis.bodyhealthsystem.body.player.BodyProvider;
import xyz.srgnis.bodyhealthsystem.network.ServerNetworking;

@Mixin(LivingEntity.class)
public class OnHealMixin {
    @ModifyVariable(method = "heal", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    public float handleHeal(float amount) {
        LivingEntity le = (LivingEntity)(Object)this;
        if(le instanceof PlayerEntity pe){
            Body body = ((BodyProvider)pe).getBody();
            body.heal(amount);
            body.updateHealth();
            if(!le.getWorld().isClient){
                ServerNetworking.syncBody(pe);
            }
            return 0;
        }
        return amount;
    }
}
