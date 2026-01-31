package com.hackclient.module.impl.combat;

import com.hackclient.module.Module;

public class KillAura extends Module {
    public KillAura() {
        super("KillAura", "Automatically attacks entities around you", Category.COMBAT);
        addSetting(new com.hackclient.setting.NumberSetting("Range", 4, 3, 6, 0.1));
    }

    @Override
    public void onTick() {
        if (mc.player == null || mc.world == null)
            return;

        for (net.minecraft.entity.Entity entity : mc.world.getEntities()) {
            if (entity instanceof net.minecraft.entity.LivingEntity && entity != mc.player) {
                if (mc.player.distanceTo(entity) <= 4) { // Use setting here later
                    if (((net.minecraft.entity.LivingEntity) entity).getHealth() > 0) {
                        mc.interactionManager.attackEntity(mc.player, entity);
                        mc.player.swingHand(net.minecraft.util.Hand.MAIN_HAND);
                        return; // Attack one at a time
                    }
                }
            }
        }
    }
}
