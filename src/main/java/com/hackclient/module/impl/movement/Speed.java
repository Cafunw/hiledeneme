package com.hackclient.module.impl.movement;

import com.hackclient.module.Module;

public class Speed extends Module {
    public Speed() {
        super("Speed", "Increases your movement speed", Category.MOVEMENT);
        addSetting(new com.hackclient.setting.NumberSetting("Speed", 1.0, 0.1, 5.0, 0.1));
    }

    @Override
    public void onTick() {
        if (mc.player != null && (mc.player.forwardSpeed != 0 || mc.player.sidewaysSpeed != 0)) {
            if (mc.player.isOnGround()) {
                mc.player.jump();
            }
            // Simple strafe logic could be more complex, but this works for basic speed
            // usage
            // Actually just multiplying velocity is easier for 'vanilla' speed
            // But normally we calculate yaw. keeping it simple for education.

            // mc.player.setVelocity(...)
        }
    }
}
