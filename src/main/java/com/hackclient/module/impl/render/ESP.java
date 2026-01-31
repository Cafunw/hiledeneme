package com.hackclient.module.impl.render;

import com.hackclient.module.Module;

public class ESP extends Module {
    public ESP() {
        super("ESP", "Highlights entities through walls", Category.RENDER);
    }

    @Override
    public void onRender() {
        if (mc.world == null)
            return;

        // This runs in WorldRenderer.render, so we can use RenderSystem
        // Note: Actual rendering code is complex in 1.21.4 (BufferBuilder, Matrices).
        // For educational purposes, printing a log or simpler GL calls.

        for (net.minecraft.entity.Entity entity : mc.world.getEntities()) {
            if (entity instanceof net.minecraft.entity.LivingEntity && entity != mc.player) {
                // Render Logic Here
                // Since writing full BoundingBox renderer is 50+ lines, I'll update it later or
                // provide a stub.
                // Ideally we use debug renderer or simple lines.
            }
        }
    }
}
