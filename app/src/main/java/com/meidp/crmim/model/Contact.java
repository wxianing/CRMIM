package com.meidp.crmim.model;

import java.io.Serializable;
import java.util.List;

/**
 * Package： com.meidp.crmim.model
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/8/10
 */
public class Contact implements Serializable {

    /**
     * ID : 223
     * CompanyCD : C1002
     * DeptNO : BMBH000093
     * SuperDeptID : 1
     * PYshort : RSBJFGS
     * DeptName : 销售部
     * AccountFlag : null
     * Address : null
     * Post : null
     * LinkMan : null
     * Tel : null
     * Fax : null
     * Email : null
     * Duty : null
     * UsedStatus : 1
     * Description : null
     * ModifiedDate : 2016-08-03 14:41:57
     * ModifiedUserID : 1
     * saleflag : null
     * subflag : null
     * director : 1876
     * DeptIDPath : 1,223,
     * Users : [{"CompanyCD":"C1002","UserID":2,"LoginName":"mayun","password":"670B14728AD9902AECBA32E22FA4F6BD","EmployeeID":1876,"UsedStatus":"1","LockFlag":"0","OpenDate":"2011-11-24 21:44:43","CloseDate":"3999-12-30 00:00:00","IsRoot":"0","LastLoginTime":null,"EmployeeName":"马云","NameEn":null,"PhotoURL":"http://beacon.meidp.com/upload/head/160809210728754088.jpg","DeptID":223,"QuarterName":null,"Mobile":"15889356386","Telephone":null,"DeptName":"销售部","rongcloudToken":"4Z7pSelUyOSTBXXkPrTXX8fKDSmkBZ3iMfp4c76/dYJjMW7cx4GdaFfVNiKV37y9wS7Ifw3qGlM="}]
     */

    private int ID;
    private String CompanyCD;
    private String DeptNO;
    private int SuperDeptID;
    private String PYshort;
    private String DeptName;
    private Object AccountFlag;
    private Object Address;
    private Object Post;
    private Object LinkMan;
    private Object Tel;
    private Object Fax;
    private Object Email;
    private Object Duty;
    private String UsedStatus;
    private Object Description;
    private String ModifiedDate;
    private String ModifiedUserID;
    private Object saleflag;
    private Object subflag;
    private int director;
    private String DeptIDPath;
    /**
     * CompanyCD : C1002
     * UserID : 2
     * LoginName : mayun
     * password : 670B14728AD9902AECBA32E22FA4F6BD
     * EmployeeID : 1876
     * UsedStatus : 1
     * LockFlag : 0
     * OpenDate : 2011-11-24 21:44:43
     * CloseDate : 3999-12-30 00:00:00
     * IsRoot : 0
     * LastLoginTime : null
     * EmployeeName : 马云
     * NameEn : null
     * PhotoURL : http://beacon.meidp.com/upload/head/160809210728754088.jpg
     * DeptID : 223
     * QuarterName : null
     * Mobile : 15889356386
     * Telephone : null
     * DeptName : 销售部
     * rongcloudToken : 4Z7pSelUyOSTBXXkPrTXX8fKDSmkBZ3iMfp4c76/dYJjMW7cx4GdaFfVNiKV37y9wS7Ifw3qGlM=
     */

    private List<UsersBean> Users;

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

    public String getDeptNO() {
        return DeptNO;
    }

    public void setDeptNO(String DeptNO) {
        this.DeptNO = DeptNO;
    }

    public int getSuperDeptID() {
        return SuperDeptID;
    }

    public void setSuperDeptID(int SuperDeptID) {
        this.SuperDeptID = SuperDeptID;
    }

    public String getPYshort() {
        return PYshort;
    }

    public void setPYshort(String PYshort) {
        this.PYshort = PYshort;
    }

    public String getDeptName() {
        return DeptName;
    }

    public void setDeptName(String DeptName) {
        this.DeptName = DeptName;
    }

    public Object getAccountFlag() {
        return AccountFlag;
    }

    public void setAccountFlag(Object AccountFlag) {
        this.AccountFlag = AccountFlag;
    }

    public Object getAddress() {
        return Address;
    }

    public void setAddress(Object Address) {
        this.Address = Address;
    }

    public Object getPost() {
        return Post;
    }

    public void setPost(Object Post) {
        this.Post = Post;
    }

    public Object getLinkMan() {
        return LinkMan;
    }

    public void setLinkMan(Object LinkMan) {
        this.LinkMan = LinkMan;
    }

    public Object getTel() {
        return Tel;
    }

    public void setTel(Object Tel) {
        this.Tel = Tel;
    }

    public Object getFax() {
        return Fax;
    }

    public void setFax(Object Fax) {
        this.Fax = Fax;
    }

