package com.cantarino.brewer.controllers.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.cantarino.brewer.model.Cidade;

public class CidadeConverter implements Converter<String, Cidade> {

	@Override
	public Cidade convert(String source) {

		if (StringUtils.isEmpty(source))
			return null;

		Cidade _cidade = new Cidade();
		_cidade.setCodigo(Long.valueOf(source));

		return _cidade;

	}

}
