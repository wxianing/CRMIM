package com.meidp.crmim.model;

import java.io.Serializable;

/**
 * Package： com.meidp.crmim.model
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/8/11
 */
public class Feedbacks implements Serializable {

    /**
     * ID : 16
     * CompanyCD : C1002
     * AdviceType : 1
     * FromUserID : 1873
     * DoUserID : 1874
     * Title : 客服反馈无人跟进
     * Content : 投诉多次
     * CreateDate : 2016-08-03 15:16:28
     * DisplayName : 0
     */

    private int ID;
    private String CompanyCD;
    private String AdviceType;
    private int FromUserID;
    private int DoUserID;
    private String Title;
    private String Content;
    private String CreateDate;
    private String DisplayName;

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

    public String getAdviceType() {
        return AdviceType;
    }

    public void setAdviceType(String AdviceType) {
        this.AdviceType = AdviceType;
    }

    public int getFromUserID() {
        return FromUserID;
    }

    public void setFromUserID(int FromUserID) {
        this.FromUserID = FromUserID;
    }

    public int getDoUserID() {
        return DoUserID;
    }

    public void setDoUserID(int DoUserID) {
        this.DoUserID = DoUserID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String CreateDate) {
        this.CreateDate = CreateDate;
    }

    public String getDisplayName() {
        return DisplayName;
    }

    public void setDisplayName(String DisplayName) {
        this.DisplayName = DisplayName;
    }
}
