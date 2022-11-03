/*
 * Copyright (c) 2016, 2017, 2018, 2019 FabricMC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package xyz.srgnis.bodyhealthsystem.items;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import xyz.srgnis.bodyhealthsystem.body.player.BodyProvider;
import xyz.srgnis.bodyhealthsystem.client.screen.HealScreenHandler;

//FIXME: null pointers
public class MedkitItem extends Item {
	public MedkitItem(Settings settings) {
		super(settings);
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		ItemStack stack = user.getStackInHand(hand);
		user.openHandledScreen(createScreenHandlerFactory(stack, user));
		return TypedActionResult.success(stack);
	}

	@Override
	public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
		if(entity instanceof BodyProvider){
			user.openHandledScreen(createScreenHandlerFactory(stack, entity));
		}
		return ActionResult.CONSUME;
	}

	private ExtendedScreenHandlerFactory createScreenHandlerFactory(ItemStack stack, LivingEntity entity) {
		return new ExtendedScreenHandlerFactory() {

			@Override
			public ScreenHandler createMenu(int syncId, PlayerInventory inventory, PlayerEntity player) {
				return new HealScreenHandler(syncId, inventory, stack, entity);
			}

			@Override
			public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
				buf.writeItemStack(stack);
				buf.writeInt(entity.getId());
			}

			@Override
			public Text getDisplayName() {
				//FIXME
				return Text.literal("Medkit");
			}
		};
	}
}
