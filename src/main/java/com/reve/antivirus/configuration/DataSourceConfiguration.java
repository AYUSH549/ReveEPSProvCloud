package com.reve.antivirus.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
public class DataSourceConfiguration {
	
	
	 @Bean(name = "datasource")
	    @ConfigurationProperties("database.datasource")
	    @Primary
	    public DataSource dataSource(){
	        return DataSourceBuilder.create().build();
	    }
	
	
	@Bean(name="tm1")
	@Primary
    @Autowired
    DataSourceTransactionManager tm1(@Qualifier ("datasource") DataSource datasource) {
        DataSourceTransactionManager txm  = new DataSourceTransactionManager(datasource);
        return txm;
    }

	
}
