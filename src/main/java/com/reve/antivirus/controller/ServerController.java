package com.reve.antivirus.controller;

import java.net.Socket;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

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

import com.reve.antivirus.constants.ApplicationConstant;
import com.reve.antivirus.constants.Errors;
import com.reve.antivirus.constants.Messages;
import com.reve.antivirus.constants.Status;
import com.reve.antivirus.dao.DAOAdapter;
import com.reve.antivirus.entities.LanServerDTO;
import com.reve.antivirus.entities.LoginDTO;
import com.reve.antivirus.entities.LoginMapDTO;
import com.reve.antivirus.entities.ProductKeyDTO;
import com.reve.antivirus.entities.RegisterRequestDTO;
import com.reve.antivirus.entities.ServerInfoDTO;
import com.reve.antivirus.entities.ServerLoadDTO;
import com.reve.antivirus.entities.ServerMappingDTO;
import com.reve.antivirus.lib.Library;
import com.reve.antivirus.lib.SocketFactory;
import packet.DataPacket;
import tcp.TCPProcessor;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Service
@RestController
@RequestMapping(value = "/server")
public class ServerController {

	private static final Logger logger = LogManager.getLogger(ServerController.class.getName());

	@Autowired
	private HttpServletRequest request;

	@Qualifier("datasource")
	@Autowired
	private javax.sql.DataSource dataSource;

