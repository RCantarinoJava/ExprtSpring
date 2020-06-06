package com.cantarino.brewer.model.dto;

import com.cantarino.brewer.repository.Usuarios;

public enum StatusUsuario {

	ATIVAR {
		@Override
		public void Execute(Long[] codigos, Usuarios usuario) {
			usuario.findByCodigoIn(codigos).forEach(u -> u.setAtivo(true));
		}
	},

	DESATIVAR {
		@Override
		public void Execute(Long[] codigos, Usuarios usuario) {
			usuario.findByCodigoIn(codigos).forEach(u -> u.setAtivo(false));

		}
	};

	public abstract void Execute(Long[] codigos, Usuarios usuario);

}
