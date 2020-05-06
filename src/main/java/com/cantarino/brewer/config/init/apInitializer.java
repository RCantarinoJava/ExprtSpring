package com.cantarino.brewer.config.init;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.cantarino.brewer.config.JPAConfig;
import com.cantarino.brewer.config.SecurityConfig;
import com.cantarino.brewer.config.ServiceConfig;
import com.cantarino.brewer.config.WebConfig;

public class apInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {

		// modulo que e configurado antes do serveletWeb
		return new Class<?>[] { JPAConfig.class , ServiceConfig.class , SecurityConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {

		return new Class<?>[] { WebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {

		return new String[] { "/" };
	}

	@Override
	protected Filter[] getServletFilters() {
		// forçar encoded utf-8
		CharacterEncodingFilter _encodingFilter = new CharacterEncodingFilter();
		_encodingFilter.setEncoding("UTF-8");
		_encodingFilter.setForceEncoding(true);

		return new Filter[] { _encodingFilter };
	}
	
	@Override
	protected void customizeRegistration(Dynamic registration)
	{
		registration.setMultipartConfig(new MultipartConfigElement(""));
	}

}
