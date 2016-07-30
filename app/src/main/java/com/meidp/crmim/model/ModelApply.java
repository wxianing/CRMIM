package com.meidp.crmim.model;

import java.io.Serializable;

/**
 * Package： com.meidp.crmim.model
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/7/22
 */
public class ModelApply implements Serializable {

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
    private Object Specification;
    private Object Size;
    private String ABCType;
    private Object Remark;
    private int Creator;
    private String CreateDate;
    private String UsedStatus;
    private Object ImgUrl;
    private Object Description;
    private Object SellPrice;
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

    public Object getSpecification() {
        return Specification;
    }

    public void setSpecification(Object Specification) {
        this.Specification = Specification;
    }

    public Object getSize() {
        return Size;
    }

    public void setSize(Object Size) {
        this.Size = Size;
    }

    public String getABCType() {
        return ABCType;
    }

    public void setABCType(String ABCType) {
        this.ABCType = ABCType;
    }

    public Object getRemark() {
        return Remark;
    }

    public void setRemark(Object Remark) {
        this.Remark = Remark;
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

    public Object getImgUrl() {
        return ImgUrl;
    }

    public void setImgUrl(Object ImgUrl) {
        this.ImgUrl = ImgUrl;
    }

    public Object getDescription() {
        return Description;
    }

    public void setDescription(Object Description) {
        this.Description = Description;
    }

    public Object getSellPrice() {
        return SellPrice;
    }

    public void setSellPrice(Object SellPrice) {
        this.SellPrice = SellPrice;
    }

    public int getSaleUnitID() {
        return SaleUnitID;
    }

    public void setSaleUnitID(int SaleUnitID) {
        this.SaleUnitID = SaleUnitID;
    }
}
