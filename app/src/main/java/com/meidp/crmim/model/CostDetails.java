package com.meidp.crmim.model;

import java.io.Serializable;
import java.util.List;

/**
 * Package： com.meidp.crmim.model
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/8/10
 */
public class CostDetails implements Serializable {

    /**
     * ID : 0
     * ExpCode : FY2016080400000049
     * Title :  110寸8MP医疗会诊大屏邮寄费
     * Applyor : 1
     * AriseDate : 2016-08-04 19:09
     * NeedDate :
     * TotalAmount : 100
     * PayType : null
     * Reason : 测试数据
     * Status : 1
     * Creator : 1874
     * CreateDate : 2016-08-04 19:09
     * ExpType : 0
     * CustID : 0
     * ModifiedDate : null
     * Confirmor : null
     * ConfirmDate : null
     * ProjectID : null
     * ProjectName :
     * Attachment : null
     * CreatorName : 汪志红
     * Details : [{"ExpTypeName":null,"ID":199,"ExpID":122,"SortNo":0,"ExpType":0,"Amount":1,"ExpRemark":""}]
     * CurrStepNo : 0
     * FlowSteps : [{"CheckStatus":"1","CheckStatusName":"待审核","Note":null,"ActorName":"manager","ID":560,"CompanyCD":"C1002","FlowNo":"LCBH000104","StepNo":1,"StepName":"市场部","Actor":1885,"ModifiedDate":"2016-08-15 12:21:15","ModifiedUserID":"1","DeptID":null},{"CheckStatus":"1","CheckStatusName":"待审核","Note":null,"ActorName":"majordomo","ID":561,"CompanyCD":"C1002","FlowNo":"LCBH000104","StepNo":2,"StepName":"营销中心","Actor":1886,"ModifiedDate":"2016-08-15 12:21:15","ModifiedUserID":"1","DeptID":null},{"CheckStatus":"0","CheckStatusName":"通过","Note":"","ActorName":"president","ID":562,"CompanyCD":"C1002","FlowNo":"LCBH000104","StepNo":3,"StepName":"总办","Actor":1884,"ModifiedDate":"2016-08-15 12:21:15","ModifiedUserID":"1","DeptID":null}]
     * FlowStatus : 3
     * FlowStatusName : 通过审批
     */

    private int ID;
    private String ExpCode;
    private String Title;
    private int Applyor;
    private String AriseDate;
    private String NeedDate;
    private int TotalAmount;
    private Object PayType;
    private String Reason;
    private String Status;
    private int Creator;
    private String CreateDate;
    private int ExpType;
    private int CustID;
    private Object ModifiedDate;
    private Object Confirmor;
    private Object ConfirmDate;
    private Object ProjectID;
    private String ProjectName;
    private Object Attachment;
    private String CreatorName;
    private int CurrStepNo;
    private String FlowStatus;
    private String FlowStatusName;
    /**
     * ExpTypeName : null
     * ID : 199
     * ExpID : 122
     * SortNo : 0
     * ExpType : 0
     * Amount : 1
     * ExpRemark :
     */

    private List<DetailsBean> Details;
    /**
     * CheckStatus : 1
     * CheckStatusName : 待审核
     * Note : null
     * ActorName : manager
     * ID : 560
     * CompanyCD : C1002
     * FlowNo : LCBH000104
     * StepNo : 1
     * StepName : 市场部
     * Actor : 1885
     * ModifiedDate : 2016-08-15 12:21:15
     * ModifiedUserID : 1
     * DeptID : null
     */

    private List<FlowStepsBean> FlowSteps;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getExpCode() {
        return ExpCode;
    }

    public void setExpCode(String ExpCode) {
        this.ExpCode = ExpCode;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public int getApplyor() {
        return Applyor;
    }

    public void setApplyor(int Applyor) {
        this.Applyor = Applyor;
    }

    public String getAriseDate() {
        return AriseDate;
    }

    public void setAriseDate(String AriseDate) {
        this.AriseDate = AriseDate;
    }

    public String getNeedDate() {
        return NeedDate;
    }

    public void setNeedDate(String NeedDate) {
        this.NeedDate = NeedDate;
    }

    public int getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(int TotalAmount) {
        this.TotalAmount = TotalAmount;
    }

    public Object getPayType() {
        return PayType;
    }

    public void setPayType(Object PayType) {
        this.PayType = PayType;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String Reason) {
        this.Reason = Reason;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
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

    public int getExpType() {
        return ExpType;
    }

    public void setExpType(int ExpType) {
        this.ExpType = ExpType;
    }

    public int getCustID() {
        return CustID;
    }

    public void setCustID(int CustID) {
        this.CustID = CustID;
    }

    public Object getModifiedDate() {
        return ModifiedDate;
    }

    public void setModifiedDate(Object ModifiedDate) {
        this.ModifiedDate = ModifiedDate;
    }

    public Object getConfirmor() {
        return Confirmor;
    }

    public void setConfirmor(Object Confirmor) {
        this.Confirmor = Confirmor;
    }

    public Object getConfirmDate() {
        return ConfirmDate;
    }

    public void setConfirmDate(Object ConfirmDate) {
        this.ConfirmDate = ConfirmDate;
    }

    public Object getProjectID() {
        return ProjectID;
    }

    public void setProjectID(Object ProjectID) {
        this.ProjectID = ProjectID;
    }

    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String ProjectName) {
        this.ProjectName = ProjectName;
    }

    public Object getAttachment() {
        return Attachment;
    }

    public void setAttachment(Object Attachment) {
        this.Attachment = Attachment;
    }

    public String getCreatorName() {
        return CreatorName;
    }

    public void setCreatorName(String CreatorName) {
        this.CreatorName = CreatorName;
    }

    public int getCurrStepNo() {
        return CurrStepNo;
    }

    public void setCurrStepNo(int CurrStepNo) {
        this.CurrStepNo = CurrStepNo;
    }

    public String getFlowStatus() {
        return FlowStatus;
    }

    public void setFlowStatus(String FlowStatus) {
        this.FlowStatus = FlowStatus;
    }

    public String getFlowStatusName() {
        return FlowStatusName;
    }

    public void setFlowStatusName(String FlowStatusName) {
        this.FlowStatusName = FlowStatusName;
    }

    public List<DetailsBean> getDetails() {
        return Details;
    }

    public void setDetails(List<DetailsBean> Details) {
        this.Details = Details;
    }

    public List<FlowStepsBean> getFlowSteps() {
        return FlowSteps;
    }

    public void setFlowSteps(List<FlowStepsBean> FlowSteps) {
        this.FlowSteps = FlowSteps;
    }

    public static class DetailsBean {
        private Object ExpTypeName;
        private int ID;
        private int ExpID;
        private int SortNo;
        private int ExpType;
        private int Amount;
        private String ExpRemark;

        public Object getExpTypeName() {
            return ExpTypeName;
        }

        public void setExpTypeName(Object ExpTypeName) {
            this.ExpTypeName = ExpTypeName;
        }

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

        public int getAmount() {
            return Amount;
        }

        public void setAmount(int Amount) {
            this.Amount = Amount;
        }

        public String getExpRemark() {
            return ExpRemark;
        }

        public void setExpRemark(String ExpRemark) {
            this.ExpRemark = ExpRemark;
        }
    }

    public static class FlowStepsBean {
        private String CheckStatus;
        private String CheckStatusName;
        private String Note;
        private String ActorName;
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

        public String getActorName() {
            return ActorName;
        }

        public void setActorName(String ActorName) {
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
