package com.reve.antivirus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.reve.antivirus.controller.HomePageController;

public class CommonDAO {
		
	private static final Logger logger = LogManager.getLogger(HomePageController.class.getName());
	
	
	public static long getNextSequenceID(Connection con, String tableName) {
        Connection cn = con;
        long id = 0;
        try {
            if (cn == null) {
                //cn = DatabaseManager.getInstance().getConnection();
            }
            
            
            String sql = "select TABLE_NAME,NEXT_ID,MODIFICATION_TIME from raSequencer where TABLE_NAME = ?";
          

            PreparedStatement stmt = cn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt.setString(1, tableName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                id = rs.getLong(2);
                rs.updateLong(2, id + 1);
                rs.updateLong(3, System.currentTimeMillis());
                rs.updateRow();
            } else {
                id = 1000;
                rs.moveToInsertRow();
                rs.updateString(1, tableName);
                rs.updateLong(2, id + 1);
                rs.updateLong(3, System.currentTimeMillis());
                rs.insertRow();
            }
            rs.close();
            stmt.close();
        } catch (Exception ex) {
            logger.info(ex);
        } finally {
            try {
                if (con == null && cn != null) {
                    cn.close();
                    logger.info("Connection Closed!!!");
                }
            } catch (Exception ex) {
                logger.info(ex);
            }
        }
        return id;
    }
	
	
	
	
	

}
