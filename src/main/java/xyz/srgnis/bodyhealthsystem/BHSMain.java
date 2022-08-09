package xyz.srgnis.bodyhealthsystem;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.srgnis.bodyhealthsystem.body.PlayerBodyProvider;
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
	}
}
