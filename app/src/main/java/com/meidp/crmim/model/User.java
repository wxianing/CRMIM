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
     *
     *
     "CompanyCD": "C1002",
     "UserID": 3,
     "LoginName": "wang",
     "EmployeeID": 1875,
     "IsRoot": "0",
     "EmployeeName": "王显宁",
     "PhotoURL": "http://beacon.meidp.comC1002/20160709102108552577.jpg",
     "PothoData": null,
     "DeptID": 229,
     "DeptName": "销售一部",
     "ZhiWu": null,
     "userCode": null,
     "Mobile": "15889356386",
     "Email": null,
     "NickName": null,
     "rongcloudToken": "fus8AJ25Hn7uhYmEnrL4elA+OIcFr5b3w8RT1pmYxlcNiQi3j3zfLhwjArmCW5FBcnNFKjQCNsuQLwNiXsNt7Q=="
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
    /**
     * PothoData : null
     * Mobile : 15889356386
     * Email : null
     * NickName : null
     */

    private Object PothoData;
    private String Mobile;
    private Object Email;
    private Object NickName;


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

    public Object getPothoData() {
        return PothoData;
    }

    public void setPothoData(Object PothoData) {
        this.PothoData = PothoData;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String Mobile) {
        this.Mobile = Mobile;
    }

    public Object getEmail() {
        return Email;
    }

    public void setEmail(Object Email) {
        this.Email = Email;
    }

    public Object getNickName() {
        return NickName;
    }

    public void setNickName(Object NickName) {
        this.NickName = NickName;
    }
}
