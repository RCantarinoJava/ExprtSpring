package com.cantarino.brewer.repository.helper.cerveja;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cantarino.brewer.model.Cerveja;
import com.cantarino.brewer.model.dto.CervejaDTO;
import com.cantarino.brewer.repository.filter.CervejaFilter;

public interface CervejasQueries {

	public Page<Cerveja> filtrar(CervejaFilter cervejaFilter, Pageable pageable);

	public List<CervejaDTO> filtrarBy(String skuOuNome);

}
