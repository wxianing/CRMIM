package com.meidp.crmim.model;

import java.io.Serializable;
import java.util.List;

/**
 * Package： com.meidp.crmim.model
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/8/19
 */
public class StockUpDetails implements Serializable {

    /**
     * CheckStatus : 0
     * FlowStatus : 2
     * CheckStatusName : 审批通过
     * FlowSteps : [{"CheckStatus":"0","CheckStatusName":"通过","Note":"纸巾","ActorName":null,"ID":581,"CompanyCD":"C1002","FlowNo":"LCBH000110","StepNo":1,"StepName":"一次审核","Actor":1873,"ModifiedDate":"2016-08-18 09:41:48","ModifiedUserID":"1","DeptID":null},{"CheckStatus":"1","CheckStatusName":"待审核","Note":null,"ActorName":null,"ID":582,"CompanyCD":"C1002","FlowNo":"LCBH000110","StepNo":2,"StepName":"二次审核","Actor":1879,"ModifiedDate":"2016-08-18 09:41:48","ModifiedUserID":"1","DeptID":null}]
     * FlowStatusName : 审批中
     * CurrStepNo : 0
     * Details : [{"ID":347,"OrderNo":"XSDD2016080046","ProductID":122,"ProductCount":8888,"Discount":null,"UnitPrice":null,"totalPrice":null,"SendCount":null,"TotalFee":null,"UsedUnitID":0,"UsedUnitCount":8888,"UsedPrice":10000,"ExRate":null,"OrderID":207,"ProductName":"纸巾","Brand":0,"ProdNo":"WPBH000024"}]
     * EndDateStr :
     * ID : 207
     * CompanyCD : C1002
     * OrderNo : XSDD2016080046
     * CustID : 213
     * Title : 纸巾
     * BillStatus : 1
     * CreateDate : 2016-08-18 16:41:15
     * Creator : 201886
     * TotalFee : 88880000
     * OrderDate : 2016-08-18 00:00:00
     * ConfirmDate : null
     * CustNo : null
     * CustName : null
     * ProjectID : 80
     * ProjectName : 818下午测试333
     * ProjectNo : XM2016080060
     * CreatorName : 杨柳燕
     * CanViewUser : null
     * EndDate : null
     * SendDate : null
     * CountTotal : 8888
     */

    private int CheckStatus;
    private int FlowStatus;
    private String CheckStatusName;
    private String FlowStatusName;
    private int CurrStepNo;
    private String EndDateStr;
    private int ID;
    private String CompanyCD;
    private String OrderNo;
    private int CustID;
    private String Title;
    private String BillStatus;
    private String CreateDate;
    private int Creator;
    private int TotalFee;
    private String OrderDate;
    private Object ConfirmDate;
    private Object CustNo;
    private String CustName;
    private int ProjectID;
    private String ProjectName;
    private String ProjectNo;
    private String CreatorName;
    private Object CanViewUser;
    private Object EndDate;
    private Object SendDate;
    private int CountTotal;
    /**
     * CheckStatus : 0
     * CheckStatusName : 通过
     * Note : 纸巾
     * ActorName : null
     * ID : 581
     * CompanyCD : C1002
     * FlowNo : LCBH000110
     * StepNo : 1
     * StepName : 一次审核
     * Actor : 1873
     * ModifiedDate : 2016-08-18 09:41:48
     * ModifiedUserID : 1
     * DeptID : null
     */

    private List<FlowStepsBean> FlowSteps;
    /**
     * ID : 347
     * OrderNo : XSDD2016080046
     * ProductID : 122
     * ProductCount : 8888
     * Discount : null
     * UnitPrice : null
     * totalPrice : null
     * SendCount : null
     * TotalFee : null
     * UsedUnitID : 0
     * UsedUnitCount : 8888
     * UsedPrice : 10000
     * ExRate : null
     * OrderID : 207
     * ProductName : 纸巾
     * Brand : 0
     * ProdNo : WPBH000024
     */

    private List<DetailsBean> Details;

    public int getCheckStatus() {
        return CheckStatus;
    }

