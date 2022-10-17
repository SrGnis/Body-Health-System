package xyz.srgnis.bodyhealthsystem;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.srgnis.bodyhealthsystem.client.BagScreenHandler;
import xyz.srgnis.bodyhealthsystem.client.hud.BHSHud;
import xyz.srgnis.bodyhealthsystem.command.DevCommands;
import xyz.srgnis.bodyhealthsystem.config.Config;
import xyz.srgnis.bodyhealthsystem.items.BagItem;
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

	public static final ScreenHandlerType<BagScreenHandler> BAG_SCREEN_HANDLER = new ScreenHandlerType<>(BagScreenHandler::new);
	public static final Item BAG = new BagItem(new Item.Settings().group(ItemGroup.TOOLS).maxCount(1));
	@Override
	public void onInitialize() {
		ServerNetworking.initialize();
		DevCommands.initialize();
		ModItems.registerItems();
		ModStatusEffects.registerStatusEffects();
		Config.init(MOD_ID, Config.class);
		Registry.register(Registry.SCREEN_HANDLER, new Identifier(MOD_ID,"bag"), BAG_SCREEN_HANDLER);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID,"bag"), BAG);
	}
}
