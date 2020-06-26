package com.reve.antivirus.entities;

import java.io.Serializable;



import com.reve.antivirus.entities.vframework.AbstractDTO;

import reveantivirus.annotations.Column;
import reveantivirus.annotations.DTO;
import reveantivirus.annotations.Id;
import reveantivirus.annotations.TableName;
import reveantivirus.annotations.utils.SerializeTo;

@DTO
@TableName("ADDRESS")
@reveantivirus.annotations.utils.Serializable
public class AddressDTO extends AbstractDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public static final String ADDRESS_ID = "ADDRESS_ID";
	public static final String BUILDING_NO = "BUILDING_NO";
	public static final String COUNTRY = "COUNTRY";
	public static final String CITY = "CITY";
	public static final String PIN_CODE = "PIN_CODE";
	public static final String STREET = "STREET";
	public static final String STATE = "STATE";
	
	@Id(autoIncrement = true)
	@Column(name = ADDRESS_ID)
    @SerializeTo
	private long addressId;
	
	@Column(name = COUNTRY)
    @SerializeTo
	private String country;
	
	@Column(name = CITY)
    @SerializeTo
	private String city;
	
	@Column(name = PIN_CODE)
    @SerializeTo
	private String pinCode;
	
	@Column(name = BUILDING_NO)
    @SerializeTo
	private String buildingNo;
	
	@Column(name = STREET)
    @SerializeTo
	private String street;
	
	@Column(name = STATE)
    @SerializeTo
	private String state;
	
	
	
	public long getAddressId() {
		return addressId;
	}
	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPinCode() {
		return pinCode;
	}
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	public String getBuildingNo() {
		return buildingNo;
	}
	public void setBuildingNo(String buildingNo) {
		this.buildingNo = buildingNo;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
		
	
	
}
