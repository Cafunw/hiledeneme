package com.hackclient.module.impl.movement;

import com.hackclient.module.Module;

public class NoFall extends Module {
    public NoFall() {
        super("NoFall", "Prevents fall damage", Category.MOVEMENT);
    }

    @Override
    public void onTick() {
        if (mc.player != null && mc.player.fallDistance > 2.5f) {
            mc.player.networkHandler
                    .sendPacket(new net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket.OnGroundOnly(true,
                            mc.player.horizontalCollision));
            // In 1.21.4 packet names might vary, keeping it generic or using OnGround flag.
            // Actually usually we just spoof the packet.
        }
    }
}
