package com.meidp.crmim.model;

import java.io.Serializable;
import java.util.List;

/**
 * Package： com.meidp.crmim.model
 * Author： wxianing
 * 作  用：    备货
 * 时  间： 2016/8/19
 */
public class StockUps implements Serializable{

    /**
     * CheckStatusName : 待审批
     * FlowSteps : [{"CheckStatus":"1","CheckStatusName":"待审核","Note":null,"ActorName":null,"ID":544,"CompanyCD":"C1002","FlowNo":"LCBH000042","StepNo":1,"StepName":"一次审核","Actor":1874,"ModifiedDate":"2016-07-13 16:53:28","ModifiedUserID":"1","DeptID":null},{"CheckStatus":"1","CheckStatusName":"待审核","Note":null,"ActorName":null,"ID":545,"CompanyCD":"C1002","FlowNo":"LCBH000042","StepNo":2,"StepName":"再次审核","Actor":1873,"ModifiedDate":"2016-07-13 16:53:28","ModifiedUserID":"1","DeptID":null}]
     * FlowStatusName : 待审批
     * CurrStepNo : 0
     * Details : null
     * EndDateStr : 2016-07-31
     * ID : 183
     * CompanyCD : C1002
     * OrderNo : DD10001
     * CustID : 251
     * Title : 电脑订单销售
     * BillStatus : 1
     * CreateDate : 2016-07-13 16:52:37
     * Creator : 1873
     * CreatorName : 和智
     * TotalFee : 2790
     * OrderDate : 2016-07-13 00:00:00
     * ConfirmDate : null
     * CustNo : KHBH000081
     * CustName : 深圳中医院
     * ProjectID : null
     * ProjectName : null
     * ProjectNo : null
     * FlowStatus : 1
     * FlowID : 544
     * FlowNo : LCBH000042
     * CheckState : 1
     * StepNo : 1
     * Actor : 1874
     * BillTypeFlag : 5
     * BillTypeCode : 3
     * CanViewUser : 1874,1875
     * EndDate : 2016-07-31 00:00:00
     * SendDate : null
     * CountTotal : 1
     */

    private String CheckStatusName;
    private String FlowStatusName;
    private int CurrStepNo;
    private Object Details;
    private String EndDateStr;
    private int ID;
    private String CompanyCD;
    private String OrderNo;
    private int CustID;
    private String Title;
    private String BillStatus;
    private String CreateDate;
    private int Creator;
    private String CreatorName;
    private int TotalFee;
    private String OrderDate;
    private Object ConfirmDate;
    private String CustNo;
    private String CustName;
    private Object ProjectID;
    private Object ProjectName;
    private Object ProjectNo;
    private String FlowStatus;
    private int FlowID;
    private String FlowNo;
    private int CheckState;
    private int StepNo;
    private int Actor;
    private int BillTypeFlag;
    private int BillTypeCode;
    private String CanViewUser;
    private String EndDate;
    private Object SendDate;
    private int CountTotal;
    /**
     * CheckStatus : 1
     * CheckStatusName : 待审核
     * Note : null
     * ActorName : null
     * ID : 544
     * CompanyCD : C1002
     * FlowNo : LCBH000042
     * StepNo : 1
     * StepName : 一次审核
     * Actor : 1874
     * ModifiedDate : 2016-07-13 16:53:28
     * ModifiedUserID : 1
     * DeptID : null
     */

    private List<FlowStepsBean> FlowSteps;

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

    public Object getDetails() {
        return Details;
    }

    public void setDetails(Object Details) {
        this.Details = Details;
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

    public String getCreatorName() {
        return CreatorName;
    }

    public void setCreatorName(String CreatorName) {
        this.CreatorName = CreatorName;
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

    public String getCustNo() {
        return CustNo;
    }

    public void setCustNo(String CustNo) {
        this.CustNo = CustNo;
    }

    public String getCustName() {
        return CustName;
    }

    public void setCustName(String CustName) {
        this.CustName = CustName;
    }

    public Object getProjectID() {
        return ProjectID;
    }

    public void setProjectID(Object ProjectID) {
        this.ProjectID = ProjectID;
    }

    public Object getProjectName() {
        return ProjectName;
    }

    public void setProjectName(Object ProjectName) {
        this.ProjectName = ProjectName;
    }

    public Object getProjectNo() {
        return ProjectNo;
    }

    public void setProjectNo(Object ProjectNo) {
        this.ProjectNo = ProjectNo;
    }

    public String getFlowStatus() {
        return FlowStatus;
    }

    public void setFlowStatus(String FlowStatus) {
        this.FlowStatus = FlowStatus;
    }

    public int getFlowID() {
        return FlowID;
    }

    public void setFlowID(int FlowID) {
        this.FlowID = FlowID;
    }

    public String getFlowNo() {
        return FlowNo;
    }

    public void setFlowNo(String FlowNo) {
        this.FlowNo = FlowNo;
    }

    public int getCheckState() {
        return CheckState;
    }

    public void setCheckState(int CheckState) {
        this.CheckState = CheckState;
    }

    public int getStepNo() {
        return StepNo;
    }

    public void setStepNo(int StepNo) {
        this.StepNo = StepNo;
    }

    public int getActor() {
        return Actor;
    }

    public void setActor(int Actor) {
        this.Actor = Actor;
    }

    public int getBillTypeFlag() {
        return BillTypeFlag;
    }

    public void setBillTypeFlag(int BillTypeFlag) {
        this.BillTypeFlag = BillTypeFlag;
    }

    public int getBillTypeCode() {
        return BillTypeCode;
    }

    public void setBillTypeCode(int BillTypeCode) {
        this.BillTypeCode = BillTypeCode;
    }

    public String getCanViewUser() {
        return CanViewUser;
    }

    public void setCanViewUser(String CanViewUser) {
        this.CanViewUser = CanViewUser;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String EndDate) {
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

    public static class FlowStepsBean {
        private String CheckStatus;
        private String CheckStatusName;
        private Object Note;
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

        public Object getNote() {
            return Note;
        }

        public void setNote(Object Note) {
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
}
