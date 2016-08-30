package com.meidp.crmim.model;

import java.io.Serializable;
import java.util.List;

/**
 * Package： com.meidp.crmim.model
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/8/11
 */
public class Menber implements Serializable {

    /**
     * userstrs : null
     * users : [{"PhotoURL":"http://beacon.meidp.com/Images/defaulthead.png","DeptName":"总经办","EmployeeName":"president","QuarterName":"总经理","ID":53,"discussionId":"a7247278-410e-4ff2-b8f4-8b5240520ec7","UserID":14,"ModifiedDate":"2016-08-17 10:41:02"},{"PhotoURL":"http://beacon.meidp.com/upload/head/160817154856056353.jpg","DeptName":"营销中心","EmployeeName":"majordomo","QuarterName":"总监","ID":54,"discussionId":"a7247278-410e-4ff2-b8f4-8b5240520ec7","UserID":13,"ModifiedDate":"2016-08-17 10:41:02"},{"PhotoURL":"http://beacon.meidp.com/upload/head/160817155240805824.jpg","DeptName":"深圳销售测试部","EmployeeName":"和智","QuarterName":"经理","ID":55,"discussionId":"a7247278-410e-4ff2-b8f4-8b5240520ec7","UserID":5,"ModifiedDate":"2016-08-17 10:41:13"},{"PhotoURL":"http://beacon.meidp.com/upload/head/160817154856056353.jpg","DeptName":"营销中心","EmployeeName":"majordomo","QuarterName":"总监","ID":56,"discussionId":"a7247278-410e-4ff2-b8f4-8b5240520ec7","UserID":13,"ModifiedDate":"2016-08-17 15:07:08"},{"PhotoURL":"http://beacon.meidp.com/upload/head/160816161229370569.jpg","DeptName":"市场部","EmployeeName":"manager","QuarterName":"经理","ID":57,"discussionId":"a7247278-410e-4ff2-b8f4-8b5240520ec7","UserID":12,"ModifiedDate":"2016-08-17 15:07:08"}]
     * userCount : 0
     * ID : 24
     * discussionId : a7247278-410e-4ff2-b8f4-8b5240520ec7
     * discussionName : 测试创建群组
     * ModifiedDate : 2016-08-17 15:07:08
     * CreateUserID : 5
     * Status : 1
     */

    private Object userstrs;
    private int userCount;
    private int ID;
    private String discussionId;
    private String discussionName;
    private String ModifiedDate;
    private int CreateUserID;
    private int Status;
    /**
     * PhotoURL : http://beacon.meidp.com/Images/defaulthead.png
     * DeptName : 总经办
     * EmployeeName : president
     * QuarterName : 总经理
     * ID : 53
     * discussionId : a7247278-410e-4ff2-b8f4-8b5240520ec7
     * UserID : 14
     * ModifiedDate : 2016-08-17 10:41:02
     */

    private List<UsersBean> users;

    public Object getUserstrs() {
        return userstrs;
    }

    public void setUserstrs(Object userstrs) {
        this.userstrs = userstrs;
    }

    public int getUserCount() {
        return userCount;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
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

    public List<UsersBean> getUsers() {
        return users;
    }

    public void setUsers(List<UsersBean> users) {
        this.users = users;
    }

    public static class UsersBean implements Serializable{
        private String PhotoURL;
        private String DeptName;
        private String EmployeeName;
        private String QuarterName;
        private int ID;
        private String discussionId;
        private int UserID;
        private String ModifiedDate;

        public String getPhotoURL() {
            return PhotoURL;
        }

        public void setPhotoURL(String PhotoURL) {
            this.PhotoURL = PhotoURL;
        }

        public String getDeptName() {
            return DeptName;
        }

        public void setDeptName(String DeptName) {
            this.DeptName = DeptName;
        }

        public String getEmployeeName() {
            return EmployeeName;
        }

        public void setEmployeeName(String EmployeeName) {
            this.EmployeeName = EmployeeName;
        }

        public String getQuarterName() {
            return QuarterName;
        }

        public void setQuarterName(String QuarterName) {
            this.QuarterName = QuarterName;
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

        public int getUserID() {
            return UserID;
        }

        public void setUserID(int UserID) {
            this.UserID = UserID;
        }

        public String getModifiedDate() {
            return ModifiedDate;
        }

        public void setModifiedDate(String ModifiedDate) {
            this.ModifiedDate = ModifiedDate;
        }
    }
}
