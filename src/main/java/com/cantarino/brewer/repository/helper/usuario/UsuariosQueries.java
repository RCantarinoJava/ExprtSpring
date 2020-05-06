package com.cantarino.brewer.repository.helper.usuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cantarino.brewer.model.Usuario;
import com.cantarino.brewer.repository.filter.UsuarioFilter;

public interface UsuariosQueries {

	public Page<Usuario> filtrar(UsuarioFilter filter, Pageable pageable);
}
