package xyz.srgnis.bodyhealthsystem.mixin;

import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.srgnis.bodyhealthsystem.body.BodyPart;
import xyz.srgnis.bodyhealthsystem.body.player.PlayerBody;
import xyz.srgnis.bodyhealthsystem.body.player.PlayerBodyProvider;

@Mixin(PlayerEntity.class)
public class PlayerTickMixin {
    @Inject(method = "tick", at = @At("HEAD"))
    public void onTick(CallbackInfo ci){
        PlayerEntity player = (PlayerEntity) (Object) this;
        PlayerBody body = ((PlayerBodyProvider)player).getBody();
        body.applyCriticalPartsEffect();
    }
}
