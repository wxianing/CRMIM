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
     * TotalFee : 1801399600
     * Applyer : 16
     * ApplyNo : YJSQ2016080096
     * ProjectName : 818下午测试333
     * FlowStatusName : 待审批
     * Creator : 201886
     * BillStatus : 1
     * CustID : 254
     * CreateDate : 2016-08-18 17:16
     * Title : 测试818818818
     * CustLinkManId : 0
     * CustName : 中南大学湘雅二医院
     * FlowStatus : 1
     * CustTel : 13724327532
     * CreatorName : 杨柳燕
     * CurrStepNo : 0
     * details : [{"ProductID":45,"ID":141,"ProductName":"联想天逸电脑","UsedPrice":2200,"Remark":"","ProdNo":"no2","ProductCount":818818}]
     * CompanyCD : C1002
     * detailstr : null
     * ID : 141
     * Remark : null
     * ProjectID : 80
     * LinkManName : null
     * FlowSteps : [{"DeptID":null,"ModifiedDate":"2016-08-16 16:39:43","CheckStatusName":"待审核","StepNo":1,"CheckStatus":"1","Actor":1873,"FlowNo":"LCBH000107","StepName":"深圳销售测试部","CompanyCD":"C1002","ID":571,"ModifiedUserID":"1","Note":null,"ActorName":null},{"DeptID":null,"ModifiedDate":"2016-08-16 16:39:43","CheckStatusName":"待审核","StepNo":2,"CheckStatus":"1","Actor":1879,"FlowNo":"LCBH000107","StepName":"销售测试部","CompanyCD":"C1002","ID":572,"ModifiedUserID":"1","Note":null,"ActorName":null},{"DeptID":null,"ModifiedDate":"2016-08-16 16:39:43","CheckStatusName":"待审核","StepNo":3,"CheckStatus":"1","Actor":1876,"FlowNo":"LCBH000107","StepName":"研发中心测试部","CompanyCD":"C1002","ID":573,"ModifiedUserID":"1","Note":null,"ActorName":null}]
     */

    private int TotalFee;
    private int Applyer;
    private String ApplyNo;
    private String ProjectName;
    private String FlowStatusName;
    private int Creator;
    private String BillStatus;
    private int CustID;
    private String CreateDate;
    private String Title;
    private int CustLinkManId;
    private String CustName;
    private String FlowStatus;
    private String CustTel;
    private String CreatorName;
    private int CurrStepNo;
    private String CompanyCD;
    private Object detailstr;
    private int ID;
    private Object Remark;
    private int ProjectID;
    private Object LinkManName;
    /**
     * ProductID : 45
     * ID : 141
     * ProductName : 联想天逸电脑
     * UsedPrice : 2200
     * Remark :
     * ProdNo : no2
     * ProductCount : 818818
     */

    private List<DetailsBean> details;
    /**
     * DeptID : null
     * ModifiedDate : 2016-08-16 16:39:43
     * CheckStatusName : 待审核
     * StepNo : 1
     * CheckStatus : 1
     * Actor : 1873
     * FlowNo : LCBH000107
     * StepName : 深圳销售测试部
     * CompanyCD : C1002
     * ID : 571
     * ModifiedUserID : 1
     * Note : null
     * ActorName : null
     */

    private List<FlowStepsBean> FlowSteps;

    public int getTotalFee() {
        return TotalFee;
    }

    public void setTotalFee(int TotalFee) {
        this.TotalFee = TotalFee;
    }

    public int getApplyer() {
        return Applyer;
    }

    public void setApplyer(int Applyer) {
        this.Applyer = Applyer;
    }

    public String getApplyNo() {
        return ApplyNo;
    }

    public void setApplyNo(String ApplyNo) {
        this.ApplyNo = ApplyNo;
    }

    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String ProjectName) {
        this.ProjectName = ProjectName;
    }

    public String getFlowStatusName() {
        return FlowStatusName;
    }

    public void setFlowStatusName(String FlowStatusName) {
        this.FlowStatusName = FlowStatusName;
    }

    public int getCreator() {
        return Creator;
    }

    public void setCreator(int Creator) {
        this.Creator = Creator;
    }

    public String getBillStatus() {
        return BillStatus;
    }

    public void setBillStatus(String BillStatus) {
        this.BillStatus = BillStatus;
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

    public int getCustLinkManId() {
        return CustLinkManId;
    }

    public void setCustLinkManId(int CustLinkManId) {
        this.CustLinkManId = CustLinkManId;
    }

    public String getCustName() {
        return CustName;
    }

    public void setCustName(String CustName) {
        this.CustName = CustName;
    }

    public String getFlowStatus() {
        return FlowStatus;
    }

    public void setFlowStatus(String FlowStatus) {
        this.FlowStatus = FlowStatus;
    }

    public String getCustTel() {
        return CustTel;
    }

    public void setCustTel(String CustTel) {
        this.CustTel = CustTel;
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

    public String getCompanyCD() {
        return CompanyCD;
    }

    public void setCompanyCD(String CompanyCD) {
        this.CompanyCD = CompanyCD;
    }

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

    public Object getRemark() {
        return Remark;
    }

    public void setRemark(Object Remark) {
        this.Remark = Remark;
    }

    public int getProjectID() {
        return ProjectID;
    }

    public void setProjectID(int ProjectID) {
        this.ProjectID = ProjectID;
    }

    public Object getLinkManName() {
        return LinkManName;
    }

    public void setLinkManName(Object LinkManName) {
        this.LinkManName = LinkManName;
    }

    public List<DetailsBean> getDetails() {
        return details;
    }

    public void setDetails(List<DetailsBean> details) {
        this.details = details;
    }

    public List<FlowStepsBean> getFlowSteps() {
        return FlowSteps;
    }

    public void setFlowSteps(List<FlowStepsBean> FlowSteps) {
        this.FlowSteps = FlowSteps;
    }

    public static class DetailsBean {
        private int ProductID;
        private int ID;
        private String ProductName;
        private int UsedPrice;
        private String Remark;
        private String ProdNo;
        private int ProductCount;

        public int getProductID() {
            return ProductID;
        }

        public void setProductID(int ProductID) {
            this.ProductID = ProductID;
        }

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getProductName() {
            return ProductName;
        }

        public void setProductName(String ProductName) {
            this.ProductName = ProductName;
        }

        public int getUsedPrice() {
            return UsedPrice;
        }

        public void setUsedPrice(int UsedPrice) {
            this.UsedPrice = UsedPrice;
        }

        public String getRemark() {
            return Remark;
        }

        public void setRemark(String Remark) {
            this.Remark = Remark;
        }

        public String getProdNo() {
            return ProdNo;
        }

        public void setProdNo(String ProdNo) {
            this.ProdNo = ProdNo;
        }

        public int getProductCount() {
            return ProductCount;
        }

        public void setProductCount(int ProductCount) {
            this.ProductCount = ProductCount;
        }
    }

    public static class FlowStepsBean {
        private Object DeptID;
        private String ModifiedDate;
        private String CheckStatusName;
        private int StepNo;
        private String CheckStatus;
        private int Actor;
        private String FlowNo;
        private String StepName;
        private String CompanyCD;
        private int ID;
        private String ModifiedUserID;
        private Object Note;
        private Object ActorName;

        public Object getDeptID() {
            return DeptID;
        }

        public void setDeptID(Object DeptID) {
            this.DeptID = DeptID;
        }

        public String getModifiedDate() {
            return ModifiedDate;
        }

        public void setModifiedDate(String ModifiedDate) {
            this.ModifiedDate = ModifiedDate;
        }

        public String getCheckStatusName() {
            return CheckStatusName;
        }

        public void setCheckStatusName(String CheckStatusName) {
            this.CheckStatusName = CheckStatusName;
        }

        public int getStepNo() {
            return StepNo;
        }

        public void setStepNo(int StepNo) {
            this.StepNo = StepNo;
        }

        public String getCheckStatus() {
            return CheckStatus;
        }

        public void setCheckStatus(String CheckStatus) {
            this.CheckStatus = CheckStatus;
        }

        public int getActor() {
            return Actor;
        }

        public void setActor(int Actor) {
            this.Actor = Actor;
        }

        public String getFlowNo() {
            return FlowNo;
        }

        public void setFlowNo(String FlowNo) {
            this.FlowNo = FlowNo;
        }

        public String getStepName() {
            return StepName;
        }

        public void setStepName(String StepName) {
            this.StepName = StepName;
        }

        public String getCompanyCD() {
            return CompanyCD;
        }

        public void setCompanyCD(String CompanyCD) {
            this.CompanyCD = CompanyCD;
        }

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getModifiedUserID() {
            return ModifiedUserID;
        }

        public void setModifiedUserID(String ModifiedUserID) {
            this.ModifiedUserID = ModifiedUserID;
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
    }
}