	@RequestMapping(value = "/serverRegistration", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String register() {

		JSONObject responseJson = new JSONObject();
		int errNum = Errors.UNKNOWN;
		Connection conn = null;
		logger.info("asdfd");

		try {

			String loginId = request.getParameter("loginId");
			String mobile = request.getParameter("mobile");
			String password = request.getParameter("password");
			String timezone = "Asia/Kolkata";

			logger.info("REQUEST_SERVER_REGISTRATION {" + "LOGIN_ID : " + loginId + "," + "MOBILE : " + mobile + ","
					+ "PASSWORD : " + "<hidden>" + "}");

			if (loginId == null || mobile == null || password == null) {
				responseJson.put("msgType", Errors.UNKNOWN);
				responseJson.put("message", "Missing Parameters");
				return responseJson.toString();
			}

			RegisterRequestDTO regDTO = null;

			HashMap<String, Object> filters = new HashMap<String, Object>();
			conn = dataSource.getConnection();
			filters.put(RegisterRequestDTO.EMAIL, loginId);
			List<RegisterRequestDTO> data = (List<RegisterRequestDTO>) DAOAdapter.find(conn, RegisterRequestDTO.class,
					filters, null);
			filters.clear();

			if (data == null || data.size() == 0) {
				responseJson.put("msgType", Errors.UNKNOWN);
				responseJson.put("Message", "Failed to deliver request");
				logger.info("Data not found");
				return responseJson.toString();
			}

			regDTO = data.get(0);

			if (conn != null) {
				try {
					conn.close();
					logger.info("Connection closed");
				} catch (Exception e) {
				}
			}

			DataPacket requestPacket = new DataPacket();
			requestPacket.setMessageType(Messages.REQUEST_SERVER_REGISTRATION);
			requestPacket.setString(Messages.ATTRIBUTE_LOGIN_NAME, regDTO.getCompanyName());
			requestPacket.setString(Messages.LOGIN_ID, loginId);
			requestPacket.setString(Messages.MOBILE, mobile);
			requestPacket.setString(Messages.PASSWORD, password);
			requestPacket.setString(Messages.TIMEZONE, timezone);
			requestPacket.setString(Messages.ATTRIBUTE_COUNTRY_ID, regDTO.getCountry());
			requestPacket.setString(Messages.IP_ADDRESS, "0.0.0.0");
			requestPacket.setString(Messages.API_KEY, ApplicationConstant.API_KEY);
			requestPacket.setString(Messages.API_VERSION, "");
			requestPacket.setInt(Messages.IS_WEB, 1);
			requestPacket.setString(Messages.RANDOM_NO, "" + System.currentTimeMillis());

			logger.info(requestPacket);

			try {

				ServerMappingDTO serverMappingDTO = new ServerMappingDTO();
				Socket soc = SocketFactory.getSocket(serverMappingDTO);

				if (soc == null) {
					responseJson.put("msgType", "0");
					responseJson.put("message", "Error in Socket");
					return responseJson.toString();
				}

				TCPProcessor.send(soc.getOutputStream(), requestPacket);
				DataPacket responsePacket = TCPProcessor.receive(soc.getInputStream());
				soc.close();

				logger.info(responsePacket);

				int msgType = responsePacket.getMessageType();
				if (msgType == Messages.RESPONSE_LID) {

					Long lid = responsePacket.getLongLong(Messages.ATTRIBUTE_LID);
					logger.info("lid" + " " + lid);

					if (lid == 0) {

						responseJson.put("msgType", Errors.RETRY_LATER);
						responseJson.put("message", "Lid found 0");
						return responseJson.toString();
					}

					LoginDTO loginDTO = new LoginDTO();
					loginDTO.setName(regDTO.getCompanyName());
					loginDTO.setLoginId(loginId);
					loginDTO.setMobile(mobile);
					loginDTO.setPassword(password);
					loginDTO.setCountry(regDTO.getCountry());
					loginDTO.setNotificationServices(0);
					loginDTO.setPid(0);
					loginDTO.setLastLoginTime(0);
					loginDTO.setUserType(0);
					loginDTO.setTimeZone(timezone);
					loginDTO.setLid(lid);

					conn = dataSource.getConnection();

					int updatedRows = DAOAdapter.insert(conn, LoginDTO.class, loginDTO);

					if (conn != null) {
						try {
							conn.close();
							logger.info("Connection closed");
						} catch (Exception e) {
						}
					}

					if (updatedRows <= 0) {
						logger.info("Failed to register");
						responseJson.put("msgType", Errors.UNKNOWN);
						responseJson.put("message", "Failed to register");
						return responseJson.toString();
					}

					serverMappingDTO.setLid(lid);

					conn = dataSource.getConnection();

					errNum = DAOAdapter.insert(conn, ServerMappingDTO.class, serverMappingDTO);
					logger.info(errNum);

					if (errNum <= 0) {

						logger.info(" Insertion Failed in ServerMapping");
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

					conn = dataSource.getConnection();

					regDTO.setStatus(Status.REGISTERED);

					int update = DAOAdapter.update(conn, RegisterRequestDTO.class, regDTO);

					if (conn != null) {
						try {
							conn.close();
							logger.info("Connection closed");
						} catch (Exception e) {
						}
					}

					if (update <= 0) {

						responseJson.put("msgType", Errors.UNKNOWN);
						responseJson.put("message", "Failed to Register");
						return responseJson.toString();
					}

					responseJson.put("msgType", Errors.OK);
					responseJson.put("message", "Registration Successful");
					return responseJson.toString();

				} else {
					responseJson.put("msgType", "0");
					responseJson.put("message", "Something went wrong. Try again!!!");
					return responseJson.toString();
				}

			} catch (Exception e) {
				logger.info(e);
				responseJson.put("msgType", "0");
				responseJson.put("message", "Something went wrong. Try again!!!");
				return responseJson.toString();
			}

		}

		catch (Exception e) {
			logger.info(e.getMessage(), e);
			responseJson.put("msgType", Errors.RETRY_LATER);
			responseJson.put("Message", "Exception Occured");
			return responseJson.toString();
		}

	}

	@RequestMapping(value = "/serverActivation", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String activation() {

		JSONObject responseJson = new JSONObject();
		int errNum = Errors.UNKNOWN;
		Connection conn = null;

		try {

			String email = request.getParameter("email");
			String productKey = request.getParameter("productKey");
			String serialKey = request.getParameter("serialKey");
			Long services = Long.parseLong(request.getParameter("services"));

			logger.info("REQUEST_SERVER_ACTIVATION {" + "EMAIL :" + email + "," + "PRODUCT_KEY : " + productKey + ","
					+ "SERIAL_KEY : " + serialKey + "," + "}");

			if (email == null || serialKey == null) {
				responseJson.put("msgType", Errors.UNKNOWN);
				responseJson.put("message", "Missing Parameters");
				return responseJson.toString();
			}

			LoginDTO logindto = null;
			RegisterRequestDTO regDTO = null;
			long serverId = 0;
			String uniqueKey = null;
			boolean flag = true;
			List<LanServerDTO> lanList = null;
			LoginMapDTO loginMapDTO = null;
			LanServerDTO lanDTO = null;
			HashMap<String, Object> filters = new HashMap<String, Object>();
			conn = dataSource.getConnection();

			filters.put(LoginDTO.LOGIN_ID, email);

			List<LoginDTO> data = (List<LoginDTO>) DAOAdapter.find(conn, LoginDTO.class, filters, null);
			filters.clear();
			logindto = data.get(0);

			if (data == null || data.size() == 0) {
				responseJson.put("msgType", Errors.UNKNOWN);
				responseJson.put("Message", "Failed to deliver request");
				logger.info("Data not found");
				return responseJson.toString();
			}

			if (conn != null) {
				try {
					conn.close();
					logger.info("Connection closed");
				} catch (Exception e) {
				}
			}

			conn = dataSource.getConnection();
			ProductKeyDTO prodto = null;
			filters.put(ProductKeyDTO.PRO_KEY, productKey);
			filters.put(ProductKeyDTO.SERIAL_KEY, serialKey);

			List<ProductKeyDTO> prodList = (List<ProductKeyDTO>) DAOAdapter.find(conn, ProductKeyDTO.class, filters,
					null);
			filters.clear();

			if (prodList == null || prodList.size() == 0) {
				responseJson.put("msgType", Errors.UNKNOWN);
				responseJson.put("Message", "Failed to deliver request");
				logger.info("Product key data not found");
				return responseJson.toString();
			}

			prodto = prodList.get(0);

			if (conn != null) {
				try {
					conn.close();
					logger.info("Connection closed");
				} catch (Exception e) {
				}
			}

			byte[] encryptedProKey = Library.encrypt(productKey.getBytes());

			DataPacket requestPacket = new DataPacket();
			requestPacket.setMessageType(Messages.REQUEST_SERVER_ACTIVATION);
			requestPacket.setByteArray(Messages.PRODUCT_KEY, encryptedProKey, 16);
			requestPacket.setString(Messages.SERIAL_KEY, serialKey);
			requestPacket.setLongLong(Messages.ATTRIBUTE_LID, logindto.getLid());
			requestPacket.setLongLong(Messages.SERVICES, services);
			requestPacket.setInt(Messages.PRODUCT_TYPE, 6);
			requestPacket.setString(Messages.API_KEY, ApplicationConstant.API_KEY);
			requestPacket.setString(Messages.API_VERSION, "");
			requestPacket.setInt(Messages.IS_WEB, 1);
			requestPacket.setString(Messages.RANDOM_NO, "" + System.currentTimeMillis());
			requestPacket.setLongLong(Messages.PRO_ID, prodto.getProId());
			requestPacket.setLongLong(Messages.DATE_EXPIRY, prodto.getDateExpiry());
			requestPacket.setLongLong(Messages.ACTIVATION_TIME, prodto.getActivationTime());
			requestPacket.setString(Messages.IP_RESTRICTION, prodto.getIpRestriction());
			requestPacket.setInt(Messages.MAX_LOCATION_SUPPORTED, prodto.getMaxLocationSupported());
			requestPacket.setInt(Messages.AVAILABLE_SUPPORTING_LOCATIONS, prodto.getAvailLocationSupported());
			requestPacket.setInt(Messages.KEY_TYPE, prodto.getKeyType());
			requestPacket.setInt(Messages.MODIFICATION_FLAG, prodto.getModFlag());

			try {

				conn = dataSource.getConnection();
				filters.put(ServerMappingDTO.LID, logindto.getLid());
				List<ServerMappingDTO> dataList = (List<ServerMappingDTO>) DAOAdapter.find(conn, ServerMappingDTO.class,
						filters, null);
				filters.clear();

				if (dataList == null || dataList.size() == 0) {
					logger.info("Data not found in ServerInfoDTO");
					responseJson.put("msgType", Errors.UNKNOWN);
					responseJson.put("message", "Data not found in ServerMapping");
					return responseJson.toString();
				}

				if (conn != null) {
					try {
						conn.close();
						logger.info("Connection closed");
					} catch (Exception e) {
					}
				}

				Socket soc = SocketFactory.getSocket(dataList.get(0).getServerInfoId());

				if (soc == null) {
					responseJson.put("msgType", "0");
					responseJson.put("message", "Error in Socket");
					return responseJson.toString();
				}

				TCPProcessor.send(soc.getOutputStream(), requestPacket);
				DataPacket responsePacket = TCPProcessor.receive(soc.getInputStream());
				soc.close();

				logger.info(responsePacket);
				int msgType = responsePacket.getMessageType();
				long activationTime = responsePacket.getLongLong(Messages.ACTIVATION_TIME);

				if (msgType != Messages.RESPONSE_LAN_SERVER_DETAILS) {

					responseJson.put("msgType", Errors.UNKNOWN);
					responseJson.put("message", "Error occured");
					return responseJson.toString();

				}

				serverId = responsePacket.getLongLong(Messages.SERVER_ID);
				uniqueKey = responsePacket.getString(Messages.UNIQUE_KEY);

				loginMapDTO = new LoginMapDTO();
				loginMapDTO.setLid(data.get(0).getLid());
				loginMapDTO.setServerId(serverId);
				loginMapDTO.setUniqueKey(uniqueKey);

				conn = dataSource.getConnection();

				int updatedRows = DAOAdapter.insert(conn, LoginMapDTO.class, loginMapDTO);
				if (updatedRows <= 0) {
					responseJson.put("msgType", Errors.UNKNOWN);
					responseJson.put("message", "Insertion Failed in LoginMap");
					return responseJson.toString();
				}

				logger.info("LOGINMAP table updated");
				filters.clear();

				if (conn != null) {
					try {
						conn.close();
						logger.info("Connection closed");
					} catch (Exception e) {
					}
				}

				conn = dataSource.getConnection();
				ProductKeyDTO prokeydto = null;
				filters.put(ProductKeyDTO.PRO_KEY, productKey);
				filters.put(ProductKeyDTO.SERIAL_KEY, serialKey);

				List<ProductKeyDTO> prodata = (List<ProductKeyDTO>) DAOAdapter.find(conn, ProductKeyDTO.class, filters,
						null);
				filters.clear();

				if (prodata == null || prodata.size() == 0) {
					responseJson.put("msgType", Errors.UNKNOWN);
					responseJson.put("Message", "Failed to deliver request");
					logger.info("Product key data not found");
					return responseJson.toString();
				}

				prokeydto = prodata.get(0);
				prokeydto.setActivationTime(activationTime);
				prokeydto.setAvailLocationSupported(prodata.get(0).getAvailLocationSupported() - 1);

				int updateTable = DAOAdapter.update(conn, ProductKeyDTO.class, prokeydto);

				if (updateTable <= 0) {

					responseJson.put("msgType", Errors.UNKNOWN);
					responseJson.put("message", "Error Updating product table while activation");
					return responseJson.toString();
				}

				if (conn != null) {
					try {
						conn.close();
						logger.info("Connection closed");
					} catch (Exception e) {
					}
				}

				conn = dataSource.getConnection();
				ServerLoadDTO serverload = null;
				filters.put(ServerLoadDTO.SERVER_INFO_ID, dataList.get(0).getServerInfoId());
				List<ServerLoadDTO> loadList = (List<ServerLoadDTO>) DAOAdapter.find(conn, ServerLoadDTO.class, filters,
						null);
				filters.clear();

				if (loadList == null || loadList.size() == 0) {
					logger.info("Data not found in ServerLoadDTO");
					responseJson.put("msgType", Errors.UNKNOWN);
					responseJson.put("message", "Data not found in ServerLoad");
					return responseJson.toString();
				}

				serverload = loadList.get(0);
				serverload.setLoginCount(serverload.getLoginCount() + 1);
				serverload.setUsersCount(serverload.getUsersCount() + prokeydto.getTotal());
				serverload.setLoad(serverload.getLoad() + prokeydto.getTotal());

				if (conn != null) {
					try {
						conn.close();
						logger.info("Connection closed");
					} catch (Exception e) {
					}
				}

				int updateLoad = DAOAdapter.update(conn, ServerLoadDTO.class, serverload);

				if (updateLoad <= 0) {

					responseJson.put("msgType", Errors.UNKNOWN);
					responseJson.put("message", "Error Updating Load ");
					return responseJson.toString();
				}

				filters.put(RegisterRequestDTO.EMAIL, email);
				conn = dataSource.getConnection();
				List<RegisterRequestDTO> listDTO = (List<RegisterRequestDTO>) DAOAdapter.find(conn,
						RegisterRequestDTO.class, filters, null);

				regDTO = listDTO.get(0);
				regDTO.setStatus(Status.ACTIVATED);

				int update = DAOAdapter.update(conn, RegisterRequestDTO.class, regDTO);

				if (conn != null) {
					try {
						conn.close();
						logger.info("Connection closed");
					} catch (Exception e) {
					}
				}

				if (update <= 0) {

					responseJson.put("msgType", Errors.UNKNOWN);
					responseJson.put("message", "Failed to Activate");
					return responseJson.toString();
				}

				responseJson.put("msgType", Errors.OK);
				responseJson.put("message", "Activated Successful");
				return responseJson.toString();

			} catch (Exception e) {
				logger.info(e);
				responseJson.put("msgType", "0");
				responseJson.put("message", "Something went wrong. Try again!!!");
				return responseJson.toString();
			}

		} catch (Exception e) {
			logger.info(e.getMessage(), e);
			responseJson.put("msgType", Errors.RETRY_LATER);
			responseJson.put("Message", "Exception Occured");
			return responseJson.toString();
		}

	}

	@RequestMapping(value = "/updateKey", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String updateKey() {

		JSONObject responseJson = new JSONObject();
		int errNum = Errors.UNKNOWN;
		Connection conn = null;
		HashMap<String, Object> filters = new HashMap<String, Object>();

		try {
			int keyType = Integer.parseInt(request.getParameter("keyType"));
			int maxLocations = Integer.parseInt(request.getParameter("maxLocations"));
			int availableLocations = Integer.parseInt(request.getParameter("availableLocations"));
			String ipRestriction = request.getParameter("ipRestriction");
			long activationTime = Long.parseLong(request.getParameter("activationTime"));
			long proId = Long.parseLong(request.getParameter("proId"));
			String proKey = request.getParameter("proKey");
			int total = Integer.parseInt(request.getParameter("total"));
			int avail = Integer.parseInt(request.getParameter("avail"));
			int validity = Integer.parseInt(request.getParameter("validity"));
			int productType = Integer.parseInt(request.getParameter("productType"));
			long dateCreated = Long.parseLong(request.getParameter("dateCreated"));
			long dateExpiry = Long.parseLong(request.getParameter("dateExpiry"));
			String serialKey = request.getParameter("serialKey");
			int modification = Integer.parseInt(request.getParameter("modification"));

			conn = dataSource.getConnection();
			ProductKeyDTO prodto = null;
			filters.put(ProductKeyDTO.PRO_KEY, proKey);
			filters.put(ProductKeyDTO.SERIAL_KEY, serialKey);
			filters.put(ProductKeyDTO.KEY_TYPE, keyType);

			List<ProductKeyDTO> prodList = (List<ProductKeyDTO>) DAOAdapter.find(conn, ProductKeyDTO.class, filters,
					null);
			filters.clear();

			if (prodList == null || prodList.size() == 0) {
				responseJson.put("msgType", Errors.UNKNOWN);
				responseJson.put("Message", "Failed to deliver request");
				logger.info("Product key data not found");
				return responseJson.toString();
			}

			if (conn != null) {
				try {
					conn.close();
					logger.info("Connection closed");
				} catch (Exception e) {
				}
			}

			prodto.setKeyType(keyType);
			prodto.setMaxLocationSupported(maxLocations);
			prodto.setAvailLocationSupported(availableLocations);
			prodto.setIpRestriction(ipRestriction);
			prodto.setActivationTime(activationTime);
			prodto.setProId(proId);
			prodto.setProductKey(proKey);
			prodto.setSerialKey(serialKey);
			prodto.setTotal(total);
			prodto.setAvail(avail);
			prodto.setValidity(validity);
			prodto.setProductType(productType);
			prodto.setDateCreated(dateCreated);
			prodto.setDateExpiry(dateExpiry);
			prodto.setModFlag(modification);

			conn = dataSource.getConnection();

			int updateRows = DAOAdapter.update(conn, ProductKeyDTO.class, prodto);
			logger.info(updateRows);

			if (updateRows <= 0) {

				logger.info("Updation Failed");
				responseJson.put("msgType", Errors.UNKNOWN);
				responseJson.put("Message", "Updation Failed");
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
			responseJson.put("message", "Product Key table Updated Successfully");
			return responseJson.toString();

		} catch (Exception e) {
			logger.info(e.getMessage(), e);
			responseJson.put("msgType", Errors.RETRY_LATER);
			responseJson.put("Message", "Exception Occured");
			return responseJson.toString();
		}

	}

	@RequestMapping(value = "/getServerDetails", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String getServerDetails() {

		JSONObject responseJson = new JSONObject();
		int errNum = Errors.UNKNOWN;

		return null;
	}

}
