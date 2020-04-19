package com.cantarino.brewer.controllers.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.cantarino.brewer.model.Estado;

public class EstadoConverter implements Converter<String, Estado> {

	@Override
	public Estado convert(String source) {

		if (StringUtils.isEmpty(source))
			return null;

		Estado _estado = new Estado();
		_estado.setCodigo(Long.valueOf(source));

		return _estado;

	}

}
