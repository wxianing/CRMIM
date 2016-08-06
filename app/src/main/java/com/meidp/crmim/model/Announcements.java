package com.meidp.crmim.model;

import java.io.Serializable;

/**
 * Package： com.meidp.crmim.model
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/7/18
 */
public class Announcements implements Serializable {

    /**
     * ID : 32
     * NewsTitle : 综合会诊显示方案
     * NewsContent : 巨烽远程会诊系统主要由影像诊断工作站、医用会诊大屏、高清视音频交互终端、网络设备以及配套设备组成，可以实现远程会诊、远程影像与心电专科诊断、远程监护、手术示教、视频会议、远程培训、会诊教学等功能，为各级医院提供远程会诊需求。
     * ComfirmDate : 2016-07-15 14:37
     * CreatorName :
     * IsRead : 0
     * ImgUrl : http://beacon.meidp.com
     */

    private int ID;
    private String NewsTitle;
    private String NewsContent;
    private String ComfirmDate;
    private String CreatorName;
    private int IsRead;
    private String ImgUrl;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNewsTitle() {
        return NewsTitle;
    }

    public void setNewsTitle(String NewsTitle) {
        this.NewsTitle = NewsTitle;
    }

    public String getNewsContent() {
        return NewsContent;
    }

    public void setNewsContent(String NewsContent) {
        this.NewsContent = NewsContent;
    }

    public String getComfirmDate() {
        return ComfirmDate;
    }

    public void setComfirmDate(String ComfirmDate) {
        this.ComfirmDate = ComfirmDate;
    }

    public String getCreatorName() {
        return CreatorName;
    }

    public void setCreatorName(String CreatorName) {
        this.CreatorName = CreatorName;
    }

    public int getIsRead() {
        return IsRead;
    }

    public void setIsRead(int IsRead) {
        this.IsRead = IsRead;
    }

    public String getImgUrl() {
        return ImgUrl;
    }

    public void setImgUrl(String ImgUrl) {
        this.ImgUrl = ImgUrl;
    }
}
