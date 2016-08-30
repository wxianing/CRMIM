package com.meidp.crmim.model;

import java.io.Serializable;
import java.util.List;

/**
 * Package： com.meidp.crmim.model
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/7/22
 */
public class ProjectDetails implements Serializable {


    /**
     * ConstructionDetails : [{"ID":106,"SummaryName":"测试","DutyPerson":null,"CompanyCD":"C1002","projectID":48,"ProcessScale":10000,"PersonNum":1,"Rate":0.2,"ProessID":null,"BeginDate":"2016-08-09 00:00:00","EndDate":"2016-08-09 00:00:00","ProjectMemo":"无","Creator":1874,"CreateDate":"2016-08-09 15:55:13"}]
     * ID : 48
     * ProjectNo : XM2016080027
     * ProjectName : 2MP灰阶医用显示器推广
     * CreateDate : 2016-08-04 16:54:53
     * Creator : 1874
     * StartDate :
     * EndDate :
     * CustID : 258
     * CustLinkMan : 梁健聪
     * LinkTel : 13530003777
     * SuccessRate : 0.2
     * Remark : 无
     * Investment : 1000
     * AcceptMoney : 0
     * AcceptDate :
     * ExpectAcceptDate :
     * LinkMan : 0
     * ProjectDirectionId : 0
     * ProjectDirectionName : null
     * CreatorName : null
     * CompanyId : 0
     * CompanyName :
     * DepartmentName : null
     * ZhiWu : null
     * IsAppoint : null
     */

    private int ID;
    private String ProjectNo;
    private String ProjectName;
    private String CreateDate;
    private int Creator;
    private String StartDate;
    private String EndDate;
    private int CustID;
    private String CustLinkMan;
    private String LinkTel;
    private double SuccessRate;
    private String Remark;
    private int Investment;
    private int AcceptMoney;
    private String AcceptDate;
    private String ExpectAcceptDate;
    private int LinkMan;
    private int ProjectDirectionId;
    private Object ProjectDirectionName;
    private Object CreatorName;
    private int CompanyId;
    private String CompanyName;
    private String DepartmentName;
    private String ZhiWu;
    private Object IsAppoint;
    private String CanViewUser;
    /**
     * ProcessList : null
     * CustName : 上海市同仁医院
     * CanViewUserName : 马云
     */

    private String CustName;
    private String CanViewUserName;
    /**
     * ProcessId : 1
     * ProcessName : 样机申请
     * FileNames : null
     * FilePaths : null
     * orderby : 2
     * Msg :
     */

    private List<ProcessListBean> ProcessList;

    public String getCanViewUser() {
        return CanViewUser;
    }

    public void setCanViewUser(String canViewUser) {
        CanViewUser = canViewUser;
    }

    /**
     * ID : 106
     * SummaryName : 测试
     * DutyPerson : null
     * CompanyCD : C1002
     * projectID : 48
     * ProcessScale : 10000
     * PersonNum : 1
     * Rate : 0.2
     * ProessID : null
     * BeginDate : 2016-08-09 00:00:00
     * EndDate : 2016-08-09 00:00:00
     * ProjectMemo : 无
     * Creator : 1874
     * CreateDate : 2016-08-09 15:55:13
     */

    private List<ConstructionDetailsBean> ConstructionDetails;
    /**
     * Status : 2
     * StatusName : 备货中
     */

    private int Status;
    private String StatusName;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getProjectNo() {
        return ProjectNo;
    }

