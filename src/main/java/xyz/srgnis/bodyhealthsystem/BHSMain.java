package xyz.srgnis.bodyhealthsystem;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.srgnis.bodyhealthsystem.client.hud.BHSHud;
import xyz.srgnis.bodyhealthsystem.command.DevCommands;
import xyz.srgnis.bodyhealthsystem.config.ConfigInstance;
import xyz.srgnis.bodyhealthsystem.config.ModConfig;
import xyz.srgnis.bodyhealthsystem.network.ClientNetworking;
import xyz.srgnis.bodyhealthsystem.network.ServerNetworking;
import xyz.srgnis.bodyhealthsystem.registry.ModItems;
import xyz.srgnis.bodyhealthsystem.registry.ModStatusEffects;

public class BHSMain implements ModInitializer {
	public static final String MOD_ID = "bodyhealthsystem";
	public  static Identifier MOD_IDENTIFIER = new Identifier(BHSMain.MOD_ID);
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final ItemGroup BHS_GROUP = FabricItemGroupBuilder.create(
			new Identifier(MOD_ID, "general"))
			.icon(() -> new ItemStack(ModItems.PLASTER_ITEM))
			.build();
	public static ConfigInstance CONFIG;
	@Override
	public void onInitialize() {
		ModConfig.setup(MOD_ID);
		CONFIG = ModConfig.init();
		HudRenderCallback.EVENT.register(new BHSHud());
		ClientNetworking.initialize();
		ServerNetworking.initialize();
		DevCommands.initialize();
		ModItems.registerItems();
		ModStatusEffects.registerStatusEffects();

	}
}
