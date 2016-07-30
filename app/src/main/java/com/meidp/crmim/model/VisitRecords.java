package com.meidp.crmim.model;

import java.io.Serializable;

/**
 * Package： com.meidp.crmim.model
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/7/20
 */
public class VisitRecords implements Serializable {

    /**
     * ID : 1
     * ContactNo : sample string 2
     * Title : sample string 3
     * Contents : sample string 4
     * CustID : 1
     * CustName : sample string 5
     * CustLinkMan : 1
     * CustLinkManName : sample string 6
     * LinkTel : sample string 7
     * LinkDate : 2016-07-20 10:59:29
     * ModifiedDate : 2016-07-20 10:59:29
     * Linker : 1
     * LocationAddress : sample string 8
     */

    private int ID;
    private String ContactNo;
    private String Title;
    private String Contents;
    private int CustID;
    private String CustName;
    private int CustLinkMan;
    private String CustLinkManName;
    private String LinkTel;
    private String LinkDate;
    private String ModifiedDate;
    private int Linker;
    private String LocationAddress;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getContactNo() {
        return ContactNo;
    }

    public void setContactNo(String ContactNo) {
        this.ContactNo = ContactNo;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getContents() {
        return Contents;
    }

    public void setContents(String Contents) {
        this.Contents = Contents;
    }

    public int getCustID() {
        return CustID;
    }

    public void setCustID(int CustID) {
        this.CustID = CustID;
    }

    public String getCustName() {
        return CustName;
    }

    public void setCustName(String CustName) {
        this.CustName = CustName;
    }

    public int getCustLinkMan() {
        return CustLinkMan;
    }

    public void setCustLinkMan(int CustLinkMan) {
        this.CustLinkMan = CustLinkMan;
    }

    public String getCustLinkManName() {
        return CustLinkManName;
    }

    public void setCustLinkManName(String CustLinkManName) {
        this.CustLinkManName = CustLinkManName;
    }

    public String getLinkTel() {
        return LinkTel;
    }

    public void setLinkTel(String LinkTel) {
        this.LinkTel = LinkTel;
    }

    public String getLinkDate() {
        return LinkDate;
    }

    public void setLinkDate(String LinkDate) {
        this.LinkDate = LinkDate;
    }

    public String getModifiedDate() {
        return ModifiedDate;
    }

    public void setModifiedDate(String ModifiedDate) {
        this.ModifiedDate = ModifiedDate;
    }

    public int getLinker() {
        return Linker;
    }

    public void setLinker(int Linker) {
        this.Linker = Linker;
    }

    public String getLocationAddress() {
        return LocationAddress;
    }

    public void setLocationAddress(String LocationAddress) {
        this.LocationAddress = LocationAddress;
    }
}
