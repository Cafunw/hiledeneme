package com.hackclient.setting;

import java.util.Arrays;
import java.util.List;

public class ModeSetting extends Setting<String> {
    private List<String> modes;
    private int index;

    public ModeSetting(String name, String defaultMode, String... modes) {
        super(name, defaultMode);
        this.modes = Arrays.asList(modes);
        this.index = this.modes.indexOf(defaultMode);
    }

    public List<String> getModes() {
        return modes;
    }

    public void setMode(String mode) {
        if (modes.contains(mode)) {
            setValue(mode);
            index = modes.indexOf(mode);
        }
    }

    public void cycle() {
        index++;
        if (index >= modes.size()) {
            index = 0;
        }
        setValue(modes.get(index));
    }
}
