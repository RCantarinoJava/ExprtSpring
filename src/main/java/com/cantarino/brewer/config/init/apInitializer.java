package com.cantarino.brewer.config.init;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.cantarino.brewer.config.WebConfig;

public class apInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {

		return new Class<?>[]{  WebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		
		return new String[] {"/"};
	}

}
