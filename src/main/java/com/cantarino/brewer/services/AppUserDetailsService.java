package com.cantarino.brewer.services;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cantarino.brewer.model.Usuario;
import com.cantarino.brewer.repository.filter.UsuarioFilter;

@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioService usuarioService;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Optional<Usuario> returnoUsuario = usuarioService.findByEmail(getUsuarioFilter(email));
		Usuario usuario = returnoUsuario.orElseThrow(() -> new UsernameNotFoundException("falha ao buscar usuario"));
		return new User(usuario.getEmail(), usuario.getSenha(), getPermissoes(usuario));
	}

	private Collection<? extends GrantedAuthority> getPermissoes(Usuario usuario) {

		Set<SimpleGrantedAuthority> autorizacoes = new HashSet<SimpleGrantedAuthority>();

		List<String> listaPermissoes = usuarioService.getPermissoes(usuario);
		listaPermissoes.forEach(p -> autorizacoes.add(new SimpleGrantedAuthority(p.toUpperCase())));

		return autorizacoes;
	}

	private UsuarioFilter getUsuarioFilter(String email) {
		UsuarioFilter filter = new UsuarioFilter();
		filter.setEmail(email);
		return filter;
	}

}
