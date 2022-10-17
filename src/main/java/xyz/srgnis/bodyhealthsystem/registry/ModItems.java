package xyz.srgnis.bodyhealthsystem.registry;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import xyz.srgnis.bodyhealthsystem.BHSMain;
import xyz.srgnis.bodyhealthsystem.items.BagItem;
import xyz.srgnis.bodyhealthsystem.items.MorphineItem;
import xyz.srgnis.bodyhealthsystem.items.PlasterItem;

public class ModItems {
    public static final Item PLASTER_ITEM = new PlasterItem(new FabricItemSettings().group(BHSMain.BHS_GROUP));
    public static final Item MORPHINE_ITEM = new MorphineItem(new FabricItemSettings().group(BHSMain.BHS_GROUP));
    public static final Item BAG = new BagItem(new Item.Settings().group(ItemGroup.TOOLS).maxCount(1));

    public static void registerItems(){
        Registry.register(Registry.ITEM, new Identifier(BHSMain.MOD_ID, "plaster"), PLASTER_ITEM);
        Registry.register(Registry.ITEM, new Identifier(BHSMain.MOD_ID, "morphine"), MORPHINE_ITEM);
        Registry.register(Registry.ITEM, new Identifier(BHSMain.MOD_ID,"bag"), BAG);

    }
}
