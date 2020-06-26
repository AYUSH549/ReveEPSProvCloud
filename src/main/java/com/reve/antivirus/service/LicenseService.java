package com.reve.antivirus.service;

import java.sql.Connection;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.reve.antivirus.dao.ProductKeyDAO;
import com.reve.antivirus.entities.ProductKeyDTO;


@Component
public class LicenseService {
	
	@Autowired
	private ProductKeyDAO productKeyDAO;
	
	@Transactional
	public List<ProductKeyDTO> fetchLicenseDetails(int limit, int offset) {

		return productKeyDAO.fetchLicenseDetails(limit,offset);
	}
	
	@Transactional
	public JSONArray getKeys(Connection con, long startingDate, long endingDate, int si, int ei, String serialKey, String productKey) {

		return productKeyDAO.getKeys(con,startingDate,endingDate,si,ei,serialKey,productKey);
	}
	
	@Transactional
	 public JSONObject getCount(Connection con, int product_type, int key_type, String product_key, String serial_key, int validity, long date_created, long date_expired)
	 {
		return productKeyDAO.getCount(con,product_type,key_type,product_key,product_key,validity,date_created,date_expired);
	 }

	

}
