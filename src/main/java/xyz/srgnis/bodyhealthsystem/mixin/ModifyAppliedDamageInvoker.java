package xyz.srgnis.bodyhealthsystem.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(LivingEntity.class)
public interface ModifyAppliedDamageInvoker {

    @Invoker("modifyAppliedDamage")
    float invokeModifyAppliedDamage(DamageSource source, float amount);
}
