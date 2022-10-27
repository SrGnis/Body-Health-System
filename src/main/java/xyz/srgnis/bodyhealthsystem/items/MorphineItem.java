package xyz.srgnis.bodyhealthsystem.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import xyz.srgnis.bodyhealthsystem.registry.ModStatusEffects;

//TODO: MorphineItem needs more setup
public class MorphineItem extends Item {
    public MorphineItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        ItemStack itemStack = playerEntity.getStackInHand(hand);
        playerEntity.addStatusEffect(new StatusEffectInstance(ModStatusEffects.MORPHINE_EFFECT, 3600, 0));
        itemStack.decrement(1);
        return TypedActionResult.consume(itemStack);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        entity.addStatusEffect(new StatusEffectInstance(ModStatusEffects.MORPHINE_EFFECT, 3600, 0));
        itemStack.decrement(1);
        return ActionResult.CONSUME;
    }
}
