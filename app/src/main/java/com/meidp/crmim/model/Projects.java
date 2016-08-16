package com.meidp.crmim.model;

import java.io.Serializable;

/**
 * Package： com.meidp.crmim.model
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/7/18
 */
public class Projects implements Serializable {

    /**
     * ID : 38
     * ProjectNo : XM2016070017
     * ProjectName : 10MP灰阶一体双屏
     * CreateDate : 2016-07-21 15:39:22
     * Creator : 1875
     * StartDate :
     * EndDate :
     * CustID : 253
     * CustLinkMan : 郑三炮
     * LinkTel : 15889356386
     * SuccessRate : 0.8
     * Remark :
     * Investment : 150000
     * AcceptMoney : 0
     * AcceptDate :
     * ExpectAcceptDate :
     * LinkMan : 0
     * ProjectDirectionId : 0
     * ProjectDirectionName :
     * CreatorName : 王显宁
     * CompanyId : 0
     * CompanyName :
     * DepartmentName : null
     * ZhiWu : null
     */

    private int ID;
    private String ProjectNo;
    private String ProjectName;
    private String CreateDate;
    private int Creator;
    private String StartDate;
    private String EndDate;
    private int CustID;
    private String CustLinkMan;
    private String LinkTel;
    private double SuccessRate;
    private String Remark;
    private int Investment;
    private int AcceptMoney;
    private String AcceptDate;
    private String ExpectAcceptDate;
    private int LinkMan;
    private int ProjectDirectionId;
    private String ProjectDirectionName;
    private String CreatorName;
    private int CompanyId;
    private String CompanyName;
    private Object DepartmentName;
    private Object ZhiWu;
    /**
     * IsAppoint : null
     * Status : 2
     * StatusName : 备货中
     */

    private Object IsAppoint;
    private int Status;
    private String StatusName;

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

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String StartDate) {
        this.StartDate = StartDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String EndDate) {
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

    public void setRemark(String Remark) {
        this.Remark = Remark;
    }

    public int getInvestment() {
        return Investment;
    }

    public void setInvestment(int Investment) {
        this.Investment = Investment;
    }

    public int getAcceptMoney() {
        return AcceptMoney;
    }

    public void setAcceptMoney(int AcceptMoney) {
        this.AcceptMoney = AcceptMoney;
    }

    public String getAcceptDate() {
        return AcceptDate;
    }

    public void setAcceptDate(String AcceptDate) {
        this.AcceptDate = AcceptDate;
    }

    public String getExpectAcceptDate() {
        return ExpectAcceptDate;
    }

    public void setExpectAcceptDate(String ExpectAcceptDate) {
        this.ExpectAcceptDate = ExpectAcceptDate;
    }

    public int getLinkMan() {
        return LinkMan;
    }

    public void setLinkMan(int LinkMan) {
        this.LinkMan = LinkMan;
    }

    public int getProjectDirectionId() {
        return ProjectDirectionId;
    }

    public void setProjectDirectionId(int ProjectDirectionId) {
        this.ProjectDirectionId = ProjectDirectionId;
    }

    public String getProjectDirectionName() {
        return ProjectDirectionName;
    }

    public void setProjectDirectionName(String ProjectDirectionName) {
        this.ProjectDirectionName = ProjectDirectionName;
    }

    public String getCreatorName() {
        return CreatorName;
    }

    public void setCreatorName(String CreatorName) {
        this.CreatorName = CreatorName;
    }

    public int getCompanyId() {
        return CompanyId;
    }

    public void setCompanyId(int CompanyId) {
        this.CompanyId = CompanyId;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String CompanyName) {
        this.CompanyName = CompanyName;
    }

    public Object getDepartmentName() {
        return DepartmentName;
    }

    public void setDepartmentName(Object DepartmentName) {
        this.DepartmentName = DepartmentName;
    }

    public Object getZhiWu() {
        return ZhiWu;
    }

    public void setZhiWu(Object ZhiWu) {
        this.ZhiWu = ZhiWu;
    }

    public Object getIsAppoint() {
        return IsAppoint;
    }

    public void setIsAppoint(Object IsAppoint) {
        this.IsAppoint = IsAppoint;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public String getStatusName() {
        return StatusName;
    }

    public void setStatusName(String StatusName) {
        this.StatusName = StatusName;
    }
}
