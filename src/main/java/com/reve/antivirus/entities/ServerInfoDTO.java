package com.reve.antivirus.entities;


import java.io.Serializable;

import com.reve.antivirus.entities.vframework.AbstractDTO;

import reveantivirus.annotations.Column;
import reveantivirus.annotations.DTO;
import reveantivirus.annotations.Id;
import reveantivirus.annotations.TableName;
import reveantivirus.annotations.utils.SerializeTo;

@DTO
@TableName("SERVER_INFO")
@reveantivirus.annotations.utils.Serializable
public class ServerInfoDTO extends AbstractDTO implements Serializable {
	
	public static final String SERVER_INFO_ID = "SERVER_INFO_ID";
	public static final String IP = "IP";
	public static final String PORT = "PORT";
	

    @Column(name = SERVER_INFO_ID)
    @SerializeTo
    private long serverInfoId;
    
    @Column(name = IP)
    @SerializeTo
    private String ip;
    
    
    @Column(name = PORT)
    @SerializeTo
    private int port;


    
	public long getServerInfoId() {
		return serverInfoId;
	}


	public void setServerInfoId(long serverInfoId) {
		this.serverInfoId = serverInfoId;
	}


	public String getIp() {
		return ip;
	}


	public void setIp(String ip) {
		this.ip = ip;
	}


	public int getPort() {
		return port;
	}


	public void setPort(int port) {
		this.port = port;
	}
    

}
