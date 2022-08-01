package xyz.srgnis.bodyhealthsystem.mixin;

import com.mojang.authlib.GameProfile;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.encryption.PlayerPublicKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.srgnis.bodyhealthsystem.BHSMain;
import xyz.srgnis.bodyhealthsystem.TheInterface;
import xyz.srgnis.bodyhealthsystem.body.BodyPart;
import xyz.srgnis.bodyhealthsystem.body.Identifiers;
import xyz.srgnis.bodyhealthsystem.body.PlayerBody;

@Mixin(PlayerEntity.class)
public class JoinMixin implements TheInterface {
    public PlayerBody the_body = new PlayerBody();
    @Inject(method = "<init>*", at = @At("RETURN"))
    public void addPartsOnInit(World world, BlockPos pos, float yaw, GameProfile gameProfile, PlayerPublicKey publicKey, CallbackInfo ci) {
        the_body.addPart(Identifiers.HEAD, new BodyPart(5,5,(Entity) (Object) this,Identifiers.HEAD));
        the_body.addPart(Identifiers.BODY, new BodyPart(5,5,(Entity) (Object) this,Identifiers.BODY));
        BHSMain.LOGGER.info("Added Body");
    }

    @Override
    public PlayerBody getThe_body() {
        return the_body;
    }

    @Override
    public void setThe_body(PlayerBody pb) {
        the_body = pb;
    }
}
