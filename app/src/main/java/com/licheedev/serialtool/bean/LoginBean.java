package com.licheedev.serialtool.bean;

import com.licheedev.serialtool.base.BaseEntity;

public class LoginBean extends BaseEntity {

    /**
     * name : jk002
     * roleLevel : 1
     * username : 网二
     */

    private String name;
    private int roleLevel;
    private String username;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRoleLevel() {
        return roleLevel;
    }

    public void setRoleLevel(int roleLevel) {
        this.roleLevel = roleLevel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
