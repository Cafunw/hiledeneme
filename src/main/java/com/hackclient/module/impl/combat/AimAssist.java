package com.hackclient.module.impl.combat;

import com.hackclient.module.Module;

public class AimAssist extends Module {
    public AimAssist() {
        super("AimAssist", "Automatically aims at entities", Category.COMBAT);
    }

    @Override
    public void onTick() {
        if (mc.player == null || mc.world == null)
            return;

        net.minecraft.entity.Entity target = null;
        double minDst = 999;

        for (net.minecraft.entity.Entity entity : mc.world.getEntities()) {
            if (entity instanceof net.minecraft.entity.LivingEntity && entity != mc.player) {
                double dst = mc.player.distanceTo(entity);
                if (dst < minDst && dst <= 4) {
                    minDst = dst;
                    target = entity;
                }
            }
        }

        if (target != null) {
            double dx = target.getX() - mc.player.getX();
            double dy = (target.getY() + target.getEyeHeight(target.getPose()))
                    - (mc.player.getY() + mc.player.getEyeHeight(mc.player.getPose()));
            double dz = target.getZ() - mc.player.getZ();
            double dist = Math.sqrt(dx * dx + dz * dz);

            float yaw = (float) (Math.atan2(dz, dx) * 180.0D / Math.PI) - 90.0F;
            float pitch = (float) -(Math.atan2(dy, dist) * 180.0D / Math.PI);

            mc.player.setYaw(yaw);
            mc.player.setPitch(pitch);
        }
    }
}
