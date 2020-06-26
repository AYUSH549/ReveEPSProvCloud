package com.reve.antivirus.entities;


import java.io.Serializable;

import com.reve.antivirus.entities.vframework.AbstractDTO;

import reveantivirus.annotations.Column;
import reveantivirus.annotations.DTO;
import reveantivirus.annotations.TableName;
import reveantivirus.annotations.utils.SerializeTo;

@DTO
@TableName("SERVER_MAPPING")
@reveantivirus.annotations.utils.Serializable
public class ServerMappingDTO extends AbstractDTO implements Serializable{
	
	public static final String LID = "LID";
	public static final String SERVER_INFO_ID = "SERVER_INFO_ID";
	
	@Column(name = LID)
    @SerializeTo
	private long lid;
	
	
	@Column(name = SERVER_INFO_ID)
    @SerializeTo
	private long serverInfoId;


	public long getLid() {
		return lid;
	}


	public void setLid(long lid) {
		this.lid = lid;
	}


	public long getServerInfoId() {
		return serverInfoId;
	}


	public void setServerInfoId(long serverInfoId) {
		this.serverInfoId = serverInfoId;
	}
	
	
	
	

}
