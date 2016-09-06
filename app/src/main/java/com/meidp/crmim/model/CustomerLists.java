package com.meidp.crmim.model;

import java.io.Serializable;
import java.util.List;

/**
 * Package： com.meidp.crmim.model
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/7/19
 */
public class CustomerLists implements Serializable {

    private int ID;
    private String CustNo;
    private String CustName;
    private String CustShort;
    private String Tel;
    private String Province;
    private String City;
    private String ContactName;
    private String Mobile;
    private String email;
    private String Remark;

    private String CreatorName;

    /**
     * Address : sample string 12
     * SellArea : sample string 13
     * Creator : 14
     * ContactAge : sample string 16
     * ContactAddress : sample string 17
     * ContactOperation : sample string 18
     * ContactQQ : sample string 19
     * ContactSex : sample string 20
     * CanViewUser : sample string 21
     */

    private String Address;
    private String SellArea;
    private int Creator;
    private String ContactAge;
    private String ContactAddress;
    private String ContactOperation;
    private String ContactQQ;
    private String ContactSex;
    private String CanViewUser;
    /**
     * ID : 1
     * CustNo : sample string 2
     * CompanyCD : sample string 3
     * LinkManName : sample string 4
     * Sex : sample string 5
     * Important : sample string 6
     * Company : sample string 7
     * Appellation : sample string 8
     * Department : sample string 9
     * Position : sample string 10
     * Operation : sample string 11
     * WorkTel : sample string 12
     * Fax : sample string 13
     * Handset : sample string 14
     * MailAddress : sample string 15
     * HomeTel : sample string 16
     * MSN : sample string 17
     * QQ : sample string 18
     * Post : sample string 19
     * HomeAddress : sample string 20
     * Remark : sample string 21
     * Age : sample string 22
     * Likes : sample string 23
     * LinkType : 1
     * Birthday : 2016-09-04 21:27:02
     * PaperType : sample string 24
     * PaperNum : sample string 25
     * Photo : sample string 26
     * ModifiedDate : 2016-09-04 21:27:02
     * ModifiedUserID : sample string 27
     * CanViewUser : sample string 28
     * CanViewUserName : sample string 29
     * Creator : 1
     * CreatedDate : 2016-09-04 21:27:02
     * HomeTown : sample string 30
     * NationalID : 1
     * birthcity : sample string 31
     * CultureLevel : 1
     * Professional : 1
     * GraduateSchool : sample string 32
     * IncomeYear : sample string 33
     * FuoodDrink : sample string 34
     * LoveMusic : sample string 35
     * LoveColor : sample string 36
     * LoveSmoke : sample string 37
     * LoveDrink : sample string 38
     * LoveTea : sample string 39
     * LoveBook : sample string 40
     * LoveSport : sample string 41
     * LoveClothes : sample string 42
     * Cosmetic : sample string 43
     * Nature : sample string 44
     * Appearance : sample string 45
     * AdoutBody : sample string 46
     * AboutFamily : sample string 47
     * Car : sample string 48
     * LiveHouse : sample string 49
     * ProfessionalDes : sample string 50
     */

    private List<LinMansBean> LinMans;
    /**
     * ID : 1
     * CompanyCD : sample string 2
     * CustID : 1
     * CustLinkMan : 1
     * ContactNo : sample string 3
     * Title : sample string 4
     * LinkReasonID : 1
     * LinkMode : 1
     * LinkDate : 2016-09-04 21:27:02
     * Linker : 1
     * Contents : sample string 5
     * ModifiedDate : 2016-09-04 21:27:02
     * ModifiedUserID : sample string 6
     * CanViewUser : sample string 7
     * CanViewUserName : sample string 8
     * LinkTel : sample string 9
     * Lat : 1.1
     * Lon : 1.1
     * LocationAddress : sample string 10
     * ImgList : sample string 11
     * Creator : 1
     * Department : sample string 12
     * Position : sample string 13
     * ContactFrom : 1
     * ProjectID : 1
     */

