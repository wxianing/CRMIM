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
     * code : 0
     * enumcode : 0
     * msg : success
     * data : {"ID":2,"CompanyCD":"C1002","ExhibitionNo":"ZHSQ2016080042","ExhibitionNature":null,"LinkManName":"马云","LinkTel":"15889356386","Title":"画展","AttendPersons":100,"TotalMoney":0,"ExhibitionStartDate":"2016-08-12 00:00","ExhibitionEndDate":"2016-08-12 00:00","ExhibitionPlan":"文化交流","ExhibitionAim":"文化交流","UnionPartner":"王建林","PlanFee":null,"Competitors":"刘强东","Address":"北京朝阳区","Creator":1874,"CreateDate":"2016-08-12 19:01","Confirmor":null,"ConfirmDate":null,"ModifiedDate":"2016-08-12 19:01","ModifiedUserID":"1","CanViewUser":null,"BillStatus":1,"Details":[{"ID":2,"CompanyCD":null,"ExhibitionNo":"ZHSQ2016080042","ProductID":0,"ProductCount":2,"UnitID":0,"TotalFee":0,"Remark":null,"ModifiedDate":"2016-08-12 19:01:14","ModifiedUserID":null,"UsedPrice":0,"ExhibitionId":2}],"DetailStr":null,"CreatorName":"manager"}
     */

    private int code;
    private int enumcode;
    private String msg;
    /**
     * ID : 2
     * CompanyCD : C1002
     * ExhibitionNo : ZHSQ2016080042
     * ExhibitionNature : null
     * LinkManName : 马云
     * LinkTel : 15889356386
     * Title : 画展
     * AttendPersons : 100
     * TotalMoney : 0
     * ExhibitionStartDate : 2016-08-12 00:00
     * ExhibitionEndDate : 2016-08-12 00:00
     * ExhibitionPlan : 文化交流
     * ExhibitionAim : 文化交流
     * UnionPartner : 王建林
     * PlanFee : null
     * Competitors : 刘强东
     * Address : 北京朝阳区
     * Creator : 1874
     * CreateDate : 2016-08-12 19:01
     * Confirmor : null
     * ConfirmDate : null
     * ModifiedDate : 2016-08-12 19:01
     * ModifiedUserID : 1
     * CanViewUser : null
     * BillStatus : 1
     * Details : [{"ID":2,"CompanyCD":null,"ExhibitionNo":"ZHSQ2016080042","ProductID":0,"ProductCount":2,"UnitID":0,"TotalFee":0,"Remark":null,"ModifiedDate":"2016-08-12 19:01:14","ModifiedUserID":null,"UsedPrice":0,"ExhibitionId":2}]
     * DetailStr : null
     * CreatorName : manager
     */

    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getEnumcode() {
        return enumcode;
    }

    public void setEnumcode(int enumcode) {
        this.enumcode = enumcode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private int ID;
        private String CompanyCD;
        private String ExhibitionNo;
        private Object ExhibitionNature;
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
        private Object PlanFee;
        private String Competitors;
        private String Address;
        private int Creator;
        private String CreateDate;
        private Object Confirmor;
        private Object ConfirmDate;
        private String ModifiedDate;
        private String ModifiedUserID;
        private Object CanViewUser;
        private int BillStatus;
        private Object DetailStr;
        private String CreatorName;
        /**
         * ID : 2
         * CompanyCD : null
         * ExhibitionNo : ZHSQ2016080042
         * ProductID : 0
         * ProductCount : 2
         * UnitID : 0
         * TotalFee : 0
         * Remark : null
         * ModifiedDate : 2016-08-12 19:01:14
         * ModifiedUserID : null
         * UsedPrice : 0
         * ExhibitionId : 2
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

        public Object getExhibitionNature() {
            return ExhibitionNature;
        }

        public void setExhibitionNature(Object ExhibitionNature) {
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

        public Object getPlanFee() {
            return PlanFee;
        }

        public void setPlanFee(Object PlanFee) {
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

        public Object getCanViewUser() {
            return CanViewUser;
        }

        public void setCanViewUser(Object CanViewUser) {
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
            private Object CompanyCD;
            private String ExhibitionNo;
            private int ProductID;
            private int ProductCount;
            private int UnitID;
            private int TotalFee;
            private Object Remark;
            private String ModifiedDate;
            private Object ModifiedUserID;
            private int UsedPrice;
            private int ExhibitionId;

            public int getID() {
                return ID;
            }

            public void setID(int ID) {
                this.ID = ID;
            }

            public Object getCompanyCD() {
                return CompanyCD;
            }

            public void setCompanyCD(Object CompanyCD) {
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

            public Object getRemark() {
                return Remark;
            }

            public void setRemark(Object Remark) {
                this.Remark = Remark;
            }

            public String getModifiedDate() {
                return ModifiedDate;
            }

            public void setModifiedDate(String ModifiedDate) {
                this.ModifiedDate = ModifiedDate;
            }

            public Object getModifiedUserID() {
                return ModifiedUserID;
            }

            public void setModifiedUserID(Object ModifiedUserID) {
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
}
