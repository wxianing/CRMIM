package com.meidp.crmim.model;

import java.io.Serializable;
import java.util.List;

/**
 * Package： com.meidp.crmim.model
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/7/18
 */
public class Announcements implements Serializable {

    /**
     * PageIndex : 1
     * RecordCount : 8
     * DataList : [{"ID":32,"NewsTitle":"测试公告","NewsContent":"下午加餐","ComfirmDate":"2016-07-15T14:37:19.12","CreatorName":null},{"ID":30,"NewsTitle":"xzzxzxz","NewsContent":"zxxzzxzxzx","ComfirmDate":"2010-08-20T10:35:44.5","CreatorName":null},{"ID":29,"NewsTitle":"qwe123","NewsContent":"123123","ComfirmDate":"2010-08-20T10:11:36.327","CreatorName":null},{"ID":11,"NewsTitle":"222","NewsContent":"222","ComfirmDate":"2010-08-20T09:50:52.127","CreatorName":null}]
     * TotalModel :
     */

    private int PageIndex;
    private int RecordCount;
    private String TotalModel;
    /**
     * ID : 32
     * NewsTitle : 测试公告
     * NewsContent : 下午加餐
     * ComfirmDate : 2016-07-15T14:37:19.12
     * CreatorName : null
     */

    private List<DataListBean> DataList;

    public int getPageIndex() {
        return PageIndex;
    }

    public void setPageIndex(int PageIndex) {
        this.PageIndex = PageIndex;
    }

    public int getRecordCount() {
        return RecordCount;
    }

    public void setRecordCount(int RecordCount) {
        this.RecordCount = RecordCount;
    }

    public String getTotalModel() {
        return TotalModel;
    }

    public void setTotalModel(String TotalModel) {
        this.TotalModel = TotalModel;
    }

    public List<DataListBean> getDataList() {
        return DataList;
    }

    public void setDataList(List<DataListBean> DataList) {
        this.DataList = DataList;
    }

    public static class DataListBean {
        private int ID;
        private String NewsTitle;
        private String NewsContent;
        private String ComfirmDate;
        private Object CreatorName;

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

        public Object getCreatorName() {
            return CreatorName;
        }

        public void setCreatorName(Object CreatorName) {
            this.CreatorName = CreatorName;
        }
    }
}
