package com.meidp.crmim.model;

import java.io.Serializable;

/**
 * Package： com.meidp.crmim.model
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/7/20
 */
public class Friends implements Serializable {

    /**
     * CompanyCD : C1002
     * UserID : 1
     * LoginName : admin
     * EmployeeID : 1873
     * IsRoot : 0
     * EmployeeName : 和智
     * PhotoURL : /upload/head/160720142447276255.jpg
     * PothoData : null
     * DeptID : 224
     * DeptName : 信息部
     * ZhiWu : null
     * userCode : null
     * Mobile : 和智
     * Email : null
     * NickName : null
     * rongcloudToken : mWRfAyTAhdJT3/GJv8fIYlA+OIcFr5b3w8RT1pmYxldfZQJ5WkBV/I9RLBdSb2Tssu8GDmRDsytPzKMjGyfuVQ==
     */

    private String CompanyCD;
    private int UserID;
    private String LoginName;
    private int EmployeeID;
    private String IsRoot;
    private String EmployeeName;
    private String PhotoURL;
    private int DeptID;
    private String DeptName;
    private String Mobile;
    private String rongcloudToken;

    public String getCompanyCD() {
        return CompanyCD;
    }

    public void setCompanyCD(String CompanyCD) {
        this.CompanyCD = CompanyCD;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public String getLoginName() {
        return LoginName;
    }

    public void setLoginName(String LoginName) {
        this.LoginName = LoginName;
    }

    public int getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(int EmployeeID) {
        this.EmployeeID = EmployeeID;
    }

    public String getIsRoot() {
        return IsRoot;
    }

    public void setIsRoot(String IsRoot) {
        this.IsRoot = IsRoot;
    }

    public String getEmployeeName() {
        return EmployeeName;
    }

    public void setEmployeeName(String EmployeeName) {
        this.EmployeeName = EmployeeName;
    }

    public String getPhotoURL() {
        return PhotoURL;
    }

    public void setPhotoURL(String PhotoURL) {
        this.PhotoURL = PhotoURL;
    }

    public int getDeptID() {
        return DeptID;
    }

    public void setDeptID(int DeptID) {
        this.DeptID = DeptID;
    }

    public String getDeptName() {
        return DeptName;
    }

    public void setDeptName(String DeptName) {
        this.DeptName = DeptName;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String Mobile) {
        this.Mobile = Mobile;
    }

    public String getRongcloudToken() {
        return rongcloudToken;
    }

    public void setRongcloudToken(String rongcloudToken) {
        this.rongcloudToken = rongcloudToken;
    }
}
