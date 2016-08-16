package com.meidp.crmim.model;

import java.io.Serializable;

/**
 * Package： com.meidp.crmim.model
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/8/15
 */
public class PrototypePrepares implements Serializable {

    /**
     * ID : 1
     * OrderNo : sample string 2
     * SortNo : 1
     * ProductID : 1
     * ProductCount : 1.0
     * UnitID : 1
     * UnitPrice : 1.0
     * totalPrice : 1.0
     * Discount : 1.0
     * SendTime : 1
     * Package : 1
     * Remark : sample string 3
     * SendCount : 1.0
     * PlanProductCount : 1.0
     * UseStockCount : 1.0
     * ModifiedDate : 2016-08-15 19:34:02
     * ModifiedUserID : sample string 4
     * CompanyCD : sample string 5
     * TaxPrice : 1.0
     * TaxRate : 1.0
     * TotalFee : 1.0
     * TotalTax : 1.0
     * UsedUnitID : 1
     * UsedUnitCount : 1.0
     * UsedPrice : 1.0
     * ExRate : 1.0
     */

    private int ID;
    private String OrderNo;
    private int SortNo;
    private int ProductID;
    private double ProductCount;
    private int UnitID;
    private double UnitPrice;
    private double totalPrice;
    private double Discount;
    private int SendTime;
    private int Package;
    private String Remark;
    private double SendCount;
    private double PlanProductCount;
    private double UseStockCount;
    private String ModifiedDate;
    private String ModifiedUserID;
    private String CompanyCD;
    private double TaxPrice;
    private double TaxRate;
    private double TotalFee;
    private double TotalTax;
    private int UsedUnitID;
    private double UsedUnitCount;
    private double UsedPrice;
    private double ExRate;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getOrderNo() {
        return OrderNo;
    }

    public void setOrderNo(String OrderNo) {
        this.OrderNo = OrderNo;
    }

    public int getSortNo() {
        return SortNo;
    }

    public void setSortNo(int SortNo) {
        this.SortNo = SortNo;
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

    public double getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(double UnitPrice) {
        this.UnitPrice = UnitPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getDiscount() {
        return Discount;
    }

    public void setDiscount(double Discount) {
        this.Discount = Discount;
    }

    public int getSendTime() {
        return SendTime;
    }

    public void setSendTime(int SendTime) {
        this.SendTime = SendTime;
    }

    public int getPackage() {
        return Package;
    }

    public void setPackage(int Package) {
        this.Package = Package;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
    }

    public double getSendCount() {
        return SendCount;
    }

    public void setSendCount(double SendCount) {
        this.SendCount = SendCount;
    }

    public double getPlanProductCount() {
        return PlanProductCount;
    }

    public void setPlanProductCount(double PlanProductCount) {
        this.PlanProductCount = PlanProductCount;
    }

    public double getUseStockCount() {
        return UseStockCount;
    }

    public void setUseStockCount(double UseStockCount) {
        this.UseStockCount = UseStockCount;
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

    public String getCompanyCD() {
        return CompanyCD;
    }

    public void setCompanyCD(String CompanyCD) {
        this.CompanyCD = CompanyCD;
    }

    public double getTaxPrice() {
        return TaxPrice;
    }

    public void setTaxPrice(double TaxPrice) {
        this.TaxPrice = TaxPrice;
    }

    public double getTaxRate() {
        return TaxRate;
    }

    public void setTaxRate(double TaxRate) {
        this.TaxRate = TaxRate;
    }

    public double getTotalFee() {
        return TotalFee;
    }

    public void setTotalFee(double TotalFee) {
        this.TotalFee = TotalFee;
    }

    public double getTotalTax() {
        return TotalTax;
    }

    public void setTotalTax(double TotalTax) {
        this.TotalTax = TotalTax;
    }

    public int getUsedUnitID() {
        return UsedUnitID;
    }

    public void setUsedUnitID(int UsedUnitID) {
        this.UsedUnitID = UsedUnitID;
    }

    public double getUsedUnitCount() {
        return UsedUnitCount;
    }

    public void setUsedUnitCount(double UsedUnitCount) {
        this.UsedUnitCount = UsedUnitCount;
    }

    public double getUsedPrice() {
        return UsedPrice;
    }

    public void setUsedPrice(double UsedPrice) {
        this.UsedPrice = UsedPrice;
    }

    public double getExRate() {
        return ExRate;
    }

    public void setExRate(double ExRate) {
        this.ExRate = ExRate;
    }
}
