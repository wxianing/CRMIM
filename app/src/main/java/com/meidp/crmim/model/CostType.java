package com.meidp.crmim.model;

import java.io.Serializable;

/**
 * Package： com.meidp.crmim.model
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/7/23
 */
public class CostType implements Serializable {

    /**
     * ID : 56
     * CompanyCD : C1002
     * TypeFlag : 7
     * TypeCode : 4
     * TypeName : 采购物品
     * Description :
     * UsedStatus : 1
     * ModifiedDate : 2009-06-26 17:32:06
     * ModifiedUserID : yangyang
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
