package com.meidp.crmim.model;

import java.io.Serializable;

/**
 * Package： com.meidp.crmim.model
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/8/11
 */
public class Teams implements Serializable {

    /**
     * TeamRemark :
     * PersonCount : 5
     * TeamName : 销售部成员
     * EmployeeNames : manager,majordomo,王健林,王显宁,马云,
     * CreateDateStr : 2016-08-11 22:02
     * Creator : 1875
     * Employees : 1885,1886,1879,1875,1876
     * CreateUserId : 0
     * ID : 6
     * CreateDate : 2016-08-11 22:02:31
     */

    private String TeamRemark;
    private int PersonCount;
    private String TeamName;
    private String EmployeeNames;
    private String CreateDateStr;
    private int Creator;
    private String Employees;
    private int CreateUserId;
    private int ID;
    private String CreateDate;

    public String getTeamRemark() {
        return TeamRemark;
    }

    public void setTeamRemark(String TeamRemark) {
        this.TeamRemark = TeamRemark;
    }

    public int getPersonCount() {
        return PersonCount;
    }

    public void setPersonCount(int PersonCount) {
        this.PersonCount = PersonCount;
    }

    public String getTeamName() {
        return TeamName;
    }

    public void setTeamName(String TeamName) {
        this.TeamName = TeamName;
    }

    public String getEmployeeNames() {
        return EmployeeNames;
    }

    public void setEmployeeNames(String EmployeeNames) {
        this.EmployeeNames = EmployeeNames;
    }

    public String getCreateDateStr() {
        return CreateDateStr;
    }

    public void setCreateDateStr(String CreateDateStr) {
        this.CreateDateStr = CreateDateStr;
    }

    public int getCreator() {
        return Creator;
    }

    public void setCreator(int Creator) {
        this.Creator = Creator;
    }

    public String getEmployees() {
        return Employees;
    }

    public void setEmployees(String Employees) {
        this.Employees = Employees;
    }

    public int getCreateUserId() {
        return CreateUserId;
    }

    public void setCreateUserId(int CreateUserId) {
        this.CreateUserId = CreateUserId;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String CreateDate) {
        this.CreateDate = CreateDate;
    }
}
