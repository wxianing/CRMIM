package com.meidp.crmim.model;

import java.io.Serializable;

/**
 * Package： com.meidp.crmim.model
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/8/12
 */
public class ProduceEntitys implements Serializable {

    /**
     * ID : 1
     * CompanyCD : sample string 2
     * ExhibitionNo : sample string 3
     * ProductID : 1
     * ProductCount : 1.0
     * UnitID : 1
     * TotalFee : 1.0
     * Remark : sample string 4
     * ModifiedDate : 2016-08-12 18:41:21
     * ModifiedUserID : sample string 5
     * UsedPrice : 1.0
     * ExhibitionId : 6
     */

    private int ID;
    private String CompanyCD;
    private String ExhibitionNo;
    private int ProductID;
    private double ProductCount;
    private int UnitID;
    private double TotalFee;
    private String Remark;
    private String ModifiedDate;
    private String ModifiedUserID;
    private double UsedPrice;
    private int ExhibitionId;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCompanyCD() {
        return CompanyCD;
    }

    public void setCompanyCD(String CompanyCD) {
        this.CompanyCD = CompanyCD;
    }

    public String getExhibitionNo() {
        return ExhibitionNo;
    }

    public void setExhibitionNo(String ExhibitionNo) {
        this.ExhibitionNo = ExhibitionNo;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    public double getProductCount() {
        return ProductCount;
    }

    public void setProductCount(double ProductCount) {
        this.ProductCount = ProductCount;
    }

    public int getUnitID() {
        return UnitID;
    }

    public void setUnitID(int UnitID) {
        this.UnitID = UnitID;
    }

    public double getTotalFee() {
        return TotalFee;
    }

    public void setTotalFee(double TotalFee) {
        this.TotalFee = TotalFee;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
    }

    public String getModifiedDate() {
        return ModifiedDate;
    }

    public void setModifiedDate(String ModifiedDate) {
        this.ModifiedDate = ModifiedDate;
    }

    public String getModifiedUserID() {
        return ModifiedUserID;
    }

    public void setModifiedUserID(String ModifiedUserID) {
        this.ModifiedUserID = ModifiedUserID;
    }

    public double getUsedPrice() {
        return UsedPrice;
    }

    public void setUsedPrice(double UsedPrice) {
        this.UsedPrice = UsedPrice;
    }

    public int getExhibitionId() {
        return ExhibitionId;
    }

    public void setExhibitionId(int ExhibitionId) {
        this.ExhibitionId = ExhibitionId;
    }
}
