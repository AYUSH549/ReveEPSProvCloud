package com.reve.antivirus.entities;

import java.io.Serializable;

import com.reve.antivirus.entities.vframework.AbstractDTO;

import reveantivirus.annotations.Column;
import reveantivirus.annotations.DTO;
import reveantivirus.annotations.Id;
import reveantivirus.annotations.TableName;
import reveantivirus.annotations.utils.SerializeTo;

@DTO
@TableName("LAN_SERVER")
@reveantivirus.annotations.utils.Serializable
public class LanServerDTO  extends AbstractDTO implements Serializable{
	
    public static final String SERVER_ID = "SERVER_ID";
    public static final String UNIQUE_KEY = "UNIQUE_KEY";
    public static final String PRO_ID = "PRO_ID";
    public static final String EMAIL = "EMAIL";
    public static final String MOBILE = "MOBILE";
    public static final String IP = "IP";
    public static final String SERVICES = "SERVICES";
    public static final String LAST_MODIFICATION_TIME = "LAST_MODIFICATION_TIME";
    public static final String LAST_SYNC_TIME = "LAST_SYNC_TIME";
    public static final String ACTIVATION_ALLOWED = "ACTIVATION_ALLOWED";
    public static final String PRODUCT_VERSION = "PRODUCT_VERSION";
    public static final String IS_BETA_PRODUCT = "IS_BETA_PRODUCT";
    
    
    @Id
    @Column(name = SERVER_ID)
    @SerializeTo
    private long serverId;
    
    @Column(name = UNIQUE_KEY)
    @SerializeTo
    private String uniqueKey;
    
    @Column(name = PRO_ID)
    @SerializeTo
    private long proId;
    
    @Column(name = EMAIL)
    @SerializeTo
    private String email;
    
    @Column(name = MOBILE)
    @SerializeTo
    private String mobile;
    
    @Column(name = IP)
    @SerializeTo
    private String ip;
    
    @Column(name = SERVICES)
    @SerializeTo
    private long services;
    
    @Column(name = LAST_MODIFICATION_TIME)
    @SerializeTo
    private long lastModificationTime;
    
    @Column(name = LAST_SYNC_TIME)
    @SerializeTo
    private long lastSyncTime;
    
    @Column(name = ACTIVATION_ALLOWED)
    @SerializeTo
    private int activationAllowed;
    
    @Column(name = PRODUCT_VERSION)
    @SerializeTo
    private String productVersion;
    
    @Column(name = IS_BETA_PRODUCT)
    @SerializeTo
    private int isBetaProduct;
    

    public int getIsBetaProduct() {
        return isBetaProduct;
    }

    public void setIsBetaProduct(int isBetaProduct) {
        this.isBetaProduct = isBetaProduct;
    }

    public long getLastSyncTime() {
        return lastSyncTime;
    }

    public void setLastSyncTime(long lastSyncTime) {
        this.lastSyncTime = lastSyncTime;
    }

    public String getProductVersion() {
        return productVersion;
    }

    public void setProductVersion(String productVersion) {
        this.productVersion = productVersion;
    }

    public int getActivationAllowed() {
        return activationAllowed;
    }

    public void setActivationAllowed(int activationAllowed) {
        this.activationAllowed = activationAllowed;
    }

    public long getLastModificationTime() {
        return lastModificationTime;
    }

    public void setLastModificationTime(long lastModificationTime) {
        this.lastModificationTime = lastModificationTime;
    }
    
    

    public long getServices() {
        return services;
    }

    public void setServices(long services) {
        this.services = services;
    }

    
    
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public long getServerId() {
        return serverId;
    }

    public void setServerId(long serverId) {
        this.serverId = serverId;
    }

    public String getUniqueKey() {
        return uniqueKey;
    }

    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }

    public long getProId() {
        return proId;
    }

    public void setProId(long proId) {
        this.proId = proId;
    }
}
