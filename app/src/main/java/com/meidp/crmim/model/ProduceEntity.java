package com.meidp.crmim.model;

import java.io.Serializable;

/**
 * Package： com.meidp.crmim.model
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/7/29
 */
public class ProduceEntity implements Serializable {

    /**
     * ID : 519
     * PYShort : YYXSQ
     * ProductName : 医用显示器
     * BarCode : 333333332222
     * TypeID : 3
     * BigType : 1
     * UnitID : 64
     * Brand : 79
     * Specification : null
     * Size : null
     * ABCType : B
     * Remark : null
     * Creator : 2
     * CreateDate : 2016-07-12T00:00:00
     * UsedStatus : 1
     * ImgUrl : null
     * Description : null
     * SellPrice : null
     * SaleUnitID : 64
     */

    private int ID;
    private String PYShort;
    private String ProductName;
    private String BarCode;
    private int TypeID;
    private String BigType;
    private int UnitID;
    private int Brand;
    private String ABCType;
    private int Creator;
    private String CreateDate;
    private String UsedStatus;
    private int SaleUnitID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getPYShort() {
        return PYShort;
    }

    public void setPYShort(String PYShort) {
        this.PYShort = PYShort;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public String getBarCode() {
        return BarCode;
    }

    public void setBarCode(String BarCode) {
        this.BarCode = BarCode;
    }

    public int getTypeID() {
        return TypeID;
    }

    public void setTypeID(int TypeID) {
        this.TypeID = TypeID;
    }

    public String getBigType() {
        return BigType;
    }

    public void setBigType(String BigType) {
        this.BigType = BigType;
    }

    public int getUnitID() {
        return UnitID;
    }

    public void setUnitID(int UnitID) {
        this.UnitID = UnitID;
    }

    public int getBrand() {
        return Brand;
    }

    public void setBrand(int Brand) {
        this.Brand = Brand;
    }

    public String getABCType() {
        return ABCType;
    }

    public void setABCType(String ABCType) {
        this.ABCType = ABCType;
    }

    public int getCreator() {
        return Creator;
    }

    public void setCreator(int Creator) {
        this.Creator = Creator;
    }

    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String CreateDate) {
        this.CreateDate = CreateDate;
    }

    public String getUsedStatus() {
        return UsedStatus;
    }

    public void setUsedStatus(String UsedStatus) {
        this.UsedStatus = UsedStatus;
    }

    public int getSaleUnitID() {
        return SaleUnitID;
    }

    public void setSaleUnitID(int SaleUnitID) {
        this.SaleUnitID = SaleUnitID;
    }
}
