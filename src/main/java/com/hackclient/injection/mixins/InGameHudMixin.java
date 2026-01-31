package com.hackclient.injection.mixins;

import com.hackclient.Client;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {
    @Inject(method = "render", at = @At("TAIL"))
    public void render(DrawContext context, net.minecraft.client.render.RenderTickCounter tickCounter,
            CallbackInfo ci) {
        if (Client.getHUD() != null) {
            Client.getHUD().draw(context);
        }
    }
}
