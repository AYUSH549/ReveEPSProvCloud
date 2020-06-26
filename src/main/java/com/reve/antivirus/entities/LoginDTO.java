package com.reve.antivirus.entities;

import java.io.Serializable;

import reveantivirus.annotations.Column;
import com.reve.antivirus.entities.vframework.AbstractDTO;

import reveantivirus.annotations.DTO;
import reveantivirus.annotations.Id;
import reveantivirus.annotations.TableName;
import reveantivirus.annotations.utils.SerializeTo;

@DTO
@TableName("LOGIN")
@reveantivirus.annotations.utils.Serializable
public class LoginDTO  extends AbstractDTO implements Serializable{
	
	
    public static final String LID = "LID";
    public static final String LOGIN_ID = "LOGIN_ID";
    public static final String PASSWORD = "PASSWORD";
    public static final String PID = "PID"; 
    public static final String USER_TYPE = "USER_TYPE"; //
    public static final String LAST_LOGIN_TIME = "LAST_LOGIN_TIME"; //
    public static final String NAME = "NAME";
    public static final String MOBILE = "MOBILE";
    public static final String COUNTRY = "COUNTRY";
    public static final String NOTIFICATION_SERVICE = "NOTIFICATION_SERVICE";
    public static final String TIMEZONE = "TIMEZONE";
    
    
    @Column(name = LID)
    @SerializeTo
    private long lid;
    
    @Column(name = LOGIN_ID)
    @SerializeTo
    private String loginId;
    
    @Column(name = PASSWORD)
    @SerializeTo
    private String password;
    
    @Column(name = PID)
    @SerializeTo
    private long pid;
    
    @Column(name = USER_TYPE)
    @SerializeTo
    private int userType;
    
    @Column(name = LAST_LOGIN_TIME)
    @SerializeTo
    private long lastLoginTime;

    @Column(name = NAME)
    @SerializeTo
    private String name;
    
    @Column(name = MOBILE)
    @SerializeTo
    private String mobile;
    
    @Column(name = COUNTRY)
    @SerializeTo
    private String country;
    
    @Column(name = NOTIFICATION_SERVICE)
    @SerializeTo
    private long notificationServices;
    
    @Column(name = TIMEZONE)
    @SerializeTo
    private String timeZone;

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public long getNotificationServices() {
        return notificationServices;
    }

    public void setNotificationServices(long notificationServices) {
        this.notificationServices = notificationServices;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    
    public long getLid() {
        return lid;
    }

    public void setLid(long lid) {
        this.lid = lid;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public long getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    
}