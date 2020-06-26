package com.reve.antivirus.controller;

import java.sql.Connection;
import java.util.ArrayList;
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
import com.reve.antivirus.constants.Status;
import com.reve.antivirus.dao.DAOAdapter;
import com.reve.antivirus.entities.AddressDTO;
import com.reve.antivirus.entities.RegisterRequestDTO;
import com.reve.antivirus.entities.ServerInfoDTO;
import com.reve.antivirus.entities.ServerMappingDTO;
import com.reve.antivirus.service.RegisterService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Service
@RestController
@RequestMapping(value = "/home")
public class HomePageController {

	private static final Logger logger = LogManager.getLogger(HomePageController.class.getName());

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private RegisterService regsiterService;

	@Qualifier("datasource")
	@Autowired
	private javax.sql.DataSource dataSource;

	@RequestMapping(value = "/requestRegister", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String registerRequest() {

		JSONObject responseJson = new JSONObject();
		int errNum = Errors.UNKNOWN;
		Connection conn = null;

		try {

			String companyName = request.getParameter("companyName");
			long timestamp = System.currentTimeMillis();
			int isVerified = 0;
			Integer noOfUsers = 0;
			if (request.getParameter("noOfUser") != null) {
				noOfUsers = Integer.parseInt(request.getParameter("noOfUser"));
			}

			Long addressId = 0L;

			String buildingNo = request.getParameter("buildingNo");
			String city = request.getParameter("city");
			String pinCode = request.getParameter("pinCode");
			String country = request.getParameter("country");
			String state = request.getParameter("state");
			String street = request.getParameter("street");
			String mobile = request.getParameter("mobile");
			String requestByWhom = request.getParameter("requestByWhom");
			String email = request.getParameter("email");

			int status = Status.PENDING;

			logger.info("REQUEST_REGISTER {" + "email : " + email + " , companyName : " + companyName + ", "
					+ "timestamp : " + timestamp + ", " + "isVerified : " + isVerified + ", " + "noOfUsers : "
					+ noOfUsers + ", " + "buildingNo : " + buildingNo + "," + "city : " + city + "," + "pinCode : "
					+ pinCode + "," + "country : " + country + "," + "mobile : " + mobile + "," + "requestByWhom : "
					+ requestByWhom + "state : " + state + "street : " + street + "}");

			if (email == null || buildingNo == null || companyName == null || city == null || pinCode == null
					|| country == null || mobile == null || requestByWhom == null || street == null) {

				responseJson.put("msgType", Errors.UNKNOWN);
				responseJson.put("message", "Missing Parameters");
				return responseJson.toString();

			}

			int checkRequest = regsiterService.checkIfExist(email);
			logger.info("checkRequest" + "" + checkRequest);

			logger.info("checkRequest" + "  " + checkRequest);

			if (checkRequest == Errors.LOGIN_ID_ALREADY_EXIST) {
				responseJson.put("msgType", Errors.LOGIN_ID_ALREADY_EXIST);
				responseJson.put("Message", "Request Already Found with Same Email");
				logger.info("Request Already Found");
				return responseJson.toString();
			}

			AddressDTO adDTO = new AddressDTO();
			adDTO.setCountry(country);
			adDTO.setCity(city);
			adDTO.setPinCode(pinCode);
			adDTO.setBuildingNo(buildingNo);
			adDTO.setState(state);
			adDTO.setStreet(street);

			int updatedRows = 0;

			conn = dataSource.getConnection();

			updatedRows = DAOAdapter.insert(conn, AddressDTO.class, adDTO);

			if (updatedRows <= 0) {
				logger.info("Address Insertion Failed");
				responseJson.put("msgType", Errors.UNKNOWN);
				responseJson.put("Message", "Failed to Deliver Request");
				return responseJson.toString();

			}

			addressId = regsiterService.getAddressId(conn);

			logger.info("addressId" + " " + addressId);

			if (addressId == null) {
				logger.info("Error fetching addressId");
				responseJson.put("msgType", Errors.ERROR_FETCHING_DATA);
				responseJson.put("Message", "Error fetching addressId");
				return responseJson.toString();
			}

			logger.info("addressId" + " " + addressId);

			if (conn != null) {
				try {
					conn.close();
					logger.info("Connection closed");
				} catch (Exception e) {
				}
			}

			RegisterRequestDTO regDTO = new RegisterRequestDTO();
			regDTO.setCompanyName(companyName);
			regDTO.setTimestamp(timestamp);
			regDTO.setIsVerified(isVerified);
			regDTO.setNoOfUsers(noOfUsers);
			regDTO.setAddressId(addressId);
			regDTO.setMobile(mobile);
			regDTO.setRequestByWhom(requestByWhom);
			regDTO.setEmail(email);
			regDTO.setStatus(status);
			regDTO.setCountry(country);

			conn = dataSource.getConnection();

			errNum = DAOAdapter.insert(conn, RegisterRequestDTO.class, regDTO);
			logger.info(errNum);

			if (errNum <= 0) {

				logger.info("Request Insertion Failed");
				responseJson.put("msgType", Errors.UNKNOWN);
				responseJson.put("Message", "Failed to Deliver Request");
				return responseJson.toString();

			}

			if (conn != null) {
				try {
					conn.close();
					logger.info("Connection closed");
				} catch (Exception e) {
				}
			}

		}

		catch (Exception e) {
			logger.info(e.getMessage(), e);
			responseJson.put("msgType", Errors.RETRY_LATER);
			responseJson.put("Message", "Exception Occured");
			return responseJson.toString();
		}

		responseJson.put("msgType", Errors.OK);
		responseJson.put("Message", "Request send Successfully");
		logger.info("Request send Successfully");
		return responseJson.toString();

	}

	@RequestMapping(value = "/getByEmail", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String getRegisterRequest() {

		JSONObject responseJson = new JSONObject();
		logger.info("/getByEmail");
		Connection conn = null;

		try {

			String email = request.getParameter("email");
			logger.info("GET_BY_EMAIL {" + "email" + " " + email + "}");

			if (conn == null) {
				conn = dataSource.getConnection();
			}

			HashMap<String, Object> filters = new HashMap<String, Object>();
			filters.put(RegisterRequestDTO.EMAIL, email);
			List<RegisterRequestDTO> data = (List<RegisterRequestDTO>) DAOAdapter.find(conn, RegisterRequestDTO.class,
					filters, null);
			filters.clear();

			if (data == null || data.size() == 0) {
				responseJson.put("msgType", Errors.UNKNOWN);
				responseJson.put("Message", "Invalid Request");
				logger.info("Data not found");
				return responseJson.toString();
			}

			if (conn != null) {
				try {
					dataSource.getConnection().close();
					logger.info("Connection closed");
				} catch (Exception e) {
				}
			}

			responseJson.put("msgType", Errors.UNKNOWN);
			responseJson.put("Message", "Success");
			responseJson.put("Data", data);
			logger.info("Successfully fetch data");
			return responseJson.toString();

		} catch (Exception e) {
			logger.info(e.getMessage(), e);
			responseJson.put("msgType", Errors.RETRY_LATER);
			responseJson.put("Message", "Exception Occured");
			return responseJson.toString();
		}

	}

	@RequestMapping(value = "/getRegDetails", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String getAllRegisterRequestDetails() {

		JSONObject responseJson = new JSONObject();
		logger.info("/getRegDetails");

		try {

			int startIndex = 0;
			int endIndex = Integer.MAX_VALUE - 1;
			int start = 0;
			int end = 0;

			if ((request.getParameter("startIndex") != "")) {

				startIndex = Integer.parseInt(request.getParameter("startIndex"));
			}

			if ((request.getParameter("endIndex") != "")) {

				endIndex = Integer.parseInt(request.getParameter("endIndex"));
			}

			if (request.getParameter("start") != null) {
				if ((request.getParameter("start") != "")) {

					start = Integer.parseInt(request.getParameter("start"));
				}
			}

			if ((request.getParameter("end") != "")) {

				endIndex = Integer.parseInt(request.getParameter("end"));
			}

			int limit = endIndex - startIndex + 1;
			int offset = startIndex;
			HashMap<String, Object> data = new HashMap<String, Object>();

			logger.info("Limit" + " " + limit);
			logger.info("Offset" + " " + offset);

			logger.info("GET_RANGE {" + "}");
			List<RegisterRequestDTO> fetchRange = regsiterService.fetchRange(limit, offset);

			if (fetchRange == null || fetchRange.size() == 0) {
				responseJson.put("msgType", Errors.UNKNOWN);
				responseJson.put("Message", "Failed to deliver request");
				logger.info("Data Fetch Failed");
				return responseJson.toString();
			}

			Integer count = regsiterService.getCount();

			if (count < 0) {
				responseJson.put("msgType", Errors.UNKNOWN);
				responseJson.put("Message", "Failed to deliver request");
				logger.info("count Fetch Failed");
				return responseJson.toString();
			}

			responseJson.put("msgType", Errors.OK);
			responseJson.put("Message", "Success");
			logger.info("Data Fetch Successfully");
			responseJson.put("MaxCount", count);
			responseJson.put("data", fetchRange);
			return responseJson.toString();

		} catch (Exception e) {
			logger.info(e.getMessage(), e);
			responseJson.put("msgType", Errors.RETRY_LATER);
			responseJson.put("Message", "Exception Occured");
			return responseJson.toString();
		}

	}

	@RequestMapping(value = "/rejectRegisterRequest", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String rejectRegisterRequest() {

		JSONObject responseJson = new JSONObject();
		Connection conn = null;
		logger.info("/rejectRegisterRequest");
		try {
			String email = request.getParameter("email");

			if (email == null) {
				responseJson.put("msgType", Errors.UNKNOWN);
				responseJson.put("Message", "Invalid Request");
				logger.info("Data not found");
				return responseJson.toString();
			}

			logger.info("REJECT_REQUEST {" + "email" + " " + email + "}");

			conn = dataSource.getConnection();

			HashMap<String, Object> filters = new HashMap<String, Object>();
			filters.put(RegisterRequestDTO.EMAIL, email);
			List<RegisterRequestDTO> data = (List<RegisterRequestDTO>) DAOAdapter.find(conn, RegisterRequestDTO.class,
					filters, null);
			filters.clear();

			if (data == null || data.size() == 0) {
				responseJson.put("msgType", Errors.UNKNOWN);
				responseJson.put("Message", "Invalid Request");
				logger.info("Data not found");
				return responseJson.toString();
			}

			if (conn != null) {
				try {
					dataSource.getConnection().close();
					logger.info("Connection closed");
				} catch (Exception e) {
				}
			}

			RegisterRequestDTO regDTO = data.get(0);
			regDTO.setStatus(Status.REJECTED);

			conn = dataSource.getConnection();

			int updateRows = DAOAdapter.update(conn, RegisterRequestDTO.class, regDTO);
			logger.info(updateRows);

			if (updateRows <= 0) {

				logger.info("Request Insertion Failed");
				responseJson.put("msgType", Errors.UNKNOWN);
				responseJson.put("Message", "Failed to Deliver Request");
				return responseJson.toString();

			}

			if (conn != null) {
				try {
					dataSource.getConnection().close();
					logger.info("Connection closed");
				} catch (Exception e) {

				}
			}

			responseJson.put("msgType", Errors.OK);
			responseJson.put("Message", "Success");
			logger.info("Data Fetch Successfully");
			return responseJson.toString();
		} catch (Exception e) {
			logger.info(e.getMessage(), e);
			responseJson.put("msgType", Errors.RETRY_LATER);
			responseJson.put("Message", "Exception Occured");
			return responseJson.toString();
		}
	}

	@RequestMapping(value = "/countRegisterRequest", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String countRegisterRequest() {

		JSONObject responseJson = new JSONObject();
		int errNum = Errors.UNKNOWN;

		try {

			Integer count = regsiterService.getCount();

			if (count < 0) {
				responseJson.put("msgType", Errors.UNKNOWN);
				responseJson.put("Message", "Failed to deliver request");
				logger.info("count Fetch Failed");
				return responseJson.toString();
			}

			responseJson.put("msgType", Errors.OK);
			responseJson.put("Message", "Success");
			logger.info("Data Fetch Successfully");
			responseJson.put("MaxCount", count);
			return responseJson.toString();

		} catch (Exception e) {
			logger.info(e.getMessage(), e);
			responseJson.put("msgType", Errors.RETRY_LATER);
			responseJson.put("Message", "Exception Occured");
			return responseJson.toString();
		}

	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String login() {

		JSONObject responseJson = new JSONObject();
		logger.info("/login");

		return null;
	}


}

// Code Fold Ctrl + Shift + NUM_KEYPAD_DIVIDE
// Code UnFold Ctrl + Shift + NUM_KEYPAD_MULTIPLY