    public void setProjectNo(String ProjectNo) {
        this.ProjectNo = ProjectNo;
    }

    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String ProjectName) {
        this.ProjectName = ProjectName;
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

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String StartDate) {
        this.StartDate = StartDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String EndDate) {
        this.EndDate = EndDate;
    }

    public int getCustID() {
        return CustID;
    }

    public void setCustID(int CustID) {
        this.CustID = CustID;
    }

    public String getCustLinkMan() {
        return CustLinkMan;
    }

    public void setCustLinkMan(String CustLinkMan) {
        this.CustLinkMan = CustLinkMan;
    }

    public String getLinkTel() {
        return LinkTel;
    }

    public void setLinkTel(String LinkTel) {
        this.LinkTel = LinkTel;
    }

    public double getSuccessRate() {
        return SuccessRate;
    }

    public void setSuccessRate(double SuccessRate) {
        this.SuccessRate = SuccessRate;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
    }

    public int getInvestment() {
        return Investment;
    }

    public void setInvestment(int Investment) {
        this.Investment = Investment;
    }

    public int getAcceptMoney() {
        return AcceptMoney;
    }

    public void setAcceptMoney(int AcceptMoney) {
        this.AcceptMoney = AcceptMoney;
    }

    public String getAcceptDate() {
        return AcceptDate;
    }

    public void setAcceptDate(String AcceptDate) {
        this.AcceptDate = AcceptDate;
    }

    public String getExpectAcceptDate() {
        return ExpectAcceptDate;
    }

    public void setExpectAcceptDate(String ExpectAcceptDate) {
        this.ExpectAcceptDate = ExpectAcceptDate;
    }

    public int getLinkMan() {
        return LinkMan;
    }

    public void setLinkMan(int LinkMan) {
        this.LinkMan = LinkMan;
    }

    public int getProjectDirectionId() {
        return ProjectDirectionId;
    }

    public void setProjectDirectionId(int ProjectDirectionId) {
        this.ProjectDirectionId = ProjectDirectionId;
    }

    public Object getProjectDirectionName() {
        return ProjectDirectionName;
    }

    public void setProjectDirectionName(Object ProjectDirectionName) {
        this.ProjectDirectionName = ProjectDirectionName;
    }

    public Object getCreatorName() {
        return CreatorName;
    }

    public void setCreatorName(Object CreatorName) {
        this.CreatorName = CreatorName;
    }

    public int getCompanyId() {
        return CompanyId;
    }

    public void setCompanyId(int CompanyId) {
        this.CompanyId = CompanyId;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String CompanyName) {
        this.CompanyName = CompanyName;
    }

    public String getDepartmentName() {
        return DepartmentName;
    }

    public void setDepartmentName(String DepartmentName) {
        this.DepartmentName = DepartmentName;
    }

    public String getZhiWu() {
        return ZhiWu;
    }

    public void setZhiWu(String ZhiWu) {
        this.ZhiWu = ZhiWu;
    }

    public Object getIsAppoint() {
        return IsAppoint;
    }

    public void setIsAppoint(Object IsAppoint) {
        this.IsAppoint = IsAppoint;
    }

    public List<ConstructionDetailsBean> getConstructionDetails() {
        return ConstructionDetails;
    }

    public void setConstructionDetails(List<ConstructionDetailsBean> ConstructionDetails) {
        this.ConstructionDetails = ConstructionDetails;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public String getStatusName() {
        return StatusName;
    }

    public void setStatusName(String StatusName) {
        this.StatusName = StatusName;
    }


    public String getCustName() {
        return CustName;
    }

    public void setCustName(String CustName) {
        this.CustName = CustName;
    }

    public String getCanViewUserName() {
        return CanViewUserName;
    }

    public void setCanViewUserName(String CanViewUserName) {
        this.CanViewUserName = CanViewUserName;
    }

    public List<ProcessListBean> getProcessList() {
        return ProcessList;
    }

    public void setProcessList(List<ProcessListBean> ProcessList) {
        this.ProcessList = ProcessList;
    }

    public static class ConstructionDetailsBean implements Serializable{
        private int ID;
        private String SummaryName;
        private Object DutyPerson;
        private String CompanyCD;
        private int projectID;
        private int ProcessScale;
        private int PersonNum;
        private double Rate;
        private Object ProessID;
        private String BeginDate;
        private String EndDate;
        private String ProjectMemo;
        private int Creator;
        private String CreateDate;

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getSummaryName() {
            return SummaryName;
        }

        public void setSummaryName(String SummaryName) {
            this.SummaryName = SummaryName;
        }

        public Object getDutyPerson() {
            return DutyPerson;
        }

        public void setDutyPerson(Object DutyPerson) {
            this.DutyPerson = DutyPerson;
        }

        public String getCompanyCD() {
            return CompanyCD;
        }

        public void setCompanyCD(String CompanyCD) {
            this.CompanyCD = CompanyCD;
        }

        public int getProjectID() {
            return projectID;
        }

        public void setProjectID(int projectID) {
            this.projectID = projectID;
        }

        public int getProcessScale() {
            return ProcessScale;
        }

        public void setProcessScale(int ProcessScale) {
            this.ProcessScale = ProcessScale;
        }

        public int getPersonNum() {
            return PersonNum;
        }

        public void setPersonNum(int PersonNum) {
            this.PersonNum = PersonNum;
        }

        public double getRate() {
            return Rate;
        }

        public void setRate(double Rate) {
            this.Rate = Rate;
        }

        public Object getProessID() {
            return ProessID;
        }

        public void setProessID(Object ProessID) {
            this.ProessID = ProessID;
        }

        public String getBeginDate() {
            return BeginDate;
        }

        public void setBeginDate(String BeginDate) {
            this.BeginDate = BeginDate;
        }

        public String getEndDate() {
            return EndDate;
        }

        public void setEndDate(String EndDate) {
            this.EndDate = EndDate;
        }

        public String getProjectMemo() {
            return ProjectMemo;
        }

        public void setProjectMemo(String ProjectMemo) {
            this.ProjectMemo = ProjectMemo;
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
    }

    public static class ProcessListBean implements Serializable {
        private int ProcessId;
        private String ProcessName;
        private Object FileNames;
        private Object FilePaths;
        private int orderby;
        private String Msg;
        private String ProcessTime;

        public String getProcessTime() {
            return ProcessTime;
        }

        public void setProcessTime(String processTime) {
            ProcessTime = processTime;
        }

        public int getProcessId() {
            return ProcessId;
        }

        public void setProcessId(int ProcessId) {
            this.ProcessId = ProcessId;
        }

        public String getProcessName() {
            return ProcessName;
        }

        public void setProcessName(String ProcessName) {
            this.ProcessName = ProcessName;
        }

        public Object getFileNames() {
            return FileNames;
        }

        public void setFileNames(Object FileNames) {
            this.FileNames = FileNames;
        }

        public Object getFilePaths() {
            return FilePaths;
        }

        public void setFilePaths(Object FilePaths) {
            this.FilePaths = FilePaths;
        }

        public int getOrderby() {
            return orderby;
        }

        public void setOrderby(int orderby) {
            this.orderby = orderby;
        }

        public String getMsg() {
            return Msg;
        }

        public void setMsg(String Msg) {
            this.Msg = Msg;
        }
    }
}
