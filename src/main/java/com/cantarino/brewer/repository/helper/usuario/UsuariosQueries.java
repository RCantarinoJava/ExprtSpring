package com.cantarino.brewer.repository.helper.usuario;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cantarino.brewer.model.Usuario;
import com.cantarino.brewer.repository.filter.UsuarioFilter;

public interface UsuariosQueries {

	public Page<Usuario> filtrar(UsuarioFilter filter, Pageable pageable);

	public Optional<Usuario> findByEmail(UsuarioFilter filter);

	public List<String> getPermissoes(Usuario filter);

}
