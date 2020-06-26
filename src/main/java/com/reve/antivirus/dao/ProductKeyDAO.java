package com.reve.antivirus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import com.reve.antivirus.constants.Errors;
import com.reve.antivirus.entities.ProductKeyDTO;


@Repository
public class ProductKeyDAO {
	
	
	private static final Logger logger = LogManager.getLogger(ProductKeyDAO.class.getName());

	@Qualifier("datasource")
	@Autowired
	private javax.sql.DataSource dataSource;
	
	@RegisterMapper(RegMapper.class)
	interface RegisterSQLs {

		
		@SqlQuery("SELECT * from PRODUCT_KEY ORDER BY DATE_CREATED DESC LIMIT :limit OFFSET :offset")
		@GetGeneratedKeys
		List<ProductKeyDTO> fetchLicenseDetails(@Bind("limit") int limit, @Bind("offset") int offset);
				

	}
	
	public List<ProductKeyDTO> fetchLicenseDetails(int limit, int offset)
	{
		Connection conn = DataSourceUtils.getConnection(dataSource);
		Handle handle = DBI.open(conn);
		RegisterSQLs regsqls = handle.attach(RegisterSQLs.class);
		return regsqls.fetchLicenseDetails(limit,offset);
		
	}
	
	public JSONArray getKeys(Connection con, long startingDate, long endingDate, int si, int ei, String serialKey, String productKey) {

        Connection cn = con;
        JSONArray proKeyArr = new JSONArray();
        int PRO_ID = 0;
        HashMap<Integer, Object> values = new HashMap<Integer, Object>();
        try {
            logger.info("start " + startingDate);
            logger.info("end " + endingDate);
            logger.info("startIndex " + si);
            logger.info("endIndex " + ei);
            int limit = ei - si + 1;
            PreparedStatement stmt = null;

            String sql = "select PRO_KEY,SERIAL_KEY,VALIDITY,TOTAL,AVAIL,MAX_LOCATION_SUPPORTED,KEY_TYPE,DATE_CREATED,ACTIVATION_TIME from PRODUCT_KEY where ";

            int i = 1;
            if (productKey != null) {
                logger.info(sql);
                sql += "PRO_KEY = ? and ";
                values.put(i, productKey);
                i++;
            }

            if (serialKey != null) {
                sql += "SERIAL_KEY = ? and ";
                values.put(i, serialKey);
                i++;
            }

            sql += "DATE_CREATED between ? and ? order by DATE_CREATED desc limit ? offset ?";
            logger.info(sql);

            stmt = cn.prepareStatement(sql);

            for (Map.Entry<Integer, Object> entrySet : values.entrySet()) {
                stmt.setObject(entrySet.getKey(), entrySet.getValue());
            }
            
            logger.info(values);

            stmt.setLong(i, startingDate);
            i++;
            stmt.setLong(i, endingDate);
            i++;
            stmt.setInt(i, limit);
            i++;
            stmt.setInt(i, si);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                JSONObject obj = new JSONObject();
                obj.put("proKey", rs.getString(1));
                obj.put("serialKey", rs.getString(2));
                obj.put("validity", rs.getInt(3));
                obj.put("total", rs.getInt(4));
                obj.put("avail", rs.getInt(5));
                obj.put("max_location_supported", rs.getInt(6));
                obj.put("keyType", rs.getInt(7));
                obj.put("date_created", rs.getLong(8));
                obj.put("actTime", rs.getLong(9));

                proKeyArr.put(obj);
                logger.info(obj);
            }
        } catch (Exception ex) {
            logger.info(ex);
        }
        return proKeyArr;
    }
	
	  public JSONObject getCount(Connection con, int product_type, int key_type, String product_key, String serial_key, int validity, long date_created, long date_expired) {
	        Connection cn = con;
	        int errNum = Errors.UNKNOWN;
	        int count = 0, activatedCount = 0;
	        int i = 1 ;
	        JSONObject obj = new JSONObject();
	        HashMap<Integer, Object> values = new HashMap<Integer, Object>();
	        try {
	            
	            StringBuffer query = new StringBuffer();

	            PreparedStatement stmt = null;
	            String sql = "select count(*) from PRODUCT_KEY";
	            stmt = cn.prepareStatement(sql);
	            
	            ResultSet rs = stmt.executeQuery();
	            if (rs.next()) {
	                count = rs.getInt(1);
	            }
	         
	            query.append(" where ");
	            
	            if (product_type != 0) {
	                query.append("PRODUCT_TYPE = ? and ");
	                values.put(i,product_type);
	                i++;
	            }

	            if (key_type != 0) {
	                query.append("KEY_TYPE = ? and ");
	                values.put(i,key_type);
	                i++;
	            }

	            if (product_key != null) {
	                query.append("PRODUCT_KEY = ? and ");
	                values.put(i,product_key);
	                i++;
	            }
	            if (serial_key != null) {
	                query.append("SERIAL_KEY = ? and ");
	                values.put(i,serial_key);
	                i++;
	            }
	            if (validity != 0) {
	                query.append("VALIDITY = ? and ");
	                values.put(i,validity);
	                i++;
	            }
	            if (date_created != 0) {
	                query.append("DATE_CREATED = ? and ");
	                values.put(i,date_created);
	                i++;
	            }
	            if (date_expired != 0) {
	                query.append("DATE_EXPIRED = ? and ");
	                values.put(i,date_expired);
	                i++;
	            }

	            logger.info(query.toString());
	                                
	            PreparedStatement stmt1 = null;
	            String sql1 = "select count(*) from PRODUCT_KEY";
	            sql1+=query.toString();
	            sql1+="ACTIVATION_TIME > 0";
	            
	            logger.info(sql1);
	            
	            stmt1 = cn.prepareStatement(sql1);
	            
	            logger.info(sql1);
	             
	            for (Map.Entry<Integer, Object> entrySet : values.entrySet()) {
	                stmt1.setObject(entrySet.getKey(), entrySet.getValue());
	               
	            }
	                      
	            ResultSet rs1 = stmt1.executeQuery();
	            
	            
	            if (rs1.next()) {
	                activatedCount = rs1.getInt(1);
	            }

	            obj.put("count", count);
	            obj.put("activatedCount", activatedCount);
	        } catch (Exception ex) {
	            logger.info(ex);
	            
	        } 
	        return obj;
	    }
	
	
	public static class RegMapper implements ResultSetMapper<ProductKeyDTO> {
		@Override
		public ProductKeyDTO map(int i, ResultSet r, StatementContext statementContext) throws SQLException {
			ProductKeyDTO bean = new ProductKeyDTO();		
			bean.setProId((Long) r.getLong("PRO_ID"));
			bean.setProductKey(r.getString("PRO_KEY"));
			bean.setTotal((Integer) r.getInt("TOTAL"));
			bean.setAvail((Integer) r.getInt("AVAIL"));
			bean.setValidity((Integer) r.getInt("VALIDITY"));
			bean.setProductType((Integer) r.getInt("PRODUCT_TYPE"));
			bean.setDateCreated((Long) r.getLong("DATE_CREATED"));
			bean.setDateExpiry((Long) r.getLong("DATE_EXPIRY"));
			bean.setSerialKey(r.getString("SERIAL_KEY"));
			bean.setActivationTime((Long) r.getLong("ACTIVATION_TIME"));
			bean.setMaxLocationSupported((Integer) r.getInt("MAX_LOCATION_SUPPORTED"));
			bean.setAvailLocationSupported((Integer) r.getInt("AVAILABLE_SUPPORTING_LOCATIONS"));
			bean.setIpRestriction(r.getString("IP_RESTRICTION"));
			bean.setKeyType((Integer) r.getInt("KEY_TYPE"));
			bean.setModFlag((Integer) r.getInt("MODIFICATION_FLAG"));
			return bean;
		}
	}
	
	
	

}
