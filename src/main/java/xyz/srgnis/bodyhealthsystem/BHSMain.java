package xyz.srgnis.bodyhealthsystem;

import static net.minecraft.server.command.CommandManager.*;
import static xyz.srgnis.bodyhealthsystem.network.ClientNetworking.handleHealthChange;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.srgnis.bodyhealthsystem.body.BodyPart;
import xyz.srgnis.bodyhealthsystem.body.player.PlayerBodyProvider;
import xyz.srgnis.bodyhealthsystem.client.hud.BHSHud;
import xyz.srgnis.bodyhealthsystem.command.DevCommands;
import xyz.srgnis.bodyhealthsystem.network.ClientNetworking;
import xyz.srgnis.bodyhealthsystem.network.ServerNetworking;

public class BHSMain implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.

	public static final String MOD_ID = "bhs";
	public  static Identifier MOD_IDENTIFIER = new Identifier(BHSMain.MOD_ID);
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		HudRenderCallback.EVENT.register(new BHSHud());
		ClientNetworking.initialize();
		ServerNetworking.initialize();
		DevCommands.initialize();

	}

}