    public Object getEmail() {
        return Email;
    }

    public void setEmail(Object Email) {
        this.Email = Email;
    }

    public Object getDuty() {
        return Duty;
    }

    public void setDuty(Object Duty) {
        this.Duty = Duty;
    }

    public String getUsedStatus() {
        return UsedStatus;
    }

    public void setUsedStatus(String UsedStatus) {
        this.UsedStatus = UsedStatus;
    }

    public Object getDescription() {
        return Description;
    }

    public void setDescription(Object Description) {
        this.Description = Description;
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

    public Object getSaleflag() {
        return saleflag;
    }

    public void setSaleflag(Object saleflag) {
        this.saleflag = saleflag;
    }

    public Object getSubflag() {
        return subflag;
    }

    public void setSubflag(Object subflag) {
        this.subflag = subflag;
    }

    public int getDirector() {
        return director;
    }

    public void setDirector(int director) {
        this.director = director;
    }

    public String getDeptIDPath() {
        return DeptIDPath;
    }

    public void setDeptIDPath(String DeptIDPath) {
        this.DeptIDPath = DeptIDPath;
    }

    public List<UsersBean> getUsers() {
        return Users;
    }

    public void setUsers(List<UsersBean> Users) {
        this.Users = Users;
    }

    public static class UsersBean {
        private String CompanyCD;
        private int UserID;
        private String LoginName;
        private String password;
        private int EmployeeID;
        private String UsedStatus;
        private String LockFlag;
        private String OpenDate;
        private String CloseDate;
        private String IsRoot;
        private Object LastLoginTime;
        private String EmployeeName;
        private Object NameEn;
        private String PhotoURL;
        private int DeptID;
        private String QuarterName;
        private String Mobile;
        private Object Telephone;
        private String DeptName;
        private String rongcloudToken;

        public String getCompanyCD() {
            return CompanyCD;
        }

        public void setCompanyCD(String CompanyCD) {
            this.CompanyCD = CompanyCD;
        }

        public int getUserID() {
            return UserID;
        }

        public void setUserID(int UserID) {
            this.UserID = UserID;
        }

        public String getLoginName() {
            return LoginName;
        }

        public void setLoginName(String LoginName) {
            this.LoginName = LoginName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getEmployeeID() {
            return EmployeeID;
        }

        public void setEmployeeID(int EmployeeID) {
            this.EmployeeID = EmployeeID;
        }

        public String getUsedStatus() {
            return UsedStatus;
        }

        public void setUsedStatus(String UsedStatus) {
            this.UsedStatus = UsedStatus;
        }

        public String getLockFlag() {
            return LockFlag;
        }

        public void setLockFlag(String LockFlag) {
            this.LockFlag = LockFlag;
        }

        public String getOpenDate() {
            return OpenDate;
        }

        public void setOpenDate(String OpenDate) {
            this.OpenDate = OpenDate;
        }

        public String getCloseDate() {
            return CloseDate;
        }

        public void setCloseDate(String CloseDate) {
            this.CloseDate = CloseDate;
        }

        public String getIsRoot() {
            return IsRoot;
        }

        public void setIsRoot(String IsRoot) {
            this.IsRoot = IsRoot;
        }

        public Object getLastLoginTime() {
            return LastLoginTime;
        }

        public void setLastLoginTime(Object LastLoginTime) {
            this.LastLoginTime = LastLoginTime;
        }

        public String getEmployeeName() {
            return EmployeeName;
        }

        public void setEmployeeName(String EmployeeName) {
            this.EmployeeName = EmployeeName;
        }

        public Object getNameEn() {
            return NameEn;
        }

        public void setNameEn(Object NameEn) {
            this.NameEn = NameEn;
        }

        public String getPhotoURL() {
            return PhotoURL;
        }

        public void setPhotoURL(String PhotoURL) {
            this.PhotoURL = PhotoURL;
        }

        public int getDeptID() {
            return DeptID;
        }

        public void setDeptID(int DeptID) {
            this.DeptID = DeptID;
        }

        public String getQuarterName() {
            return QuarterName;
        }

        public void setQuarterName(String QuarterName) {
            this.QuarterName = QuarterName;
        }

        public String getMobile() {
            return Mobile;
        }

        public void setMobile(String Mobile) {
            this.Mobile = Mobile;
        }

        public Object getTelephone() {
            return Telephone;
        }

        public void setTelephone(Object Telephone) {
            this.Telephone = Telephone;
        }

        public String getDeptName() {
            return DeptName;
        }

        public void setDeptName(String DeptName) {
            this.DeptName = DeptName;
        }

        public String getRongcloudToken() {
            return rongcloudToken;
        }

        public void setRongcloudToken(String rongcloudToken) {
            this.rongcloudToken = rongcloudToken;
        }
    }
}
