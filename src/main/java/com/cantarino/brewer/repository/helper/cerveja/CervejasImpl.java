package com.cantarino.brewer.repository.helper.cerveja;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.cantarino.brewer.model.Cerveja;
import com.cantarino.brewer.model.dto.CervejaDTO;
import com.cantarino.brewer.repository.filter.CervejaFilter;
import com.cantarino.brewer.repository.utils.Paginacao;

public class CervejasImpl implements CervejasQueries {

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private Paginacao paginacao;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<Cerveja> filtrar(CervejaFilter cervejaFilter, Pageable pageable) {

		Criteria criteria = manager.unwrap(Session.class).createCriteria(Cerveja.class);
		paginacao.Prepare(criteria, pageable);
		buildOrder(criteria, pageable);
		buildFilter(cervejaFilter, criteria);

		return new PageImpl<>(criteria.list(), pageable, getTotal(criteria));

	}

	private void buildOrder(Criteria criteria, Pageable pageable) {

		Sort sort = pageable.getSort();
		if (sort == null)
			return;

		Sort.Order order = sort.iterator().next();
		String campo = order.getProperty();
		criteria.addOrder(order.isAscending() ? Order.asc(campo) : Order.desc(campo));

	}

	private void buildFilter(CervejaFilter cervejaFilter, Criteria criteria) {
		if (cervejaFilter == null)
			return;

		if (!StringUtils.isEmpty(cervejaFilter.getSku()))
			criteria.add(Restrictions.eq("sku", cervejaFilter.getSku()));

		if (!StringUtils.isEmpty(cervejaFilter.getNome()))
			criteria.add(Restrictions.ilike("nome", cervejaFilter.getNome(), MatchMode.ANYWHERE));

		if (isEstiloPresente(cervejaFilter))
			criteria.add(Restrictions.eq("estilo", cervejaFilter.getEstilo()));

		if (cervejaFilter.getValorDe() != null)
			criteria.add(Restrictions.ge("valor", cervejaFilter.getValorDe()));

		if (cervejaFilter.getValorAte() != null)
			criteria.add(Restrictions.le("valor", cervejaFilter.getValorAte()));
	}

	private Long getTotal(Criteria criteria) {
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();

	}

	private boolean isEstiloPresente(CervejaFilter filter) {
		return filter.getEstilo() != null 
					&& filter.getEstilo().getCodigo() != null;
	}

	@Override
	public List<CervejaDTO> filtrarBy(String skuOuNome) {

		StringBuilder _jpqlBuilder = new StringBuilder();
		_jpqlBuilder.append("select new com.cantarino.brewer.model.dto.CervejaDTO(codigo,nome,sku,origem,valor,foto)");
		_jpqlBuilder.append(" from Cerveja where lower(sku) like  :skuOuNome or  lower(nome) like :skuOuNome ");

		List<CervejaDTO> _cervejasFiltered = manager.createQuery(_jpqlBuilder.toString(), CervejaDTO.class)
													.setParameter("skuOuNome", skuOuNome.toLowerCase() +  "%")
													.getResultList();

		return _cervejasFiltered;
	}

}
