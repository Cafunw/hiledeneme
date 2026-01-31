package com.hackclient.module.impl.render;

import com.hackclient.module.Module;

public class FullBright extends Module {
    private double oldGamma;

    public FullBright() {
        super("FullBright", "Makes the game brighter", Category.RENDER);
    }

    @Override
    public void onEnable() {
        oldGamma = mc.options.getGamma().getValue();
        mc.options.getGamma().setValue(100.0);
    }

    @Override
    public void onDisable() {
        mc.options.getGamma().setValue(oldGamma);
    }
}
