package xyz.srgnis.bodyhealthsystem;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.srgnis.bodyhealthsystem.command.DevCommands;
import xyz.srgnis.bodyhealthsystem.config.Config;
import xyz.srgnis.bodyhealthsystem.items.MedkitItem;
import xyz.srgnis.bodyhealthsystem.network.ServerNetworking;
import xyz.srgnis.bodyhealthsystem.registry.ScreenHandlers;
import xyz.srgnis.bodyhealthsystem.registry.ModItems;
import xyz.srgnis.bodyhealthsystem.registry.ModStatusEffects;

import static net.minecraft.client.util.InputUtil.GLFW_CURSOR;
import static net.minecraft.client.util.InputUtil.GLFW_CURSOR_NORMAL;

public class BHSMain implements ModInitializer {
	public static final String MOD_ID = "bodyhealthsystem";
	public  static Identifier MOD_IDENTIFIER = new Identifier(BHSMain.MOD_ID);
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final RegistryKey<ItemGroup> BHS_GROUP = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(MOD_ID, "general"));

	public static Identifier id(String path) {
		return new Identifier(MOD_ID, path);
	}
	@Override
	public void onInitialize() {
		Registry.register(Registries.ITEM_GROUP, BHS_GROUP, FabricItemGroup.builder()
				.icon(() -> new ItemStack(ModItems.PLASTER_ITEM))
				.displayName(Text.translatable("bodyhealthsystem.group.general"))
				.build());

		ServerNetworking.initialize();
		DevCommands.initialize();
		ModItems.registerItems();
		ModStatusEffects.registerStatusEffects();
		Config.init(MOD_ID, Config.class);
		ScreenHandlers.registerScreenHandlers();
	}

	public static boolean debuggerReleaseControl() {
		GLFW.glfwSetInputMode(MinecraftClient.getInstance().getWindow().getHandle(), GLFW_CURSOR, GLFW_CURSOR_NORMAL);
		return true;
	}
}
