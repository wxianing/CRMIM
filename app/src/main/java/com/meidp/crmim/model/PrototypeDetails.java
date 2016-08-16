package com.meidp.crmim.model;

import java.io.Serializable;
import java.util.List;

/**
 * Package： com.meidp.crmim.model
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/8/10
 */
public class PrototypeDetails implements Serializable {


    /**
     * details : [{"ID":231,"ProductID":124,"ProductName":"M652T-65寸医用会诊大屏","ProductCount":10,"Remark":"","UsedPrice":0,"ProdNo":"WPBH000025"}]
     * detailstr : null
     * ID : 0
     * CompanyCD : C1002
     * ApplyNo : YJSQ2016080051
     * CustID : 252
     * CustTel : 15889356386
     * Title : san
     * Applyer : 2
     * TotalFee : 0
     * Remark : null
     * BillStatus : 1
     * Creator : 1876
     * CreateDate : 2016-08-09 15:10
     * ProjectID : 38
     */

    private Object detailstr;
    private int ID;
    private String CompanyCD;
    private String ApplyNo;
    private int CustID;
    private String CustTel;
    private String Title;
    private int Applyer;
    private int TotalFee;
    private Object Remark;
    private String BillStatus;
    private int Creator;
    private String CreateDate;
    private int ProjectID;
    private String CreatorName;

    private String FlowStatusName;

    public String getFlowStatusName() {
        return FlowStatusName;
    }

    public void setFlowStatusName(String flowStatusName) {
        FlowStatusName = flowStatusName;
    }

    public String getCreatorName() {
        return CreatorName;
    }

    public void setCreatorName(String creatorName) {
        CreatorName = creatorName;
    }

    /**
     * ID : 231
     * ProductID : 124
     * ProductName : M652T-65寸医用会诊大屏
     * ProductCount : 10
     * Remark :
     * UsedPrice : 0
     * ProdNo : WPBH000025
     */

    private List<DetailsBean> details;

    public Object getDetailstr() {
        return detailstr;
    }

    public void setDetailstr(Object detailstr) {
        this.detailstr = detailstr;
    }

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

    public String getApplyNo() {
        return ApplyNo;
    }

    public void setApplyNo(String ApplyNo) {
        this.ApplyNo = ApplyNo;
    }

    public int getCustID() {
        return CustID;
    }

    public void setCustID(int CustID) {
        this.CustID = CustID;
    }

    public String getCustTel() {
        return CustTel;
    }

    public void setCustTel(String CustTel) {
        this.CustTel = CustTel;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public int getApplyer() {
        return Applyer;
    }

    public void setApplyer(int Applyer) {
        this.Applyer = Applyer;
    }

    public int getTotalFee() {
        return TotalFee;
    }

    public void setTotalFee(int TotalFee) {
        this.TotalFee = TotalFee;
    }

    public Object getRemark() {
        return Remark;
    }

    public void setRemark(Object Remark) {
        this.Remark = Remark;
    }

    public String getBillStatus() {
        return BillStatus;
    }

    public void setBillStatus(String BillStatus) {
        this.BillStatus = BillStatus;
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

    public int getProjectID() {
        return ProjectID;
    }

    public void setProjectID(int ProjectID) {
        this.ProjectID = ProjectID;
    }

    public List<DetailsBean> getDetails() {
        return details;
    }

    public void setDetails(List<DetailsBean> details) {
        this.details = details;
    }

    public static class DetailsBean {
        private int ID;
        private int ProductID;
        private String ProductName;
        private int ProductCount;
        private String Remark;
        private int UsedPrice;
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

        public int getProductCount() {
            return ProductCount;
        }

        public void setProductCount(int ProductCount) {
            this.ProductCount = ProductCount;
        }

        public String getRemark() {
            return Remark;
        }

        public void setRemark(String Remark) {
            this.Remark = Remark;
        }

        public int getUsedPrice() {
            return UsedPrice;
        }

        public void setUsedPrice(int UsedPrice) {
            this.UsedPrice = UsedPrice;
        }

        public String getProdNo() {
            return ProdNo;
        }

        public void setProdNo(String ProdNo) {
            this.ProdNo = ProdNo;
        }
    }
}
