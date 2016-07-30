package com.meidp.crmim.model;

import java.io.Serializable;

/**
 * Package： com.meidp.crmim.model
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/7/29
 */
public class CostEntrity implements Serializable {

    /**
     * ID : 1
     * ExpID : 1
     * SortNo : 1
     * ExpType : 1
     * Amount : 1.0
     * ExpRemark : sample string 2
     */

    private int ID;
    private int ExpID;
    private int SortNo;
    private int ExpType;
    private double Amount;
    private String ExpRemark;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getExpID() {
        return ExpID;
    }

    public void setExpID(int ExpID) {
        this.ExpID = ExpID;
    }

    public int getSortNo() {
        return SortNo;
    }

    public void setSortNo(int SortNo) {
        this.SortNo = SortNo;
    }

    public int getExpType() {
        return ExpType;
    }

    public void setExpType(int ExpType) {
        this.ExpType = ExpType;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double Amount) {
        this.Amount = Amount;
    }

    public String getExpRemark() {
        return ExpRemark;
    }

    public void setExpRemark(String ExpRemark) {
        this.ExpRemark = ExpRemark;
    }
}
