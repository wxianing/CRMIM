package com.meidp.crmim.model;

import java.io.Serializable;

/**
 * Package： com.meidp.crmim.model
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/8/3
 */
public class MyKnowledges implements Serializable {

    /**
     * ID : 44
     * CompanyCD : C1002
     * CultureTypeID : 9
     * Title : 巨烽显示简介
     * Culturetent : 深圳市巨烽显示科技有限公司是一家致力于显示技术创新与应用的国家级高新技术企业，产品覆盖临床、诊断、超声、内窥手术以及会诊等医疗影像显示领域，提供专业的定制产品服务。

     * Attachment :
     * CreateDeptID : 224
     * Creator : 1873
     * CreateDate : 2016-08-01 01:00:00
     * ModifiedDate : 2016-08-03 15:12:38
     * ModifiedUserID : 1
     */

    private int ID;
    private String CompanyCD;
    private int CultureTypeID;
    private String Title;
    private String Culturetent;
    private String Attachment;
    private int CreateDeptID;
    private int Creator;
    private String CreateDate;
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

    public int getCultureTypeID() {
        return CultureTypeID;
    }

    public void setCultureTypeID(int CultureTypeID) {
        this.CultureTypeID = CultureTypeID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getCulturetent() {
        return Culturetent;
    }

    public void setCulturetent(String Culturetent) {
        this.Culturetent = Culturetent;
    }

    public String getAttachment() {
        return Attachment;
    }

    public void setAttachment(String Attachment) {
        this.Attachment = Attachment;
    }

    public int getCreateDeptID() {
        return CreateDeptID;
    }

    public void setCreateDeptID(int CreateDeptID) {
        this.CreateDeptID = CreateDeptID;
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
