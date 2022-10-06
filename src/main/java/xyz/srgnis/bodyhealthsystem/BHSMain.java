package xyz.srgnis.bodyhealthsystem;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.srgnis.bodyhealthsystem.client.hud.BHSHud;
import xyz.srgnis.bodyhealthsystem.command.DevCommands;
import xyz.srgnis.bodyhealthsystem.items.BandageItem;
import xyz.srgnis.bodyhealthsystem.network.ClientNetworking;
import xyz.srgnis.bodyhealthsystem.network.ServerNetworking;

public class BHSMain implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.

	public static final String MOD_ID = "bhs";
	public  static Identifier MOD_IDENTIFIER = new Identifier(BHSMain.MOD_ID);
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final ItemGroup BHS_GROUP = FabricItemGroupBuilder.create(
			new Identifier("bodyhealthsystem", "general"))
			.icon(() -> new ItemStack(Items.BOWL))
			.build();

	public static final Item BANDAGE_ITEM = new BandageItem(new FabricItemSettings().group(BHS_GROUP));

	@Override
	public void onInitialize() {
		HudRenderCallback.EVENT.register(new BHSHud());
		ClientNetworking.initialize();
		ServerNetworking.initialize();
		DevCommands.initialize();
		registerItems();

	}

	public void registerItems(){
		Registry.register(Registry.ITEM, new Identifier("bodyhealthsystem", "bandage"), BANDAGE_ITEM);
	}

}
