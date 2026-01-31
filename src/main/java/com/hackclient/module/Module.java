package com.hackclient.module;

import com.hackclient.Client;
import com.hackclient.setting.Setting;
import net.minecraft.client.MinecraftClient;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Module {
    protected final MinecraftClient mc = MinecraftClient.getInstance();
    private String name;
    private String description;
    private Category category;
    private int key;
    private boolean enabled;

    public Module(String name, String description, Category category) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.key = 0;
    }

    public void toggle() {
        enabled = !enabled;
        if (enabled) {
            onEnable();
        } else {
            onDisable();
        }
    }

    public void onEnable() {
        Client.getEventManager().register(this);
    }

    public void onDisable() {
        Client.getEventManager().unregister(this);
    }

    public void onTick() {
    }

    public void onRender() {
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        if (enabled)
            onEnable();
        else
            onDisable();
    }

    public enum Category {
        COMBAT, MOVEMENT, RENDER
    }

    private List<Setting> settings = new ArrayList<>();

    public void addSetting(Setting setting) {
        settings.add(setting);
    }

    public void addSettings(Setting... settings) {
        this.settings.addAll(Arrays.asList(settings));
    }

    public List<Setting> getSettings() {
        return settings;
    }
}
