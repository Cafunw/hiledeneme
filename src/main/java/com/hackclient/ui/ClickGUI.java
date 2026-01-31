package com.hackclient.ui;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import com.hackclient.module.Module;
import com.hackclient.ui.panel.Panel;
import java.util.ArrayList;
import java.util.List;

public class ClickGUI extends Screen {
    private List<Panel> panels = new ArrayList<>();

    public ClickGUI() {
        super(Text.of("ClickGUI"));
    }

    @Override
    protected void init() {
        super.init();
        if (panels.isEmpty()) {
            int x = 10;
            for (Module.Category category : Module.Category.values()) {
                panels.add(new Panel(category.name(), x, 10, 100, 15));
                x += 110;
            }
        }
    }

    @Override
    public void render(net.minecraft.client.gui.DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        for (Panel panel : panels) {
            panel.render(context, mouseX, mouseY, delta);
        }
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        for (Panel panel : panels) {
            panel.mouseClicked(mouseX, mouseY, button);
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        for (Panel panel : panels) {
            panel.mouseReleased(mouseX, mouseY, button);
        }
        return super.mouseReleased(mouseX, mouseY, button);
    }
}
