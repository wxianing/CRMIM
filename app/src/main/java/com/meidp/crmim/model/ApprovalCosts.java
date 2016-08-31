package com.meidp.crmim.model;

import java.io.Serializable;
import java.util.List;

/**
 * Package： com.meidp.crmim.model
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/8/14
 */
public class ApprovalCosts implements Serializable {

    /**
     * Attachment : null
     * ProjectName :
     * ConfirmDate : null
     * Creator : 1874
     * Confirmor : null
     * CustID : 0
     * CreateDate : 2016-08-04 19:09
     * Title : C81W+31寸8MP一体化双屏显示器维修
     * ExpCode : null
     * NeedDate :
     * ModifiedDate : null
     * Status : 1
     * AriseDate : 2016-08-04 19:09
     * CreatorName : 汪志红
     * Applyor : 1
     * ID : 123
     * TotalAmount : 0
     * PayType : null
     * ProjectID : null
     * ExpType : 0
     * Reason : 维修零件
     */

    private Object Attachment;
    private String ProjectName;
    private Object ConfirmDate;
    private int Creator;
    private Object Confirmor;
    private int CustID;
    private String CreateDate;
    private String Title;
    private String ExpCode;
    private String NeedDate;
    private Object ModifiedDate;
    private String Status;
    private String AriseDate;
    private String CreatorName;
    private int Applyor;
    private int ID;
    private int TotalAmount;
    private Object PayType;
    private Object ProjectID;
    private int ExpType;
    private String Reason;
    /**
     * Details : [{"ExpTypeName":"差旅补贴","ID":224,"ExpID":148,"SortNo":null,"ExpType":1,"Amount":100,"ExpRemark":null}]
     * CurrStepNo : 0
     * FlowSteps : [{"CheckStatus":"0","CheckStatusName":"审批通过","Note":"无","ActorName":null,"DeptName":null,"ID":574,"CompanyCD":"C1002","FlowNo":"LCBH000108","StepNo":1,"StepName":"销售测试部","Actor":1879,"ModifiedDate":"2016-08-16 16:41:09","ModifiedUserID":"1","DeptID":null},{"CheckStatus":"1","CheckStatusName":"待审批","Note":null,"ActorName":null,"DeptName":null,"ID":575,"CompanyCD":"C1002","FlowNo":"LCBH000108","StepNo":2,"StepName":"研发中心测试部","Actor":1876,"ModifiedDate":"2016-08-16 16:41:09","ModifiedUserID":"1","DeptID":null}]
     * FlowStatus : 2
     * FlowStatusName : 审批中
     * detailstr : null
     * CheckStatus : 0
     * CheckStatusName : 审批通过
     */

    private int CurrStepNo;
    private String FlowStatus;
    private String FlowStatusName;
    private Object detailstr;
    private String CheckStatus;
    private String CheckStatusName;
    /**
     * ExpTypeName : 差旅补贴
     * ID : 224
     * ExpID : 148
     * SortNo : null
     * ExpType : 1
     * Amount : 100
     * ExpRemark : null
     */

    private List<DetailsBean> Details;
    /**
     * CheckStatus : 0
     * CheckStatusName : 审批通过
     * Note : 无
     * ActorName : null
     * DeptName : null
     * ID : 574
     * CompanyCD : C1002
     * FlowNo : LCBH000108
     * StepNo : 1
     * StepName : 销售测试部
     * Actor : 1879
     * ModifiedDate : 2016-08-16 16:41:09
     * ModifiedUserID : 1
     * DeptID : null
     */

    private List<FlowStepsBean> FlowSteps;

    public Object getAttachment() {
        return Attachment;
    }

    public void setAttachment(Object Attachment) {
        this.Attachment = Attachment;
    }

    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String ProjectName) {
        this.ProjectName = ProjectName;
    }

    public Object getConfirmDate() {
        return ConfirmDate;
    }

    public void setConfirmDate(Object ConfirmDate) {
        this.ConfirmDate = ConfirmDate;
    }

    public int getCreator() {
        return Creator;
    }

    public void setCreator(int Creator) {
        this.Creator = Creator;
    }

    public Object getConfirmor() {
        return Confirmor;
    }

    public void setConfirmor(Object Confirmor) {
        this.Confirmor = Confirmor;
    }

    public int getCustID() {
        return CustID;
    }

    public void setCustID(int CustID) {
        this.CustID = CustID;
    }

    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String CreateDate) {
        this.CreateDate = CreateDate;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getExpCode() {
        return ExpCode;
    }

    public void setExpCode(String ExpCode) {
        this.ExpCode = ExpCode;
    }

    public String getNeedDate() {
        return NeedDate;
    }

    public void setNeedDate(String NeedDate) {
        this.NeedDate = NeedDate;
    }

    public Object getModifiedDate() {
        return ModifiedDate;
    }

    public void setModifiedDate(Object ModifiedDate) {
        this.ModifiedDate = ModifiedDate;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getAriseDate() {
        return AriseDate;
    }

    public void setAriseDate(String AriseDate) {
        this.AriseDate = AriseDate;
    }

    public String getCreatorName() {
        return CreatorName;
    }

    public void setCreatorName(String CreatorName) {
        this.CreatorName = CreatorName;
    }

    public int getApplyor() {
        return Applyor;
    }

    public void setApplyor(int Applyor) {
        this.Applyor = Applyor;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public Object getProjectID() {
        return ProjectID;
    }

    public void setProjectID(Object ProjectID) {
        this.ProjectID = ProjectID;
    }

    public int getExpType() {
        return ExpType;
    }

    public void setExpType(int ExpType) {
        this.ExpType = ExpType;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String Reason) {
        this.Reason = Reason;
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

    public Object getDetailstr() {
        return detailstr;
    }

    public void setDetailstr(Object detailstr) {
        this.detailstr = detailstr;
    }

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

    public static class DetailsBean implements Serializable {
        private String ExpTypeName;
        private int ID;
        private int ExpID;
        private Object SortNo;
        private int ExpType;
        private int Amount;
        private Object ExpRemark;

        public String getExpTypeName() {
            return ExpTypeName;
        }

        public void setExpTypeName(String ExpTypeName) {
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

        public Object getSortNo() {
            return SortNo;
        }

        public void setSortNo(Object SortNo) {
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

        public Object getExpRemark() {
            return ExpRemark;
        }

        public void setExpRemark(Object ExpRemark) {
            this.ExpRemark = ExpRemark;
        }
    }

    public static class FlowStepsBean implements Serializable {
        private String CheckStatus;
        private String CheckStatusName;
        private String Note;
        private Object ActorName;
        private Object DeptName;
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

        public Object getDeptName() {
            return DeptName;
        }

        public void setDeptName(Object DeptName) {
            this.DeptName = DeptName;
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
