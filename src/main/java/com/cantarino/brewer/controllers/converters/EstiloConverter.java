package com.cantarino.brewer.controllers.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.cantarino.brewer.model.Estilo;

public class EstiloConverter implements Converter<String, Estilo> {

	@Override
	public Estilo convert(String source) {
		
		if(StringUtils.isEmpty(source)) return null;
		
		Estilo _estilo = new Estilo();
		_estilo.setCodigo(Long.valueOf(source));

		return _estilo;

	}

}
