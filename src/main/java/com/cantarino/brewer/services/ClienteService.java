package com.cantarino.brewer.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.cantarino.brewer.model.Cliente;
import com.cantarino.brewer.repository.Clientes;
import com.cantarino.brewer.repository.filter.ClienteFilter;
import com.cantarino.brewer.validations.exceptions.ClienteJaCadastradoException;
import com.cantarino.brewer.validations.exceptions.ErroEntidade;

@Service
public class ClienteService {

	@Autowired
	private Clientes clienteRepository;

	@Transactional
	public void Salvar(Cliente cliente) {

		if (isxists(cliente))
			throw new ClienteJaCadastradoException("Cpf/Cnpj já cadastrado");

		clienteRepository.save(cliente);
	}

	public Page<Cliente> filtrar(ClienteFilter clienteFilter, Pageable pageable) {
		return clienteRepository.filtrar(clienteFilter, pageable);
	}

	public List<Cliente> pesquisarPor(String nome) {
		
		this.Validar(nome);
		return clienteRepository.findByNomeStartingWithIgnoreCase(nome);
	}

	private boolean isxists(Cliente cliente) {
		return clienteRepository.findBycpfOuCnpj(cliente.getCpfOuCnpjSemMascara()).isPresent();
	}

	private void Validar(String nome) {
		if (StringUtils.isEmpty(nome))
			throw new ErroEntidade("Nome não pode ser vazio");

		if (nome.length() < 3)
			throw new ErroEntidade("Nome deve conter mais de 3 caracteres");
	}

}
