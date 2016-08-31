package com.meidp.crmim.model;

import java.io.Serializable;

/**
 * Package： com.meidp.crmim.model
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/8/31
 */
public class PersonalLinkmans implements Serializable {

    private int LinkmanType;
    private String CompanyName;
    private String CompanyPhone;
    private String Email;
    private String LinkmanName;
    private String MobilePhone;
    private int Sex;
    private String Position;
    private int LinkEmployeeID;

    public int getLinkEmployeeID() {
        return LinkEmployeeID;
    }

    public void setLinkEmployeeID(int linkEmployeeID) {
        LinkEmployeeID = linkEmployeeID;
    }

    public int getLinkmanType() {
        return LinkmanType;
    }

    public void setLinkmanType(int linkmanType) {
        LinkmanType = linkmanType;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getCompanyPhone() {
        return CompanyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        CompanyPhone = companyPhone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getLinkmanName() {
        return LinkmanName;
    }

    public void setLinkmanName(String linkmanName) {
        LinkmanName = linkmanName;
    }

    public String getMobilePhone() {
        return MobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        MobilePhone = mobilePhone;
    }

    public int getSex() {
        return Sex;
    }

    public void setSex(int sex) {
        Sex = sex;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String position) {
        Position = position;
    }

}
