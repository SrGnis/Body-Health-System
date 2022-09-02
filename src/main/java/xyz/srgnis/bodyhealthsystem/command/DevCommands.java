package xyz.srgnis.bodyhealthsystem.command;

import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import xyz.srgnis.bodyhealthsystem.body.player.PlayerBodyProvider;
import xyz.srgnis.bodyhealthsystem.network.ServerNetworking;

import static net.minecraft.server.command.CommandManager.literal;

public class DevCommands {

    public static void initialize(){
        CommandRegistrationCallback.EVENT.register(DevCommands::create_commands);
    }

    private static void create_commands(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess, CommandManager.RegistrationEnvironment environment) {
        dispatcher.register(literal("bhs_dev")
                .executes(context -> {
                    context.getSource().getPlayer().sendMessage(Text.literal(((PlayerBodyProvider)context.getSource().getPlayer()).getBody().toString()));
                    return 1;
                }).then(literal("reset").executes(context -> {
                    ((PlayerBodyProvider)context.getSource().getPlayer()).getBody().healAll();
                    ServerNetworking.syncBody(context.getSource().getPlayer());
                    return 1;
                })).then(literal("damage").executes(context -> {
                    ((PlayerBodyProvider)context.getSource().getPlayer()).getBody().applyDamageBySource(1, null);
                    ServerNetworking.syncBody(context.getSource().getPlayer());
                    return 1;
                })));
    }
}
