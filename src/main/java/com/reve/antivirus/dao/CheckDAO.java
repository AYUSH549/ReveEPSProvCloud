package com.reve.antivirus.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import com.reve.antivirus.constants.Errors;
import com.reve.antivirus.entities.AddressDTO;
import com.reve.antivirus.entities.RegisterRequestDTO;

@Repository
public class CheckDAO {

	private static final Logger logger = LogManager.getLogger(CheckDAO.class.getName());

	@Qualifier("datasource")
	@Autowired
	private javax.sql.DataSource dataSource;
	
	@RegisterMapper(RegMapper.class)
	interface RegisterSQLs {

		@SqlQuery("SELECT LAST_INSERT_ID()")
		Long getAddressId();
		
		@SqlQuery("SELECT REG_ID FROM REGISTER_REQUEST where EMAIL=:email")
		Long checkIfExist(@Bind("email") String email);
		
		@SqlQuery("SELECT * from REGISTER_REQUEST")		
		List<RegisterRequestDTO> fetchDetails();
		
		@SqlQuery("SELECT * from REGISTER_REQUEST ORDER BY TIMESTAMP DESC LIMIT :limit OFFSET :offset")
		@GetGeneratedKeys
		List<RegisterRequestDTO> fetchRange(@Bind("limit") int limit, @Bind("offset") int offset);
		
		@SqlQuery("SELECT count(*) from REGISTER_REQUEST")		
		Integer getCount();
			
		
		

	}
	
	@RegisterMapper(AddressMapper.class)
	interface AddressSQLs {
		
		@SqlQuery("SELECT * from ADDRESS LIMIT :limit OFFSET :offset")
		List<AddressDTO> fetchaddress(@Bind("limit") int limit, @Bind("offset") int offset);
	}
	

	public List<RegisterRequestDTO> fetchRange(int limit, int offset)
	{
		Connection conn = DataSourceUtils.getConnection(dataSource);
		Handle handle = DBI.open(conn);
		RegisterSQLs regsqls = handle.attach(RegisterSQLs.class);
		return regsqls.fetchRange(limit,offset);
		
	}
	
	public List<AddressDTO> fetchaddress(int limit, int offset)
	{
		Connection conn = DataSourceUtils.getConnection(dataSource);
		Handle handle = DBI.open(conn);
		AddressSQLs regsqls = handle.attach(AddressSQLs.class);
		return regsqls.fetchaddress(limit,offset);
		
	}
	
	public Integer getCount()
	{
		Connection conn = DataSourceUtils.getConnection(dataSource);
		Handle handle = DBI.open(conn);
		RegisterSQLs regsqls = handle.attach(RegisterSQLs.class);
		return regsqls.getCount();
		
	}
	
	public Long getAddressId(Connection conn)
	{	Connection con = null;
		if(conn != null) {
			con = conn;
		} else {
			con = DataSourceUtils.getConnection(dataSource);
		 }
		Handle handle = DBI.open(conn);
		RegisterSQLs regsqls = handle.attach(RegisterSQLs.class);		
		long errNum = Errors.ERROR_FETCHING_DATA;
		try
		{
			Long addressId = regsqls.getAddressId();
			return addressId;
		}
		catch(Exception e)
		{
			logger.info(e.getMessage(), e);
			return  -1L;
		} finally {
			
			if(conn == null) {
				if(con != null) {					
					try {
						dataSource.getConnection().close();
						logger.info("Connection closed");
					} catch (Exception e) {
					}
				}
			}
		}
		
		
	}

	
	
	public Integer checkIfExist(String email) {
		Connection conn = DataSourceUtils.getConnection(dataSource);
		Handle handle = DBI.open(conn);
		RegisterSQLs regsqls = handle.attach(RegisterSQLs.class);
		int errNum = Errors.UNKNOWN;
		try {

			Object resultSet = regsqls.checkIfExist(email);
			logger.info("ResultSet"+" "+resultSet);
			if (resultSet != null) {

				errNum = Errors.LOGIN_ID_ALREADY_EXIST;
				return errNum;

			}

		} catch (Exception e) {
			logger.info(e.getMessage(), e);
			return errNum;

		}
		
		return errNum;

	}
	

	
	
	public List<RegisterRequestDTO> fetchDetails()
	{
		Connection conn = DataSourceUtils.getConnection(dataSource);
		Handle handle = DBI.open(conn);
		RegisterSQLs regsqls = handle.attach(RegisterSQLs.class);
		return regsqls.fetchDetails();
		
	}

	public static class RegMapper implements ResultSetMapper<RegisterRequestDTO> {
		@Override
		public RegisterRequestDTO map(int i, ResultSet r, StatementContext statementContext) throws SQLException {
			RegisterRequestDTO bean = new RegisterRequestDTO();
		
			bean.setRegId((Long) r.getLong("REG_ID"));
			bean.setCompanyName(r.getString("COMPANY_NAME"));
			bean.setTimestamp((Long) r.getLong("TIMESTAMP"));
			bean.setIsVerified((Integer) r.getInt("IS_VERIFIED"));
			bean.setNoOfUsers((Integer) r.getInt("NO_OF_USERS"));
			bean.setAddressId((Long) r.getLong("ADDRESS_ID"));
			bean.setMobile(r.getString("MOBILE"));
			bean.setRequestByWhom(r.getString("REQUEST_BY_WHOM"));
			bean.setEmail(r.getString("EMAIL"));
			bean.setStatus((Integer) r.getInt("STATUS"));
			bean.setCountry(r.getString("COUNTRY"));
			return bean;
		}
	}
	
	
	public static class AddressMapper implements ResultSetMapper<AddressDTO> {
		@Override
		public AddressDTO map(int i, ResultSet r, StatementContext statementContext) throws SQLException {
			AddressDTO bean = new AddressDTO();

			bean.setAddressId((Long) r.getLong("ADDRESS_ID"));
	     	bean.setCountry(r.getString("COUNTRY"));
	     	bean.setCity(r.getString("CITY"));
	     	bean.setPinCode(r.getString("PIN_CODE"));
	     	bean.setBuildingNo(r.getString("BUILDING_NO"));
	     	bean.setStreet(r.getString("STREET"));
	     	bean.setState(r.getString("STATE"));	     	
			return bean;
		}
	}

}
