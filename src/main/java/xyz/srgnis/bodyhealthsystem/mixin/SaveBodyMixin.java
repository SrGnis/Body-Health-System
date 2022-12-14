package xyz.srgnis.bodyhealthsystem.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.srgnis.bodyhealthsystem.body.player.BodyProvider;

@Mixin(PlayerEntity.class)
public class SaveBodyMixin {
    @Inject(method = "writeCustomDataToNbt", at = @At("RETURN"))
    public void serializeBodyParts(NbtCompound tag, CallbackInfo ci) {
        ((BodyProvider)this).getBody().writeToNbt(tag);
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("RETURN"))
    public void deserializeBodyParts(NbtCompound tag, CallbackInfo ci) {
        BodyProvider pe = (BodyProvider)this;
        pe.getBody().readFromNbt(tag);
    }
}
