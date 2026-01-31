package com.hackclient;

import com.hackclient.module.ModuleManager;
import com.hackclient.ui.ClickGUI;
import com.hackclient.ui.HUD;
import net.minecraft.client.MinecraftClient;

public class Client {
    public static final String NAME = "HackClient";
    public static final String VERSION = "1.0";
    public static final MinecraftClient mc = MinecraftClient.getInstance();

    private static ModuleManager moduleManager;
    private static ClickGUI clickGUI;
    private static HUD hud;
    private static com.hackclient.event.EventManager eventManager;

    public static void init() {
        System.out.println("[" + NAME + "] Initializing...");

        eventManager = new com.hackclient.event.EventManager();
        moduleManager = new ModuleManager();
        clickGUI = new ClickGUI();
        hud = new HUD();

        // Modülleri kaydet
        moduleManager.registerModules();

        System.out.println("[" + NAME + "] Initialized successfully!");
    }

    public static ModuleManager getModuleManager() {
        return moduleManager;
    }

    public static ClickGUI getClickGUI() {
        return clickGUI;
    }

    public static HUD getHUD() {
        return hud;
    }

    public static com.hackclient.event.EventManager getEventManager() {
        return eventManager;
    }
}