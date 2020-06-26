package com.reve.antivirus.entities;

import reveantivirus.annotations.Column;
import reveantivirus.annotations.DTO;
import reveantivirus.annotations.Id;
import reveantivirus.annotations.TableName;
import reveantivirus.annotations.utils.SerializeTo;

@DTO
@TableName("PRODUCT_KEY")
@reveantivirus.annotations.utils.Serializable
public class ProductKeyDTO {
	
	
    private static final long serialVersionUID = 1L;
	
	public static final String PRO_ID = "PRO_ID";
	public static final String PRO_KEY = "PRO_KEY"; 
	public static final String TOTAL = "TOTAL";
	public static final String AVAIL = "AVAIL";
	public static final String VALIDITY = "VALIDITY";
	public static final String PRODUCT_TYPE = "PRODUCT_TYPE";
	public static final String DATE_CREATED = "DATE_CREATED";
	public static final String DATE_EXPIRY = "DATE_EXPIRY";
	public static final String SERIAL_KEY = "SERIAL_KEY";
	public static final String ACTIVATION_TIME = "ACTIVATION_TIME";
	public static final String MAX_LOCATION_SUPPORTED = "MAX_LOCATION_SUPPORTED";
	public static final String AVAILABLE_SUPPORTING_LOCATIONS = "AVAILABLE_SUPPORTING_LOCATIONS";
	public static final String IP_RESTRICTION = "IP_RESTRICTION";
	public static final String KEY_TYPE = "KEY_TYPE";
	public static final String MODIFICATION_FLAG = "MODIFICATION_FLAG";
	
	
	@Id
	@Column(name = PRO_ID)
    @SerializeTo
    private long proId;
	
	@Column(name = PRO_KEY)
    @SerializeTo
    private String productKey;
	
	@Column(name = TOTAL)
    @SerializeTo
	private int total;
	
	@Column(name = AVAIL)
    @SerializeTo
	private int avail;
	
	@Column(name = VALIDITY)
    @SerializeTo
	private int validity;
	
	@Column(name = PRODUCT_TYPE)
    @SerializeTo
	private int productType;
	
	@Column(name = DATE_CREATED)
    @SerializeTo
	private long dateCreated;
	
	@Column(name = DATE_EXPIRY)
    @SerializeTo
	private long dateExpiry;
	
	@Column(name = SERIAL_KEY)
    @SerializeTo
	private String serialKey;
	
	@Column(name = ACTIVATION_TIME)
    @SerializeTo
	private long activationTime;
	
	@Column(name = MAX_LOCATION_SUPPORTED)
    @SerializeTo
	private int maxLocationSupported;
	
	@Column(name = AVAILABLE_SUPPORTING_LOCATIONS)
    @SerializeTo
	private int availLocationSupported;
	
	@Column(name = IP_RESTRICTION)
    @SerializeTo
	private String ipRestriction;
	
	@Column(name = KEY_TYPE)
    @SerializeTo
	private int keyType;
	
	@Column(name = MODIFICATION_FLAG)
    @SerializeTo
	private int modFlag;

	
	public long getProId() {
		return proId;
	}

	public void setProId(long proId) {
		this.proId = proId;
	}

	public String getProductKey() {
		return productKey;
	}

	public void setProductKey(String productKey) {
		this.productKey = productKey;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getAvail() {
		return avail;
	}

	public void setAvail(int avail) {
		this.avail = avail;
	}

	public int getValidity() {
		return validity;
	}

	public void setValidity(int validity) {
		this.validity = validity;
	}

	public int getProductType() {
		return productType;
	}

	public void setProductType(int productType) {
		this.productType = productType;
	}

	public long getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(long dateCreated) {
		this.dateCreated = dateCreated;
	}

	public long getDateExpiry() {
		return dateExpiry;
	}

	public void setDateExpiry(long dateExpiry) {
		this.dateExpiry = dateExpiry;
	}

	public String getSerialKey() {
		return serialKey;
	}

	public void setSerialKey(String serialKey) {
		this.serialKey = serialKey;
	}

	public long getActivationTime() {
		return activationTime;
	}

	public void setActivationTime(long activationTime) {
		this.activationTime = activationTime;
	}

	public int getMaxLocationSupported() {
		return maxLocationSupported;
	}

	public void setMaxLocationSupported(int maxLocationSupported) {
		this.maxLocationSupported = maxLocationSupported;
	}

	public int getAvailLocationSupported() {
		return availLocationSupported;
	}

	public void setAvailLocationSupported(int availLocationSupported) {
		this.availLocationSupported = availLocationSupported;
	}

	public String getIpRestriction() {
		return ipRestriction;
	}

	public void setIpRestriction(String ipRestriction) {
		this.ipRestriction = ipRestriction;
	}

	public int getKeyType() {
		return keyType;
	}

	public void setKeyType(int keyType) {
		this.keyType = keyType;
	}

	public int getModFlag() {
		return modFlag;
	}

	public void setModFlag(int modFlag) {
		this.modFlag = modFlag;
	}
	
	
	

}
