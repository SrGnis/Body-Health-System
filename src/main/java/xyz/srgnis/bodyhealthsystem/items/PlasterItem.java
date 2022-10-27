package xyz.srgnis.bodyhealthsystem.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import xyz.srgnis.bodyhealthsystem.body.player.BodyProvider;

//TODO: PlaterItem needs more setup
public class PlasterItem extends Item {
    public PlasterItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        if(playerEntity.getHealth() < playerEntity.getMaxHealth()){
            ItemStack itemStack = playerEntity.getStackInHand(hand);
            playerEntity.playSound(SoundEvents.BLOCK_WOOL_BREAK, 1.0F, 1.0F);
            playerEntity.heal(2);
            itemStack.decrement(1);
            return TypedActionResult.consume(itemStack);
        }
        return TypedActionResult.fail(playerEntity.getStackInHand(hand));
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
            if(entity.getHealth() < entity.getMaxHealth()){
                ItemStack itemStack = user.getStackInHand(hand);
                entity.playSound(SoundEvents.BLOCK_WOOL_BREAK, 1.0F, 1.0F);
                entity.heal(2);
                itemStack.decrement(1);
                return ActionResult.CONSUME;
            }
        return ActionResult.PASS;
    }
}
