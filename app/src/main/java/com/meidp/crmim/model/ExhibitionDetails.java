package com.meidp.crmim.model;

import java.io.Serializable;
import java.util.List;

/**
 * Package： com.meidp.crmim.model
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/8/12
 */
public class ExhibitionDetails implements Serializable {

    /**
     * ID : 1
     * CompanyCD : C1002
     * ExhibitionNo : ZHSQ2016080040
     * ExhibitionNature : 1
     * LinkManName : 汪工
     * LinkTel : 13530083234
     * Title : 会展中心展会推广会
     * AttendPersons : 100
     * TotalMoney : 120
     * ExhibitionStartDate : 2016-08-01 00:00
     * ExhibitionEndDate : 2016-08-20 00:00
     * ExhibitionPlan : 这是计划
     * ExhibitionAim : 这是目标
     * UnionPartner : 这是合作伙伴
     * PlanFee : 10000
     * Competitors : 无竞争对手
     * Address : 深圳会展中心
     * Creator : 1874
     * CreateDate : 2016-08-09 21:06
     * Confirmor : null
     * ConfirmDate : null
     * ModifiedDate : 2016-08-09 21:06
     * ModifiedUserID : 1
     * CanViewUser :
     * BillStatus : 1
     * Details : [{"ID":1,"CompanyCD":"C1002","ExhibitionNo":"ZHSQ2016080040","ProductID":1,"ProductCount":2,"UnitID":1,"TotalFee":1500,"Remark":"sample string 4","ModifiedDate":"2016-08-09 21:06:56","ModifiedUserID":"1","UsedPrice":60,"ExhibitionId":1}]
     * DetailStr : null
     * CreatorName : 汪志红
     */

    private int ID;
    private String CompanyCD;
    private String ExhibitionNo;
    private int ExhibitionNature;
    private String LinkManName;
    private String LinkTel;
    private String Title;
    private int AttendPersons;
    private int TotalMoney;
    private String ExhibitionStartDate;
    private String ExhibitionEndDate;
    private String ExhibitionPlan;
    private String ExhibitionAim;
    private String UnionPartner;
    private int PlanFee;
    private String Competitors;
    private String Address;
    private int Creator;
    private String CreateDate;
    private Object Confirmor;
    private Object ConfirmDate;
    private String ModifiedDate;
    private String ModifiedUserID;
    private String CanViewUser;
    private int BillStatus;
    private Object DetailStr;
    private String CreatorName;
    /**
     * ID : 1
     * CompanyCD : C1002
     * ExhibitionNo : ZHSQ2016080040
     * ProductID : 1
     * ProductCount : 2
     * UnitID : 1
     * TotalFee : 1500
     * Remark : sample string 4
     * ModifiedDate : 2016-08-09 21:06:56
     * ModifiedUserID : 1
     * UsedPrice : 60
     * ExhibitionId : 1
     */

    private List<DetailsBean> Details;

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

    public int getExhibitionNature() {
        return ExhibitionNature;
    }

    public void setExhibitionNature(int ExhibitionNature) {
        this.ExhibitionNature = ExhibitionNature;
    }

    public String getLinkManName() {
        return LinkManName;
    }

    public void setLinkManName(String LinkManName) {
        this.LinkManName = LinkManName;
    }

    public String getLinkTel() {
        return LinkTel;
    }

    public void setLinkTel(String LinkTel) {
        this.LinkTel = LinkTel;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public int getAttendPersons() {
        return AttendPersons;
    }

    public void setAttendPersons(int AttendPersons) {
        this.AttendPersons = AttendPersons;
    }

    public int getTotalMoney() {
        return TotalMoney;
    }

    public void setTotalMoney(int TotalMoney) {
        this.TotalMoney = TotalMoney;
    }

    public String getExhibitionStartDate() {
        return ExhibitionStartDate;
    }

    public void setExhibitionStartDate(String ExhibitionStartDate) {
        this.ExhibitionStartDate = ExhibitionStartDate;
    }

    public String getExhibitionEndDate() {
        return ExhibitionEndDate;
    }

    public void setExhibitionEndDate(String ExhibitionEndDate) {
        this.ExhibitionEndDate = ExhibitionEndDate;
    }

    public String getExhibitionPlan() {
        return ExhibitionPlan;
    }

    public void setExhibitionPlan(String ExhibitionPlan) {
        this.ExhibitionPlan = ExhibitionPlan;
    }

    public String getExhibitionAim() {
        return ExhibitionAim;
    }

    public void setExhibitionAim(String ExhibitionAim) {
        this.ExhibitionAim = ExhibitionAim;
    }

    public String getUnionPartner() {
        return UnionPartner;
    }

    public void setUnionPartner(String UnionPartner) {
        this.UnionPartner = UnionPartner;
    }

    public int getPlanFee() {
        return PlanFee;
    }

    public void setPlanFee(int PlanFee) {
        this.PlanFee = PlanFee;
    }

    public String getCompetitors() {
        return Competitors;
    }

    public void setCompetitors(String Competitors) {
        this.Competitors = Competitors;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
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

    public String getCanViewUser() {
        return CanViewUser;
    }

    public void setCanViewUser(String CanViewUser) {
        this.CanViewUser = CanViewUser;
    }

    public int getBillStatus() {
        return BillStatus;
    }

    public void setBillStatus(int BillStatus) {
        this.BillStatus = BillStatus;
    }

    public Object getDetailStr() {
        return DetailStr;
    }

    public void setDetailStr(Object DetailStr) {
        this.DetailStr = DetailStr;
    }

    public String getCreatorName() {
        return CreatorName;
    }

    public void setCreatorName(String CreatorName) {
        this.CreatorName = CreatorName;
    }

    public List<DetailsBean> getDetails() {
        return Details;
    }

    public void setDetails(List<DetailsBean> Details) {
        this.Details = Details;
    }

    public static class DetailsBean {
        private int ID;
        private String CompanyCD;
        private String ExhibitionNo;
        private int ProductID;
        private int ProductCount;
        private int UnitID;
        private int TotalFee;
        private String Remark;
        private String ModifiedDate;
        private String ModifiedUserID;
        private int UsedPrice;
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

        public int getProductCount() {
            return ProductCount;
        }

        public void setProductCount(int ProductCount) {
            this.ProductCount = ProductCount;
        }

        public int getUnitID() {
            return UnitID;
        }

        public void setUnitID(int UnitID) {
            this.UnitID = UnitID;
        }

        public int getTotalFee() {
            return TotalFee;
        }

        public void setTotalFee(int TotalFee) {
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

        public int getUsedPrice() {
            return UsedPrice;
        }

        public void setUsedPrice(int UsedPrice) {
            this.UsedPrice = UsedPrice;
        }

        public int getExhibitionId() {
            return ExhibitionId;
        }

        public void setExhibitionId(int ExhibitionId) {
            this.ExhibitionId = ExhibitionId;
        }
    }
}
