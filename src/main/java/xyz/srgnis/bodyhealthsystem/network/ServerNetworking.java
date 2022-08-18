package xyz.srgnis.bodyhealthsystem.network;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import xyz.srgnis.bodyhealthsystem.BHSMain;
import xyz.srgnis.bodyhealthsystem.body.BodyPart;
import xyz.srgnis.bodyhealthsystem.body.player.PlayerBodyProvider;

public class ServerNetworking {

    public static void initialize(){
        ServerPlayConnectionEvents.JOIN.register(ServerNetworking::syncBody);
    }

    public static void syncBody(ServerPlayNetworkHandler spnh, PacketSender packetSender, MinecraftServer server){
        syncBody(spnh.player);
    }

    public static void syncBody(PlayerEntity pe){
        PacketByteBuf buf = PacketByteBufs.create();

        for (BodyPart part : ((PlayerBodyProvider)pe).getBody().getParts()) {
            buf.writeIdentifier(part.getIdentifier());
            buf.writeFloat(part.getHealth());
            buf.writeFloat(part.getMaxHealth());
        }
        //Todo: Use other identifier
        //Handled by ClientNetworking.handleHealthChange
        ServerPlayNetworking.send( (ServerPlayerEntity) pe, BHSMain.MOD_IDENTIFIER, buf);
    }
}
