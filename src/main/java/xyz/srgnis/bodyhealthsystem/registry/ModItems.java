package xyz.srgnis.bodyhealthsystem.registry;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import xyz.srgnis.bodyhealthsystem.BHSMain;
import xyz.srgnis.bodyhealthsystem.items.MedkitItem;
import xyz.srgnis.bodyhealthsystem.items.MorphineItem;
import xyz.srgnis.bodyhealthsystem.items.PlasterItem;

public class ModItems {
    public static final Item PLASTER_ITEM = new PlasterItem(new FabricItemSettings().group(BHSMain.BHS_GROUP));
    public static final Item MORPHINE_ITEM = new MorphineItem(new FabricItemSettings().group(BHSMain.BHS_GROUP));
    public static final Item MEDKIT_ITEM = new MedkitItem(new FabricItemSettings().group(BHSMain.BHS_GROUP));

    public static void registerItems(){
        Registry.register(Registry.ITEM, new Identifier(BHSMain.MOD_ID, "plaster"), PLASTER_ITEM);
        Registry.register(Registry.ITEM, new Identifier(BHSMain.MOD_ID, "morphine"), MORPHINE_ITEM);
        Registry.register(Registry.ITEM, new Identifier(BHSMain.MOD_ID,"medkit"), MEDKIT_ITEM);

    }
}
