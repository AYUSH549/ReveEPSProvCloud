package com.reve.antivirus.lib;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.reve.antivirus.constants.Errors;
import com.reve.antivirus.dao.DAOAdapter;
import com.reve.antivirus.deployment.Deployment;
import com.reve.antivirus.entities.ServerInfoDTO;
import com.reve.antivirus.entities.ServerMappingDTO;
import com.reve.antivirus.loadbalancer.LoadBalancer;
import com.reve.antivirus.service.LoadBalancerService;



public class SocketFactory {
	
	private static final Logger logger = LogManager.getLogger(SocketFactory.class.getName());
	
	@Autowired
	private static LoadBalancerService loadbalanceService;

	@Qualifier("datasource")
	@Autowired
	private static javax.sql.DataSource dataSource;
	
	
    public static Socket getSocket(ServerMappingDTO serverMappingDTO) throws UnknownHostException, IOException{
    	
    	Connection conn = null;
    	HashMap<String, Object> filters = new HashMap<String, Object>();
    	ServerInfoDTO serverInfo = null;
    	Socket soc  = null;
    	
    	try
    	{
    		Long serverInfoId = loadbalanceService.getServerInfoId();
    		conn = dataSource.getConnection();
    		filters.put(ServerInfoDTO.SERVER_INFO_ID,serverInfoId);
    		List<ServerInfoDTO> dataList = (List<ServerInfoDTO>) DAOAdapter.find(conn, ServerInfoDTO.class,
					filters, null);
			filters.clear();
			
			if (conn != null) {
				try {
					dataSource.getConnection().close();
					logger.info("Connection closed");
				} catch (Exception e) {
				}
			}
			
			if (dataList == null || dataList.size() == 0) {
				logger.info("Data not found in ServerInfoDTO");
				return soc;
			}
			
			serverInfo = dataList.get(0);
			
			String host = serverInfo.getIp();
			int port = serverInfo.getPort();
			soc = new Socket(host, port);
			serverMappingDTO.setServerInfoId(serverInfoId);
			return soc;	

    	}
    	catch(Exception e)
    	{
    		logger.info("Exception Occured");
			return soc;
			
    	}
    	
		
	}
    

    public static Socket getSocket(Long serverInfoId) throws UnknownHostException, IOException{
    	Connection conn = null;
    	HashMap<String, Object> filters = new HashMap<String, Object>();
    	ServerInfoDTO serverInfo = null;
    	Socket soc  = null;
    	
    	try
    	{
    		conn = dataSource.getConnection();
    		filters.put(ServerInfoDTO.SERVER_INFO_ID,serverInfoId);
    		List<ServerInfoDTO> dataList = (List<ServerInfoDTO>) DAOAdapter.find(conn, ServerInfoDTO.class,
					filters, null);
			filters.clear();
			
			if (conn != null) {
				try {
					dataSource.getConnection().close();
					logger.info("Connection closed");
				} catch (Exception e) {
				}
			}
			
			if (dataList == null || dataList.size() == 0) {
				logger.info("Data not found in ServerInfoDTO");
				return soc;
			}
			
			serverInfo = dataList.get(0);
			
			String host = serverInfo.getIp();
			int port = serverInfo.getPort();
			soc = new Socket(host, port);
			return soc;	

    	}
    	catch(Exception e)
    	{
    		logger.info("Exception Occured");
			return soc;
			
    	}   
		
	}
    
    
   
    
    
    
}









//public static Socket getSocket() throws UnknownHostException, IOException{
//
//
//Socket soc  = null;
//Connection conn = null;
//String host = null;
//int port = 0;
//ServerLoadTO dto = LoadBalancer.selectServer();
//HashMap<String, Object> filters = new HashMap<String, Object>();
//filters.put(ServerInfoDTO.SERVER_INFO_ID, dto);
//List<ServerInfoDTO> getdata = (List<ServerInfoDTO>) DAOAdapter.find(conn, ServerInfoDTO.class,
//		   filters, null);
//filters.clear();
//
//host = dto.getIp();
//port = dto.getPort();
//
//soc = new Socket(host, port);
//       
//return soc;
//
//}
//