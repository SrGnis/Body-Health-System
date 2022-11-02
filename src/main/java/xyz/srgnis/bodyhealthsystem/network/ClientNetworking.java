package xyz.srgnis.bodyhealthsystem.network;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import xyz.srgnis.bodyhealthsystem.BHSMain;
import xyz.srgnis.bodyhealthsystem.body.player.BodyProvider;

import static xyz.srgnis.bodyhealthsystem.BHSMain.id;

public class ClientNetworking {

    public static void initialize(){
        ClientPlayNetworking.registerGlobalReceiver(BHSMain.MOD_IDENTIFIER, ClientNetworking::handleHealthChange);
        ClientPlayNetworking.registerGlobalReceiver(id("data_request"), ClientNetworking::updateEntity);
    }

    private static void updateEntity(MinecraftClient client, ClientPlayNetworkHandler clientPlayNetworkHandler, PacketByteBuf buf, PacketSender packetSender) {
        Entity entity = client.player.getWorld().getEntityById(buf.readInt());
        while (buf.isReadable()) {
            Identifier id = buf.readIdentifier();
            float health = buf.readFloat();
            float maxhealth = buf.readFloat();

            //TODO: Add config sync at join and remove setMaxHealth on ClientNetworking.handleHealthChange
            client.execute(() -> ((BodyProvider) entity).getBody().getPart(id).setMaxHealth(maxhealth));
            client.execute(() -> ((BodyProvider) entity).getBody().getPart(id).setHealth(health));
        }
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

    public static void requestBodyData(LivingEntity entity){
        PacketByteBuf buf = PacketByteBufs.create();

        buf.writeInt(entity.getId());

        ClientPlayNetworking.send(id("data_request"), buf);
    }
}
