package com.cantarino.brewer.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cantarino.brewer.model.Estilo;
import com.cantarino.brewer.repository.Estilos;
import com.cantarino.brewer.repository.filter.EstiloFilter;
import com.cantarino.brewer.validations.exceptions.ErroEntidade;

@Service
public class EstiloService {

	@Autowired
	private Estilos estiloRepository;

	public List<Estilo> getAll() {
		return estiloRepository.findAll();
	}

	@Transactional
	public Estilo Salvar(Estilo estilo) {
		if (estiloRepository.findByNomeIgnoreCase(estilo.getNome()).isPresent())
			throw new ErroEntidade("O estilo informado já esta cadastrado.");

		return estiloRepository.saveAndFlush(estilo);

	}

	public Page<Estilo> filtrar(EstiloFilter filter, Pageable pageable) {
		return estiloRepository.filtrar(filter, pageable);
	}

}
