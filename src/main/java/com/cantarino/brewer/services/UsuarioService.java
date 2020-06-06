package com.cantarino.brewer.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.cantarino.brewer.model.Usuario;
import com.cantarino.brewer.model.dto.StatusUsuario;
import com.cantarino.brewer.repository.Usuarios;
import com.cantarino.brewer.repository.filter.UsuarioFilter;
import com.cantarino.brewer.validations.exceptions.ErroEntidade;

@Service
public class UsuarioService {

	@Autowired
	private Usuarios UsuarioRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public List<Usuario> getAll() {
		return UsuarioRepository.findAll();
	}

	@Transactional
	public Usuario Salvar(Usuario usuario) {
		ValidaEmail(usuario);
		ValidaSenha(usuario);
		CriptograrSenha(usuario);

		return UsuarioRepository.saveAndFlush(usuario);
	}

	private void CriptograrSenha(Usuario usuario) {

		if (!usuario.isNovo())
			return;

		usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
		usuario.setConfirmacaoSenha(usuario.getSenha());
	}

	private void ValidaSenha(Usuario usuario) {
		if (usuario.isNovo() && StringUtils.isEmpty(usuario.getSenha()))
			throw new ErroEntidade("Senha é obrigatoria.");
	}

	private void ValidaEmail(Usuario usuario) {
		if (UsuarioRepository.findByEmailIgnoreCase(usuario.getEmail()).isPresent())
			throw new ErroEntidade("este email informado já esta cadastrado.");
	}

	public Page<Usuario> filtrar(UsuarioFilter filter, Pageable pageable) {
		return UsuarioRepository.filtrar(filter, pageable);
	}

	public Optional<Usuario> findByEmail(UsuarioFilter filter) {
		return UsuarioRepository.findByEmail(filter);
	}

	public List<String> getPermissoes(Usuario filter) {
		return UsuarioRepository.getPermissoes(filter);
	}

	@Transactional
	public void alterarStatus(Long[] codigos, StatusUsuario status) {

		status.Execute(codigos, UsuarioRepository);

	}

}
