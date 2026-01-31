package com.hackclient.module.impl.movement;

import com.hackclient.module.Module;

public class Fly extends Module {
    public Fly() {
        super("Fly", "Allows you to fly in survival mode", Category.MOVEMENT);
    }

    @Override
    public void onTick() {
        if (mc.player != null) {
            mc.player.getAbilities().flying = true;
            mc.player.getAbilities().allowFlying = true;
        }
    }

    @Override
    public void onDisable() {
        if (mc.player != null) {
            mc.player.getAbilities().flying = false;
            mc.player.getAbilities().allowFlying = false;
        }
    }
}