    public void setCheckStatus(int CheckStatus) {
        this.CheckStatus = CheckStatus;
    }

    public int getFlowStatus() {
        return FlowStatus;
    }

    public void setFlowStatus(int FlowStatus) {
        this.FlowStatus = FlowStatus;
    }

    public String getCheckStatusName() {
        return CheckStatusName;
    }

    public void setCheckStatusName(String CheckStatusName) {
        this.CheckStatusName = CheckStatusName;
    }

    public String getFlowStatusName() {
        return FlowStatusName;
    }

    public void setFlowStatusName(String FlowStatusName) {
        this.FlowStatusName = FlowStatusName;
    }

    public int getCurrStepNo() {
        return CurrStepNo;
    }

    public void setCurrStepNo(int CurrStepNo) {
        this.CurrStepNo = CurrStepNo;
    }

    public String getEndDateStr() {
        return EndDateStr;
    }

    public void setEndDateStr(String EndDateStr) {
        this.EndDateStr = EndDateStr;
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

    public String getOrderNo() {
        return OrderNo;
    }

    public void setOrderNo(String OrderNo) {
        this.OrderNo = OrderNo;
    }

    public int getCustID() {
        return CustID;
    }

    public void setCustID(int CustID) {
        this.CustID = CustID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getBillStatus() {
        return BillStatus;
    }

    public void setBillStatus(String BillStatus) {
        this.BillStatus = BillStatus;
    }

    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String CreateDate) {
        this.CreateDate = CreateDate;
    }

    public int getCreator() {
        return Creator;
    }

    public void setCreator(int Creator) {
        this.Creator = Creator;
    }

    public int getTotalFee() {
        return TotalFee;
    }

    public void setTotalFee(int TotalFee) {
        this.TotalFee = TotalFee;
    }

    public String getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(String OrderDate) {
        this.OrderDate = OrderDate;
    }

    public Object getConfirmDate() {
        return ConfirmDate;
    }

    public void setConfirmDate(Object ConfirmDate) {
        this.ConfirmDate = ConfirmDate;
    }

    public Object getCustNo() {
        return CustNo;
    }

    public void setCustNo(Object CustNo) {
        this.CustNo = CustNo;
    }

    public String getCustName() {
        return CustName;
    }

    public void setCustName(String CustName) {
        this.CustName = CustName;
    }

    public int getProjectID() {
        return ProjectID;
    }

    public void setProjectID(int ProjectID) {
        this.ProjectID = ProjectID;
    }

    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String ProjectName) {
        this.ProjectName = ProjectName;
    }

    public String getProjectNo() {
        return ProjectNo;
    }

    public void setProjectNo(String ProjectNo) {
        this.ProjectNo = ProjectNo;
    }

    public String getCreatorName() {
        return CreatorName;
    }

    public void setCreatorName(String CreatorName) {
        this.CreatorName = CreatorName;
    }

    public Object getCanViewUser() {
        return CanViewUser;
    }

    public void setCanViewUser(Object CanViewUser) {
        this.CanViewUser = CanViewUser;
    }

    public Object getEndDate() {
        return EndDate;
    }

    public void setEndDate(Object EndDate) {
        this.EndDate = EndDate;
    }

    public Object getSendDate() {
        return SendDate;
    }

    public void setSendDate(Object SendDate) {
        this.SendDate = SendDate;
    }

    public int getCountTotal() {
        return CountTotal;
    }

    public void setCountTotal(int CountTotal) {
        this.CountTotal = CountTotal;
    }

    public List<FlowStepsBean> getFlowSteps() {
        return FlowSteps;
    }

    public void setFlowSteps(List<FlowStepsBean> FlowSteps) {
        this.FlowSteps = FlowSteps;
    }

    public List<DetailsBean> getDetails() {
        return Details;
    }

    public void setDetails(List<DetailsBean> Details) {
        this.Details = Details;
    }

    public static class FlowStepsBean {
        private String CheckStatus;
        private String CheckStatusName;
        private String Note;
        private Object ActorName;
        private int ID;
        private String CompanyCD;
        private String FlowNo;
        private int StepNo;
        private String StepName;
        private int Actor;
        private String ModifiedDate;
        private String ModifiedUserID;
        private Object DeptID;

