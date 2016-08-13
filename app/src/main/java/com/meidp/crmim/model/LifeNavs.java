package com.meidp.crmim.model;

import java.io.Serializable;
import java.util.List;

/**
 * Package： com.meidp.crmim.model
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/8/13
 */
public class LifeNavs implements Serializable {

    /**
     * ID : 1
     * YearNav : sample string 2
     * EmployeeID : 3
     * TotalMineMoneyByYear : 1
     * TotalMineMoneyByFuture : 1
     * TeamCountByYear : 1
     * TeamCountByFuture : 1
     * VisitCountByDay : 1
     * VisitCountByYear : 1
     * CustomCountByYear : 1
     * ProjectCountByYear : 1
     * ProjectMoneyByYear : 1
     * SaleMoneyByYear : 1
     * CreateDate : 2016-08-13 14:25:07
     * CreateUserId : 1
     * ModifyDate : 2016-08-13 14:25:07
     * CreateDateStr : sample string 6
     * Changes : [{"ID":1,"EmployeeNavID":2,"ChanceType":3,"CustTotalCount":1,"ChangeContent":"sample string 4","ChanceTypeName":"sample string 5"},{"ID":1,"EmployeeNavID":2,"ChanceType":3,"CustTotalCount":1,"ChangeContent":"sample string 4","ChanceTypeName":"sample string 5"},{"ID":1,"EmployeeNavID":2,"ChanceType":3,"CustTotalCount":1,"ChangeContent":"sample string 4","ChanceTypeName":"sample string 5"}]
     * ChangeStr : sample string 7
     * ChanceTypeList : [{"ID":1,"CompanyCD":"sample string 2","TypeFlag":3,"TypeCode":4,"TypeName":"sample string 5","Description":"sample string 6","UsedStatus":"sample string 7","ModifiedDate":"2016-08-13 14:25:07","ModifiedUserID":"sample string 8"},{"ID":1,"CompanyCD":"sample string 2","TypeFlag":3,"TypeCode":4,"TypeName":"sample string 5","Description":"sample string 6","UsedStatus":"sample string 7","ModifiedDate":"2016-08-13 14:25:07","ModifiedUserID":"sample string 8"},{"ID":1,"CompanyCD":"sample string 2","TypeFlag":3,"TypeCode":4,"TypeName":"sample string 5","Description":"sample string 6","UsedStatus":"sample string 7","ModifiedDate":"2016-08-13 14:25:07","ModifiedUserID":"sample string 8"}]
     */

    private int ID;
    private String YearNav;
    private int EmployeeID;
    private int TotalMineMoneyByYear;
    private int TotalMineMoneyByFuture;
    private int TeamCountByYear;
    private int TeamCountByFuture;
    private int VisitCountByDay;
    private int VisitCountByYear;
    private int CustomCountByYear;
    private int ProjectCountByYear;
    private int ProjectMoneyByYear;
    private int SaleMoneyByYear;
    private String CreateDate;
    private int CreateUserId;
    private String ModifyDate;
    private String CreateDateStr;
    private String ChangeStr;
    /**
     * ID : 1
     * EmployeeNavID : 2
     * ChanceType : 3
     * CustTotalCount : 1
     * ChangeContent : sample string 4
     * ChanceTypeName : sample string 5
     */

    private List<ChangesBean> Changes;
    /**
     * ID : 1
     * CompanyCD : sample string 2
     * TypeFlag : 3
     * TypeCode : 4
     * TypeName : sample string 5
     * Description : sample string 6
     * UsedStatus : sample string 7
     * ModifiedDate : 2016-08-13 14:25:07
     * ModifiedUserID : sample string 8
     */

    private List<ChanceTypeListBean> ChanceTypeList;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getYearNav() {
        return YearNav;
    }

    public void setYearNav(String YearNav) {
        this.YearNav = YearNav;
    }

    public int getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(int EmployeeID) {
        this.EmployeeID = EmployeeID;
    }

    public int getTotalMineMoneyByYear() {
        return TotalMineMoneyByYear;
    }

    public void setTotalMineMoneyByYear(int TotalMineMoneyByYear) {
        this.TotalMineMoneyByYear = TotalMineMoneyByYear;
    }

    public int getTotalMineMoneyByFuture() {
        return TotalMineMoneyByFuture;
    }

    public void setTotalMineMoneyByFuture(int TotalMineMoneyByFuture) {
        this.TotalMineMoneyByFuture = TotalMineMoneyByFuture;
    }

    public int getTeamCountByYear() {
        return TeamCountByYear;
    }

    public void setTeamCountByYear(int TeamCountByYear) {
        this.TeamCountByYear = TeamCountByYear;
    }

    public int getTeamCountByFuture() {
        return TeamCountByFuture;
    }

