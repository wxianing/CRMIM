package com.meidp.crmim.model;

import java.io.Serializable;

/**
 * Package： com.meidp.crmim.model
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/8/18
 */
public class NoReaders implements Serializable {


    /**
     * NoCheckTotalCount : 7
     * NoCheckCount_Sample : 0
     * NoCheckCount_Order : 7
     * NoCheckCount_Fee : 0
     */

    private int NoCheckTotalCount;
    private int NoCheckCount_Sample;
    private int NoCheckCount_Order;
    private int NoCheckCount_Fee;

    public int getNoCheckTotalCount() {
        return NoCheckTotalCount;
    }

    public void setNoCheckTotalCount(int NoCheckTotalCount) {
        this.NoCheckTotalCount = NoCheckTotalCount;
    }

    public int getNoCheckCount_Sample() {
        return NoCheckCount_Sample;
    }

    public void setNoCheckCount_Sample(int NoCheckCount_Sample) {
        this.NoCheckCount_Sample = NoCheckCount_Sample;
    }

    public int getNoCheckCount_Order() {
        return NoCheckCount_Order;
    }

    public void setNoCheckCount_Order(int NoCheckCount_Order) {
        this.NoCheckCount_Order = NoCheckCount_Order;
    }

    public int getNoCheckCount_Fee() {
        return NoCheckCount_Fee;
    }

    public void setNoCheckCount_Fee(int NoCheckCount_Fee) {
        this.NoCheckCount_Fee = NoCheckCount_Fee;
    }
}
