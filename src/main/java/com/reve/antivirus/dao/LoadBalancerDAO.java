package com.reve.antivirus.dao;

import java.sql.Connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import com.reve.antivirus.dao.CheckDAO.RegMapper;


@Repository
public class LoadBalancerDAO {

	private static final Logger logger = LogManager.getLogger(CheckDAO.class.getName());

	@Qualifier("datasource")
	@Autowired
	private static javax.sql.DataSource dataSource;

	@RegisterMapper(RegMapper.class)
	interface RegisterSQLs {
		
		@SqlQuery("SELECT SERVER_INFO_ID from SERVER_LOAD where LOAD = (SELECT min(LOAD) from SERVER_LOAD)")
		Long getServerInfoId();

	}

	public static Long getServerInfoId() {
		Connection conn = DataSourceUtils.getConnection(dataSource);
		Handle handle = DBI.open(conn);
		RegisterSQLs regsqls = handle.attach(RegisterSQLs.class);
		return regsqls.getServerInfoId();

	}

}
