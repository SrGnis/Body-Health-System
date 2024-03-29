package xyz.srgnis.bodyhealthsystem.mixin;

import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.srgnis.bodyhealthsystem.client.hud.BHSHud;
import xyz.srgnis.bodyhealthsystem.config.Config;

@Mixin(InGameHud.class)
public class NoHeartsMixin {
    @Inject(method = "renderHealthBar(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/entity/player/PlayerEntity;IIIIFIIIZ)V", at = @At("HEAD"), cancellable = true)
    public void renderHealthBar(DrawContext context, PlayerEntity player, int x, int y, int lines, int regeneratingHeartIndex, float maxHealth, int lastHealth, int health, int absorption, boolean blinking, CallbackInfo ci) {
        if(Config.hiddeVanillaHealth)
        ci.cancel();
    }

    @ModifyVariable(
            method = "Lnet/minecraft/client/gui/hud/InGameHud;renderStatusBars(Lnet/minecraft/client/gui/DrawContext;)V",
            at = @At("STORE"),
            ordinal = 9,
            index = 17,
            name = "s",
            require = 0
    )
    private int moveArmorBarDown(int s) {
        if(Config.hiddeVanillaHealth) {
            return s + 10;
        }else{
            return s;
        }
    }
}
