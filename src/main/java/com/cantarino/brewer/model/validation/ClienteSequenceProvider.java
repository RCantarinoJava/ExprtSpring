package com.cantarino.brewer.model.validation;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import com.cantarino.brewer.model.Cliente;

public class ClienteSequenceProvider implements DefaultGroupSequenceProvider<Cliente> {

	@Override
	public List<Class<?>> getValidationGroups(Cliente cliente) {

		List<Class<?>> grupos = new ArrayList<>();
		grupos.add(Cliente.class);

		if (IsPessoaSelecionada(cliente))
			grupos.add(cliente.getTipoPessoa().getGrupo());

		return grupos;
	}

	private boolean IsPessoaSelecionada(Cliente cliente) {
		return cliente != null && cliente.getTipoPessoa() != null;
	}

}
