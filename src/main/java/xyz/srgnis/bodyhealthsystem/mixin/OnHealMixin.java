package xyz.srgnis.bodyhealthsystem.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import xyz.srgnis.bodyhealthsystem.body.player.PlayerBody;
import xyz.srgnis.bodyhealthsystem.body.player.PlayerBodyProvider;
import xyz.srgnis.bodyhealthsystem.network.ServerNetworking;

import java.util.ArrayList;

@Mixin(LivingEntity.class)
public class OnHealMixin {
    @ModifyVariable(method = "heal", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    public float handleHeal(float amount) {
        LivingEntity le = (LivingEntity)(Object)this;
        if(le instanceof PlayerEntity){
            PlayerEntity pe = (PlayerEntity) le;
            PlayerBody body = ((PlayerBodyProvider)pe).getBody();
            ArrayList<Identifier> identifiers = body.getPartsIdentifiers();
            body.getPart(identifiers.get(pe.getRandom().nextInt(identifiers.size()))).heal(amount);
            ServerNetworking.syncBody(pe);
            return 0;
        }
        return amount;
    }
}
