package com.reve.antivirus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.reve.antivirus.dao.LoadBalancerDAO;

@Component
public class LoadBalancerService {

	@Autowired
	private LoadBalancerDAO loadBalancerDAO;

	@Transactional
	public Long getServerInfoId() {

		return loadBalancerDAO.getServerInfoId();
	}

}
