package com.cantarino.brewer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cantarino.brewer.model.Cerveja;

import Repository.CervejaRepository;

@Service
public class CervejaService {

	@Autowired
	private CervejaRepository cervejaRepository;

	
	@Transactional
	public Cerveja Salvar(Cerveja cerveja) {

		cervejaRepository.save(cerveja);
		return cerveja;

	}

}
