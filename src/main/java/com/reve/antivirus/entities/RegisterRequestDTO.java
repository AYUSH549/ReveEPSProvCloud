package com.reve.antivirus.entities;

import java.io.Serializable;
import reveantivirus.annotations.Id;

import com.reve.antivirus.entities.vframework.AbstractDTO;

import reveantivirus.annotations.Column;

import reveantivirus.annotations.DTO;
import reveantivirus.annotations.TableName;
import reveantivirus.annotations.utils.SerializeTo;

@DTO
@TableName("REGISTER_REQUEST")
@reveantivirus.annotations.utils.Serializable
public class RegisterRequestDTO extends AbstractDTO implements Serializable {


	private static final long serialVersionUID = 1L;
	
	public static final String REG_ID = "REG_ID";
	public static final String COMPANY_NAME = "COMPANY_NAME";
	public static final String TIMESTAMP = "TIMESTAMP";
	public static final String IS_VERIFIED = "IS_VERIFIED";
	public static final String NO_OF_USERS = "NO_OF_USERS";
	public static final String ADDRESS_ID = "ADDRESS_ID";
	public static final String MOBILE = "MOBILE";
	public static final String REQUEST_BY_WHOM = "REQUEST_BY_WHOM";
	public static final String EMAIL = "EMAIL";
	public static final String STATUS = "STATUS";
	public static final String COUNTRY = "COUNTRY";
	
	@Id(autoIncrement = true)
	@Column(name = REG_ID)
    @SerializeTo
	private Long regId;
	
	@Column(name = COMPANY_NAME)
    @SerializeTo
	private String companyName;
	
	@Column(name = TIMESTAMP)
    @SerializeTo
	private long timestamp;
	
	@Column(name = IS_VERIFIED)
    @SerializeTo
	private int isVerified;
	
	@Column(name = NO_OF_USERS)
    @SerializeTo
	private int noOfUsers;
	
	@Column(name = ADDRESS_ID)
    @SerializeTo
	private long addressId;
	
	@Column(name = MOBILE)
    @SerializeTo
	private String mobile;
	
	@Column(name = REQUEST_BY_WHOM)
    @SerializeTo
	private String requestByWhom;
	
	@Column(name = EMAIL)
    @SerializeTo
	private String email;
	
	@Column(name = STATUS)
    @SerializeTo
	private int status;
	
	@Column(name = COUNTRY)
    @SerializeTo
	private String country;

	public Long getRegId() {
		return regId;
	}

	public void setRegId(Long regId) {
		this.regId = regId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public int getIsVerified() {
		return isVerified;
	}

	public void setIsVerified(int isVerified) {
		this.isVerified = isVerified;
	}

	public int getNoOfUsers() {
		return noOfUsers;
	}

	public void setNoOfUsers(int noOfUsers) {
		this.noOfUsers = noOfUsers;
	}
	

	public long getAddressId() {
		return addressId;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	public String getRequestByWhom() {
		return requestByWhom;
	}

	public void setRequestByWhom(String requestByWhom) {
		this.requestByWhom = requestByWhom;
	}

	@Override
	public String toString() {
		return "RegisterRequestDTO [regId=" + regId + ", companyName=" + companyName + ", timestamp=" + timestamp
				+ ", isVerified=" + isVerified + ", noOfUsers=" + noOfUsers + ", addressId=" + addressId + ", mobile="
				+ mobile + ", requestByWhom=" + requestByWhom + ", email=" + email + ", status=" + status
				+ ", countryId=" + country + ", getRegId()=" + getRegId() + ", getCompanyName()=" + getCompanyName()
				+ ", getEmail()=" + getEmail() + ", getTimestamp()=" + getTimestamp() + ", getIsVerified()="
				+ getIsVerified() + ", getNoOfUsers()=" + getNoOfUsers() + ", getAddressId()=" + getAddressId()
				+ ", getStatus()=" + getStatus() + ", getCountryId()=" + getCountry() + ", getMobile()=" + getMobile()
				+ ", getRequestByWhom()=" + getRequestByWhom() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	
	

}
