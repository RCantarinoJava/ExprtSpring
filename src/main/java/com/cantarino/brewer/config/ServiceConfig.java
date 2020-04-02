package com.cantarino.brewer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.cantarino.brewer.services.CervejaService;
import com.cantarino.brewer.storage.local.FotoStorageLocal;
import com.cantarino.brewer.storage.local.IFotoStorage;

@Configuration
@ComponentScan(basePackageClasses = CervejaService.class)
public class ServiceConfig {

	@Bean
	public IFotoStorage fotoStorage() {
		return new FotoStorageLocal();
	}

}
