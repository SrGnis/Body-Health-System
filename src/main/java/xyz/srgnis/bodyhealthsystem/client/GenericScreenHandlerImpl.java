package xyz.srgnis.bodyhealthsystem.client;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandlerType;
import xyz.srgnis.bodyhealthsystem.client.GenericScreenHandler;
import xyz.srgnis.bodyhealthsystem.registry.CustomScreenHandler;

public class GenericScreenHandlerImpl extends GenericScreenHandler {

    public GenericScreenHandlerImpl(int syncId, PlayerInventory inventory) {
        super(CustomScreenHandler.BAG_SCREEN_HANDLER, syncId, inventory.player);
    }
}
