package com.meidp.crmim.model;

/**
 * @Package com.qianfeng.explistviewdemo.bean
 * @作 用:
 * @创 建 人: zhangwei
 * @日 期: 三月
 * @修 改 人:
 * @日 期:
 */
public class Child {
    private int icon;
    private String name;


    public Child(int icon, String name) {
        this.icon = icon;
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
