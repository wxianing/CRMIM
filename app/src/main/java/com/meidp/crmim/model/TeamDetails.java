package com.meidp.crmim.model;

import java.io.Serializable;
import java.util.List;

/**
 * Package： com.meidp.crmim.model
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/8/12
 */
public class TeamDetails implements Serializable {

    /**
     * ID : 6
     * TeamName : 销售部成员
     * TeamRemark :
     * CreateDate : 2016-08-11 22:02:31
     * Creator : 1875
     * CreateUserId : 0
     * Employees : 1885,1886,1879,1875,1876
     * CreateDateStr : 2016-08-11 22:02
     * PersonCount : 0
     * EmployeeNames : manager,majordomo,王健林,王显宁,马云,
     * users : [{"CompanyCD":"C1002","UserID":12,"LoginName":"manager","password":"E10ADC3949BA59ABBE56E057F20F883E","EmployeeID":1885,"UsedStatus":"1","LockFlag":"0","OpenDate":"2016-08-10 00:00:00","CloseDate":"2020-01-01 00:00:00","IsRoot":"0","LastLoginTime":null,"EmployeeName":"manager","NameEn":null,"PhotoURL":"http://beacon.meidp.com/upload/head/160811112603409944.jpg","DeptID":241,"QuarterName":"经理","Mobile":"15889356386","Telephone":null,"DeptName":"市场部","rongcloudToken":"CDDnMy0rjTOILrnRm9lpiFA+OIcFr5b3w8RT1pmYxlcNiQi3j3zfLggHAunlYYHERYKWx+OriZWQLwNiXsNt7Q=="},{"CompanyCD":"C1002","UserID":13,"LoginName":"majordomo","password":"E10ADC3949BA59ABBE56E057F20F883E","EmployeeID":1886,"UsedStatus":"1","LockFlag":"0","OpenDate":"2016-08-10 00:00:00","CloseDate":"2020-01-01 00:00:00","IsRoot":"0","LastLoginTime":null,"EmployeeName":"majordomo","NameEn":null,"PhotoURL":"http://beacon.meidp.com/upload/head/160811101558381415.jpg","DeptID":223,"QuarterName":"总监","Mobile":"13580153907","Telephone":null,"DeptName":"营销中心","rongcloudToken":"wsjMLj93r5jys80LuWL9q8fKDSmkBZ3iMfp4c76/dYK7hT/p2W4YQ0kwfuo9wFHW+Y4TyiSgeYE="},{"CompanyCD":"C1002","UserID":7,"LoginName":"wangjl","password":"670B14728AD9902AECBA32E22FA4F6BD","EmployeeID":1879,"UsedStatus":"1","LockFlag":"0","OpenDate":"2016-08-03 00:00:00","CloseDate":"2020-01-01 00:00:00","IsRoot":"0","LastLoginTime":null,"EmployeeName":"王健林","NameEn":null,"PhotoURL":"http://beacon.meidp.com/Images/defaulthead.png","DeptID":229,"QuarterName":null,"Mobile":null,"Telephone":null,"DeptName":"深圳销售部","rongcloudToken":"V4J9UZyUE7s+5SLSPPrTc1A+OIcFr5b3w8RT1pmYxlcNiQi3j3zfLv3xKyr7/6WFYJmcQkWpnr6QLwNiXsNt7Q=="},{"CompanyCD":"C1002","UserID":3,"LoginName":"wang","password":"DD4B21E9EF71E1291183A46B913AE6F2","EmployeeID":1875,"UsedStatus":"1","LockFlag":"0","OpenDate":"2016-07-01 00:00:00","CloseDate":"2019-07-01 00:00:00","IsRoot":"0","LastLoginTime":"2016-07-11 22:33:32","EmployeeName":"王显宁","NameEn":null,"PhotoURL":"http://beacon.meidp.com/upload/head/160811152758237026.jpg","DeptID":229,"QuarterName":null,"Mobile":"15889356358","Telephone":null,"DeptName":"深圳销售部","rongcloudToken":"fus8AJ25Hn7uhYmEnrL4elA+OIcFr5b3w8RT1pmYxlcNiQi3j3zfLhwjArmCW5FBcnNFKjQCNsuQLwNiXsNt7Q=="},{"CompanyCD":"C1002","UserID":2,"LoginName":"mayun","password":"670B14728AD9902AECBA32E22FA4F6BD","EmployeeID":1876,"UsedStatus":"1","LockFlag":"0","OpenDate":"2011-11-24 21:44:43","CloseDate":"3999-12-30 00:00:00","IsRoot":"0","LastLoginTime":null,"EmployeeName":"马云","NameEn":null,"PhotoURL":"http://beacon.meidp.com/upload/head/160811100622357927.jpg","DeptID":223,"QuarterName":null,"Mobile":"15889356386","Telephone":null,"DeptName":"营销中心","rongcloudToken":"4Z7pSelUyOSTBXXkPrTXX8fKDSmkBZ3iMfp4c76/dYJjMW7cx4GdaFfVNiKV37y9wS7Ifw3qGlM="}]
     */

