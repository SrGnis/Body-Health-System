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

package xyz.srgnis.bodyhealthsystem.client.screen;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import xyz.srgnis.bodyhealthsystem.registry.CustomScreenHandler;


public class HealScreenHandler extends ScreenHandler {

	protected final PlayerEntity user;
	protected final LivingEntity entity;
	protected HealScreenHandler(ScreenHandlerType<? extends HealScreenHandler> type, int syncId, PlayerEntity user, LivingEntity entity) {
		super(type, syncId);
		this.user = user;
		this.entity = entity;
	}

	public HealScreenHandler(int syncId, PlayerInventory inventory, LivingEntity entity) {
		this(CustomScreenHandler.BAG_SCREEN_HANDLER, syncId, inventory.player, entity);
	}

	public HealScreenHandler(int syncId, PlayerInventory inventory) {
		this(CustomScreenHandler.BAG_SCREEN_HANDLER, syncId, inventory.player, inventory.player);
	}

	@Override
	public ItemStack transferSlot(PlayerEntity player, int index) {
		return ItemStack.EMPTY;
	}

	@Override
	public boolean canUse(PlayerEntity player) {
		return true;
	}
}