    private List<ContractsBean> Contracts;
    /**
     * ID : 1
     * CompanyCD : sample string 2
     * ProjectNo : sample string 3
     * ProjectName : sample string 4
     * CustID : 1
     * CustLinkMan : sample string 5
     * LinkTel : sample string 6
     * LinkMan : 1
     * Tel : sample string 7
     * Address : sample string 8
     * StartDate : 2016-09-04 21:27:02
     * EndDate : 2016-09-04 21:27:02
     * OverView : sample string 9
     * Remark : sample string 10
     * Creator : 1
     * CreateDate : 2016-09-04 21:27:02
     * ModifiedDate : 2016-09-04 21:27:02
     * CanViewUser : sample string 11
     * CanViewUserName : sample string 12
     * Investment : 1.0
     * SuccessRate : 1.0
     * ExtField1 : sample string 13
     * ExtField2 : sample string 14
     * ExtField3 : sample string 15
     * ExtField4 : sample string 16
     * ExtField5 : sample string 17
     * ExtField6 : sample string 18
     * ExtField7 : sample string 19
     * ExtField8 : sample string 20
     * ExtField9 : sample string 21
     * ExtField10 : sample string 22
     * ExtField11 : sample string 23
     * ExtField12 : sample string 24
     * ExtField13 : sample string 25
     * ExtField14 : sample string 26
     * ExtField15 : sample string 27
     * ExtField16 : sample string 28
     * ExtField17 : sample string 29
     * ExtField18 : sample string 30
     * ExtField19 : sample string 31
     * ExtField20 : sample string 32
     * TransPublic : 1
     * FollowEmployeeId : 1
     * IsFollowed : 1
     * TransDate : 2016-09-04 21:27:02
     * ProjectDirectionId : 1
     * CompanyId : 1
     * DepartmentName : sample string 33
     * ZhiWu : sample string 34
     * IsAppoint : 1
     * CustLinkManId : 1
     * Status : 1
     * DatePlanDesc : sample string 35
     */

    private List<ProjectsBean> Projects;

    public String getCreatorName() {
        return CreatorName;
    }

