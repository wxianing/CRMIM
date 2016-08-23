package com.meidp.crmim.model;

import java.io.Serializable;

/**
 * Package： com.meidp.crmim.model
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/8/22
 */
public class TeamMembers implements Serializable {

    /**
     * ID : 121
     * CompanyCD : C1002
     * Creator : 1875
     * LinkmanGroupID : null
     * LinkmanName : 李三
     * Sex : 1
     * CompanyName : null
     * Birthday : null
     * MobilePhone : 15889356386
     * CompanyPhone : 2551887
     * Email : 453525331@qq.com
     * Fax : null
     * QQ : 453525331
     * ICQ : null
     * MSN : null
     * CompanyWebsite : null
     * CompanyAddress : null
     * principalship : null
     * Remark :
     * CreateTime : 2016-08-22 15:20:13
     * Position : 软件测试师
     */

    private int ID;
    private String CompanyCD;
    private int Creator;
    private String LinkmanName;
    private String Sex;
    private String MobilePhone;
    private String CompanyPhone;
    private String Email;
    private String QQ;
    private String Remark;
    private String CreateTime;
    private String Position;

    private String CompanyName;

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

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

    public int getCreator() {
        return Creator;
    }

    public void setCreator(int Creator) {
        this.Creator = Creator;
    }

    public String getLinkmanName() {
        return LinkmanName;
    }

    public void setLinkmanName(String LinkmanName) {
        this.LinkmanName = LinkmanName;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String Sex) {
        this.Sex = Sex;
    }

    public String getMobilePhone() {
        return MobilePhone;
    }

    public void setMobilePhone(String MobilePhone) {
        this.MobilePhone = MobilePhone;
    }

    public String getCompanyPhone() {
        return CompanyPhone;
    }

    public void setCompanyPhone(String CompanyPhone) {
        this.CompanyPhone = CompanyPhone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getQQ() {
        return QQ;
    }

    public void setQQ(String QQ) {
        this.QQ = QQ;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String CreateTime) {
        this.CreateTime = CreateTime;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String Position) {
        this.Position = Position;
    }
}
