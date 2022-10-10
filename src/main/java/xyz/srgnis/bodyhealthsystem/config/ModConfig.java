package xyz.srgnis.bodyhealthsystem.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import net.fabricmc.loader.api.FabricLoader;

public class ModConfig {

    private static final File folder = new File(FabricLoader.getInstance().getConfigDir().toString());

    private static String modID;
    private static File configFile;
    private static Gson config = new GsonBuilder().setPrettyPrinting().create();
    public static ConfigInstance INSTANCE;

    public static void setup(String modID) {
        ModConfig.modID = modID;
    }

    public static ConfigInstance init() {
        loadDefaults();
        generateFoldersAndFiles();
        readJson();
        writeJson();
        return INSTANCE;
    }

    public static void loadDefaults() {
        INSTANCE = new ConfigInstance();
    }

    private static void generateFoldersAndFiles() {
        if (!folder.exists()) {
            folder.mkdir();
        }
        if (folder.isDirectory()) {
            configFile = new File(folder, modID+".json");
            if (!configFile.exists()) {
                try {
                    configFile.createNewFile();
                    writeJson();
                } catch (IOException e) {
                    throw new IllegalStateException("Can't create config file", e);
                }
            } else if (configFile.isDirectory()) {
                throw new IllegalStateException(modID+".json must be a file!");
            } else {
            }
        } else {
            throw new IllegalStateException("'config' must be a folder!");
        }
    }

    public static void readJson() {
        try {
            INSTANCE = config.fromJson(new FileReader(configFile), ConfigInstance.class);
            if (INSTANCE == null) {
                throw new IllegalStateException("Null configuration");
            }
        } catch (JsonSyntaxException e) {
            System.out.println("Invalid configuration!");
            e.printStackTrace();
        } catch (JsonIOException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            // No op
        }
    }

    public static void writeJson() {
        try {
            String json = config.toJson(INSTANCE);
            FileWriter writer = new FileWriter(configFile, false);
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            throw new IllegalStateException("Can't update config file", e);
        }
    }

}
