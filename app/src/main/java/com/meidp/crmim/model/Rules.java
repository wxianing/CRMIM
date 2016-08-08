package com.meidp.crmim.model;

import java.io.Serializable;

/**
 * Package： com.meidp.crmim.model
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/8/8
 */
public class Rules implements Serializable {


    /**
     * ID : 6
     * CompanyCD : C1002
     * CultureTypeID : 1
     * Title : 巨烽服务承诺
     * Culturetent : 巨烽一贯坚持以“专注缔造专业、为客户创造价值”的服务宗旨，以“专业、协作、感恩”为理念，以“维护公司市场形象、为客户提供优质服务”为使命，竭诚为全球医疗器械设备商、终端医院和专用显示器设备用户提供高质量的产品和优质的服务。
     * Attachment :
     * CreateDeptID : 226
     * Creator : 2
     * CreateDate : 2016-08-03 01:00:00
     * ModifiedDate : 2016-08-03 15:12:22
     * ModifiedUserID : 1
     * ImgUrl : http://beacon.meidp.com/upload/201607/33.jpg
     * Summary : null
     * ReadCount : null
     * CollectCount : null
     * CommentCount : null
     * IsRead : 0
     * ClickUrl : http://beacon.meidp.com:98/Mobi/Office/CultureDocsInfo?Id6&UserId=1
     */

    private int ID;
    private String CompanyCD;
    private int CultureTypeID;
    private String Title;
    private String Culturetent;
    private String Attachment;
    private int CreateDeptID;
    private int Creator;
    private String CreateDate;
    private String ModifiedDate;
    private String ModifiedUserID;
    private String ImgUrl;
    private Object Summary;
    private Object ReadCount;
    private Object CollectCount;
    private Object CommentCount;
    private int IsRead;
    private String ClickUrl;

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

    public int getCultureTypeID() {
        return CultureTypeID;
    }

    public void setCultureTypeID(int CultureTypeID) {
        this.CultureTypeID = CultureTypeID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getCulturetent() {
        return Culturetent;
    }

    public void setCulturetent(String Culturetent) {
        this.Culturetent = Culturetent;
    }

    public String getAttachment() {
        return Attachment;
    }

    public void setAttachment(String Attachment) {
        this.Attachment = Attachment;
    }

    public int getCreateDeptID() {
        return CreateDeptID;
    }

    public void setCreateDeptID(int CreateDeptID) {
        this.CreateDeptID = CreateDeptID;
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

    public String getModifiedDate() {
        return ModifiedDate;
    }

    public void setModifiedDate(String ModifiedDate) {
        this.ModifiedDate = ModifiedDate;
    }

    public String getModifiedUserID() {
        return ModifiedUserID;
    }

    public void setModifiedUserID(String ModifiedUserID) {
        this.ModifiedUserID = ModifiedUserID;
    }

    public String getImgUrl() {
        return ImgUrl;
    }

    public void setImgUrl(String ImgUrl) {
        this.ImgUrl = ImgUrl;
    }

    public Object getSummary() {
        return Summary;
    }

    public void setSummary(Object Summary) {
        this.Summary = Summary;
    }

    public Object getReadCount() {
        return ReadCount;
    }

    public void setReadCount(Object ReadCount) {
        this.ReadCount = ReadCount;
    }

    public Object getCollectCount() {
        return CollectCount;
    }

    public void setCollectCount(Object CollectCount) {
        this.CollectCount = CollectCount;
    }

    public Object getCommentCount() {
        return CommentCount;
    }

    public void setCommentCount(Object CommentCount) {
        this.CommentCount = CommentCount;
    }

    public int getIsRead() {
        return IsRead;
    }

    public void setIsRead(int IsRead) {
        this.IsRead = IsRead;
    }

    public String getClickUrl() {
        return ClickUrl;
    }

    public void setClickUrl(String ClickUrl) {
        this.ClickUrl = ClickUrl;
    }
}
