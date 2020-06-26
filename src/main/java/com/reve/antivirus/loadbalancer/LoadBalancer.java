package com.reve.antivirus.loadbalancer;

import com.reve.antivirus.dao.DAOAdapter;
import com.reve.antivirus.entities.ServerLoadDTO;
import java.util.HashMap;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoadBalancer {

	private static final Logger logger = LogManager.getLogger(LoadBalancer.class.getName());

	public static ServerLoadMinHeap heap = null;
	private static final Object heapLock = new Object();
	

	public static boolean cachingData() {
		HashMap<String, Object> filters = new HashMap<String, Object>();
		List<ServerLoadDTO> serverLoadList = (List<ServerLoadDTO>) DAOAdapter.find(null, ServerLoadDTO.class, null, null);
		if (serverLoadList == null || serverLoadList.isEmpty()) {
			logger.info("No data server load table.");
			return false;
		}
		heap = new ServerLoadMinHeap(serverLoadList.size() + 1);
		for (ServerLoadDTO value : serverLoadList) {
			heap.insert(value);
		}
		heap.minHeap();
		return true;
	}

	public static ServerLoadDTO selectServer() {
//		synchronized(heapLock){
//			if (heap == null) {
//				if (!cachingData()) {
//					return null;
//				}
//			}
//			while(heap.size());
//			com.reve.antivirus.entities.ServerLoadDTO value = heap.remove();
//			int lCount = value.getLoginCount();
//			int uCount = value.getUsersCount();
//			int currLoad = value.getLoad();
//			
//			value.setLoginCount(loginCount + 1);
//			//value.setUsersCount(usersCount + uCount);			
//			value.setLoad(currLoad + loginCount + usersCount);
//			
//
//			
//			heap.insert(value);
//			return value;
//			
//			
//			
//		}
		return null;
	}

}
