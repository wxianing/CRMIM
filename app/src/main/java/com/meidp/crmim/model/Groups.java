package com.meidp.crmim.model;

import java.io.Serializable;

/**
 * Package： com.meidp.crmim.model
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/7/21
 */
public class Groups implements Serializable {

    /**
     * userstrs : null
     * users : []
     * ID : 5
     * discussionId : 523f7eaa-bb57-4fdb-a31d-8677bddc26a6
     * discussionName : 好运来
     * ModifiedDate : 2016-07-21 15:13:15
     * CreateUserID : 3
     * Status : 1
     */

    private Object userstrs;
    private int ID;
    private String discussionId;
    private String discussionName;
    private String ModifiedDate;
    private int CreateUserID;
    private int Status;

    public Object getUserstrs() {
        return userstrs;
    }

    public void setUserstrs(Object userstrs) {
        this.userstrs = userstrs;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDiscussionId() {
        return discussionId;
    }

    public void setDiscussionId(String discussionId) {
        this.discussionId = discussionId;
    }

    public String getDiscussionName() {
        return discussionName;
    }

    public void setDiscussionName(String discussionName) {
        this.discussionName = discussionName;
    }

    public String getModifiedDate() {
        return ModifiedDate;
    }

    public void setModifiedDate(String ModifiedDate) {
        this.ModifiedDate = ModifiedDate;
    }

    public int getCreateUserID() {
        return CreateUserID;
    }

    public void setCreateUserID(int CreateUserID) {
        this.CreateUserID = CreateUserID;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }
}
