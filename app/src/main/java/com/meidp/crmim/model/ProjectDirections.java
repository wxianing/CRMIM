package com.meidp.crmim.model;

import java.io.Serializable;

/**
 * Package： com.meidp.crmim.model
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/8/15
 */
public class ProjectDirections implements Serializable {

    /**
     * ID : 808
     * CompanyCD : C1002
     * TypeFlag : 1
     * TypeCode : 7
     * TypeName : 3D解剖
     * Description : 3D解剖
     * UsedStatus : 1
     * ModifiedDate : 2016-08-02 14:26:47
     * ModifiedUserID : 1
     */

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
