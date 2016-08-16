package com.meidp.crmim.model;

/**
 * Created by Administrator on 2015/2/27.
 * 版本信息实体类
 */
public class Versions {

    private int Id;
    private String VersionCode;
    private String VersionName;
    private boolean IsForcedUpdate;
    private int ClientAppId;
    private int FileSize;
    private String FilePath;
    private String FileDesc;
    private int CreateUserId;
    private String CreateUserCnName;
    private String CreateTime;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getVersionCode() {
        return VersionCode;
    }

    public void setVersionCode(String VersionCode) {
        this.VersionCode = VersionCode;
    }

    public String getVersionName() {
        return VersionName;
    }

    public void setVersionName(String VersionName) {
        this.VersionName = VersionName;
    }

    public boolean isIsForcedUpdate() {
        return IsForcedUpdate;
    }

    public void setIsForcedUpdate(boolean IsForcedUpdate) {
        this.IsForcedUpdate = IsForcedUpdate;
    }

    public int getClientAppId() {
        return ClientAppId;
    }

    public void setClientAppId(int ClientAppId) {
        this.ClientAppId = ClientAppId;
    }

    public int getFileSize() {
        return FileSize;
    }

    public void setFileSize(int FileSize) {
        this.FileSize = FileSize;
    }

    public String getFilePath() {
        return FilePath;
    }

    public void setFilePath(String FilePath) {
        this.FilePath = FilePath;
    }

    public String getFileDesc() {
        return FileDesc;
    }

    public void setFileDesc(String FileDesc) {
        this.FileDesc = FileDesc;
    }

    public int getCreateUserId() {
        return CreateUserId;
    }

    public void setCreateUserId(int CreateUserId) {
        this.CreateUserId = CreateUserId;
    }

    public String getCreateUserCnName() {
        return CreateUserCnName;
    }

    public void setCreateUserCnName(String CreateUserCnName) {
        this.CreateUserCnName = CreateUserCnName;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String CreateTime) {
        this.CreateTime = CreateTime;
    }
}