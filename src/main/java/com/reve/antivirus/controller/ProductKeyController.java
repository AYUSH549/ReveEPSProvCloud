package com.reve.antivirus.controller;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
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
import com.reve.antivirus.dao.CommonDAO;
import com.reve.antivirus.dao.DAOAdapter;
import com.reve.antivirus.entities.ProductKeyDTO;
import com.reve.antivirus.entities.RegisterRequestDTO;
import com.reve.antivirus.lib.Library;
import com.reve.antivirus.service.LicenseService;
import com.reve.antivirus.service.RegisterService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Service
@RestController
@RequestMapping(value = "/License")
public class ProductKeyController {

	private static final Logger logger = LogManager.getLogger(HomePageController.class.getName());

	@Autowired
	private HttpServletRequest request;

	@Qualifier("datasource")
	@Autowired
	private javax.sql.DataSource dataSource;

	@Autowired
	private LicenseService licenseService;

	@RequestMapping(value = "/generateLicense", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String generateLicense() {

		JSONObject responseJson = new JSONObject();
		int errNum = Errors.UNKNOWN;
		Connection conn = null;

		try {

			int productType = 0;
			int validity = 0;
			int noOfUser = 0;
			long dateExpiry = 0;
			int keyType = 0;
			int maxLocations = 0;

			if (request.getParameter("productType") != null) {
				productType = Integer.parseInt(request.getParameter("productType"));
			}

			if (request.getParameter("validity") != null) {
				validity = Integer.parseInt(request.getParameter("validity"));
			}

			if (request.getParameter("noOfUser") != null) {
				noOfUser = Integer.parseInt(request.getParameter("noOfUser"));

			}

			if (request.getParameter("dateExpiry") != null) {
				dateExpiry = Long.parseLong(request.getParameter("dateExpiry"));

			} else {
				dateExpiry = Long.MAX_VALUE - 1;
			}

			if (request.getParameter("keyType") != null) {
				keyType = Integer.parseInt(request.getParameter("keyType"));
			}

			if (request.getParameter("maxLocations") != null) {
				maxLocations = Integer.parseInt(request.getParameter("maxLocations"));

			}

			conn = dataSource.getConnection();

			long prodID = CommonDAO.getNextSequenceID(conn, "PRODUCT_KEY");

			if (conn != null) {
				try {
					conn.close();
					logger.info("Connection closed");
				} catch (Exception e) {
				}
			}

			String productKey = Library.getNextRandomKey();
			String serialKey = String.format("%s%04d%04d%03d%04d", "E", noOfUser, validity, maxLocations, prodID);

			logger.info("GENERATE_LICENSE {" + "productType : " + productType + " , validity : " + validity + ", "
					+ "noOfUser : " + noOfUser + ", " + "dateExpiry : " + dateExpiry + ", " + "keyType : " + keyType
					+ ", " + "maxLocations : " + maxLocations + "prodID : " + prodID + "productKey : " + productKey
					+ "serialKey : " + serialKey + "}");

			ProductKeyDTO dto = new ProductKeyDTO();
			dto.setProId(prodID);
			dto.setProductKey(productKey);
			dto.setProductType(productType);
			dto.setValidity(validity);
			dto.setTotal(noOfUser);
			dto.setAvail(noOfUser);
			dto.setDateExpiry(dateExpiry);
			dto.setKeyType(keyType);
			dto.setMaxLocationSupported(maxLocations);
			dto.setAvailLocationSupported(maxLocations);
			dto.setDateCreated(System.currentTimeMillis());
			dto.setSerialKey(serialKey);
			dto.setIpRestriction("0.0.0.0");
			dto.setActivationTime(0);

			conn = dataSource.getConnection();

			HashMap<String, Object> filters = new HashMap<String, Object>();

			filters.put(ProductKeyDTO.PRO_KEY, productKey);
			filters.put(ProductKeyDTO.SERIAL_KEY, serialKey);

			List<ProductKeyDTO> data = (List<ProductKeyDTO>) DAOAdapter.find(conn, ProductKeyDTO.class, filters, null);
			filters.clear();

			if (data != null && data.size() != 0) {
				responseJson.put("msgType", Errors.UNKNOWN);
				responseJson.put("Message", "Invalid Request");
				logger.info("Product key or Serial Key already Exist");
				return responseJson.toString();
			}

			int updatedRows = DAOAdapter.insert(conn, ProductKeyDTO.class, dto);

			if (updatedRows <= 0) {

				logger.info("Product Key Insertion Failed");
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

		} catch (Exception e) {
			logger.info(e.getMessage(), e);
			responseJson.put("msgType", Errors.RETRY_LATER);
			responseJson.put("Message", "Exception Occured");
			return responseJson.toString();
		}

		responseJson.put("msgType", Errors.OK);
		responseJson.put("Message", "License Generated Successfully");
		logger.info("License Generated Successfully");
		return responseJson.toString();

	}

	@RequestMapping(value = "/getLicense", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String getLicense() {

		JSONObject responseJson = new JSONObject();
		int errNum = Errors.UNKNOWN;
		Connection conn = null;

		try {

			String serialKey = request.getParameter("serialKey");
			String productKey = request.getParameter("productKey");
			int keyType = 0;
			if (request.getParameter("keyType") != null) {
				keyType = Integer.parseInt(request.getParameter("keyType"));
			}

			conn = dataSource.getConnection();
			HashMap<String, Object> filters = new HashMap<String, Object>();

			filters.put(ProductKeyDTO.PRO_KEY, productKey);
			filters.put(ProductKeyDTO.SERIAL_KEY, serialKey);
			filters.put(ProductKeyDTO.KEY_TYPE, keyType);

			List<ProductKeyDTO> data = (List<ProductKeyDTO>) DAOAdapter.find(conn, ProductKeyDTO.class, filters, null);
			filters.clear();

			if (data == null || data.size() == 0) {
				responseJson.put("msgType", Errors.UNKNOWN);
				responseJson.put("Message", "Invalid Request");
				logger.info("Product key or Serial Key doesn't Exist");
				return responseJson.toString();
			}

			responseJson.put("msgType", Errors.OK);
			responseJson.put("Message", data);
			logger.info("Data Fetch Successfully");
			return responseJson.toString();

		} catch (Exception e) {
			logger.info(e.getMessage(), e);
			responseJson.put("msgType", Errors.RETRY_LATER);
			responseJson.put("Message", "Exception Occured");
			return responseJson.toString();
		}

	}

	@RequestMapping(value = "/getProductKeyDetails", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String getProductKeyDetails() {

		JSONObject responseJson = new JSONObject();
		logger.info("/getProductKeyDetails");
		Connection conn = null;

		try {

			int startIndex = 0;
			int endIndex = Integer.MAX_VALUE - 1;
			long start = 0;
			long end = 0;

			if ((request.getParameter("startIndex") != "")) {

				startIndex = Integer.parseInt(request.getParameter("startIndex"));
			}

			if ((request.getParameter("endIndex") != "")) {

				endIndex = Integer.parseInt(request.getParameter("endIndex"));
			}

			if (request.getParameter("start") != null) {
				if ((request.getParameter("start") != "")) {

					start = Long.parseLong(request.getParameter("start"));
				}
			}

			if ((request.getParameter("end") != "")) {

				end = Long.parseLong(request.getParameter("end"));
			}

			String ProductKey = request.getParameter("productKey");
			String SerialKey = request.getParameter("serialKey");

			logger.info("GET_RANGE {" + "}");

			conn = dataSource.getConnection();

			JSONArray fetchRange = licenseService.getKeys(conn, start, end, startIndex, endIndex, SerialKey,
					ProductKey);

			if (conn != null) {
				try {
					conn.close();
					logger.info("Connection closed");
				} catch (Exception e) {
				}
			}

			responseJson.put("msgType", Errors.OK);
			responseJson.put("Message", "Success");
			logger.info("Data Fetch Successfully");
			responseJson.put("data", fetchRange);
			return responseJson.toString();

		} catch (Exception e) {
			logger.info(e.getMessage(), e);
			responseJson.put("msgType", Errors.RETRY_LATER);
			responseJson.put("Message", "Exception Occured");
			return responseJson.toString();
		}

	}

	@RequestMapping(value = "/getProductKeyCount", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String getProductKeyCount() {

		JSONObject responseJson = new JSONObject();
		logger.info("/getProductKeyDetails");
		Connection conn = null;

		try {

			int product_type = 0;
			if (request.getParameter("product_type") != null) {
				product_type = Integer.parseInt(request.getParameter("product_type"));
			}
			int key_type = 0;
			if (request.getParameter("key_type") != null) {
				key_type = Integer.parseInt(request.getParameter("key_type"));
			}

			String product_key = request.getParameter("product_key");
			String serial_key = request.getParameter("serial_key");

			int validity = 0;
			if (request.getParameter("validity") != null) {
				validity = Integer.parseInt(request.getParameter("validity"));
			}
			long date_created = 0;
			long date_expired = 0;

			if (request.getParameter("date_created") != null) {
				date_created = Long.parseLong(request.getParameter("date_created"));
			}
			if (request.getParameter("date_expired") != null) {
				date_expired = Long.parseLong(request.getParameter("date_expired"));
			}

			conn = dataSource.getConnection();

			JSONObject obj = licenseService.getCount(conn, product_type, key_type, product_key, serial_key, validity,
					date_created, date_expired);

			if (conn != null) {
				try {
					conn.close();
					logger.info("Connection closed");
				} catch (Exception e) {
				}
			}

			responseJson.put("msgType", Errors.OK);
			responseJson.put("Message", "Success");
			logger.info("Count Fetch Successfully");
			responseJson.put("data", obj);
			return responseJson.toString();

		} catch (Exception e) {
			logger.info(e.getMessage(), e);
			responseJson.put("msgType", Errors.RETRY_LATER);
			responseJson.put("Message", "Exception Occured");
			return responseJson.toString();
		}
	}

}
