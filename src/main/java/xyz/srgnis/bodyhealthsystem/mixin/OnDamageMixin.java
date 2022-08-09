package xyz.srgnis.bodyhealthsystem.mixin;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import xyz.srgnis.bodyhealthsystem.BHSMain;
import xyz.srgnis.bodyhealthsystem.body.BodyPart;
import xyz.srgnis.bodyhealthsystem.body.PlayerBodyProvider;
import xyz.srgnis.bodyhealthsystem.body.player.PlayerBody;

@Mixin(PlayerEntity.class)
public abstract class OnDamageMixin extends LivingEntity {
    @Shadow public abstract boolean damage(DamageSource source, float amount);

    protected OnDamageMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Redirect(method = "damage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;damage(Lnet/minecraft/entity/damage/DamageSource;F)Z"))
    public boolean redirectDamageToCustomLogic(LivingEntity livingEntity, DamageSource source, float amount) {
        if(livingEntity instanceof PlayerEntity){
            PlayerEntity pe = (PlayerEntity)livingEntity;
            PlayerBody body = ((PlayerBodyProvider)pe).getBody();
            body.getPart(body.getPartsIdentifiers().get(livingEntity.getRandom().nextInt(body.getPartsIdentifiers().size()))).takeDamage(amount);

            PacketByteBuf buf = PacketByteBufs.create();

            for (BodyPart part : body.getParts()) {
                buf.writeIdentifier(part.getIdentifier());
                buf.writeFloat(part.getHealth());
                buf.writeFloat(part.getMaxHealth());
            }

            ServerPlayNetworking.send((ServerPlayerEntity) pe, BHSMain.MOD_IDENTIFIER, buf);
        }

        return false;
    }
}
