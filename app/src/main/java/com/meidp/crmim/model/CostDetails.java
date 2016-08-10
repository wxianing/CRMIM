package com.meidp.crmim.model;

import java.io.Serializable;

/**
 * Package： com.meidp.crmim.model
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/8/10
 */
public class CostDetails implements Serializable {

    /**
     * ID : 0
     * ExpCode : null
     * Title : 中医院一键式医疗系统
     * Applyor : 1
     * AriseDate : 2016-08-09 00:00:00
     * NeedDate : 2016-08-09 00:00:00
     * TotalAmount : 200
     * PayType : null
     * Reason : 请领导聚餐
     * Status : 1
     * Creator : 1874
     * CreateDate : 2016-08-09 14:41:57
     * ExpType : 804
     * CustID : 254
     * ModifiedDate : null
     * Confirmor : null
     * ConfirmDate : null
     * ProjectID : null
     * Attachment : null
     * CreatorName : 汪志红
     */

    private int ID;
    private Object ExpCode;
    private String Title;
    private int Applyor;
    private String AriseDate;
    private String NeedDate;
    private int TotalAmount;
    private Object PayType;
    private String Reason;
    private String Status;
    private int Creator;
    private String CreateDate;
    private int ExpType;
    private int CustID;
    private Object ModifiedDate;
    private Object Confirmor;
    private Object ConfirmDate;
    private Object ProjectID;
    private Object Attachment;
    private String CreatorName;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Object getExpCode() {
        return ExpCode;
    }

    public void setExpCode(Object ExpCode) {
        this.ExpCode = ExpCode;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public int getApplyor() {
        return Applyor;
    }

    public void setApplyor(int Applyor) {
        this.Applyor = Applyor;
    }

    public String getAriseDate() {
        return AriseDate;
    }

    public void setAriseDate(String AriseDate) {
        this.AriseDate = AriseDate;
    }

    public String getNeedDate() {
        return NeedDate;
    }

    public void setNeedDate(String NeedDate) {
        this.NeedDate = NeedDate;
    }

    public int getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(int TotalAmount) {
        this.TotalAmount = TotalAmount;
    }

    public Object getPayType() {
        return PayType;
    }

    public void setPayType(Object PayType) {
        this.PayType = PayType;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String Reason) {
        this.Reason = Reason;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public int getCreator() {
        return Creator;
    }

    public void setCreator(int Creator) {
        this.Creator = Creator;
    }

    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String CreateDate) {
        this.CreateDate = CreateDate;
    }

    public int getExpType() {
        return ExpType;
    }

    public void setExpType(int ExpType) {
        this.ExpType = ExpType;
    }

    public int getCustID() {
        return CustID;
    }

    public void setCustID(int CustID) {
        this.CustID = CustID;
    }

    public Object getModifiedDate() {
        return ModifiedDate;
    }

    public void setModifiedDate(Object ModifiedDate) {
        this.ModifiedDate = ModifiedDate;
    }

    public Object getConfirmor() {
        return Confirmor;
    }

    public void setConfirmor(Object Confirmor) {
        this.Confirmor = Confirmor;
    }

    public Object getConfirmDate() {
        return ConfirmDate;
    }

    public void setConfirmDate(Object ConfirmDate) {
        this.ConfirmDate = ConfirmDate;
    }

    public Object getProjectID() {
        return ProjectID;
    }

    public void setProjectID(Object ProjectID) {
        this.ProjectID = ProjectID;
    }

    public Object getAttachment() {
        return Attachment;
    }

    public void setAttachment(Object Attachment) {
        this.Attachment = Attachment;
    }

    public String getCreatorName() {
        return CreatorName;
    }

    public void setCreatorName(String CreatorName) {
        this.CreatorName = CreatorName;
    }
}
