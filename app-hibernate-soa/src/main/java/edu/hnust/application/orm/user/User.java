package edu.hnust.application.orm.user;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;    
    private Integer id; // 主键    
    private String userName; // 用户姓名    
    private String password; // 密码    
    private String loginName;// 登录名    
    private String fixedTelephone;// 固定电话    
    private String mobilePhone;// 移动电话    
    private String qq;
    private String email;
    private String createDate;// 创建时间    
    private String updateDate;// 更新时间    
    private String sysRemark;// 系统备注
    private String language;// 语言    
    private String remoteAddr;    
    private Integer isSuperUser; // 是否超级管理员 0 否 1 是
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getCreateDate() {
        return createDate;
    }
    
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
    
    public String getUpdateDate() {
        return updateDate;
    }
    
    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }
    
    public String getLoginName() {
        return loginName;
    }
    
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
    
    public String getFixedTelephone() {
        return fixedTelephone;
    }
    
    public void setFixedTelephone(String fixedTelephone) {
        this.fixedTelephone = fixedTelephone;
    }
    
    public String getMobilePhone() {
        return mobilePhone;
    }
    
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }
    
    public String getQq() {
        return qq;
    }
    
    public void setQq(String qq) {
        this.qq = qq;
    }
    
    public String getSysRemark() {
        return sysRemark;
    }
    
    public void setSysRemark(String sysRemark) {
        this.sysRemark = sysRemark;
    }
    
    public String getLanguage() {
        return language;
    }
    
    public void setLanguage(String language) {
        this.language = language;
    }
    
    public String getRemoteAddr() {
        return remoteAddr;
    }
    
    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }
    
    
    public Integer getIsSuperUser() {
        return isSuperUser;
    }
    
    public void setIsSuperUser(Integer isSuperUser) {
        this.isSuperUser = isSuperUser;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", loginName=" + loginName + ", fixedTelephone=" + fixedTelephone + ", mobilePhone=" + mobilePhone + ", qq=" + qq + ", email=" + email + ", createDate=" + createDate + ", updateDate=" + updateDate + ", sysRemark=" + sysRemark + ", language=" + language + ", remoteAddr=" + remoteAddr + ", isSuperUser=" + isSuperUser + "]";
    }
}