    private int ID;
    private String TeamName;
    private String TeamRemark;
    private String CreateDate;
    private int Creator;
    private int CreateUserId;
    private String Employees;
    private String CreateDateStr;
    private int PersonCount;
    private String EmployeeNames;
    /**
     * CompanyCD : C1002
     * UserID : 12
     * LoginName : manager
     * password : E10ADC3949BA59ABBE56E057F20F883E
     * EmployeeID : 1885
     * UsedStatus : 1
     * LockFlag : 0
     * OpenDate : 2016-08-10 00:00:00
     * CloseDate : 2020-01-01 00:00:00
     * IsRoot : 0
     * LastLoginTime : null
     * EmployeeName : manager
     * NameEn : null
     * PhotoURL : http://beacon.meidp.com/upload/head/160811112603409944.jpg
     * DeptID : 241
     * QuarterName : 经理
     * Mobile : 15889356386
     * Telephone : null
     * DeptName : 市场部
     * rongcloudToken : CDDnMy0rjTOILrnRm9lpiFA+OIcFr5b3w8RT1pmYxlcNiQi3j3zfLggHAunlYYHERYKWx+OriZWQLwNiXsNt7Q==
     */

    private List<UsersBean> users;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTeamName() {
        return TeamName;
    }

    public void setTeamName(String TeamName) {
        this.TeamName = TeamName;
    }

    public String getTeamRemark() {
        return TeamRemark;
    }

    public void setTeamRemark(String TeamRemark) {
        this.TeamRemark = TeamRemark;
    }

    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String CreateDate) {
        this.CreateDate = CreateDate;
    }

    public int getCreator() {
        return Creator;
    }

    public void setCreator(int Creator) {
        this.Creator = Creator;
    }

    public int getCreateUserId() {
        return CreateUserId;
    }

    public void setCreateUserId(int CreateUserId) {
        this.CreateUserId = CreateUserId;
    }

    public String getEmployees() {
        return Employees;
    }

    public void setEmployees(String Employees) {
        this.Employees = Employees;
    }

    public String getCreateDateStr() {
        return CreateDateStr;
    }

    public void setCreateDateStr(String CreateDateStr) {
        this.CreateDateStr = CreateDateStr;
    }

    public int getPersonCount() {
        return PersonCount;
    }

    public void setPersonCount(int PersonCount) {
        this.PersonCount = PersonCount;
    }

    public String getEmployeeNames() {
        return EmployeeNames;
    }

    public void setEmployeeNames(String EmployeeNames) {
        this.EmployeeNames = EmployeeNames;
    }

    public List<UsersBean> getUsers() {
        return users;
    }

    public void setUsers(List<UsersBean> users) {
        this.users = users;
    }

    public static class UsersBean implements Serializable{

        /**
         * Id : 6
         * TeamId : 14
         * EmployeeId : 1884
         * PersonName : president
         * CreateDate : 2016-08-15 19:00:06
         * CreateUserID : 12
         */

        private int Id;
        private int TeamId;
        private int EmployeeId;
        private String PersonName;
        private String CreateDate;
        private int CreateUserID;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public int getTeamId() {
            return TeamId;
        }

        public void setTeamId(int TeamId) {
            this.TeamId = TeamId;
        }

        public int getEmployeeId() {
            return EmployeeId;
        }

        public void setEmployeeId(int EmployeeId) {
            this.EmployeeId = EmployeeId;
        }

        public String getPersonName() {
            return PersonName;
        }

        public void setPersonName(String PersonName) {
            this.PersonName = PersonName;
        }

        public String getCreateDate() {
            return CreateDate;
        }

        public void setCreateDate(String CreateDate) {
            this.CreateDate = CreateDate;
        }

        public int getCreateUserID() {
            return CreateUserID;
        }

        public void setCreateUserID(int CreateUserID) {
            this.CreateUserID = CreateUserID;
        }
    }
}
