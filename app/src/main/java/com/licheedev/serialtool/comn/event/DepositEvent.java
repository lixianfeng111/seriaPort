package com.licheedev.serialtool.comn.event;

public class DepositEvent {
    private String system_info;

    public DepositEvent(String system_info) {
        this.system_info = system_info;
    }

    public String getSystem_info() {
        return system_info;
    }

    public void setSystem_info(String system_info) {
        this.system_info = system_info;
    }
}
