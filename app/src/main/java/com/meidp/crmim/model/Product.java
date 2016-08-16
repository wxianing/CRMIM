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
    /**
     * PYShort : SB
     * BarCode : 9787301106419
     * TypeID : 4
     * BigType : 1
     * UnitID : 2
     * Brand : 79
     * Specification : fds
     * Size : null
     * ABCType : A
     * Creator : 2
     * CreateDate : 2016-07-12T00:00:00
     * UsedStatus : 1
     * ImgUrl : null
     * Description : null
     * SellPrice : 60
     * SaleUnitID : 2
     */

    private String PYShort;
    private String BarCode;
    private int TypeID;
    private String BigType;
    private int UnitID;
    private int Brand;
    private String Specification;
    private Object Size;
    private String ABCType;
    private int Creator;
    private String CreateDate;
    private String UsedStatus;
    private Object ImgUrl;
    private Object Description;
    private int SellPrice;
    private int SaleUnitID;

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

    public String getPYShort() {
        return PYShort;
    }

    public void setPYShort(String PYShort) {
        this.PYShort = PYShort;
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

    public String getSpecification() {
        return Specification;
    }

    public void setSpecification(String Specification) {
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

    public int getSellPrice() {
        return SellPrice;
    }

    public void setSellPrice(int SellPrice) {
        this.SellPrice = SellPrice;
    }

    public int getSaleUnitID() {
        return SaleUnitID;
    }

    public void setSaleUnitID(int SaleUnitID) {
        this.SaleUnitID = SaleUnitID;
    }
}
