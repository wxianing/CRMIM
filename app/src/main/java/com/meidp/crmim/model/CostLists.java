package com.meidp.crmim.model;

import java.io.Serializable;

/**
 * Package： com.meidp.crmim.model
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/7/30
 */
public class CostLists implements Serializable {

    /**
     * ID : 0
     * ExpCode : null
     * Title : 城市建设
     * Applyor : 3
     * AriseDate : 2016-07-29 14:36:07
     * NeedDate :
     * TotalAmount : null
     * PayType : null
     * Reason : 送礼物
     * Status : 1
     * Creator : 1875
     * CreateDate : 2016-07-29 14:36:07
     * ExpType : 803
     * CustID : 0
     * ModifiedDate : null
     * Confirmor : null
     * ConfirmDate : null
     * ProjectID : null
     * Attachment : null
     */

    private int ID;
    private String Title;
    private int Applyor;
    private String AriseDate;
    private String NeedDate;
    private String Reason;
    private String Status;
    private int Creator;
    private String CreateDate;
    private int ExpType;
    private int CustID;
    private String FlowStatusName;

    public String getFlowStatusName() {
        return FlowStatusName;
    }

    public void setFlowStatusName(String flowStatusName) {
        FlowStatusName = flowStatusName;
    }

    /**
     * ExpCode : null
     * TotalAmount : 50000
     * PayType : null
     * ModifiedDate : null
     * Confirmor : null
     * ConfirmDate : null
     * ProjectID : 57
     * ProjectName : 心血管疾病分析设备
     * Attachment : null
     * CreatorName : 汪志红
     */

    private Object ExpCode;
    private int TotalAmount;
    private Object PayType;
    private Object ModifiedDate;
    private Object Confirmor;
    private Object ConfirmDate;
    private int ProjectID;
    private String ProjectName;
    private Object Attachment;
    private String CreatorName;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public Object getExpCode() {
        return ExpCode;
    }

    public void setExpCode(Object ExpCode) {
        this.ExpCode = ExpCode;
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

    public int getProjectID() {
        return ProjectID;
    }

    public void setProjectID(int ProjectID) {
        this.ProjectID = ProjectID;
    }

    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String ProjectName) {
        this.ProjectName = ProjectName;
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
