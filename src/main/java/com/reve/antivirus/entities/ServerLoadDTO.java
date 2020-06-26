package com.reve.antivirus.entities;


import java.io.Serializable;

import com.reve.antivirus.entities.vframework.AbstractDTO;

import reveantivirus.annotations.Column;
import reveantivirus.annotations.DTO;
import reveantivirus.annotations.Id;
import reveantivirus.annotations.TableName;
import reveantivirus.annotations.utils.SerializeTo;

@DTO
@TableName("SERVER_LOAD")
@reveantivirus.annotations.utils.Serializable
public class ServerLoadDTO extends AbstractDTO implements Serializable {
	
	public static final String SERVER_INFO_ID = "SERVER_INFO_ID";
	public static final String LOGIN_COUNT = "LOGIN_COUNT";
	public static final String USERS_COUNT = "USERS_COUNT";
	public static final String CPU_RAM_SPEED = "CPU_RAM_SPEED";
	public static final String STORAGE_SPACE = "STORAGE_SPACE";
	public static final String BANDWIDTH = "BANDWIDTH";
	public static final String LOCATION = "LOCATION";
	public static final String LOAD = "LOAD";
	
	@Column(name = SERVER_INFO_ID)
    @SerializeTo
	private long serverInfoId;
	
	@Column(name = LOGIN_COUNT)
    @SerializeTo
	private int loginCount;
	
	
	@Column(name = USERS_COUNT)
    @SerializeTo
	private int usersCount;
	
	@Column(name = CPU_RAM_SPEED)
    @SerializeTo
	private int cpuRamSpeed;
	
	@Column(name = STORAGE_SPACE)
    @SerializeTo
	private int storageSpace;
	
	
	@Column(name = BANDWIDTH)
    @SerializeTo
	private int bandwidth;
	
	@Column(name = LOCATION)
    @SerializeTo
	private int location;
	
	@Column(name = LOAD)
    @SerializeTo
	private int load;
	

	public long getServerInfoId() {
		return serverInfoId;
	}

	public void setServerInfoId(long serverInfoId) {
		this.serverInfoId = serverInfoId;
	}

	public int getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(int loginCount) {
		this.loginCount = loginCount;
	}

	public int getUsersCount() {
		return usersCount;
	}

	public void setUsersCount(int usersCount) {
		this.usersCount = usersCount;
	}

	public int getCpuRamSpeed() {
		return cpuRamSpeed;
	}

	public void setCpuRamSpeed(int cpuRamSpeed) {
		this.cpuRamSpeed = cpuRamSpeed;
	}

	public int getStorageSpace() {
		return storageSpace;
	}

	public void setStorageSpace(int storageSpace) {
		this.storageSpace = storageSpace;
	}

	public int getBandwidth() {
		return bandwidth;
	}

	public void setBandwidth(int bandwidth) {
		this.bandwidth = bandwidth;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	public int getLoad() {
		return load;
	}

	public void setLoad(int load) {
		this.load = load;
	}
	


}
