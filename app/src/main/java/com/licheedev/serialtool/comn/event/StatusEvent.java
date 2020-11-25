package com.licheedev.serialtool.comn.event;

public class StatusEvent {
    private int statu;

    public StatusEvent(int statu) {
        this.statu = statu;
    }

    public int getStatu() {
        return statu;
    }

    public void setStatu(int statu) {
        this.statu = statu;
    }
}
