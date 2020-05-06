package com.cantarino.brewer.controllers.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.cantarino.brewer.model.Grupo;

public class GrupoConverter implements Converter<String, Grupo> {

	@Override
	public Grupo convert(String source) {

		if (StringUtils.isEmpty(source))
			return null;

		Grupo _grupo = new Grupo();
		_grupo.setCodigo(Long.valueOf(source));

		return _grupo;

	}

}
