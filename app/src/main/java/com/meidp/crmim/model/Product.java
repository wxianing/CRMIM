package com.meidp.crmim.model;

import java.io.Serializable;

/**
 * Package： com.meidp.crmim.model
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/7/29
 */
public class Product implements Serializable {


    /**
     * ID : 1
     * ProductID : 1
     * ProductName : sample string 2
     * ProductCount : 1.0
     * Remark : sample string 3
     * UsedPrice : 1.0
     * ProdNo : sample string 4
     */

    private int ID;
    private int ProductID;
    private String ProductName;
    private double ProductCount;
    private String Remark;
    private double UsedPrice;
    private String ProdNo;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public double getProductCount() {
        return ProductCount;
    }

    public void setProductCount(double ProductCount) {
        this.ProductCount = ProductCount;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
    }

    public double getUsedPrice() {
        return UsedPrice;
    }

    public void setUsedPrice(double UsedPrice) {
        this.UsedPrice = UsedPrice;
    }

    public String getProdNo() {
        return ProdNo;
    }

    public void setProdNo(String ProdNo) {
        this.ProdNo = ProdNo;
    }
}
