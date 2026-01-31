package com.hackclient.file;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hackclient.Client;
import com.hackclient.module.Module;
import com.hackclient.setting.BooleanSetting;
import com.hackclient.setting.ModeSetting;
import com.hackclient.setting.NumberSetting;
import com.hackclient.setting.Setting;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class ConfigManager {
    private final File configFile;
    private final Gson gson;

    public ConfigManager() {
        File dir = new File(Client.mc.runDirectory, "HackClient");
        if (!dir.exists())
            dir.mkdirs();
        configFile = new File(dir, "config.json");
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public void save() {
        JsonObject json = new JsonObject();
        for (Module module : Client.getModuleManager().getModules()) {
            JsonObject moduleJson = new JsonObject();
            moduleJson.addProperty("enabled", module.isEnabled());
            moduleJson.addProperty("key", module.getKey());

            JsonObject settingsJson = new JsonObject();
            for (Setting setting : module.getSettings()) {
                if (setting instanceof BooleanSetting) {
                    settingsJson.addProperty(setting.getName(), (Boolean) setting.getValue());
                } else if (setting instanceof NumberSetting) {
                    settingsJson.addProperty(setting.getName(), (Number) setting.getValue());
                } else if (setting instanceof ModeSetting) {
                    settingsJson.addProperty(setting.getName(), (String) setting.getValue());
                }
            }
            moduleJson.add("settings", settingsJson);
            json.add(module.getName(), moduleJson);
        }

        try (FileWriter writer = new FileWriter(configFile)) {
            gson.toJson(json, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load() {
        if (!configFile.exists())
            return;

        try (FileReader reader = new FileReader(configFile)) {
            JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();

            for (Map.Entry<String, JsonElement> entry : json.entrySet()) {
                Module module = Client.getModuleManager().getModule(entry.getKey());
                if (module != null) {
                    JsonObject moduleJson = entry.getValue().getAsJsonObject();
                    if (moduleJson.has("enabled") && moduleJson.get("enabled").getAsBoolean()) {
                        module.setEnabled(true);
                    }
                    if (moduleJson.has("key")) {
                        module.setKey(moduleJson.get("key").getAsInt());
                    }

                    if (moduleJson.has("settings")) {
                        JsonObject settingsJson = moduleJson.get("settings").getAsJsonObject();
                        for (Setting setting : module.getSettings()) {
                            if (settingsJson.has(setting.getName())) {
                                if (setting instanceof BooleanSetting) {
                                    ((BooleanSetting) setting)
                                            .setValue(settingsJson.get(setting.getName()).getAsBoolean());
                                } else if (setting instanceof NumberSetting) {
                                    ((NumberSetting) setting)
                                            .setValue(settingsJson.get(setting.getName()).getAsDouble());
                                } else if (setting instanceof ModeSetting) {
                                    ((ModeSetting) setting).setMode(settingsJson.get(setting.getName()).getAsString());
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
