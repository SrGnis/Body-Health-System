package xyz.srgnis.bodyhealthsystem.network;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import xyz.srgnis.bodyhealthsystem.BHSMain;
import xyz.srgnis.bodyhealthsystem.body.player.BodyProvider;

public class ClientNetworking {

    public static void initialize(){
        ClientPlayNetworking.registerGlobalReceiver(BHSMain.MOD_IDENTIFIER, ClientNetworking::handleHealthChange);
    }

    public static void handleHealthChange(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buf, PacketSender sender){
        while (buf.isReadable()) {
            Identifier id = buf.readIdentifier();
            float health = buf.readFloat();
            float maxhealth = buf.readFloat();

            //TODO: Add config sync at join and remove setMaxHealth on ClientNetworking.handleHealthChange
            client.execute(() -> ((BodyProvider) client.player).getBody().getPart(id).setMaxHealth(maxhealth));
            client.execute(() -> ((BodyProvider) client.player).getBody().getPart(id).setHealth(health));
        }
    }

    public static void useHealingItem(Identifier partID, ItemStack itemStack){
        PacketByteBuf buf = PacketByteBufs.create();

        buf.writeIdentifier(partID);
        buf.writeItemStack(itemStack);

        ClientPlayNetworking.send(BHSMain.MOD_IDENTIFIER, buf);
    }
}
