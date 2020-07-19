package com.cantarino.brewer.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cantarino.brewer.model.Cerveja;
import com.cantarino.brewer.model.dto.CervejaDTO;
import com.cantarino.brewer.repository.Cervejas;
import com.cantarino.brewer.repository.filter.CervejaFilter;
import com.cantarino.brewer.services.events.CervejaEvent;

@Service
public class CervejaService {

	@Autowired
	private Cervejas cervejaRepository;

	@Autowired
	private ApplicationEventPublisher eventPublisher;

	@Transactional
	public Cerveja Salvar(Cerveja cerveja) {

		cervejaRepository.save(cerveja);
		eventPublisher.publishEvent(new CervejaEvent(cerveja));

		return cerveja;
	}

	public List<Cerveja> getAll() {
		return cervejaRepository.findAll();
	}

	public Page<Cerveja> filtrar(CervejaFilter cervejaFilter, Pageable pageable) {
		return cervejaRepository.filtrar(cervejaFilter, pageable);
	}

	public List<CervejaDTO> filterBy(String skuOuNome) {
		return cervejaRepository.filtrarBy(skuOuNome);
	}

}
