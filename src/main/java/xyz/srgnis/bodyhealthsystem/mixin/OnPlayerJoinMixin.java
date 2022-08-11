package xyz.srgnis.bodyhealthsystem.mixin;

import com.mojang.authlib.GameProfile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.encryption.PlayerPublicKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.srgnis.bodyhealthsystem.BHSMain;
import xyz.srgnis.bodyhealthsystem.body.player.PlayerBodyProvider;
import xyz.srgnis.bodyhealthsystem.body.player.PlayerBody;

@Mixin(PlayerEntity.class)
public class OnPlayerJoinMixin implements PlayerBodyProvider {
    public PlayerBody body = null;
    @Inject(method = "<init>*", at = @At("RETURN"))
    public void addBodyOnInit(World world, BlockPos pos, float yaw, GameProfile gameProfile, PlayerPublicKey publicKey, CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        body = new PlayerBody(player);
        BHSMain.LOGGER.info("Added body to player: " + player.getName());
    }

    @Override
    public PlayerBody getBody() {
        return body;
    }

    @Override
    public void setBody(PlayerBody pb) {
        body = pb;
    }
}
