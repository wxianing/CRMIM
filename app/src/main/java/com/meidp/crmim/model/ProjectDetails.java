package com.meidp.crmim.model;

import java.io.Serializable;
import java.util.List;

/**
 * Package： com.meidp.crmim.model
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/7/22
 */
public class ProjectDetails implements Serializable {


    /**
     * ConstructionDetails : []
     * ID : 39
     * ProjectNo : XM2016070018
     * ProjectName : 华硕主板
     * CreateDate : 2016-07-21 15:54:37
     * Creator : 1873
     * StartDate : null
     * EndDate : null
     * CustID : 0
     * CustLinkMan : 老李
     * LinkTel : 15889356386
     * SuccessRate : 0.5
     * Remark : null
     * Investment : 150000
     * LinkMan : 0
     */

    private int ID;
    private String ProjectNo;
    private String ProjectName;
    private String CreateDate;
    private int Creator;
    private Object StartDate;
    private Object EndDate;
    private int CustID;
    private String CustLinkMan;
    private String LinkTel;
    private double SuccessRate;
    private String Remark;
    private int Investment;
    private int LinkMan;
    private List<?> ConstructionDetails;

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

    public int getCreator() {
        return Creator;
    }

    public void setCreator(int Creator) {
        this.Creator = Creator;
    }

    public Object getStartDate() {
        return StartDate;
    }

    public void setStartDate(Object StartDate) {
        this.StartDate = StartDate;
    }

    public Object getEndDate() {
        return EndDate;
    }

    public void setEndDate(Object EndDate) {
        this.EndDate = EndDate;
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

    public void setRemark(String remark) {
        Remark = remark;
    }

    public int getInvestment() {
        return Investment;
    }

    public void setInvestment(int Investment) {
        this.Investment = Investment;
    }

    public int getLinkMan() {
        return LinkMan;
    }

    public void setLinkMan(int LinkMan) {
        this.LinkMan = LinkMan;
    }

    public List<?> getConstructionDetails() {
        return ConstructionDetails;
    }

    public void setConstructionDetails(List<?> ConstructionDetails) {
        this.ConstructionDetails = ConstructionDetails;
    }
}
