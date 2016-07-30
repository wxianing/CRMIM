package com.meidp.crmim.model;

import java.io.Serializable;

/**
 * Package： com.meidp.crmim.model
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/7/18
 */
public class Projects implements Serializable {

    private int ID;
    private String ProjectNo;
    private String ProjectName;
    private String CreateDate;
    private String Creator;
    private String StartDate;
    private String EdnDate;
    private int CustID;
    private String CustLinkMan;
    private String LinkTel;
    private double SuccessRate;
    private String Remark;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getProjectNo() {
        return ProjectNo;
    }

    public void setProjectNo(String ProjectNo) {
        this.ProjectNo = ProjectNo;
    }

    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String ProjectName) {
        this.ProjectName = ProjectName;
    }

    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String CreateDate) {
        this.CreateDate = CreateDate;
    }

    public String getCreator() {
        return Creator;
    }

    public void setCreator(String Creator) {
        this.Creator = Creator;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String StartDate) {
        this.StartDate = StartDate;
    }

    public String getEdnDate() {
        return EdnDate;
    }

    public void setEdnDate(String EdnDate) {
        this.EdnDate = EdnDate;
    }

    public int getCustID() {
        return CustID;
    }

    public void setCustID(int CustID) {
        this.CustID = CustID;
    }

    public String getCustLinkMan() {
        return CustLinkMan;
    }

    public void setCustLinkMan(String CustLinkMan) {
        this.CustLinkMan = CustLinkMan;
    }

    public String getLinkTel() {
        return LinkTel;
    }

    public void setLinkTel(String LinkTel) {
        this.LinkTel = LinkTel;
    }

    public double getSuccessRate() {
        return SuccessRate;
    }

    public void setSuccessRate(double SuccessRate) {
        this.SuccessRate = SuccessRate;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
    }
}
