package com.meidp.crmim.model;

import java.io.Serializable;
import java.util.List;

/**
 * Package： com.meidp.crmim.model
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/7/22
 */
public class ModelApply implements Serializable {

    /**
     * CompanyCD : C1002
     * ApplyNo : YJSQ2016080038
     * CustID : 259
     * Title : 24寸临床医用显示器样机申请
     * Applyer : 1
     * CreateDate : 2016-08-04 16:24
     * Creator : 1874
     * TotalFee : 0
     * ApplyDate :
     * ConfirmDate :
     * ID : 80
     * ProductID : 519
     * ProductCount : 100
     * UsedPrice : 0
     * ProductName : 医用显示器
     * ProdNo : WPBH000057
     * Specification : null
     * SellPrice : null
     * CustNo : KHBH000087
     * ProjectName : 显示器升级换新
     * ProjectNo : XM2016070024
     * CustName : 复旦大学附属中山医院
     * DetailId : 218
     * CheckStatus : 1
     * CheckStatusName : 待审批
     * CreatorName : 汪志红
     * FlowStatus : 1
     * FlowID : 2705
     * FlowNo : LCBH000102
     * FlowSteps : [{"CheckStatus":"0","CheckStatusName":"通过","Note":"同意","ID":550,"CompanyCD":"C1002","FlowNo":"LCBH000102","StepNo":1,"StepName":"销售部","Actor":1874,"ModifiedDate":"2016-07-14 15:36:16","ModifiedUserID":"1","DeptID":null},{"CheckStatus":null,"CheckStatusName":null,"Note":null,"ID":551,"CompanyCD":"C1002","FlowNo":"LCBH000102","StepNo":2,"StepName":"账务部","Actor":1873,"ModifiedDate":"2016-07-14 15:36:16","ModifiedUserID":"1","DeptID":null}]
     */

    private String CompanyCD;
    private String ApplyNo;
    private int CustID;
    private String Title;
    private int Applyer;
    private String CreateDate;
    private int Creator;
    private int TotalFee;
    private String ApplyDate;
    private String ConfirmDate;
    private int ID;
    private int ProductID;
    private int ProductCount;
    private int UsedPrice;
    private String ProductName;
    private String ProdNo;
    private Object Specification;
    private Object SellPrice;
    private String CustNo;
    private String ProjectName;
    private String ProjectNo;
    private String CustName;
    private int DetailId;
    private int CheckStatus;
    private String CheckStatusName;
    private String CreatorName;
    private String FlowStatus;
    private int FlowID;
    private String FlowNo;
    /**
     * CheckStatus : 0
     * CheckStatusName : 通过
     * Note : 同意
     * ID : 550
     * CompanyCD : C1002
     * FlowNo : LCBH000102
     * StepNo : 1
     * StepName : 销售部
     * Actor : 1874
     * ModifiedDate : 2016-07-14 15:36:16
     * ModifiedUserID : 1
     * DeptID : null
     */

    private List<FlowStepsBean> FlowSteps;

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

    public String getApplyDate() {
        return ApplyDate;
    }

    public void setApplyDate(String ApplyDate) {
        this.ApplyDate = ApplyDate;
    }

    public String getConfirmDate() {
        return ConfirmDate;
    }

    public void setConfirmDate(String ConfirmDate) {
        this.ConfirmDate = ConfirmDate;
    }

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

    public int getProductCount() {
        return ProductCount;
    }

    public void setProductCount(int ProductCount) {
        this.ProductCount = ProductCount;
    }

    public int getUsedPrice() {
        return UsedPrice;
    }

    public void setUsedPrice(int UsedPrice) {
        this.UsedPrice = UsedPrice;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public String getProdNo() {
        return ProdNo;
    }

    public void setProdNo(String ProdNo) {
        this.ProdNo = ProdNo;
    }

    public Object getSpecification() {
        return Specification;
    }

    public void setSpecification(Object Specification) {
        this.Specification = Specification;
    }

    public Object getSellPrice() {
        return SellPrice;
    }

    public void setSellPrice(Object SellPrice) {
        this.SellPrice = SellPrice;
    }

    public String getCustNo() {
        return CustNo;
    }

    public void setCustNo(String CustNo) {
        this.CustNo = CustNo;
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

    public String getCustName() {
        return CustName;
    }

    public void setCustName(String CustName) {
        this.CustName = CustName;
    }

    public int getDetailId() {
        return DetailId;
    }

    public void setDetailId(int DetailId) {
        this.DetailId = DetailId;
    }

    public int getCheckStatus() {
        return CheckStatus;
    }

    public void setCheckStatus(int CheckStatus) {
        this.CheckStatus = CheckStatus;
    }

    public String getCheckStatusName() {
        return CheckStatusName;
    }

    public void setCheckStatusName(String CheckStatusName) {
        this.CheckStatusName = CheckStatusName;
    }

    public String getCreatorName() {
        return CreatorName;
    }

    public void setCreatorName(String CreatorName) {
        this.CreatorName = CreatorName;
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

    public List<FlowStepsBean> getFlowSteps() {
        return FlowSteps;
    }

    public void setFlowSteps(List<FlowStepsBean> FlowSteps) {
        this.FlowSteps = FlowSteps;
    }

    public static class FlowStepsBean {
        private String CheckStatus;
        private String CheckStatusName;
        private String Note;
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
