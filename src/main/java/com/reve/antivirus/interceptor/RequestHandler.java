package com.reve.antivirus.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


@Component
public class RequestHandler implements HandlerInterceptor{
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception{
//		String url = request.getRequestURL().toString();
//		logger.info("url ->" + url);
//		SessionUserDTO sessionUserDTO = null;
//		if(url.contains("/login") || url.contains("/register") || url.contains("/forgetPassord")) {
//			logger.info("login");
//			sessionUserDTO = (SessionUserDTO) request.getSession().getAttribute(ApplicationConstant.LOGIN_INFO);
//			logger.info("sessionUserDTO -> " + sessionUserDTO);
//			if(sessionUserDTO != null) {
//				logger.info("error");
//				response.sendRedirect("/login/login.jsp");
//				return false;
//			}
//		} else {
//			sessionUserDTO = (SessionUserDTO) request.getSession().getAttribute(ApplicationConstant.LOGIN_INFO);
//			if(sessionUserDTO == null) {
//				logger.info("error");
//				response.sendRedirect("/login/login.jsp");
//				return false;
//			}
//		}
//		logger.info("pre Handle Success!!!!");
		//response.sendRedirect("https://stackoverflow.com/questions/36638150/best-approach-to-redirect-an-url-using-rest");
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception{
		
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		
	}
}