        public String getCheckStatus() {
            return CheckStatus;
        }

        public void setCheckStatus(String CheckStatus) {
            this.CheckStatus = CheckStatus;
        }

        public String getCheckStatusName() {
            return CheckStatusName;
        }

        public void setCheckStatusName(String CheckStatusName) {
            this.CheckStatusName = CheckStatusName;
        }

        public String getNote() {
            return Note;
        }

        public void setNote(String Note) {
            this.Note = Note;
        }

        public Object getActorName() {
            return ActorName;
        }

        public void setActorName(Object ActorName) {
            this.ActorName = ActorName;
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

        public String getFlowNo() {
            return FlowNo;
        }

        public void setFlowNo(String FlowNo) {
            this.FlowNo = FlowNo;
        }

        public int getStepNo() {
            return StepNo;
        }

        public void setStepNo(int StepNo) {
            this.StepNo = StepNo;
        }

        public String getStepName() {
            return StepName;
        }

        public void setStepName(String StepName) {
            this.StepName = StepName;
        }

        public int getActor() {
            return Actor;
        }

        public void setActor(int Actor) {
            this.Actor = Actor;
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

        public Object getDeptID() {
            return DeptID;
        }

        public void setDeptID(Object DeptID) {
            this.DeptID = DeptID;
        }
    }

    public static class DetailsBean {
        private int ID;
        private String OrderNo;
        private int ProductID;
        private int ProductCount;
        private Object Discount;
        private Object UnitPrice;
        private Object totalPrice;
        private Object SendCount;
        private Object TotalFee;
        private int UsedUnitID;
        private int UsedUnitCount;
        private int UsedPrice;
        private Object ExRate;
        private int OrderID;
        private String ProductName;
        private int Brand;
        private String ProdNo;

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

        public int getProductID() {
            return ProductID;
        }

        public void setProductID(int ProductID) {
            this.ProductID = ProductID;
        }

        public int getProductCount() {
            return ProductCount;
        }

        public void setProductCount(int ProductCount) {
            this.ProductCount = ProductCount;
        }

        public Object getDiscount() {
            return Discount;
        }

        public void setDiscount(Object Discount) {
            this.Discount = Discount;
        }

        public Object getUnitPrice() {
            return UnitPrice;
        }

        public void setUnitPrice(Object UnitPrice) {
            this.UnitPrice = UnitPrice;
        }

        public Object getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(Object totalPrice) {
            this.totalPrice = totalPrice;
        }

        public Object getSendCount() {
            return SendCount;
        }

        public void setSendCount(Object SendCount) {
            this.SendCount = SendCount;
        }

        public Object getTotalFee() {
            return TotalFee;
        }

        public void setTotalFee(Object TotalFee) {
            this.TotalFee = TotalFee;
        }

        public int getUsedUnitID() {
            return UsedUnitID;
        }

        public void setUsedUnitID(int UsedUnitID) {
            this.UsedUnitID = UsedUnitID;
        }

        public int getUsedUnitCount() {
            return UsedUnitCount;
        }

        public void setUsedUnitCount(int UsedUnitCount) {
            this.UsedUnitCount = UsedUnitCount;
        }

        public int getUsedPrice() {
            return UsedPrice;
        }

        public void setUsedPrice(int UsedPrice) {
            this.UsedPrice = UsedPrice;
        }

        public Object getExRate() {
            return ExRate;
        }

        public void setExRate(Object ExRate) {
            this.ExRate = ExRate;
        }

        public int getOrderID() {
            return OrderID;
        }

        public void setOrderID(int OrderID) {
            this.OrderID = OrderID;
        }

        public String getProductName() {
            return ProductName;
        }

        public void setProductName(String ProductName) {
            this.ProductName = ProductName;
        }

        public int getBrand() {
            return Brand;
        }

        public void setBrand(int Brand) {
            this.Brand = Brand;
        }

        public String getProdNo() {
            return ProdNo;
        }

        public void setProdNo(String ProdNo) {
            this.ProdNo = ProdNo;
        }
    }
}
