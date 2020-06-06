package com.cantarino.brewer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cantarino.brewer.model.Usuario;
import com.cantarino.brewer.repository.filter.UsuarioFilter;
import com.cantarino.brewer.repository.helper.usuario.UsuariosQueries;

@Repository
public interface Usuarios extends JpaRepository<Usuario, Long>, UsuariosQueries {

	public Optional<Usuario> findByEmailIgnoreCase(String email);

	public Page<Usuario> filtrar(UsuarioFilter usuarioFilter, Pageable pageable);

	public List<Usuario> findByCodigoIn(Long[] codigos);

}