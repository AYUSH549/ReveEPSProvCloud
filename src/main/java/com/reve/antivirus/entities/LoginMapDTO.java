package com.reve.antivirus.entities;


import java.io.Serializable;
import com.reve.antivirus.entities.vframework.AbstractDTO;
import reveantivirus.annotations.Column;
import reveantivirus.annotations.DTO;
import reveantivirus.annotations.TableName;
import reveantivirus.annotations.utils.SerializeTo;

@DTO
@TableName("LOGINMAP")
@reveantivirus.annotations.utils.Serializable
public class LoginMapDTO extends AbstractDTO implements Serializable{


    public static final String LID = "LID";
    public static final String SERVER_ID = "SERVER_ID";
    public static final String UNIQUE_KEY = "UNIQUE_KEY";
    
    @Column(name = LID)
    @SerializeTo
    private long lid;
    
    @Column(name = SERVER_ID)
    @SerializeTo
    private long serverId;
    
    @Column(name = UNIQUE_KEY)
    @SerializeTo
    private String uniqueKey;
    
    

	public long getLid() {
		return lid;
	}

	public void setLid(long lid) {
		this.lid = lid;
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
    
   
}
