package com.cantarino.brewer.repository.helper.usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.cantarino.brewer.model.Grupo;
import com.cantarino.brewer.model.Usuario;
import com.cantarino.brewer.model.UsuarioGrupo;
import com.cantarino.brewer.repository.filter.UsuarioFilter;
import com.cantarino.brewer.repository.utils.Paginacao;

public class UsuariosImpl implements UsuariosQueries {

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private Paginacao paginacaoUtil;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<Usuario> filtrar(UsuarioFilter filter, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Usuario.class);

		//criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		paginacaoUtil.Prepare(criteria, pageable);		
		adicionarFiltro(filter, criteria);
		
		
		List<Usuario> usuariosFiltrados = criteria.list();
		usuariosFiltrados.forEach(usuario -> Hibernate.initialize(usuario.getGrupos()));
		

		return new PageImpl<>(usuariosFiltrados, pageable, total(filter, criteria));
	}

	private Long total(UsuarioFilter filter, Criteria criteria) {
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	private void adicionarFiltro(UsuarioFilter filter, Criteria criteria) {

		if (filter == null)
			return;

		if (!StringUtils.isEmpty(filter.getNome()))
			criteria.add(Restrictions.ilike("nome", filter.getNome(), MatchMode.ANYWHERE));
		
		
		if (!StringUtils.isEmpty(filter.getEmail()))
			criteria.add(Restrictions.ilike("email", filter.getEmail(), MatchMode.START));
		
		
		//criteria.createAlias("grupos", "g" , JoinType.LEFT_OUTER_JOIN);
		if (filter.getGrupos() != null &&  !filter.getGrupos().isEmpty())
		{
			List<Criterion> subqueries = new ArrayList<>();
			for(Long codGrupo  : filter.getGrupos().stream().mapToLong(Grupo::getCodigo).toArray())
			{
				DetachedCriteria dc = DetachedCriteria.forClass(UsuarioGrupo.class);
				dc.add(Restrictions.eq("id.grupo.codigo", codGrupo));
				
				
				dc.setProjection(Projections.property("id.usuario"));
				subqueries.add(Subqueries.propertyIn("codigo", dc));
			}
			
			Criterion[] criterions = new  Criterion[subqueries.size()];
			criteria.add(Restrictions.and(subqueries.toArray(criterions)));
		}
	}

	@Override
	public Optional<Usuario> findByEmail(UsuarioFilter filter) {

		return manager.createQuery(" from Usuario  where lower(email) = lower(:email)  and ativo = true ", Usuario.class)
				.setParameter("email", filter.getEmail())
				.getResultList()
				.stream()
				.findFirst();
	}

	@Override
	public List<String> getPermissoes(Usuario filter) {
		
		return manager.createQuery(" SELECT distinct p.nome from Usuario u inner join u.grupos g inner join g.permissoes p  where u = :usuario", String.class)
				.setParameter("usuario", filter)
				.getResultList();
	
	}
}
