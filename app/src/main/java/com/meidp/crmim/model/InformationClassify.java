package com.meidp.crmim.model;

import java.io.Serializable;

/**
 * Package： com.meidp.crmim.model
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/8/8
 */
public class InformationClassify implements Serializable {

    /**
     * ID : 1
     * CompanyCD : C1002
     * TypeName : 企业文化
     * SupperTypeID : 0
     * Path : 1
     * icon : http://beacon.meidp.com/upload/Basic/Culture/1.png
     * NoReadTotal : 2
     */

    private int ID;
    private String CompanyCD;
    private String TypeName;
    private int SupperTypeID;
    private String Path;
    private String icon;
    private int NoReadTotal;

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

    public String getTypeName() {
        return TypeName;
    }

    public void setTypeName(String TypeName) {
        this.TypeName = TypeName;
    }

    public int getSupperTypeID() {
        return SupperTypeID;
    }

    public void setSupperTypeID(int SupperTypeID) {
        this.SupperTypeID = SupperTypeID;
    }

    public String getPath() {
        return Path;
    }

    public void setPath(String Path) {
        this.Path = Path;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getNoReadTotal() {
        return NoReadTotal;
    }

    public void setNoReadTotal(int NoReadTotal) {
        this.NoReadTotal = NoReadTotal;
    }
}
