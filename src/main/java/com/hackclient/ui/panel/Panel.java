package com.hackclient.ui.panel;

import com.hackclient.Client;
import com.hackclient.module.Module;
import net.minecraft.client.gui.DrawContext;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Panel extends Component {
    private String title;
    public int x, y, width, height;
    private boolean open = true;
    private boolean dragging = false;
    private int dragX, dragY;
    private List<Module> modules = new ArrayList<>();

    // A esthetic Colors
    private final int HEADER_COLOR = new Color(30, 30, 30, 230).getRGB();
    private final int BG_COLOR = new Color(20, 20, 20, 200).getRGB();
    private final int TEXT_COLOR = new Color(255, 255, 255).getRGB();
    private final int ENABLED_COLOR = new Color(138, 43, 226).getRGB(); // BlueViolet
    private final int HOVER_COLOR = new Color(50, 50, 50, 180).getRGB();

    public Panel(String title, int x, int y, int width, int height) {
        this.title = title;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        for (Module module : Client.getModuleManager().getModules()) {
            if (module.getCategory().name().equalsIgnoreCase(title)) {
                modules.add(module);
            }
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // Dragging Logic
        if (dragging) {
            x = mouseX - dragX;
            y = mouseY - dragY;
        }

        // --- Render Header ---
        context.fill(x, y, x + width, y + height, HEADER_COLOR);

        // Header Border (Top line accent)
        context.fill(x, y, x + width, y + 2, ENABLED_COLOR);

        context.drawText(Client.mc.textRenderer, title, x + 6, y + height / 2 - 4, TEXT_COLOR, false);

        // Expand/Collapse Icon (Simple +/-)
        String icon = open ? "-" : "+";
        context.drawText(Client.mc.textRenderer, icon, x + width - 12, y + height / 2 - 4, TEXT_COLOR, false);

        if (open) {
            int yOffset = height;
            for (Module module : modules) {
                // Background
                context.fill(x, y + yOffset, x + width, y + yOffset + 14, BG_COLOR);

                // Hover Effect
                if (isHovered(mouseX, mouseY, x, y + yOffset, width, 14)) {
                    context.fill(x, y + yOffset, x + width, y + yOffset + 14, HOVER_COLOR);
                }

                // Module State Color
                int textColor = module.isEnabled() ? ENABLED_COLOR : new Color(170, 170, 170).getRGB();

                // Indent enabled modules slightly for visual cue
                int xOffset = module.isEnabled() ? 6 : 4;
                context.drawText(Client.mc.textRenderer, module.getName(), x + xOffset, y + yOffset + 3, textColor,
                        false);

                yOffset += 14;
            }
            // Bottom Border
            context.fill(x, y + yOffset, x + width, y + yOffset + 1, ENABLED_COLOR);
        }
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int button) {
        // Build 1.21.4'te koordinatlar double gelip int'e cast ediliyor olabilir ama
        // double çalışır.

        // Header Click Check
        if (isHovered(mouseX, mouseY, x, y, width, height)) {
            if (button == 0) { // Left Click -> Drag
                dragging = true;
                dragX = (int) mouseX - x;
                dragY = (int) mouseY - y;
            } else if (button == 1) { // Right Click -> Toggle Open
                open = !open;
            }
            return;
        }

        if (open) {
            int yOffset = height;
            for (Module module : modules) {
                if (isHovered(mouseX, mouseY, x, y + yOffset, width, 14)) {
                    if (button == 0) {
                        module.toggle();
                    }
                }
                yOffset += 14;
            }
        }
    }

    @Override
    public void mouseReleased(double mouseX, double mouseY, int button) {
        dragging = false;
    }

    // Helper to check hover
    private boolean isHovered(double mouseX, double mouseY, int x, int y, int width, int height) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }
}
