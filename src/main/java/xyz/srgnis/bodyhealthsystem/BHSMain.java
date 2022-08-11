package xyz.srgnis.bodyhealthsystem;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.srgnis.bodyhealthsystem.body.BodyPart;
import xyz.srgnis.bodyhealthsystem.body.player.PlayerBodyProvider;
import xyz.srgnis.bodyhealthsystem.client.hud.BHSHud;

public class BHSMain implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.

	public static final String MOD_ID = "bodyhealthsystem";
	public  static Identifier MOD_IDENTIFIER = new Identifier(BHSMain.MOD_ID);
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		HudRenderCallback.EVENT.register(new BHSHud());

		//TODO: create a class for the network handlers
		ClientPlayNetworking.registerGlobalReceiver(MOD_IDENTIFIER, (client, handler, buf, responseSender) -> {
			while (buf.isReadable()) {
				Identifier id = buf.readIdentifier();
				float health = buf.readFloat();
				float maxhealth = buf.readFloat();

				client.execute(() -> {
					// Everything in this lambda is run on the render thread
					((PlayerBodyProvider) client.player).getBody().getPart(id).setHealth(health);
				});
			}
		});

		ServerPlayConnectionEvents.JOIN.register((serverPlayNetworkHandler,packetSender,server)->{
			BHSMain.syncBody(serverPlayNetworkHandler.player);
		});

	}

	//TODO: Create a class for this
	public static void syncBody(PlayerEntity pe){
		PacketByteBuf buf = PacketByteBufs.create();

		for (BodyPart part : ((PlayerBodyProvider)pe).getBody().getParts()) {
			buf.writeIdentifier(part.getIdentifier());
			buf.writeFloat(part.getHealth());
			buf.writeFloat(part.getMaxHealth());
		}

		ServerPlayNetworking.send( (ServerPlayerEntity) pe, BHSMain.MOD_IDENTIFIER, buf);
	}
}
