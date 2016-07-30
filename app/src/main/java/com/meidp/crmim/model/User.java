package com.meidp.crmim.model;

import java.io.Serializable;

/**
 * Package com.meist.pinfan.model
 * 作 用：
 * 创建人：wxianing
 * 时 间：2016/6/12
 */
public class User implements Serializable {


    /**
     * CompanyCD : sample string 1
     * UserID : 2
     * LoginName : sample string 3
     * EmployeeID : 1
     * IsRoot : sample string 4
     * EmployeeName : sample string 5
     * PhotoURL : sample string 6
     * DeptID : 7
     * DeptName : sample string 8
     * ZhiWu : sample string 9
     * userCode : sample string 10
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
    private String ZhiWu;
    private String userCode;
    private String rongcloudToken;

    public String getRongcloudToken() {
        return rongcloudToken;
    }

    public void setRongcloudToken(String rongcloudToken) {
        this.rongcloudToken = rongcloudToken;
    }

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

    public String getZhiWu() {
        return ZhiWu;
    }

    public void setZhiWu(String ZhiWu) {
        this.ZhiWu = ZhiWu;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
}
