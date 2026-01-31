package com.hackclient.injection.mixins;

import com.hackclient.Client;
import net.minecraft.client.Keyboard;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public class KeyboardMixin {
    @Inject(method = "onKey", at = @At("HEAD"))
    public void onKey(long window, int key, int scancode, int action, int modifiers, CallbackInfo ci) {
        if (action == GLFW.GLFW_PRESS) {
            if (Client.getModuleManager() != null) {
                Client.getModuleManager().onKeyPressed(key);
            }

            if (key == GLFW.GLFW_KEY_RIGHT_SHIFT) {
                if (Client.getClickGUI() != null) {
                    Client.mc.setScreen(Client.getClickGUI());
                }
            }
        }
    }
}
