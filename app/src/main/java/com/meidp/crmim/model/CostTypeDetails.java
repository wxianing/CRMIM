package com.meidp.crmim.model;

import java.io.Serializable;

/**
 * Package： com.meidp.crmim.model
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/7/29
 */
public class CostTypeDetails implements Serializable {

    /**
     * ID : 31
     * CompanyCD : C1002
     * CodeName : 餐补
     * Flag : 804
     * Description :
     * UsedStatus : 1
     * ModifiedDate : 2016-07-22 10:06:44
     * ModifiedUserID : 1
     * FeeSubjectsNo : 6602004
     */

    private int ID;
    private String CompanyCD;
    private String CodeName;
    private int Flag;
    private String Description;
    private String UsedStatus;
    private String ModifiedDate;
    private String ModifiedUserID;
    private String FeeSubjectsNo;

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

    public String getCodeName() {
        return CodeName;
    }

    public void setCodeName(String CodeName) {
        this.CodeName = CodeName;
    }

    public int getFlag() {
        return Flag;
    }

    public void setFlag(int Flag) {
        this.Flag = Flag;
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

    public String getFeeSubjectsNo() {
        return FeeSubjectsNo;
    }

    public void setFeeSubjectsNo(String FeeSubjectsNo) {
        this.FeeSubjectsNo = FeeSubjectsNo;
    }
}
