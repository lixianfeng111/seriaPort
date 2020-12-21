package com.licheedev.serialtool.comn.event;

public class IsCoveringEvent {
    private boolean isCovering;

    public IsCoveringEvent(boolean isCovering) {
        this.isCovering = isCovering;
    }

    public boolean isCovering() {
        return isCovering;
    }

    public void setCovering(boolean covering) {
        isCovering = covering;
    }
}
