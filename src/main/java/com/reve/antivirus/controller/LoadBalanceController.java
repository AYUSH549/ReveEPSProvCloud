package com.reve.antivirus.controller;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.reve.antivirus.constants.Errors;
import com.reve.antivirus.dao.DAOAdapter;
import com.reve.antivirus.entities.LoginDTO;
import com.reve.antivirus.entities.ServerInfoDTO;
import com.reve.antivirus.entities.ServerMappingDTO;
import com.reve.antivirus.service.RegisterService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Service
@RestController
@RequestMapping(value = "/loadBalance")
public class LoadBalanceController {
	
	private static final Logger logger = LogManager.getLogger(LoadBalanceController.class.getName());

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private RegisterService regsiterService;

	@Qualifier("datasource")
	@Autowired
	private javax.sql.DataSource dataSource;

	@RequestMapping(value = "/getIpPortByLID", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String getIpPortByLID() {

		JSONObject responseJson = new JSONObject();
		Connection conn = null;

		try {
			long lid = (Long.parseLong(request.getParameter("lid")));
			conn = dataSource.getConnection();
			HashMap<String, Object> filters = new HashMap<String, Object>();
			filters.put(ServerMappingDTO.LID, lid);
			List<ServerMappingDTO> mapdata = (List<ServerMappingDTO>) DAOAdapter.find(conn, ServerMappingDTO.class,
					filters, null);
			filters.clear();

			if (mapdata == null || mapdata.size() == 0) {
				responseJson.put("msgType", Errors.UNKNOWN);
				responseJson.put("Message", "Failed to deliver request");
				logger.info("Data not found");
				return responseJson.toString();
			}

			Long serverInfoId = mapdata.get(0).getServerInfoId();
			filters.put(ServerInfoDTO.SERVER_INFO_ID, serverInfoId);
			List<ServerInfoDTO> getdata = (List<ServerInfoDTO>) DAOAdapter.find(conn, ServerInfoDTO.class, filters,
					null);
			filters.clear();

			if (getdata == null || getdata.size() == 0) {
				responseJson.put("msgType", Errors.UNKNOWN);
				responseJson.put("Message", "Failed to deliver request");
				logger.info("Data not found");
				return responseJson.toString();
			}

			String ip = getdata.get(0).getIp();
			int port = getdata.get(0).getPort();

			if (conn != null) {
				try {
					conn.close();
					logger.info("Connection closed");
				} catch (Exception e) {
				}
			}

			responseJson.put("msgType", Errors.OK);
			responseJson.put("IP", ip);
			responseJson.put("Port", port);
			logger.info("Successfully delivered request");
			return responseJson.toString();

		}

		catch (Exception e) {
			logger.info(e.getMessage(), e);
			responseJson.put("msgType", Errors.RETRY_LATER);
			responseJson.put("Message", "Exception Occured");
			return responseJson.toString();
		}

	}
	
	@RequestMapping(value = "/getIpPortByLoginId", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String getIpPortByLoginId() {

		JSONObject responseJson = new JSONObject();
		Connection conn = null;
		HashMap<String, Object> filters = new HashMap<String, Object>();

		try {
			
			String loginId = request.getParameter("loginId");
			String password = request.getParameter("password");
			
			if(loginId == null || password == null)
			{
				responseJson.put("msgType", Errors.UNKNOWN);
				responseJson.put("Message", "Missing Parameters");
				logger.info("Missing Parameters");
				return responseJson.toString();
			}
			
			conn = dataSource.getConnection();
			
			
			filters.put(LoginDTO.LOGIN_ID,loginId);
			filters.put(LoginDTO.PASSWORD,password);
					
			List<LoginDTO> loginList = (List<LoginDTO>) DAOAdapter.find(conn, LoginDTO.class,
					filters, null);
			filters.clear();

			if (loginList == null || loginList.size() == 0) {
				responseJson.put("msgType", Errors.UNKNOWN);
				responseJson.put("Message", "Failed to deliver request");
				logger.info("Data not found in loginTable");
				return responseJson.toString();
			}
			
			filters.put(ServerMappingDTO.LID, loginList.get(0).getLid());
			
			List<ServerMappingDTO> mapdata = (List<ServerMappingDTO>) DAOAdapter.find(conn, ServerMappingDTO.class,
					filters, null);
			filters.clear();

			if (mapdata == null || mapdata.size() == 0) {
				responseJson.put("msgType", Errors.UNKNOWN);
				responseJson.put("Message", "Failed to deliver request");
				logger.info("Data not found");
				return responseJson.toString();
			}

			Long serverInfoId = mapdata.get(0).getServerInfoId();
			filters.put(ServerInfoDTO.SERVER_INFO_ID, serverInfoId);
			List<ServerInfoDTO> getdata = (List<ServerInfoDTO>) DAOAdapter.find(conn, ServerInfoDTO.class, filters,
					null);
			filters.clear();

			if (getdata == null || getdata.size() == 0) {
				responseJson.put("msgType", Errors.UNKNOWN);
				responseJson.put("Message", "Failed to deliver request");
				logger.info("Data not found");
				return responseJson.toString();
			}

			String ip = getdata.get(0).getIp();
			int port = getdata.get(0).getPort();

			if (conn != null) {
				try {
					conn.close();
					logger.info("Connection closed");
				} catch (Exception e) {
				}
			}

			responseJson.put("msgType", Errors.OK);
			responseJson.put("IP", ip);
			responseJson.put("Port", port);
			logger.info("Successfully delivered request");
			return responseJson.toString();
			
			
			
			
		}
		catch (Exception e) {
			logger.info(e.getMessage(), e);
			responseJson.put("msgType", Errors.RETRY_LATER);
			responseJson.put("Message", "Exception Occured");
			return responseJson.toString();
		}
	
	}
	
	

}
