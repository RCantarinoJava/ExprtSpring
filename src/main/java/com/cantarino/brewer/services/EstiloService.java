package com.cantarino.brewer.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cantarino.brewer.model.Estilo;

import Repository.EstiloRepository;

@Service
public class EstiloService {

	@Autowired
	private EstiloRepository estiloRepository;

	public List<Estilo> getAll()  {
		
		return estiloRepository.findAll();

	}

}
