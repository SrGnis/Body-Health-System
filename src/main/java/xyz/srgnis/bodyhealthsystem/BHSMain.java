package xyz.srgnis.bodyhealthsystem;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.srgnis.bodyhealthsystem.command.DevCommands;
import xyz.srgnis.bodyhealthsystem.config.Config;
import xyz.srgnis.bodyhealthsystem.network.ServerNetworking;
import xyz.srgnis.bodyhealthsystem.registry.CustomScreenHandler;
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
	@Override
	public void onInitialize() {
		ServerNetworking.initialize();
		DevCommands.initialize();
		ModItems.registerItems();
		ModStatusEffects.registerStatusEffects();
		Config.init(MOD_ID, Config.class);
		CustomScreenHandler.registerScreenHandlers();
	}
}
