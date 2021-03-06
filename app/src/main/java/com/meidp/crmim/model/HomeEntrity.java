package com.meidp.crmim.model;

import java.io.Serializable;

/**
 * Package： com.meidp.crmim.model
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/7/8
 */
public class HomeEntrity implements Serializable {
    private int resId;
    private String resName;
    private int unReader;

    public HomeEntrity(int resId, String resName, int unReader) {
        this.resId = resId;
        this.resName = resName;
        this.unReader = unReader;
    }

    public int getUnReader() {
        return unReader;
    }

    public void setUnReader(int unReader) {
        this.unReader = unReader;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }
}
