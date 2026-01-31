package com.hackclient.ui;

import com.hackclient.Client;
import com.hackclient.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

import java.awt.Color;
import java.util.Comparator;
import java.util.List;

public class HUD {
    private MinecraftClient mc = MinecraftClient.getInstance();

    public void draw(DrawContext context) {

        context.drawTextWithShadow(mc.textRenderer, Client.NAME + " v" + Client.VERSION, 4, 4, -1);

        int y = 14;
        List<Module> modules = Client.getModuleManager().getModules();

        modules.sort(Comparator.comparingInt(m -> -mc.textRenderer.getWidth(m.getName())));

        for (Module module : modules) {
            if (module.isEnabled()) {
                context.drawTextWithShadow(mc.textRenderer, module.getName(),
                        context.getScaledWindowWidth() - mc.textRenderer.getWidth(module.getName()) - 4, y,
                        new Color(0, 255, 255).getRGB());
                y += 10;
            }
        }
    }
}
