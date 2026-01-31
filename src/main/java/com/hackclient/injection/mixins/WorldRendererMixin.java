package com.hackclient.injection.mixins;

import com.hackclient.Client;
import com.hackclient.module.Module;
import com.hackclient.event.impl.RenderEvent;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.WorldRenderer;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {
    @Inject(method = "render", at = @At("TAIL"))
    public void render(net.minecraft.client.util.ObjectAllocator allocator,
            net.minecraft.client.render.RenderTickCounter tickCounter, boolean renderBlockOutline,
            Camera camera, GameRenderer gameRenderer, Matrix4f matrix4f,
            Matrix4f matrix4f2, CallbackInfo ci) {
        Client.getModuleManager().getModules().stream().filter(m -> m.isEnabled()).forEach(Module::onRender);
        // Dispatch RenderEvent if needed, or just call modules directly
    }
}
