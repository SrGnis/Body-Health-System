package xyz.srgnis.bodyhealthsystem.network;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import xyz.srgnis.bodyhealthsystem.BHSMain;
import xyz.srgnis.bodyhealthsystem.body.BodyPart;
import xyz.srgnis.bodyhealthsystem.body.player.BodyProvider;

public class ServerNetworking {

    public static void initialize(){
        ServerPlayConnectionEvents.JOIN.register(ServerNetworking::syncBody);
        ServerPlayNetworking.registerGlobalReceiver(BHSMain.MOD_IDENTIFIER, ServerNetworking::handleUseHealingItem);
    }

    //TODO: to much logic here
    private static void handleUseHealingItem(MinecraftServer minecraftServer, ServerPlayerEntity serverPlayerEntity, ServerPlayNetworkHandler serverPlayNetworkHandler, PacketByteBuf packetByteBuf, PacketSender packetSender) {
        Identifier partID = packetByteBuf.readIdentifier();
        if(((BodyProvider) serverPlayerEntity).getBody().getPart(partID).isDamaged()) {
            ((BodyProvider) serverPlayerEntity).getBody().healPart(4, partID);
            //TODO: syncBody call should be in healPart method?
            serverPlayerEntity.getMainHandStack().decrement(1);
            syncBody(serverPlayerEntity);
        }
    }

    public static void syncBody(ServerPlayNetworkHandler spnh, PacketSender packetSender, MinecraftServer server){
        syncBody(spnh.player);
    }

    public static void syncBody(PlayerEntity pe){
        PacketByteBuf buf = PacketByteBufs.create();

        for (BodyPart part : ((BodyProvider)pe).getBody().getParts()) {
            buf.writeIdentifier(part.getIdentifier());
            buf.writeFloat(part.getHealth());
            buf.writeFloat(part.getMaxHealth());
        }
        //Handled by ClientNetworking.handleHealthChange
        ServerPlayNetworking.send( (ServerPlayerEntity) pe, BHSMain.MOD_IDENTIFIER, buf);
    }
}
