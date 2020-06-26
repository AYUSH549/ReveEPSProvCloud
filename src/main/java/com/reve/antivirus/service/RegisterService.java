package com.reve.antivirus.service;


import java.sql.Connection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.reve.antivirus.dao.CheckDAO;
import com.reve.antivirus.entities.AddressDTO;
//import com.reve.antivirus.entities.LoginMapDTO;
import com.reve.antivirus.entities.RegisterRequestDTO;

@Component
public class RegisterService {


	@Autowired
	private CheckDAO checkdao;


	
	@Transactional
	public Long getAddressId(Connection conn) {

		return checkdao.getAddressId(conn);
	}
	

	@Transactional
	public Integer checkIfExist(String email) {

		return checkdao.checkIfExist(email);
	}
	
	@Transactional
	public List<RegisterRequestDTO> fetchDetails() {

		return checkdao.fetchDetails();
	}
	
	@Transactional
	public List<RegisterRequestDTO> fetchRange(int limit, int offset) {

		return checkdao.fetchRange(limit,offset);
	}
	
	@Transactional
	public Integer getCount() {

		return checkdao.getCount();
	}
	
	@Transactional
	public List<AddressDTO> fetchaddress(int limit, int offset) {

		return checkdao.fetchaddress(limit,offset);
	}
	

}
