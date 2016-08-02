package com.meidp.crmim.model;

import java.io.Serializable;

/**
 * Package： com.meidp.crmim.model
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/8/2
 */
public class WorkPlans implements Serializable {


    /**
     * ID : 400
     * AimNo : MBBH20160802000040
     * AimTitle : 拜访张治忠
     * AimContent : 外婆会哦豁VPN去1哦1哦thoseuse会哦1我爱哭个婆婆子理亏哦去Roy婆婆Tim1他
     * CreateDate : 2016-08-02 16:28:52
     * StartDate : 2099-12-31
     * EndDate : 2099-12-31
     * AimSortId : 805
     * AimSortName : 拜访
     * AimFlag : 1
     * AimDirectionId : 809
     * AimDirectionName : 一键式医疗系统
     */

    private int ID;
    private String AimNo;
    private String AimTitle;
    private String AimContent;
    private String CreateDate;
    private String StartDate;
    private String EndDate;
    private int AimSortId;
    private String AimSortName;
    private String AimFlag;
    private int AimDirectionId;
    private String AimDirectionName;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getAimNo() {
        return AimNo;
    }

    public void setAimNo(String AimNo) {
        this.AimNo = AimNo;
    }

    public String getAimTitle() {
        return AimTitle;
    }

    public void setAimTitle(String AimTitle) {
        this.AimTitle = AimTitle;
    }

    public String getAimContent() {
        return AimContent;
    }

    public void setAimContent(String AimContent) {
        this.AimContent = AimContent;
    }

    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String CreateDate) {
        this.CreateDate = CreateDate;
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

    public int getAimSortId() {
        return AimSortId;
    }

    public void setAimSortId(int AimSortId) {
        this.AimSortId = AimSortId;
    }

    public String getAimSortName() {
        return AimSortName;
    }

    public void setAimSortName(String AimSortName) {
        this.AimSortName = AimSortName;
    }

    public String getAimFlag() {
        return AimFlag;
    }

    public void setAimFlag(String AimFlag) {
        this.AimFlag = AimFlag;
    }

    public int getAimDirectionId() {
        return AimDirectionId;
    }

    public void setAimDirectionId(int AimDirectionId) {
        this.AimDirectionId = AimDirectionId;
    }

    public String getAimDirectionName() {
        return AimDirectionName;
    }

    public void setAimDirectionName(String AimDirectionName) {
        this.AimDirectionName = AimDirectionName;
    }
}
