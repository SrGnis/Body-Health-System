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

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.world.World;
import xyz.srgnis.bodyhealthsystem.body.Body;
import xyz.srgnis.bodyhealthsystem.body.player.BodyProvider;
import xyz.srgnis.bodyhealthsystem.registry.ScreenHandlers;

import static xyz.srgnis.bodyhealthsystem.network.ClientNetworking.requestBodyData;


public class HealScreenHandler extends net.minecraft.screen.ScreenHandler {

	protected final PlayerEntity user;
	protected final LivingEntity entity;
	protected final ItemStack itemStack;

	public HealScreenHandler(int syncId, PlayerInventory inventory, ItemStack itemStack, LivingEntity entity) {
		super(ScreenHandlers.HEAL_SCREEN_HANDLER, syncId);
		this.user = inventory.player;
		this.entity = entity;
		this.itemStack = itemStack;
		//FIXME: is other way of doing this?
		if(entity.world.isClient()){
			requestBodyData(entity);
		}
	}

	//Used by the Client
	public HealScreenHandler(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
		this(syncId, playerInventory, readItemStack(buf), readEntity(buf, playerInventory.player.world));

	}

	private static ItemStack readItemStack(PacketByteBuf buf) {
		return buf.readItemStack();
	}

	private static LivingEntity readEntity(PacketByteBuf buf, World world) {
		int id = buf.readInt();
		return (LivingEntity) world.getEntityById(id);
	}

	public LivingEntity getEntity() {return entity;}

	public Body getBody() {
		return ((BodyProvider)entity).getBody();
	}

	public PlayerEntity getUser() {return user;}

	@Override
	public ItemStack transferSlot(PlayerEntity player, int index) {
		return ItemStack.EMPTY;
	}

	@Override
	public boolean canUse(PlayerEntity player) {
		return true;
	}

	public ItemStack getItemStack() {
		return itemStack;
	}
}
