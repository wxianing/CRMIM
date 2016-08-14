package com.meidp.crmim.model;

import java.io.Serializable;

/**
 * Package： com.meidp.crmim.model
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/8/14
 */
public class Performances implements Serializable {

    /**
     * PrjectTotalCount : 7
     * FinishProejct : 0
     * ProjectTotalMoney : 36497545
     * ProjectGathering : 0
     * ProjectReimburse : 0
     * MyProperty : 0
     * StartDate : 2016-02-14
     * EndDate : 2016-08-14
     */

    private int PrjectTotalCount;
    private int FinishProejct;
    private int ProjectTotalMoney;
    private int ProjectGathering;
    private int ProjectReimburse;
    private int MyProperty;
    private String StartDate;
    private String EndDate;

    public int getPrjectTotalCount() {
        return PrjectTotalCount;
    }

    public void setPrjectTotalCount(int PrjectTotalCount) {
        this.PrjectTotalCount = PrjectTotalCount;
    }

    public int getFinishProejct() {
        return FinishProejct;
    }

    public void setFinishProejct(int FinishProejct) {
        this.FinishProejct = FinishProejct;
    }

    public int getProjectTotalMoney() {
        return ProjectTotalMoney;
    }

    public void setProjectTotalMoney(int ProjectTotalMoney) {
        this.ProjectTotalMoney = ProjectTotalMoney;
    }

    public int getProjectGathering() {
        return ProjectGathering;
    }

    public void setProjectGathering(int ProjectGathering) {
        this.ProjectGathering = ProjectGathering;
    }

    public int getProjectReimburse() {
        return ProjectReimburse;
    }

    public void setProjectReimburse(int ProjectReimburse) {
        this.ProjectReimburse = ProjectReimburse;
    }

    public int getMyProperty() {
        return MyProperty;
    }

    public void setMyProperty(int MyProperty) {
        this.MyProperty = MyProperty;
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
}
