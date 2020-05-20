package com.cantarino.brewer.config.init;

import java.util.EnumSet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.SessionTrackingMode;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;

public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {

	@Override
	protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {

		// setSession(servletContext);
		setCookie(servletContext);

		FilterRegistration.Dynamic characterEnconding = servletContext.addFilter("encodingFilter",
				new CharacterEncodingFilter());
		characterEnconding.setInitParameter("encoding", "UTF-8");
		characterEnconding.setInitParameter("forceEncoding", "true");
		characterEnconding.addMappingForUrlPatterns(null, false, "/*");

	}

//	private void setSession(ServletContext servletContext) {
	//	servletContext.getSessionCookieConfig().setMaxAge(20);

	//}

	private void setCookie(ServletContext servletContext) {
		servletContext.setSessionTrackingModes(EnumSet.of(SessionTrackingMode.COOKIE));

	}

}
