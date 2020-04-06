package com.cantarino.brewer.repository.utils;

import org.hibernate.Criteria;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class Paginacao {

	public void Prepare(Criteria criteria, Pageable pageable) {

		int totalPerPage = pageable.getPageSize();
		int paginaAtual = pageable.getPageNumber();
		int primeiro = paginaAtual * totalPerPage;
		criteria.setFirstResult(primeiro);
		criteria.setMaxResults(totalPerPage);

	}

}
