package com.hackclient.module;

import com.hackclient.module.impl.combat.AimAssist;
import com.hackclient.module.impl.combat.KillAura;
import com.hackclient.module.impl.combat.Reach;
import com.hackclient.module.impl.movement.Fly;
import com.hackclient.module.impl.movement.NoFall;
import com.hackclient.module.impl.movement.Speed;
import com.hackclient.module.impl.render.ESP;
import com.hackclient.module.impl.render.FullBright;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {
    private List<Module> modules = new ArrayList<>();

    public ModuleManager() {
        // Empty constructor, modules registered in registerModules
    }

    public void registerModules() {
        modules.add(new KillAura());
        modules.add(new Reach());
        modules.add(new AimAssist());
        modules.add(new Fly());
        modules.add(new Speed());
        modules.add(new NoFall());
        modules.add(new ESP());
        modules.add(new FullBright());
    }

    public List<Module> getModules() {
        return modules;
    }

    public Module getModule(String name) {
        return modules.stream().filter(m -> m.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public void onTick() {
        for (Module module : modules) {
            if (module.isEnabled()) {
                module.onTick();
            }
        }
    }

    public void onKeyPressed(int key) {
        for (Module module : modules) {
            if (module.getKey() == key) {
                module.toggle();
            }
        }
    }
}