    public void setTeamCountByFuture(int TeamCountByFuture) {
        this.TeamCountByFuture = TeamCountByFuture;
    }

    public int getVisitCountByDay() {
        return VisitCountByDay;
    }

    public void setVisitCountByDay(int VisitCountByDay) {
        this.VisitCountByDay = VisitCountByDay;
    }

    public int getVisitCountByYear() {
        return VisitCountByYear;
    }

    public void setVisitCountByYear(int VisitCountByYear) {
        this.VisitCountByYear = VisitCountByYear;
    }

    public int getCustomCountByYear() {
        return CustomCountByYear;
    }

    public void setCustomCountByYear(int CustomCountByYear) {
        this.CustomCountByYear = CustomCountByYear;
    }

    public int getProjectCountByYear() {
        return ProjectCountByYear;
    }

    public void setProjectCountByYear(int ProjectCountByYear) {
        this.ProjectCountByYear = ProjectCountByYear;
    }

    public int getProjectMoneyByYear() {
        return ProjectMoneyByYear;
    }

    public void setProjectMoneyByYear(int ProjectMoneyByYear) {
        this.ProjectMoneyByYear = ProjectMoneyByYear;
    }

    public int getSaleMoneyByYear() {
        return SaleMoneyByYear;
    }

    public void setSaleMoneyByYear(int SaleMoneyByYear) {
        this.SaleMoneyByYear = SaleMoneyByYear;
    }

    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String CreateDate) {
        this.CreateDate = CreateDate;
    }

    public int getCreateUserId() {
        return CreateUserId;
    }

    public void setCreateUserId(int CreateUserId) {
        this.CreateUserId = CreateUserId;
    }

    public String getModifyDate() {
        return ModifyDate;
    }

    public void setModifyDate(String ModifyDate) {
        this.ModifyDate = ModifyDate;
    }

    public String getCreateDateStr() {
        return CreateDateStr;
    }

    public void setCreateDateStr(String CreateDateStr) {
        this.CreateDateStr = CreateDateStr;
    }

    public String getChangeStr() {
        return ChangeStr;
    }

    public void setChangeStr(String ChangeStr) {
        this.ChangeStr = ChangeStr;
    }

    public List<ChangesBean> getChanges() {
        return Changes;
    }

    public void setChanges(List<ChangesBean> Changes) {
        this.Changes = Changes;
    }

    public List<ChanceTypeListBean> getChanceTypeList() {
        return ChanceTypeList;
    }

    public void setChanceTypeList(List<ChanceTypeListBean> ChanceTypeList) {
        this.ChanceTypeList = ChanceTypeList;
    }

    public static class ChangesBean {
        private int ID;
        private int EmployeeNavID;
        private int ChanceType;
        private int CustTotalCount;
        private String ChangeContent;
        private String ChanceTypeName;

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public int getEmployeeNavID() {
            return EmployeeNavID;
        }

        public void setEmployeeNavID(int EmployeeNavID) {
            this.EmployeeNavID = EmployeeNavID;
        }

        public int getChanceType() {
            return ChanceType;
        }

        public void setChanceType(int ChanceType) {
            this.ChanceType = ChanceType;
        }

        public int getCustTotalCount() {
            return CustTotalCount;
        }

        public void setCustTotalCount(int CustTotalCount) {
            this.CustTotalCount = CustTotalCount;
        }

        public String getChangeContent() {
            return ChangeContent;
        }

        public void setChangeContent(String ChangeContent) {
            this.ChangeContent = ChangeContent;
        }

        public String getChanceTypeName() {
            return ChanceTypeName;
        }

        public void setChanceTypeName(String ChanceTypeName) {
            this.ChanceTypeName = ChanceTypeName;
        }
    }

    public static class ChanceTypeListBean {
        private int ID;
        private String CompanyCD;
        private int TypeFlag;
        private int TypeCode;
        private String TypeName;
        private String Description;
        private String UsedStatus;
        private String ModifiedDate;
        private String ModifiedUserID;

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

        public int getTypeFlag() {
            return TypeFlag;
        }

        public void setTypeFlag(int TypeFlag) {
            this.TypeFlag = TypeFlag;
        }

        public int getTypeCode() {
            return TypeCode;
        }

        public void setTypeCode(int TypeCode) {
            this.TypeCode = TypeCode;
        }

        public String getTypeName() {
            return TypeName;
        }

        public void setTypeName(String TypeName) {
            this.TypeName = TypeName;
        }

        public String getDescription() {
            return Description;
        }

        public void setDescription(String Description) {
            this.Description = Description;
        }

        public String getUsedStatus() {
            return UsedStatus;
        }

        public void setUsedStatus(String UsedStatus) {
            this.UsedStatus = UsedStatus;
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
    }
}
