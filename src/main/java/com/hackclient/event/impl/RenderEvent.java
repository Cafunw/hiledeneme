package com.hackclient.event.impl;

import com.hackclient.event.Event;

public class RenderEvent extends Event {
    private float partialTicks;

    public RenderEvent(float partialTicks) {
        this.partialTicks = partialTicks;
    }

    public float getPartialTicks() {
        return partialTicks;
    }
}