    public void setCreatorName(String creatorName) {
        CreatorName = creatorName;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCustNo() {
        return CustNo;
    }

    public void setCustNo(String CustNo) {
        this.CustNo = CustNo;
    }

    public String getCustName() {
        return CustName;
    }

    public void setCustName(String CustName) {
        this.CustName = CustName;
    }

    public String getCustShort() {
        return CustShort;
    }

    public void setCustShort(String CustShort) {
        this.CustShort = CustShort;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String Tel) {
        this.Tel = Tel;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String Province) {
        this.Province = Province;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public String getContactName() {
        return ContactName;
    }

    public void setContactName(String ContactName) {
        this.ContactName = ContactName;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String Mobile) {
        this.Mobile = Mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getSellArea() {
        return SellArea;
    }

    public void setSellArea(String SellArea) {
        this.SellArea = SellArea;
    }

    public int getCreator() {
        return Creator;
    }

    public void setCreator(int Creator) {
        this.Creator = Creator;
    }

    public String getContactAge() {
        return ContactAge;
    }

    public void setContactAge(String ContactAge) {
        this.ContactAge = ContactAge;
    }

    public String getContactAddress() {
        return ContactAddress;
    }

    public void setContactAddress(String ContactAddress) {
        this.ContactAddress = ContactAddress;
    }

    public String getContactOperation() {
        return ContactOperation;
    }

    public void setContactOperation(String ContactOperation) {
        this.ContactOperation = ContactOperation;
    }

    public String getContactQQ() {
        return ContactQQ;
    }

    public void setContactQQ(String ContactQQ) {
        this.ContactQQ = ContactQQ;
    }

    public String getContactSex() {
        return ContactSex;
    }

    public void setContactSex(String ContactSex) {
        this.ContactSex = ContactSex;
    }

    public String getCanViewUser() {
        return CanViewUser;
    }

    public void setCanViewUser(String CanViewUser) {
        this.CanViewUser = CanViewUser;
    }

    public List<LinMansBean> getLinMans() {
        return LinMans;
    }

    public void setLinMans(List<LinMansBean> LinMans) {
        this.LinMans = LinMans;
    }

    public List<ContractsBean> getContracts() {
        return Contracts;
    }

    public void setContracts(List<ContractsBean> Contracts) {
        this.Contracts = Contracts;
    }

    public List<ProjectsBean> getProjects() {
        return Projects;
    }

    public void setProjects(List<ProjectsBean> Projects) {
        this.Projects = Projects;
    }

    public static class LinMansBean {
        private int ID;
        private String CustNo;
        private String CompanyCD;
        private String LinkManName;
        private String Sex;
        private String Important;
        private String Company;
        private String Appellation;
        private String Department;
        private String Position;
        private String Operation;
        private String WorkTel;
        private String Fax;
        private String Handset;
        private String MailAddress;
        private String HomeTel;
        private String MSN;
        private String QQ;
        private String Post;
        private String HomeAddress;
        private String Remark;
        private String Age;
        private String Likes;
        private int LinkType;
        private String Birthday;
        private String PaperType;
        private String PaperNum;
        private String Photo;
        private String ModifiedDate;
        private String ModifiedUserID;
        private String CanViewUser;
        private String CanViewUserName;
        private int Creator;
        private String CreatedDate;
        private String HomeTown;
        private int NationalID;
        private String birthcity;
        private int CultureLevel;
        private int Professional;
        private String GraduateSchool;
        private String IncomeYear;
        private String FuoodDrink;
        private String LoveMusic;
        private String LoveColor;
        private String LoveSmoke;
        private String LoveDrink;
        private String LoveTea;
        private String LoveBook;
        private String LoveSport;
        private String LoveClothes;
        private String Cosmetic;
        private String Nature;
        private String Appearance;
        private String AdoutBody;
        private String AboutFamily;
        private String Car;
        private String LiveHouse;
        private String ProfessionalDes;

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getCustNo() {
            return CustNo;
        }

        public void setCustNo(String CustNo) {
            this.CustNo = CustNo;
        }

        public String getCompanyCD() {
            return CompanyCD;
        }

        public void setCompanyCD(String CompanyCD) {
            this.CompanyCD = CompanyCD;
        }

        public String getLinkManName() {
            return LinkManName;
        }

        public void setLinkManName(String LinkManName) {
            this.LinkManName = LinkManName;
        }

        public String getSex() {
            return Sex;
        }

        public void setSex(String Sex) {
            this.Sex = Sex;
        }

        public String getImportant() {
            return Important;
        }

        public void setImportant(String Important) {
            this.Important = Important;
        }

        public String getCompany() {
            return Company;
        }

        public void setCompany(String Company) {
            this.Company = Company;
        }

        public String getAppellation() {
            return Appellation;
        }

        public void setAppellation(String Appellation) {
            this.Appellation = Appellation;
        }

        public String getDepartment() {
            return Department;
        }

        public void setDepartment(String Department) {
            this.Department = Department;
        }

        public String getPosition() {
            return Position;
        }

        public void setPosition(String Position) {
            this.Position = Position;
        }

        public String getOperation() {
            return Operation;
        }

        public void setOperation(String Operation) {
            this.Operation = Operation;
        }

        public String getWorkTel() {
            return WorkTel;
        }

        public void setWorkTel(String WorkTel) {
            this.WorkTel = WorkTel;
        }

        public String getFax() {
            return Fax;
        }

        public void setFax(String Fax) {
            this.Fax = Fax;
        }

        public String getHandset() {
            return Handset;
        }

        public void setHandset(String Handset) {
            this.Handset = Handset;
        }

        public String getMailAddress() {
            return MailAddress;
        }

        public void setMailAddress(String MailAddress) {
            this.MailAddress = MailAddress;
        }

        public String getHomeTel() {
            return HomeTel;
        }

        public void setHomeTel(String HomeTel) {
            this.HomeTel = HomeTel;
        }

        public String getMSN() {
            return MSN;
        }

        public void setMSN(String MSN) {
            this.MSN = MSN;
        }

        public String getQQ() {
            return QQ;
        }

        public void setQQ(String QQ) {
            this.QQ = QQ;
        }

        public String getPost() {
            return Post;
        }

        public void setPost(String Post) {
            this.Post = Post;
        }

        public String getHomeAddress() {
            return HomeAddress;
        }

        public void setHomeAddress(String HomeAddress) {
            this.HomeAddress = HomeAddress;
        }

        public String getRemark() {
            return Remark;
        }

        public void setRemark(String Remark) {
            this.Remark = Remark;
        }

        public String getAge() {
            return Age;
        }

        public void setAge(String Age) {
            this.Age = Age;
        }

        public String getLikes() {
            return Likes;
        }

        public void setLikes(String Likes) {
            this.Likes = Likes;
        }

        public int getLinkType() {
            return LinkType;
        }

        public void setLinkType(int LinkType) {
            this.LinkType = LinkType;
        }

        public String getBirthday() {
            return Birthday;
        }

        public void setBirthday(String Birthday) {
            this.Birthday = Birthday;
        }

        public String getPaperType() {
            return PaperType;
        }

        public void setPaperType(String PaperType) {
            this.PaperType = PaperType;
        }

        public String getPaperNum() {
            return PaperNum;
        }

        public void setPaperNum(String PaperNum) {
            this.PaperNum = PaperNum;
        }

        public String getPhoto() {
            return Photo;
        }

        public void setPhoto(String Photo) {
            this.Photo = Photo;
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

        public String getCanViewUser() {
            return CanViewUser;
        }

        public void setCanViewUser(String CanViewUser) {
            this.CanViewUser = CanViewUser;
        }

        public String getCanViewUserName() {
            return CanViewUserName;
        }

        public void setCanViewUserName(String CanViewUserName) {
            this.CanViewUserName = CanViewUserName;
        }

        public int getCreator() {
            return Creator;
        }

        public void setCreator(int Creator) {
            this.Creator = Creator;
        }

        public String getCreatedDate() {
            return CreatedDate;
        }

        public void setCreatedDate(String CreatedDate) {
            this.CreatedDate = CreatedDate;
        }

        public String getHomeTown() {
            return HomeTown;
        }

        public void setHomeTown(String HomeTown) {
            this.HomeTown = HomeTown;
        }

        public int getNationalID() {
            return NationalID;
        }

        public void setNationalID(int NationalID) {
            this.NationalID = NationalID;
        }

        public String getBirthcity() {
            return birthcity;
        }

        public void setBirthcity(String birthcity) {
            this.birthcity = birthcity;
        }

        public int getCultureLevel() {
            return CultureLevel;
        }

        public void setCultureLevel(int CultureLevel) {
            this.CultureLevel = CultureLevel;
        }

        public int getProfessional() {
            return Professional;
        }

        public void setProfessional(int Professional) {
            this.Professional = Professional;
        }

        public String getGraduateSchool() {
            return GraduateSchool;
        }

        public void setGraduateSchool(String GraduateSchool) {
            this.GraduateSchool = GraduateSchool;
        }

        public String getIncomeYear() {
            return IncomeYear;
        }

        public void setIncomeYear(String IncomeYear) {
            this.IncomeYear = IncomeYear;
        }

        public String getFuoodDrink() {
            return FuoodDrink;
        }

        public void setFuoodDrink(String FuoodDrink) {
            this.FuoodDrink = FuoodDrink;
        }

        public String getLoveMusic() {
            return LoveMusic;
        }

        public void setLoveMusic(String LoveMusic) {
            this.LoveMusic = LoveMusic;
        }

        public String getLoveColor() {
            return LoveColor;
        }

        public void setLoveColor(String LoveColor) {
            this.LoveColor = LoveColor;
        }

        public String getLoveSmoke() {
            return LoveSmoke;
        }

        public void setLoveSmoke(String LoveSmoke) {
            this.LoveSmoke = LoveSmoke;
        }

        public String getLoveDrink() {
            return LoveDrink;
        }

        public void setLoveDrink(String LoveDrink) {
            this.LoveDrink = LoveDrink;
        }

        public String getLoveTea() {
            return LoveTea;
        }

        public void setLoveTea(String LoveTea) {
            this.LoveTea = LoveTea;
        }

        public String getLoveBook() {
            return LoveBook;
        }

        public void setLoveBook(String LoveBook) {
            this.LoveBook = LoveBook;
        }

        public String getLoveSport() {
            return LoveSport;
        }

        public void setLoveSport(String LoveSport) {
            this.LoveSport = LoveSport;
        }

        public String getLoveClothes() {
            return LoveClothes;
        }

        public void setLoveClothes(String LoveClothes) {
            this.LoveClothes = LoveClothes;
        }

        public String getCosmetic() {
            return Cosmetic;
        }

        public void setCosmetic(String Cosmetic) {
            this.Cosmetic = Cosmetic;
        }

        public String getNature() {
            return Nature;
        }

        public void setNature(String Nature) {
            this.Nature = Nature;
        }

        public String getAppearance() {
            return Appearance;
        }

        public void setAppearance(String Appearance) {
            this.Appearance = Appearance;
        }

        public String getAdoutBody() {
            return AdoutBody;
        }

        public void setAdoutBody(String AdoutBody) {
            this.AdoutBody = AdoutBody;
        }

        public String getAboutFamily() {
            return AboutFamily;
        }

        public void setAboutFamily(String AboutFamily) {
            this.AboutFamily = AboutFamily;
        }

        public String getCar() {
            return Car;
        }

        public void setCar(String Car) {
            this.Car = Car;
        }

        public String getLiveHouse() {
            return LiveHouse;
        }

        public void setLiveHouse(String LiveHouse) {
            this.LiveHouse = LiveHouse;
        }

        public String getProfessionalDes() {
            return ProfessionalDes;
        }

        public void setProfessionalDes(String ProfessionalDes) {
            this.ProfessionalDes = ProfessionalDes;
        }
    }

    public static class ContractsBean {
        private int ID;
        private String CompanyCD;
        private int CustID;
        private int CustLinkMan;
        private String ContactNo;
        private String Title;
        private int LinkReasonID;
        private int LinkMode;
        private String LinkDate;
        private int Linker;
        private String Contents;
        private String ModifiedDate;
        private String ModifiedUserID;
        private String CanViewUser;
        private String CanViewUserName;
        private String LinkTel;
        private double Lat;
        private double Lon;
        private String LocationAddress;
        private String ImgList;
        private int Creator;
        private String Department;
        private String Position;
        private int ContactFrom;
        private int ProjectID;

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

        public int getCustID() {
            return CustID;
        }

        public void setCustID(int CustID) {
            this.CustID = CustID;
        }

        public int getCustLinkMan() {
            return CustLinkMan;
        }

        public void setCustLinkMan(int CustLinkMan) {
            this.CustLinkMan = CustLinkMan;
        }

        public String getContactNo() {
            return ContactNo;
        }

        public void setContactNo(String ContactNo) {
            this.ContactNo = ContactNo;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String Title) {
            this.Title = Title;
        }

        public int getLinkReasonID() {
            return LinkReasonID;
        }

        public void setLinkReasonID(int LinkReasonID) {
            this.LinkReasonID = LinkReasonID;
        }

        public int getLinkMode() {
            return LinkMode;
        }

        public void setLinkMode(int LinkMode) {
            this.LinkMode = LinkMode;
        }

        public String getLinkDate() {
            return LinkDate;
        }

        public void setLinkDate(String LinkDate) {
            this.LinkDate = LinkDate;
        }

        public int getLinker() {
            return Linker;
        }

        public void setLinker(int Linker) {
            this.Linker = Linker;
        }

        public String getContents() {
            return Contents;
        }

        public void setContents(String Contents) {
            this.Contents = Contents;
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

        public String getCanViewUser() {
            return CanViewUser;
        }

        public void setCanViewUser(String CanViewUser) {
            this.CanViewUser = CanViewUser;
        }

        public String getCanViewUserName() {
            return CanViewUserName;
        }

        public void setCanViewUserName(String CanViewUserName) {
            this.CanViewUserName = CanViewUserName;
        }

        public String getLinkTel() {
            return LinkTel;
        }

        public void setLinkTel(String LinkTel) {
            this.LinkTel = LinkTel;
        }

        public double getLat() {
            return Lat;
        }

        public void setLat(double Lat) {
            this.Lat = Lat;
        }

        public double getLon() {
            return Lon;
        }

        public void setLon(double Lon) {
            this.Lon = Lon;
        }

        public String getLocationAddress() {
            return LocationAddress;
        }

        public void setLocationAddress(String LocationAddress) {
            this.LocationAddress = LocationAddress;
        }

        public String getImgList() {
            return ImgList;
        }

        public void setImgList(String ImgList) {
            this.ImgList = ImgList;
        }

        public int getCreator() {
            return Creator;
        }

        public void setCreator(int Creator) {
            this.Creator = Creator;
        }

        public String getDepartment() {
            return Department;
        }

        public void setDepartment(String Department) {
            this.Department = Department;
        }

        public String getPosition() {
            return Position;
        }

        public void setPosition(String Position) {
            this.Position = Position;
        }

        public int getContactFrom() {
            return ContactFrom;
        }

        public void setContactFrom(int ContactFrom) {
            this.ContactFrom = ContactFrom;
        }

        public int getProjectID() {
            return ProjectID;
        }

        public void setProjectID(int ProjectID) {
            this.ProjectID = ProjectID;
        }
    }

    public static class ProjectsBean {
        private int ID;
        private String CompanyCD;
        private String ProjectNo;
        private String ProjectName;
        private int CustID;
        private String CustLinkMan;
        private String LinkTel;
        private int LinkMan;
        private String Tel;
        private String Address;
        private String StartDate;
        private String EndDate;
        private String OverView;
        private String Remark;
        private int Creator;
        private String CreateDate;
        private String ModifiedDate;
        private String CanViewUser;
        private String CanViewUserName;
        private double Investment;
        private double SuccessRate;
        private String ExtField1;
        private String ExtField2;
        private String ExtField3;
        private String ExtField4;
        private String ExtField5;
        private String ExtField6;
        private String ExtField7;
        private String ExtField8;
        private String ExtField9;
        private String ExtField10;
        private String ExtField11;
        private String ExtField12;
        private String ExtField13;
        private String ExtField14;
        private String ExtField15;
        private String ExtField16;
        private String ExtField17;
        private String ExtField18;
        private String ExtField19;
        private String ExtField20;
        private int TransPublic;
        private int FollowEmployeeId;
        private int IsFollowed;
        private String TransDate;
        private int ProjectDirectionId;
        private int CompanyId;
        private String DepartmentName;
        private String ZhiWu;
        private int IsAppoint;
        private int CustLinkManId;
        private int Status;
        private String DatePlanDesc;

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

        public int getLinkMan() {
            return LinkMan;
        }

        public void setLinkMan(int LinkMan) {
            this.LinkMan = LinkMan;
        }

        public String getTel() {
            return Tel;
        }

        public void setTel(String Tel) {
            this.Tel = Tel;
        }

        public String getAddress() {
            return Address;
        }

        public void setAddress(String Address) {
            this.Address = Address;
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

        public String getOverView() {
            return OverView;
        }

        public void setOverView(String OverView) {
            this.OverView = OverView;
        }

        public String getRemark() {
            return Remark;
        }

        public void setRemark(String Remark) {
            this.Remark = Remark;
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

        public String getCanViewUser() {
            return CanViewUser;
        }

        public void setCanViewUser(String CanViewUser) {
            this.CanViewUser = CanViewUser;
        }

        public String getCanViewUserName() {
            return CanViewUserName;
        }

        public void setCanViewUserName(String CanViewUserName) {
            this.CanViewUserName = CanViewUserName;
        }

        public double getInvestment() {
            return Investment;
        }

        public void setInvestment(double Investment) {
            this.Investment = Investment;
        }

        public double getSuccessRate() {
            return SuccessRate;
        }

        public void setSuccessRate(double SuccessRate) {
            this.SuccessRate = SuccessRate;
        }

        public String getExtField1() {
            return ExtField1;
        }

        public void setExtField1(String ExtField1) {
            this.ExtField1 = ExtField1;
        }

        public String getExtField2() {
            return ExtField2;
        }

        public void setExtField2(String ExtField2) {
            this.ExtField2 = ExtField2;
        }

        public String getExtField3() {
            return ExtField3;
        }

        public void setExtField3(String ExtField3) {
            this.ExtField3 = ExtField3;
        }

        public String getExtField4() {
            return ExtField4;
        }

        public void setExtField4(String ExtField4) {
            this.ExtField4 = ExtField4;
        }

        public String getExtField5() {
            return ExtField5;
        }

        public void setExtField5(String ExtField5) {
            this.ExtField5 = ExtField5;
        }

        public String getExtField6() {
            return ExtField6;
        }

        public void setExtField6(String ExtField6) {
            this.ExtField6 = ExtField6;
        }

        public String getExtField7() {
            return ExtField7;
        }

        public void setExtField7(String ExtField7) {
            this.ExtField7 = ExtField7;
        }

        public String getExtField8() {
            return ExtField8;
        }

        public void setExtField8(String ExtField8) {
            this.ExtField8 = ExtField8;
        }

        public String getExtField9() {
            return ExtField9;
        }

        public void setExtField9(String ExtField9) {
            this.ExtField9 = ExtField9;
        }

        public String getExtField10() {
            return ExtField10;
        }

        public void setExtField10(String ExtField10) {
            this.ExtField10 = ExtField10;
        }

        public String getExtField11() {
            return ExtField11;
        }

        public void setExtField11(String ExtField11) {
            this.ExtField11 = ExtField11;
        }

        public String getExtField12() {
            return ExtField12;
        }

        public void setExtField12(String ExtField12) {
            this.ExtField12 = ExtField12;
        }

        public String getExtField13() {
            return ExtField13;
        }

        public void setExtField13(String ExtField13) {
            this.ExtField13 = ExtField13;
        }

        public String getExtField14() {
            return ExtField14;
        }

        public void setExtField14(String ExtField14) {
            this.ExtField14 = ExtField14;
        }

        public String getExtField15() {
            return ExtField15;
        }

        public void setExtField15(String ExtField15) {
            this.ExtField15 = ExtField15;
        }

        public String getExtField16() {
            return ExtField16;
        }

        public void setExtField16(String ExtField16) {
            this.ExtField16 = ExtField16;
        }

        public String getExtField17() {
            return ExtField17;
        }

        public void setExtField17(String ExtField17) {
            this.ExtField17 = ExtField17;
        }

        public String getExtField18() {
            return ExtField18;
        }

        public void setExtField18(String ExtField18) {
            this.ExtField18 = ExtField18;
        }

        public String getExtField19() {
            return ExtField19;
        }

        public void setExtField19(String ExtField19) {
            this.ExtField19 = ExtField19;
        }

        public String getExtField20() {
            return ExtField20;
        }

        public void setExtField20(String ExtField20) {
            this.ExtField20 = ExtField20;
        }

        public int getTransPublic() {
            return TransPublic;
        }

        public void setTransPublic(int TransPublic) {
            this.TransPublic = TransPublic;
        }

        public int getFollowEmployeeId() {
            return FollowEmployeeId;
        }

        public void setFollowEmployeeId(int FollowEmployeeId) {
            this.FollowEmployeeId = FollowEmployeeId;
        }

        public int getIsFollowed() {
            return IsFollowed;
        }

        public void setIsFollowed(int IsFollowed) {
            this.IsFollowed = IsFollowed;
        }

        public String getTransDate() {
            return TransDate;
        }

        public void setTransDate(String TransDate) {
            this.TransDate = TransDate;
        }

        public int getProjectDirectionId() {
            return ProjectDirectionId;
        }

        public void setProjectDirectionId(int ProjectDirectionId) {
            this.ProjectDirectionId = ProjectDirectionId;
        }

        public int getCompanyId() {
            return CompanyId;
        }

        public void setCompanyId(int CompanyId) {
            this.CompanyId = CompanyId;
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

        public int getIsAppoint() {
            return IsAppoint;
        }

        public void setIsAppoint(int IsAppoint) {
            this.IsAppoint = IsAppoint;
        }

        public int getCustLinkManId() {
            return CustLinkManId;
        }

        public void setCustLinkManId(int CustLinkManId) {
            this.CustLinkManId = CustLinkManId;
        }

        public int getStatus() {
            return Status;
        }

        public void setStatus(int Status) {
            this.Status = Status;
        }

        public String getDatePlanDesc() {
            return DatePlanDesc;
        }

        public void setDatePlanDesc(String DatePlanDesc) {
            this.DatePlanDesc = DatePlanDesc;
        }
    }
}
