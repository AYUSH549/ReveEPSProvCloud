package com.reve.antivirus.configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.reve.antivirus.interceptor.RequestHandler;



@Configuration
public class AppConfig implements WebMvcConfigurer {

	@Autowired
	RequestHandler requestHandler;
	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(requestHandler);
	}
}