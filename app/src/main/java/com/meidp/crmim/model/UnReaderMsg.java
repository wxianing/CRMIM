package com.meidp.crmim.model;

import java.io.Serializable;

/**
 * Package： com.meidp.crmim.model
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/8/5
 */
public class UnReaderMsg implements Serializable {

    /**
     * NewsCount : 3
     * NoCheckCount : 0
     */

    private int NewsCount;
    private int NoCheckCount;

    public int getNewsCount() {
        return NewsCount;
    }

    public void setNewsCount(int NewsCount) {
        this.NewsCount = NewsCount;
    }

    public int getNoCheckCount() {
        return NoCheckCount;
    }

    public void setNoCheckCount(int NoCheckCount) {
        this.NoCheckCount = NoCheckCount;
    }
}
