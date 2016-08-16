package com.meidp.crmim.model;

import java.io.Serializable;

/**
 * Package： com.meidp.crmim.model
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/8/9
 */
public class Secretary implements Serializable {

    /**
     * CreateTimeStr : 2016-08-16 12:50
     * Id : 205
     * Title : 项目<10MP灰阶一体双屏>已发货
     * BillId : 116
     * BillTypeFlag : 5
     * BillTypeCode : 4
     * StatusName :
     * Msg :
     * CreateTime : 2016-08-16 12:50:59
     * UserId : 3
     * Status : 1
     * StatusTime : 2016-08-16 12:50:59
     */

    private String CreateTimeStr;
    private int Id;
    private String Title;
    private int BillId;
    private int BillTypeFlag;
    private int BillTypeCode;
    private String StatusName;
    private String Msg;
    private String CreateTime;
    private int UserId;
    private int Status;
    private String StatusTime;

    public String getCreateTimeStr() {
        return CreateTimeStr;
    }

    public void setCreateTimeStr(String CreateTimeStr) {
        this.CreateTimeStr = CreateTimeStr;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public int getBillId() {
        return BillId;
    }

    public void setBillId(int BillId) {
        this.BillId = BillId;
    }

    public int getBillTypeFlag() {
        return BillTypeFlag;
    }

    public void setBillTypeFlag(int BillTypeFlag) {
        this.BillTypeFlag = BillTypeFlag;
    }

    public int getBillTypeCode() {
        return BillTypeCode;
    }

    public void setBillTypeCode(int BillTypeCode) {
        this.BillTypeCode = BillTypeCode;
    }

    public String getStatusName() {
        return StatusName;
    }

    public void setStatusName(String StatusName) {
        this.StatusName = StatusName;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String Msg) {
        this.Msg = Msg;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String CreateTime) {
        this.CreateTime = CreateTime;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public String getStatusTime() {
        return StatusTime;
    }

    public void setStatusTime(String StatusTime) {
        this.StatusTime = StatusTime;
    }
}
