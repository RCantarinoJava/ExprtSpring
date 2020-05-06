package com.cantarino.brewer.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cantarino.brewer.model.Grupo;
import com.cantarino.brewer.repository.Grupos;

@Service
public class GrupoService {

	@Autowired
	private Grupos grupoRepository;

	public List<Grupo> getAll() {
		return grupoRepository.findAll();
	}

}
